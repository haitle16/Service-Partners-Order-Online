package com.rachnicrice.spordering.controllers;

import com.rachnicrice.spordering.models.ApplicationUserRepository;
import com.rachnicrice.spordering.models.OrderRepository;
import com.rachnicrice.spordering.utils.ExcelConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.security.Principal;

@Controller
public class EmailController {

    @Autowired
    JavaMailSender jSender;

    @Autowired
    ApplicationUserRepository userRepo;

    @Autowired
    OrderRepository repo;

    @PostMapping("/submit")
    public RedirectView sendOrder (Principal p, Model m, Long id) {
        //Resource: https://www.baeldung.com/spring-email
        //Create a new message
        MimeMessage message = jSender.createMimeMessage();

        //Get the customer id to send in the body of the email
        String customerID = repo.getOne(id).getUser().getSpCustomer_number();

        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, true);
            //Set who the email will be sent to
            helper.setTo("Rachnicrice@gmail.com");
            //Set the subject of the email
            helper.setSubject("Customer: " + customerID);
            //Set the email body
            helper.setText("New online order from " + customerID);

            //Create the excel from the order data
            try {
                //this will create a new excel called order.xlsx by calling the export function
                Resource responseFile = ExcelConverter.export("order.xlsx", id, repo);
            } catch (IOException e) {
                e.printStackTrace();
            }

            //Create a FileSystemResource that = the file you want to send from your database
            FileSystemResource file = new FileSystemResource(new File("order.xlsx"));
            // Attach that file to your email
            helper.addAttachment("online-order.xlsx", file);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        //Send your email
        jSender.send(message);
        //Send the user back to the cart page with the submitted param (this param will cause a confirmation message to be shown via thymeleaf)
        return new RedirectView("/mycart?submitted=true");
    }


}
