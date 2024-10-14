package home.prueba.sprinter.controller;

import home.prueba.sprinter.model.Articulo;
import home.prueba.sprinter.service.ArticuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;


import java.util.List;

/*
El controlador expone los endpoints REST para las operaciones CRUD.
Este archivo mapea los endpoints HTTP y utiliza el servicio ArticuloService para realizar las operaciones correspondientes.
 */
@RestController
@RequestMapping("/api/articulos")
public class ArticuloController {
    @Autowired
    private ArticuloService articuloService;

    @GetMapping
    public List<Articulo> obtenerTodos(){
        return articuloService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Articulo> obtenerPorId(@PathVariable Long id) {
        return articuloService.obtenerArticuloPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Articulo> crear(@RequestBody Articulo articulo) {
        return new ResponseEntity<>(articuloService.crearArticulo(articulo), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Articulo> actualizar(@PathVariable Long id, @RequestBody Articulo articulo) {
        return ResponseEntity.ok(articuloService.actualizar(id, articulo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        articuloService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
