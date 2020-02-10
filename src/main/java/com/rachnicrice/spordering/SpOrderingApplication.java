package com.rachnicrice.spordering;

import com.rachnicrice.spordering.controllers.ProductController;
import com.rachnicrice.spordering.models.LineItem;
import com.rachnicrice.spordering.models.Product;
import com.rachnicrice.spordering.models.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

@SpringBootApplication
public class SpOrderingApplication {

//	private static ProductRepository productRepository;

	public static void main(String[] args) {

		SpringApplication.run(SpOrderingApplication.class, args);

	}

}
