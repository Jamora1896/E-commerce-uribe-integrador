package com.example.EcomerceUribe.modelos.mapas;


import com.example.EcomerceUribe.modelos.Cliente;
import com.example.EcomerceUribe.modelos.DTOS.ClienteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import javax.crypto.spec.PSource;
import java.util.List;

@Mapper(componentModel= "spring")
public interface IClienteMapa {

    @Mapping(source ="direccion", target = "direccion")
    @Mapping(source ="calificacion", target = "calificacion")
    @Mapping(source ="referenciaPago", target = "referenciaPago")


    ClienteDTO convertir_cliente_a_clientedto(Cliente cliente);


    List<ClienteDTO> convetir_lista_a_listaclientedto(List<Cliente> lista);


}
