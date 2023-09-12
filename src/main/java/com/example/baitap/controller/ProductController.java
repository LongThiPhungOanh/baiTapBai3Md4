package com.example.baitap.controller;
import com.example.baitap.model.Product;
import com.example.baitap.service.iml.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/products")
public class ProductController {

@Autowired
    ProductService productService = new ProductService();

@GetMapping
    public String showList(Model model) {
    model.addAttribute("list" , productService.finAll());
    return "/list";
}
@GetMapping("/create")
    public String createGet(Model model){
    model.addAttribute("product", new Product());
    return "/create";
}

@PostMapping("/create")
    public String createPost(Product product){
    int id = productService.finAll().size();
    product.setId(++id);
    productService.create(product);
    return "redirect:/products";
}
    @GetMapping("/{id}/edit")
    public String updateGet(Model model, @PathVariable("id") int id){
        Product product = productService.finOne(id);
        model.addAttribute("product", product);
        return "/update";
    }
    @PostMapping("/update/{id}")
    public String updatePost(Product product, @PathVariable("id") int id){
        if (productService.finOne(id) != null) {
            product.setId(id);
            productService.update(product);
        }
        return "redirect:/products";
    }
    @GetMapping("/{id}/delete")
    public String delete( @PathVariable("id") int id){
    productService.remove(id);
        return "redirect:/products";
    }
    @GetMapping("/{id}/view")
    public String view(Model model, @PathVariable("id") int id){
    model.addAttribute("product", productService.finOne(id));
    return "/view";
    }
    @PostMapping("/search")
    public String search(@RequestParam String name, Model model){
        List<Product> list = productService.searchByName(name);
        model.addAttribute("list", list);
        return "/search";
    }
}
