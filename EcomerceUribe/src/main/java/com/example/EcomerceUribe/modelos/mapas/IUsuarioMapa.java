package com.example.EcomerceUribe.modelos.mapas;

import com.example.EcomerceUribe.modelos.DTOS.UsuarioEspecialDTO;
import com.example.EcomerceUribe.modelos.DTOS.UsuarioGenericoDTO;
import com.example.EcomerceUribe.modelos.Usuario;
import org.mapstruct.Mapper;
import org.springframework.web.bind.annotation.Mapping;

import java.util.List;

@Mapper(componentModel= "spring")
public interface IUsuarioMapa {

    // se deben construir dos funciones por mapa
    // 1 que transforme 1 modelo en 1 DTO

    @Mapping(source = "nombres", target = "nombres")
    @Mapping(source = "correo", target = "correo")
    @Mapping(source = "estado", target = "estado")
    @Mapping(source = "fechaNacimiento", target = "fechaNacimiento")
    @Mapping(source = "documento", target = "documento")
    UsuarioGenericoDTO convertir_usuario_a_usuariogenericodto(Usuario usuario);


    // 2 que transforme una list<modelo> en 1 DTO

    List<UsuarioGenericoDTO> convetir_lista_a_listadtogenerico(List<Usuario> lista);


    @Mapping(source = "nombres", target = "nombres")
    @Mapping(source = "correo", target = "correo")
    @Mapping(source = "estado", target = "estado")
    @Mapping(source = "fechaNacimiento", target = "fechaNacimiento")
    @Mapping(source = "documento", target = "documento")
    @Mapping(source = "contraseña", target = "contraseña")
    UsuarioEspecialDTO convertir_usuario_a_usuarioespecialdto(Usuario usuario);

    List<UsuarioEspecialDTO> convetir_lista_a_listadtoespecial(List<Usuario> lista);
}
