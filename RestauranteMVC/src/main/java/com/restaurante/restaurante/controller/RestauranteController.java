/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.restaurante.restaurante.controller;

import com.restaurante.restaurante.Domain.Restaurante;
import com.restaurante.restaurante.Service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/restaurante")
public class RestauranteController {

    @Autowired
    private RestauranteService restauranteService;

    @GetMapping
    public String restaurante(Model model) {
        model.addAttribute("restaurantes", restauranteService.obtenerRestaurantes());
        model.addAttribute("content", "restaurante/lista");
        return "layout";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("restaurante", new Restaurante());
        model.addAttribute("content", "restaurante/form");
        model.addAttribute("actionUrl", "/restaurante/guardar");
        return "layout";
    }

    @PostMapping("/guardar")
    public String agregarRestaurante(@ModelAttribute Restaurante restaurante, Model model) {
        System.out.println("entrooo agregar");
        restauranteService.insertarRestaurante(restaurante.getNombre(), restaurante.getTelefono(), restaurante.getCorreo(), restaurante.getDireccion());
        return "redirect:/restaurante";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        model.addAttribute("restaurante", restauranteService.obtenerRestaurantePorId(id));
        model.addAttribute("content", "restaurante/form");
        model.addAttribute("actionUrl", "/restaurante/actualizar");
        return "layout";
    }

    @PostMapping("/actualizar")
    public String actualizarRestaurante(@ModelAttribute Restaurante restaurante, Model model) {
        restauranteService.actualizarRestaurante(restaurante.getIdRestaurante(), restaurante.getNombre(), restaurante.getTelefono(), restaurante.getCorreo(), restaurante.getDireccion(), restaurante.getIdEstado());
        return "redirect:/restaurante";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarRestaurante(@PathVariable Integer id, Model model) {
        restauranteService.eliminarRestaurante(id);
        return "redirect:/restaurante";
    }

}
