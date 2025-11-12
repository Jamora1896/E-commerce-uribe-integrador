package com.example.EcomerceUribe.controladores;

import com.example.EcomerceUribe.modelos.DTOS.PedidoDTO;
import com.example.EcomerceUribe.modelos.DTOS.ProductoDTO;
import com.example.EcomerceUribe.modelos.Pedido;
import com.example.EcomerceUribe.modelos.Producto;
import com.example.EcomerceUribe.servicios.PedidoServicio;
import com.example.EcomerceUribe.servicios.UsuarioServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/api/pedidos")
@Tag(name="controlador para operaciones en la tabla pedidos")

public class PedidoControlador {
    @Autowired
    PedidoServicio servicio;

    //guardar
    @Operation(summary = "Crear un  pedido en la base de datos")
    @PostMapping(produces = "application/son")
    public ResponseEntity<PedidoDTO> guardar(@RequestBody Pedido datos) {
        PedidoDTO respuesta = this.servicio.guardarPedido (datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    //listar todos
    @Operation(summary = "Listar todos los pedidos guardados en la base de datos")
    @GetMapping(produces = "application/son")
    public ResponseEntity<List<PedidoDTO>> listar() {
        List<PedidoDTO> respuesta = this.servicio.buscarTodosLosPedidos();
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    //buscar por ID
    @Operation(summary = "Buscar pedidos en la base de datos")
    @GetMapping(value = "/{id}", produces = "application/son")
    public ResponseEntity<PedidoDTO> buscarporId(@PathVariable Integer id) {
        PedidoDTO respuesta = this.servicio.buscarPedidoPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    //eliminar pedido por fecha de creación
    @Operation(summary="Eliminar un pedido en la base de datos")
    @DeleteMapping(value = "/{fechaCreacion}", produces = "application/son")
    public ResponseEntity<Void> eliminar(@PathVariable LocalDate fechaCreacion){
        this.servicio.eliminarPedido(fechaCreacion);
        return ResponseEntity.noContent().build();
    }
    //modificar
    @Operation(summary = "Modifica monto y fecha de creacion de pedidos en la base de datos")
    @PutMapping(value = "/{id}", produces = "application/son")
    public ResponseEntity<PedidoDTO> modificar(@PathVariable Integer id, @RequestBody Pedido datos) {
        PedidoDTO respuesta = this.servicio.actualizarPedido(id, datos);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);

    }
}
