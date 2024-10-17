package home.prueba.sprinter.controller;

import home.prueba.sprinter.model.Articulo;
import home.prueba.sprinter.service.ArticuloService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

/*
* Una vez añadidas las anotaciones de Swagger
* Para utilizarlo debemos lanzar la aplicacion
* y acceder a: http://localhost:8080/swagger-ui.html
* */
@RestController
@RequestMapping("/api/articulos")
public class ArticuloController {
    @Autowired
    private ArticuloService articuloService;

    @Operation(summary = "Obtiene todos los artículos")
    @GetMapping
    public List<Articulo> obtenerTodos(){
        return articuloService.obtenerTodos();
    }

    @Operation(summary = "Obtiene artículo concreto")
    @GetMapping("/{id}")
    public ResponseEntity<Articulo> obtenerPorId(@PathVariable Long id) {
        return articuloService.obtenerArticuloPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crea un nuevo artículo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Artículo creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud no válida")
    })
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
