package com.comp_303.Assignment1.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.comp_303.Assignment1.Model.OrderModel;

/*
 Student: Ronald Jr Ombao
 Student #: 301213219
 Date: October 12, 20204
*/

@Controller
public class OrderController {

    @GetMapping("/")
    public String getOrderForm(Model model) {
        model.addAttribute("orderModel", new OrderModel());
        return "index";  
    }

    @PostMapping("/createOrder")
    public String createOrder(@ModelAttribute OrderModel orderModel) {
        // Process the order
        return "show-order";  // Another template to show after order submission
    }
}
