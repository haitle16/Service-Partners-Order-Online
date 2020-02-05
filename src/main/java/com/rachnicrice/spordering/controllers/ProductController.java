package com.rachnicrice.spordering.controllers;


import com.rachnicrice.spordering.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    LineItemRepository lineItemRepository;


    @GetMapping("/products")
    public String showPage(Principal p, Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "item_id") String sortBy) {
//        PageRequest pagereq = PageRequest.of(page,4, Sort.by(sortBy).ascending());

        if(p != null) {
            System.out.println(p.getName()+" is logged in!");
            model.addAttribute("username", p.getName());
        } else {
            System.out.println("nobody is logged in");
        }

        model.addAttribute("data", productRepository.findAll());
        System.out.println(productRepository.findAll().toString());
        model.addAttribute("currentPage",page);
        return "products";
    }

//    @PostMapping("/mycart")
//    public RedirectView addCart(Model model, Principal p, String quantity, Product product) {
////        System.out.println(quantity);
//
//        int i = Integer.parseInt(quantity);
//        Timestamp createdAt = new Timestamp(System.currentTimeMillis());
//
//        // Test if the user has an order object in the DB
//        if (applicationUserRepository.findByUsername(p.getName()).getOrders() != null) {
//            List<Order> userOrder = applicationUserRepository.findByUsername(p.getName()).getOrders();
//
//            if(!userOrder.get(0).getSubmitted()){ // Check to see if userOrder have existing order and isSubmitted is false
//                LineItem cartItem = new LineItem(userOrder.get(0), productRepository.getOne((long) 1), i);// create new cart item with order, product, and quantity
//                lineItemRepository.save(cartItem);
//            } else { // If all orders are already submitted
//
//            }
//        } else {
//            //If no orders exist, make one
//            Order order = new Order(applicationUserRepository.findByUsername(p.getName()), createdAt,false);
//            orderRepository.save(order);
//            LineItem cartItem = new LineItem(order, productRepository.getOne((long) 1), i);// create new cart item with order, product, and quantity
//            lineItemRepository.save(cartItem);
//        }
//
//        return new RedirectView("/mycart");
//    }

    @PostMapping("/mycart")
    public RedirectView addCart(Model model, Principal p, Product product) {

        if (p != null) {
            System.out.println(p.getName()+" is logged in!");
            model.addAttribute("username", p.getName());
        } else {
            System.out.println("nobody is logged in");
        }

        Timestamp createdAt = new Timestamp(System.currentTimeMillis());
//        int i = Integer.parseInt(quantity);

        ApplicationUser loggedInUser = applicationUserRepository.findByUsername(p.getName());

        List<Order> userOrders = loggedInUser.getOrders();

        boolean startedAtLeastOneOrder = userOrders!=null;

        // Initialize as as true (case that does not have any unsubmitted orders), set to false if one is found in the loop
        boolean onlySubmittedOrders = true;
        if (userOrders!=null) {
            for (Order order : userOrders) {
                if (order.getSubmitted()==false) {
                    onlySubmittedOrders = false;
                }
            }
        }

        if (!startedAtLeastOneOrder || onlySubmittedOrders) {
            Order order = new Order(loggedInUser, createdAt,false);
            orderRepository.save(order);
            //change hard coded 1 and 10 to path variables
            LineItem cartItem = new LineItem(order, productRepository.getOne((long) 3), 10);// create new cart item with order, product, and quantity
            lineItemRepository.save(cartItem);
        } else {
            for (Order order : userOrders) {
                if (order.getSubmitted()==false) {
                    Order unsubmittedOrder = order;
                    //change hard coded 1 and 10 to path variables
                    LineItem cartItem = new LineItem(unsubmittedOrder, productRepository.getOne((long) 1), 66);// create new cart item with order, product, and quantity
                    lineItemRepository.save(cartItem);
                }
            }

        }

        return new RedirectView("/mycart");
    }


    @PostMapping("/save")
    public RedirectView save(Product product) {
        productRepository.save(product);

        return new RedirectView("/product");
    }

    @GetMapping("/delete")
    public RedirectView delete(Long id) {
        productRepository.deleteById(id);

        return new RedirectView("/product");
    }

    @GetMapping("/findOne")
    @ResponseBody
    public Product findOne(Long id) {
        return productRepository.getOne(id);

    }
}
