package com.restaurante.restaurante.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class mainController {

  @GetMapping("/")
  public String	index(Model model) {
    System.out.println("entroooooo");
    model.addAttribute("content", "index"); // `index` es el nombre de la plantilla que se va a insertar.
    return "layout";
  }

}