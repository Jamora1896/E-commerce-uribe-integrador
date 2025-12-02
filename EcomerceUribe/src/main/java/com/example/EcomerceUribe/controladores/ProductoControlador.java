package com.example.EcomerceUribe.controladores;


import com.example.EcomerceUribe.modelos.DTOS.ProductoDTO;
import com.example.EcomerceUribe.modelos.DTOS.UsuarioGenericoDTO;
import com.example.EcomerceUribe.modelos.Producto;
import com.example.EcomerceUribe.modelos.Usuario;
import com.example.EcomerceUribe.servicios.ProductoServicio;
import com.example.EcomerceUribe.servicios.UsuarioServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@Tag(name="controlador para operaciones en la tabla productos")
public class ProductoControlador {


    @Autowired
    ProductoServicio servicio;




    //guardar
    @Operation(summary = "Crear un  producto en la base de datos")
    @PostMapping(produces = "application/json")
    public ResponseEntity<ProductoDTO> guardar(@RequestBody Producto datos) {
        ProductoDTO respuesta = this.servicio.guardarProducto (datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    //listar todos
    @Operation(summary = "Listar todos los productos guardados en la base de datos")
    @GetMapping(consumes = "application/json",produces = "application/json")
    public ResponseEntity<List<ProductoDTO>> listar() {
        List<ProductoDTO> respuesta = this.servicio.buscarTodosLosProductos();
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    //buscar por ID
    @Operation(summary = "Buscar producto en la base de datos")
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ProductoDTO> buscarporId(@PathVariable Integer id) {
        ProductoDTO respuesta = this.servicio.buscarProductoPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    //eliminar
    @Operation(summary = "Eliminar producto en la base de datos")
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        this.servicio.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }

    //modificar
    @Operation(summary = "Modifica nombre y fotografia de producto en la base de datos")
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ProductoDTO> modificar(@PathVariable Integer id, @RequestBody Producto datos) {
        ProductoDTO respuesta = this.servicio.actualizarProducto(id, datos);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);

    }

}
