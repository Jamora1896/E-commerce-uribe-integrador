package com.example.EcomerceUribe.controladores;

import com.example.EcomerceUribe.modelos.Cliente;
import com.example.EcomerceUribe.modelos.DTOS.ClienteDTO;
import com.example.EcomerceUribe.modelos.DTOS.EmpleadoDTO;
import com.example.EcomerceUribe.modelos.Empleado;
import com.example.EcomerceUribe.servicios.ClienteServicio;
import com.example.EcomerceUribe.servicios.EmpleadoServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/clientes")
@Tag(name="controlador para operaciones en la tabla clientes")
public class ClienteControlador {
    @Autowired
    ClienteServicio servicio;

    //guardar
    @Operation(summary = "Crear un  cliente en la base de datos")
    @PostMapping(produces = "application/son")
    public ResponseEntity<ClienteDTO> guardar(@RequestBody Cliente datos) {
        ClienteDTO respuesta = this.servicio.guardarCliente (datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    //listar todos
    @Operation(summary = "Listar todos los clientes guardados en la base de datos")
    @GetMapping(produces = "application/son")
    public ResponseEntity<List<ClienteDTO>> listar() {
        List<ClienteDTO> respuesta = this.servicio.buscarTodosLosClientes();
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    //buscar por ID
    @Operation(summary = "Buscar en la base de datos")
    @GetMapping(value = "/{id}", produces = "application/son")
    public ResponseEntity<ClienteDTO> buscarporId(@PathVariable Integer id) {
       ClienteDTO respuesta = this.servicio.buscarClientePorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }


    //eliminar cliente
    @Operation(summary="Eliminar clientes en la base de datos")
    @DeleteMapping(value = "/{id}", produces = "application/son")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id){
        this.servicio.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }

    //modificar
    @Operation(summary = "Modifica en la base de datos")
    @PutMapping(value = "/{id}", produces = "application/son")
    public ResponseEntity<ClienteDTO> modificar(@PathVariable Integer id, @RequestBody Cliente datos) {
        ClienteDTO respuesta = this.servicio.actualizarCliente(id, datos);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);

    }
}
