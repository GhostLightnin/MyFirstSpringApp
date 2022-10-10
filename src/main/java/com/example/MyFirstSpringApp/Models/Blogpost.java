package com.example.MyFirstSpringApp.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Blogpost {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    public String title, annotation, text;
    public int views;

    public Blogpost(String title, String annotation, String text) {
        this.title = title;
        this.annotation = annotation;
        this.text = text;
    }

    public Blogpost() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }



    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }
}
