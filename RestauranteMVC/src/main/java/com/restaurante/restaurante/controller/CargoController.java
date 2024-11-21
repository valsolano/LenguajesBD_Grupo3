/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.restaurante.restaurante.controller;

import com.restaurante.restaurante.Domain.Cargo;
import com.restaurante.restaurante.Service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author valer
 */
@Controller
@RequestMapping("/cargo")
public class CargoController {

    @Autowired
    private CargoService cargoService;

    @GetMapping
    public String cargo(Model model) {
        model.addAttribute("cargos", cargoService.obtenerCargos());
        model.addAttribute("content", "cargo/lista");
        return "layout";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("cargo", new Cargo());
        model.addAttribute("content", "cargo/form");
        model.addAttribute("actionUrl", "/cargo/guardar");
        return "layout";
    }

    @PostMapping("/guardar")
    public String agregarCargo(@ModelAttribute Cargo cargo, Model model) {
        System.out.println("entrooo agregar");
        cargoService.insertarCargo(cargo.getNombre(), cargo.getDescripcion());
        return "redirect:/cargo";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        model.addAttribute("cargo", cargoService.obtenerCargoPorId(id));
        model.addAttribute("content", "cargo/form");
        model.addAttribute("actionUrl", "/cargo/actualizar");
        return "layout";
    }

    @PostMapping("/actualizar")
    public String actualizarCargo(@ModelAttribute Cargo cargo, Model model) {
        cargoService.actualizarCargo(cargo.getIdCargo(), cargo.getNombre(), cargo.getDescripcion(), cargo.getIdEstado());
        return "redirect:/cargo";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCargo(@PathVariable Integer id, Model model) {
        cargoService.eliminarCargo(id);
        return "redirect:/cargo";
    }
}
