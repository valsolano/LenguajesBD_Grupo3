
package com.restaurante.restaurante.controller;

import com.restaurante.restaurante.Domain.Categoria;
import com.restaurante.restaurante.Service.InventarioService;
import com.restaurante.restaurante.Domain.Inventario;
import com.restaurante.restaurante.Service.CategoriaService;
import com.restaurante.restaurante.Service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/categoria")
public class CategoriaController {
    
    
    @Autowired
    private CategoriaService categoriaService;
  
    
    @GetMapping
    public String categoria(Model model) {
        model.addAttribute("categorias", categoriaService.obtenerCategorias());
        model.addAttribute("content", "categoria/lista");
        return "layout";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("categoria", new Categoria());
        model.addAttribute("content", "categoria/form");
        model.addAttribute("actionUrl", "/categoria/guardar");
        return "layout";
    }

    
    
    @PostMapping("/guardar")
    public String agregarCategoria(@ModelAttribute Categoria categoria, Model model) {
        System.out.println("entrooo agregar");
        categoriaService.insertarCategoria( categoria.getNombre(), categoria.getDescripcion());
        return "redirect:/categoria";
    }
    
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        model.addAttribute("categoria", categoriaService.obtenerCategoriaPorId(id));
        model.addAttribute("content", "categoria/form");
        model.addAttribute("actionUrl", "/categoria/actualizar");
        return "layout";
    }
    
     @PostMapping("/actualizar")
    public String actualizarCategoria(@ModelAttribute Categoria categoria, Model model) {
        categoriaService.actualizarCategoria(categoria.getIdCategoria(), categoria.getNombre(), categoria.getDescripcion(),categoria.getIdEstado());
        return "redirect:/categoria";
    }
    
     @GetMapping("/eliminar/{id}")
    public String eliminarCategoria(@PathVariable Integer id, Model model) {
        categoriaService.eliminarCategoria(id);
        return "redirect:/categoria";
    }
    
   
    
    
}
