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
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.HashSet;
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

    @Autowired
    PasswordEncoder encoder;

    @GetMapping("/products")
    public String showPage(Principal p, Model model) {

        if(p != null) {
            System.out.println(p.getName()+" is logged in!");
            model.addAttribute("username", p.getName());
        } else {
            System.out.println("nobody is logged in");
        }

        // pass in isAdmin object
        model.addAttribute("isAuthorized", applicationUserRepository.findByUsername(p.getName()).getAdmin());
        System.out.println("IS ADMIN"+applicationUserRepository.findByUsername(p.getName()).getAdmin());

        model.addAttribute("data", productRepository.findAll());
        return "products";
    }

    @PostMapping("/mycart")
    public RedirectView addCart(Model model, Principal p, Long item_id, int quantity) {
        System.out.println(item_id);
        System.out.println(quantity);

        if (p != null) {
            System.out.println(p.getName()+" is logged in!");
            model.addAttribute("username", p.getName());
        } else {
            System.out.println("nobody is logged in");
        }

        Timestamp createdAt = new Timestamp(System.currentTimeMillis());
        ApplicationUser loggedInUser = applicationUserRepository.findByUsername(p.getName());
        List<Order> userOrders = loggedInUser.getOrders();
        boolean startedAtLeastOneOrder = userOrders!=null;

        // Initialize as as true (case that does not have any unsubmitted orders), set to false if one is found in the loop
        boolean onlySubmittedOrders = true;
        if (userOrders.size() > 0) {
            for (Order order : userOrders) {
                if (order.getSubmitted()==false) {
                    onlySubmittedOrders = false;
                }
            }
        }

        System.out.println("MADE IT to main part of function");

        if (!startedAtLeastOneOrder || onlySubmittedOrders) {
            Order order = new Order(loggedInUser, createdAt,false);
            orderRepository.save(order);
            //change hard coded 1 and 10 to path variables

            LineItem cartItem = new LineItem(order, productRepository.getOne(item_id), quantity);// create new cart item with order, product, and quantity
            lineItemRepository.save(cartItem);
        } else {
            for (Order order : userOrders) {
                if (order.getSubmitted()==false) {
                    Order unsubmittedOrder = order;
                    List<LineItem> lineItems = unsubmittedOrder.getItemsInThisOrder();

                    if (lineItems.isEmpty()) {
                        LineItem cartItem = new LineItem(unsubmittedOrder, productRepository.getOne(item_id), quantity);// create new cart item with order, product, and quantity
                        lineItemRepository.save(cartItem);
                    } else {
                        boolean alreadyInCart = false;
                        for (LineItem lineItem : lineItems) {
                            if (item_id.equals(lineItem.getProduct().getItem_id())) {
                                alreadyInCart = true;
                                int prevQty = lineItem.getQuantity();
                                lineItem.setQuantity(prevQty + quantity);
                                lineItemRepository.save(lineItem);
                                System.out.println("made it to UPDATE line item");
                            }
                        }
                        if (!alreadyInCart) {
                            LineItem cartItem = new LineItem(unsubmittedOrder, productRepository.getOne(item_id), quantity);// create new cart item with order, product, and quantity
                            lineItemRepository.save(cartItem);
                            System.out.println("made it to make new item");
                        }
                    }
                }
            }

        }

        return new RedirectView("/products");
    }


    @PostMapping("/save")
    public RedirectView save(Product product, Principal p) {
        if(applicationUserRepository.findByUsername(p.getName()).getAdmin()) {
            productRepository.save(product);
        }

        return new RedirectView("/products");
    }

    @GetMapping("/delete")
    public RedirectView delete(Long id, Principal p) {

        // iterating through each item in LineItem and comparing the foreign key with the id and deleting items in LineItem prior to deleting product.
        if(applicationUserRepository.findByUsername(p.getName()).getAdmin()) {
            for (LineItem item : lineItemRepository.findAll()) {
                if (item.getProduct().getId() == id) {
                    lineItemRepository.deleteById(item.getId());
                }
            }
            productRepository.deleteById(id);
        }
        return new RedirectView("/products");
    }

    @GetMapping("/findOne")
    @ResponseBody
    public Product findOne(Long id) {
        return productRepository.getOne(id);

    }

    @GetMapping("/populateDatabase")
    public RedirectView populateDatabase(Principal p) {
        if (p != null) {
            System.out.println(p.getName() + " is logged in!");
        } else {
            System.out.println("nobody is logged in");
        }

        // Make a list of products already in the database
        List<Product> dbProductList = productRepository.findAll();
        List<ApplicationUser> dbAdminList = applicationUserRepository.findAll();

        HashSet<String> dbItemCodeSet = new HashSet<>();
        for (Product product : dbProductList) {
            dbItemCodeSet.add(product.getItemCode());
        }

        // Make a list of admin accounts already in the database
        HashSet<String> dbAdminIdSet = new HashSet<>();
        for (ApplicationUser adminUser : dbAdminList) {
            dbAdminIdSet.add(adminUser.getUsername());
        }

        // make a set of products to add
        HashSet<Product> productsToAdd = new HashSet<>();
        productsToAdd.add(new Product("DS2310BLK-LF", "Downspout", "2x3", "10'", "Black", "style", "downspout", 10));
        productsToAdd.add(new Product("DS2310WMUS-LF", "Downspout", "2x3", "10'", "Musket Brown", "style", "downspout", 10));
        productsToAdd.add(new Product("DS2310LGWHT-LF", "Downspout", "2x3", "10'", "Low-Gloss White", "style", "downspout", 10));
        productsToAdd.add(new Product("DS2310WHT-LF", "Downspout", "2x3", "10'", "High Gloss White", "style", "downspout", 10));
        productsToAdd.add(new Product("DS3410LGWHT-LF", "Downspout", "3x4", "10'", "Low-Gloss White", "style", "downspout", 10));

        // make a set of new admins to add
        HashSet<ApplicationUser> adminsToAdd = new HashSet<>();
//        adminsToAdd.add(new ApplicationUser([ENTER ACCOUNT VALUES]));

        // for each product in the set, add it to the database only if there isn't already a product with that item code in the database
        for (Product product : productsToAdd) {
            if (!dbItemCodeSet.contains(product.getItemCode())) {
                productRepository.save(product);
            }
        }

        // for each admin in the set, add it to the database only if there isn't already an admin with that username in the database
        for (ApplicationUser adminUser : adminsToAdd) {
            if (!dbAdminIdSet.contains(adminUser.getUsername())) {
                applicationUserRepository.save(adminUser);
            }
        }

        return new RedirectView("/");
    }
}
