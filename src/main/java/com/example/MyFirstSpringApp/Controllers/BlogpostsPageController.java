package com.example.MyFirstSpringApp.Controllers;

import com.example.MyFirstSpringApp.Models.Blogpost;
import com.example.MyFirstSpringApp.repos.BlogpostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

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

    @GetMapping("/blog/add")
    public String blogAdd(Model model) {
        return "blogs_add_page";
    }

    @GetMapping("/blog/{id}/delete")
    public String blogDelete(Model model, @PathVariable(value = "id") long postId) {
        if (blogpostRepository.existsById(postId)){
            Optional<Blogpost> post = blogpostRepository.findById(postId);
            ArrayList<Blogpost> result = new ArrayList<>();
            post.ifPresent(result::add);
            model.addAttribute("posts", result);
            return "blogs_delete_page";
        }
        else{
            return "redirect:/blog";
        }
    }

    @GetMapping("/blog/{id}/edit")
    public String blogEdit(Model model, @PathVariable(value = "id") long postId) {
        if (blogpostRepository.existsById(postId)){
            Optional<Blogpost> post = blogpostRepository.findById(postId);
            ArrayList<Blogpost> result = new ArrayList<>();
            post.ifPresent(result::add);
            model.addAttribute("posts", result);
            return "blogs_edit";
        }
        else{
            return "redirect:/blog";
        }
    }

    @PostMapping("/blog/{id}/edit")
    public String blogEdit(Model model, @PathVariable(value = "id") long postId, @RequestParam String title, @RequestParam String text, @RequestParam String annotation){
        Blogpost post =  blogpostRepository.findById(postId).orElseThrow();
        post.setTitle(title);
        post.setText(text);
        post.setAnnotation(annotation);
        blogpostRepository.save(post);
        return "redirect:/blog/{id}";
    }


    @PostMapping("/blog/{id}/delete")
    public String blogDeleteConfirmed(Model model, @PathVariable(value = "id") long postId){
        Blogpost post =  blogpostRepository.findById(postId).orElseThrow();
        blogpostRepository.delete(post);
        return "redirect:/blog";
    }
    @PostMapping("/blog/add")
    public String blogAdd(Model model, @RequestParam String title, @RequestParam String annotation, @RequestParam String text){
        Blogpost post = new Blogpost(title, annotation, text);
        blogpostRepository.save(post);
        return "redirect:/blog";
    }

    @GetMapping("/blog/{id}")
    public String blogDetails(Model model, @PathVariable(value = "id") long postId) {
        if (!blogpostRepository.existsById(postId)) {
            return "redirect:/blog";
        }
        Optional<Blogpost> post = blogpostRepository.findById(postId);
        ArrayList<Blogpost> result = new ArrayList<>();
        post.ifPresent(result::add);
        model.addAttribute("posts", result);
        return "blogs_detailed";
    }
}
