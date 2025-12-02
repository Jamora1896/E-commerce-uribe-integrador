package com.example.EcomerceUribe.controladores;


import com.example.EcomerceUribe.modelos.DTOS.UsuarioGenericoDTO;
import com.example.EcomerceUribe.modelos.Usuario;
import com.example.EcomerceUribe.servicios.UsuarioServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name="controlador para operaciones en la tabla usuarios")
public class UsuarioControlador {

    //1.llamar al servicio
    @Autowired
    UsuarioServicio servicio;

    //2. listar los posible llamados a los servicios disponibles

    //3. se crean funciones por cada posible llamado y se les agrega el metodo http
    //correspondiente (get, put, post, delete)

    //guardar
    @Operation(summary = "Crear un usuario en la base de datos")
    @PostMapping(produces = "application/json")
    public ResponseEntity<UsuarioGenericoDTO> guardar(@RequestBody Usuario datos) {
        UsuarioGenericoDTO respuesta = this.servicio.guardarUsuariogenerico(datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    //listar todos
    @Operation(summary = "Listar todos los usuarios guardados en la base de datos")
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<UsuarioGenericoDTO>> listar() {
        List<UsuarioGenericoDTO> respuesta = this.servicio.buscarTodosLosUsuarios();
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    //buscar por ID
    @Operation(summary = "Buscar un usuario en la base de datos")
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<UsuarioGenericoDTO> buscarporId(@PathVariable Integer id) {
        UsuarioGenericoDTO respuesta = this.servicio.buscarUsuarioGenericoPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    //buscar por correo
    @Operation(summary = "Buscar un usuario en la base de datos")
    @GetMapping(value = "/{correo}", produces = "application/json")
    public ResponseEntity<UsuarioGenericoDTO> buscarporCorreo(@PathVariable String correo) {
        UsuarioGenericoDTO respuesta = this.servicio.buscarUsuarioGenericoPorCorreo(correo);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    //eliminar
    @Operation(summary = "Eliminar un usuario en la base de datos")
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        this.servicio.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    //modificar
    @Operation(summary = "Modifica nombre y correo de  un usuario en la base de datos")
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<UsuarioGenericoDTO> modificar(@PathVariable Integer id, @RequestBody Usuario datos) {
        UsuarioGenericoDTO respuesta = this.servicio.actualizarUsuario(id, datos);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);

    }
}
