package com.example.MyFirstSpringApp.Controllers;

import com.example.MyFirstSpringApp.Models.Blogpost;
import com.example.MyFirstSpringApp.repos.BlogpostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BlogpostsPageController {

    @Autowired
    private BlogpostRepository blogpostRepository;

    @GetMapping("/blog")
    public String blog(Model model) {
        Iterable<Blogpost> posts = blogpostRepository.findAll();
        model.addAttribute("posts", posts);
        return "blogs_main_page";
    }
}
