/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.restaurante.restaurante.controller;


import com.restaurante.restaurante.Domain.Proveedor;
import com.restaurante.restaurante.Service.ProveedorService;

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
@RequestMapping("/proveedor")
public class ProveedorController {
    
    @Autowired
    private ProveedorService proveedorService;
    
    @GetMapping
    public String proveedor(Model model) {
        model.addAttribute("proveedores", proveedorService.obtenerProveedores());
        model.addAttribute("content", "proveedor/lista");
        return "layout";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("proveedor", new Proveedor());
        model.addAttribute("content", "proveedor/form");
        model.addAttribute("actionUrl", "/proveedor/guardar");
        return "layout";
    }

    @PostMapping("/guardar")
    public String agregarProveedor(@ModelAttribute Proveedor proveedor, Model model) {
        System.out.println("entrooo agregar");
        proveedorService.insertarProveedor( proveedor.getNombre(), proveedor.getTelefono(),proveedor.getEmail(),proveedor.getIdDireccion());
        return "redirect:/inventario";
    }
    
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        model.addAttribute("proveedor", proveedorService.obtenerProveedorPorId(id));
        model.addAttribute("content", "proveedor/form");
        model.addAttribute("actionUrl", "/proveedor/actualizar");
        return "layout";
    }

     @PostMapping("/actualizar")
    public String actualizarProveedor(@ModelAttribute Proveedor proveedor, Model model) {
        proveedorService.actualizarProveedor(proveedor.getIdProveedor(), proveedor.getNombre(), proveedor.getTelefono(),proveedor.getEmail(),proveedor.getIdDireccion(), proveedor.getIdEstado());
        return "redirect:/inventario";
    }
    
     @GetMapping("/eliminar/{id}")
    public String eliminarProveedor(@PathVariable Integer id, Model model) {
        proveedorService.eliminarProveedor(id);
        return "redirect:/inventario";
    }
    
    
}
