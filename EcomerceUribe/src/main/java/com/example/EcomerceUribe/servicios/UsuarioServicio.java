package com.example.EcomerceUribe.servicios;


import com.example.EcomerceUribe.modelos.DTOS.UsuarioGenericoDTO;
import com.example.EcomerceUribe.modelos.Usuario;
import com.example.EcomerceUribe.modelos.mapas.IUsuarioMapa;
import com.example.EcomerceUribe.repositorios.IUsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicio {

    @Autowired
    private IUsuarioRepositorio repositorio;

    @Autowired
    private IUsuarioMapa mapa;

    //ACTIVADO EL SERVICIO DE GUARDADO DE DATOS

    public UsuarioGenericoDTO guardarUsuariogenerico(Usuario datosUsuario) {

        //VALIDACIÓN DE CORREO DUPLICADO
        if (this.repositorio.findByCorreo(datosUsuario.getCorreo()).isPresent()) {

            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,

                    "Ya existe un usuario registrado con el correo ingresado"
            );

        }

        //VALIDACIÓN DE QUE EL NOMBRE NO ESTE VACIO
        if (datosUsuario.getNombres() == null || datosUsuario.getNombres().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,

                    "El nombre del usuario es obligatorio"
            );
        }

        //validación de que la contraseña es minima
        if (datosUsuario.getContraseña().length() < 6) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,

                    "la contraseña debe tener al menos 6 caracteres"
            );

        }

        //INTENTAR GUARDAR EL USUARIO

        Usuario usuarioQueGuardoElRepo = this.repositorio.save(datosUsuario);
        if (usuarioQueGuardoElRepo == null) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al guardar el usuario en la base de datos"
            );

        }

        return this.mapa.convertir_usuario_a_usuariogenericodto(usuarioQueGuardoElRepo);


    }


    //Buscar todos los usuario
    public List<UsuarioGenericoDTO> buscarTodosLosUsuarios() {
        List<Usuario> listaDeUsuariosConsultados = this.repositorio.findAll();
        return this.mapa.convetir_lista_a_listadtogenerico(listaDeUsuariosConsultados);
    }

    //Buscar un usuario por Id
    public UsuarioGenericoDTO buscarUsuarioGenericoPorId(Integer id) {
        Optional<Usuario> usuarioQueEstoyBuscando = this.repositorio.findById(id);
        if (!usuarioQueEstoyBuscando.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontro ningun usuario con el id " + id + " suministrado"
            );
        }
        Usuario usuarioEncontrado = usuarioQueEstoyBuscando.get();
        return this.mapa.convertir_usuario_a_usuariogenericodto(usuarioEncontrado);
    }

    //Buscar un usuario por correo
    public UsuarioGenericoDTO buscarUsuarioGenericoPorCorreo(String correo) {
        Optional<Usuario> correoDelusuarioQueEstoyBuscando = this.repositorio.findByCorreo(correo);
        if (!correoDelusuarioQueEstoyBuscando.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontro ningun usuario con el correo " + correo + " suministrado"
            );
        }
        Usuario usuarioEncontrado = correoDelusuarioQueEstoyBuscando.get();
        return this.mapa.convertir_usuario_a_usuariogenericodto(usuarioEncontrado);
    }


    //Eliminar un usuario
    public void eliminarUsuario(Integer id) {
        Optional<Usuario> usuarioQueEstoyBuscando = this.repositorio.findById(id);
        if (!usuarioQueEstoyBuscando.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontro ningun usuario con el id " + id + " suministrado"
            );
        }
        Usuario usuarioEncontrado = usuarioQueEstoyBuscando.get();
        try {
            this.repositorio.delete(usuarioEncontrado);
        } catch (Exception error) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "No se pudo eliminar el usuario" + error.getMessage()
            );

        }

    }

    // Modificar algunos datos de un usuario
    public UsuarioGenericoDTO actualizarUsuario(Integer id, Usuario datosActualizados) {
        Optional<Usuario> usuarioQueEstoyBuscando = this.repositorio.findById(id);
        if (!usuarioQueEstoyBuscando.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontro ningun usuario con el id  " + id + " suministrado"
            );

        }
            Usuario usuarioEncontrado = usuarioQueEstoyBuscando.get();

            // aplicar validaciones


            // actualizo los campos que se permitieron modificar

            usuarioEncontrado.setNombres(datosActualizados.getNombres());
            usuarioEncontrado.setCorreo(datosActualizados.getCorreo());


            //concluyo la operacion en la bd
            Usuario usuarioActualizado = this.repositorio.save(usuarioEncontrado);


            if (usuarioActualizado == null) {
                throw new ResponseStatusException(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        "Error al actualizar el usuario en la base de datos, intenta nuevamente"
                );

            }

            return this.mapa.convertir_usuario_a_usuariogenericodto(usuarioActualizado);
        }


}





