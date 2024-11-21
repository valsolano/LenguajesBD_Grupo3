/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.restaurante.restaurante.controller;

import com.restaurante.restaurante.Domain.Estado;
import com.restaurante.restaurante.Service.EstadoService;
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
@RequestMapping("/estado")
public class EstadoController {

    @Autowired
    private EstadoService estadoService;

    @GetMapping
    public String estado(Model model) {
        model.addAttribute("estados", estadoService.obtenerEstados());
        model.addAttribute("content", "estado/lista");
        return "layout";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("estado", new Estado());
        model.addAttribute("content", "estado/form");
        model.addAttribute("actionUrl", "/estado/guardar");
        return "layout";
    }

    @PostMapping("/guardar")
    public String agregarEstado(@ModelAttribute Estado estado, Model model) {
        System.out.println("entrooo agregar");
        estadoService.insertarEstado(estado.getTipoEstado());
        return "redirect:/estado";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        model.addAttribute("estado", estadoService.obtenerEstadoPorId(id));
        model.addAttribute("content", "estado/form");
        model.addAttribute("actionUrl", "/estado/actualizar");
        return "layout";
    }

    @PostMapping("/actualizar")
    public String actualizarEstado(@ModelAttribute Estado estado, Model model) {
        estadoService.actualizarEstado(estado.getIdEstado(), estado.getTipoEstado());
        return "redirect:/estado";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarEstado(@PathVariable Integer id, Model model) {
        estadoService.eliminarEstado(id);
        return "redirect:/estado";
    }

}
