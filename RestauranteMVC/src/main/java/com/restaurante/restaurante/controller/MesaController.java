/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.restaurante.restaurante.controller;

import com.restaurante.restaurante.Domain.Mesa;
import com.restaurante.restaurante.Service.MesaService;
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
@RequestMapping("/mesa")
public class MesaController {
    
    @Autowired
    private MesaService mesaService;
    
    @GetMapping
    public String mesa(Model model) {
        model.addAttribute("mesas", mesaService.obtenerMesas());
        model.addAttribute("content", "mesa/lista");
        return "layout";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("mesa", new Mesa());
        model.addAttribute("content", "mesa/form");
        model.addAttribute("actionUrl", "/mesa/guardar");
        return "layout";
    }

    @PostMapping("/guardar")
    public String agregarMesa(@ModelAttribute Mesa mesa, Model model) {
        System.out.println("entrooo agregar");
        mesaService.insertarMesa( mesa.getCapacidad());
        return "redirect:/mesa";
    }
    
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        model.addAttribute("mesa", mesaService.obtenerMesaPorId(id));
        model.addAttribute("content", "mesa/form");
        model.addAttribute("actionUrl", "/mesa/actualizar");
        return "layout";
    }

     @PostMapping("/actualizar")
    public String actualizarMesa(@ModelAttribute Mesa mesa, Model model) {
        mesaService.actualizarMesa(mesa.getIdMesa(),mesa.getCapacidad(), mesa.getIdEstado());
        return "redirect:/mesa";
    }
    
     @GetMapping("/eliminar/{id}")
    public String eliminarMesa(@PathVariable Integer id, Model model) {
        mesaService.eliminarMesa(id);
        return "redirect:/mesa";
    }
    
    
    
}
