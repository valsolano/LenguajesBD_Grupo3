/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.restaurante.restaurante.controller;

import com.restaurante.restaurante.Domain.Canton;
import com.restaurante.restaurante.Service.CantonService;
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
import org.springframework.web.bind.annotation.RestController;
@Controller
@RequestMapping("/canton")
public class CantonController {
    
    @Autowired
    private CantonService cantonService;
    
    
    
    @GetMapping
    public String canton(Model model) {
        model.addAttribute("cantones", cantonService.obtenerCantones());
        model.addAttribute("content", "canton/lista");
        return "layout";
    }
    
    
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("canton", new Canton());
        model.addAttribute("content", "canton/form");
        model.addAttribute("actionUrl", "/canton/guardar");
        return "layout";
    }
    
    @PostMapping("/guardar")
    public String agregarCanton(@ModelAttribute Canton canton, Model model) {
        System.out.println("entrooo agregar");
        cantonService.insertarCanton(canton.getNombre(),canton.getIdProvincia());
        return "redirect:/direccion";
    }
    
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        model.addAttribute("canton", cantonService.obtenerCantonPorId(id));
        model.addAttribute("content", "canton/form");
        model.addAttribute("actionUrl", "/canton/actualizar");
        return "layout";
    }
    
    @PostMapping("/actualizar")
    public String actualizarCanton(@ModelAttribute Canton canton, Model model) {
        cantonService.actualizarCanton(canton.getIdCanton(), canton.getNombre(), canton.getIdProvincia(), canton.getIdEstado());
        return "redirect:/direccion";
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminarCanton(@PathVariable Integer id, Model model) {
        cantonService.eliminarCanton(id);
        return "redirect:/direccion";
    }
    
    
    
    
}
