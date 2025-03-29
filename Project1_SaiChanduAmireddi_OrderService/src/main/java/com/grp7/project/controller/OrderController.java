package com.grp7.project.controller;

import com.grp7.project.model.Order;
import com.grp7.project.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/")
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/order")
    public String orderForm(Model model) {
        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("Pending");
        model.addAttribute("order", order);
        return "orderForm";
    }

    @PostMapping("/placeOrder")
    public String placeOrder(@ModelAttribute Order order, RedirectAttributes redirectAttrs) {
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("Pending");
        order.setTotalPrice(order.getQuantity() * order.getPricePerUnit());

        orderService.saveOrder(order);
        redirectAttrs.addFlashAttribute("success", "Order Placed Successfully!");

        return "redirect:/myOrders";
    }

    @GetMapping("/myOrders")
    public String viewMyOrders(Model model) {
        List<Order> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "myOrders";
    }
}
