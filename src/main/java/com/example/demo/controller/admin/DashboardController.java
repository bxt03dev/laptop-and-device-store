package com.example.demo.controller.admin;

import com.example.demo.domain.Order;
import com.example.demo.domain.Product;
import com.example.demo.domain.User;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DashboardController {
    private final UserService userService;
    private final ProductService productService;
    private final OrderService orderService;
    public DashboardController(UserService userService, ProductService productService, OrderService orderService) {
        this.userService = userService;
        this.productService = productService;
        this.orderService = orderService;
    }

    @GetMapping("/admin")
    public String getDashboard(Model model) {
        List<User> users = this.userService.getAllUser();
        List<Product> products = this.productService.getAllProduct();
        List<Order> orders = this.orderService.getAllOrders();
        model.addAttribute("countUsers", users.size());
        model.addAttribute("countProducts", products.size());
        model.addAttribute("countOrders", orders.size());
        return "admin/dashboard/show";
    }

}
