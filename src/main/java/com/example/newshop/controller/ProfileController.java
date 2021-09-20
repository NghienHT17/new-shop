package com.example.newshop.controller;

import com.example.newshop.model.Product;
import com.example.newshop.model.User;
import com.example.newshop.service.ProductService;
import com.example.newshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

public class ProfileController {
    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @GetMapping("cart-product")
    public ModelAndView cartProduct(Principal principal) {
        ModelAndView mv = new ModelAndView("profile/cart-product");
        User user = userService.findByEmail(principal.getName());
        mv.addObject("user", user);
        int total = findSum(user);
        mv.addObject("total", total);
        return mv;
    }

    private int findSum(User user) {
//        lay ra list san pham duoc mua boi user nay
        List<Product> list = user.getProductList();
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            Product p = list.get(i);
            sum += p.getProductPrice();
        }
        return sum;
    }

    //ham mua ne`

    @GetMapping("addToCart/{productId}")
        public ModelAndView addToCart(@PathVariable("productId") String productId,Principal principal){
            ModelAndView modelAndView = new ModelAndView(("profile/cart-product"));
            User user = userService.findByEmail(principal.getName());
            long productLongId = Long.parseLong(productId);
            Product product = productService.getProductById(productLongId).get();//lay ra object

        List<Product> productList= new ArrayList<Product>();
        productList.add(product);
        user.setProductList(productList);

        List<User> userList = new ArrayList<>();
        userList.add(user);
        product.setUserList(userList);

        userService.update(user);
        productService.addProduct(product);
        int total = findSum(user);
        modelAndView.addObject("total", total);
        modelAndView.addObject("user", user);
        return modelAndView;

        }



    }


