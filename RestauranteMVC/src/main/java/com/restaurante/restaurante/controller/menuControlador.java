package com.restaurante.restaurante.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class menuControlador {
  
  @GetMapping("/perfil")
  public String perfil() {
    return "perfil";
  }
  
  @GetMapping("/contacto")
  public String contacto() {
    return "contacto";
  }
  
  @GetMapping("/acerca-de")
  public String acercaDe() {
    return "acerca-de";
  }
}