package com.example.EcomerceUribe.controladores;


import com.example.EcomerceUribe.modelos.DTOS.EmpleadoDTO;
import com.example.EcomerceUribe.modelos.DTOS.ProductoDTO;
import com.example.EcomerceUribe.modelos.Empleado;
import com.example.EcomerceUribe.modelos.Producto;
import com.example.EcomerceUribe.servicios.EmpleadoServicio;
import com.example.EcomerceUribe.servicios.PedidoServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/empleados")
@Tag(name="controlador para operaciones en la tabla empleados")
public class EmpleadoControlador {

    @Autowired
    EmpleadoServicio servicio;

    //guardar
    @Operation(summary = "Crear un  empleado en la base de datos")
    @PostMapping(produces = "application/son")
    public ResponseEntity<EmpleadoDTO> guardar(@RequestBody Empleado datos) {
        EmpleadoDTO respuesta = this.servicio.guardarEmpleado (datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    //listar todos
    @Operation(summary = "Listar todos los empleados guardados en la base de datos")
    @GetMapping(produces = "application/son")
    public ResponseEntity<List<EmpleadoDTO>> listar() {
        List<EmpleadoDTO> respuesta = this.servicio.buscarTodosLosEmpleados();
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    //buscar por ID
    @Operation(summary = "Buscar en la base de datos")
    @GetMapping(value = "/{id}", produces = "application/son")
    public ResponseEntity<EmpleadoDTO> buscarporId(@PathVariable Integer id) {
        EmpleadoDTO respuesta = this.servicio.buscarEmpleadoPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }


    //eliminar empleado por sede
    @Operation(summary="Eliminar empleados en la base de datos")
    @DeleteMapping(value = "/{sede}", produces = "application/son")
    public ResponseEntity<Void> eliminar(@PathVariable String sede){
        this.servicio.eliminarEmpleado(sede);
        return ResponseEntity.noContent().build();
    }

    //modificar
    @Operation(summary = "Modifica en la base de datos")
    @PutMapping(value = "/{id}", produces = "application/son")
    public ResponseEntity<EmpleadoDTO> modificar(@PathVariable Integer id, @RequestBody Empleado datos) {
        EmpleadoDTO respuesta = this.servicio.actualizarEmpleado(id, datos);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);

    }
}
