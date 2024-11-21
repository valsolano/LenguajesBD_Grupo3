/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.restaurante.restaurante.controller;

import com.restaurante.restaurante.Domain.Distrito;
import com.restaurante.restaurante.Service.DistritoService;
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
@RequestMapping("/distrito")
public class DistritoController {
    
    @Autowired
    private DistritoService distritoService;
    
    
    @GetMapping
    public String distrito(Model model) {
        model.addAttribute("distritos", distritoService.obtenerDistritos());
        model.addAttribute("content", "distrito/lista");
        return "layout";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("distrito", new Distrito());
        model.addAttribute("content", "distrito/form");
        model.addAttribute("actionUrl", "/distrito/guardar");
        return "layout";
    }
    
    @PostMapping("/guardar")
    public String agregarDistrito(@ModelAttribute Distrito distrito, Model model) {
        System.out.println("entrooo agregar");
        distritoService.insertarDistrito(distrito.getNombre(),distrito.getIdCanton());
        return "redirect:/direccion";
    }
    
   @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        model.addAttribute("distrito", distritoService.obtenerDistritoPorId(id));
        model.addAttribute("content", "distrito/form");
        model.addAttribute("actionUrl", "/distrito/actualizar");
        return "layout";
    }
    
    
    @PostMapping("/actualizar")
    public String actualizarDistrito(@ModelAttribute Distrito distrito, Model model) {
        distritoService.actualizarDistrito(distrito.getIdDistrito(), distrito.getNombre(),distrito.getIdCanton(), distrito.getIdEstado());
        return "redirect:/direccion";
    }
    
     @GetMapping("/eliminar/{id}")
    public String eliminarDistrito(@PathVariable Integer id, Model model) {
        distritoService.eliminarDistrito(id);
        return "redirect:/direccion";
    }
    
     
    
}
