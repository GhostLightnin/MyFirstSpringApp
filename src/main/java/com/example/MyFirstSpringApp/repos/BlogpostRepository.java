package com.example.MyFirstSpringApp.repos;

import com.example.MyFirstSpringApp.Models.Blogpost;
import org.springframework.data.repository.CrudRepository;

public interface BlogpostRepository extends CrudRepository<Blogpost, Long> {
}
