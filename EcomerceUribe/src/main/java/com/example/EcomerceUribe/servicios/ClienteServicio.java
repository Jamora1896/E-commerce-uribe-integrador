package com.example.EcomerceUribe.servicios;

import com.example.EcomerceUribe.modelos.Cliente;
import com.example.EcomerceUribe.modelos.DTOS.ClienteDTO;
import com.example.EcomerceUribe.modelos.DTOS.UsuarioGenericoDTO;
import com.example.EcomerceUribe.modelos.Usuario;
import com.example.EcomerceUribe.modelos.mapas.IClienteMapa;
import com.example.EcomerceUribe.modelos.mapas.IUsuarioMapa;
import com.example.EcomerceUribe.repositorios.IClienteRepositorio;
import com.example.EcomerceUribe.repositorios.IUsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ClienteServicio {


    @Autowired
    private IClienteRepositorio repositorio;

    @Autowired
    private IClienteMapa mapa;

    public ClienteDTO guardarCliente(Cliente datosCliente){

        if(datosCliente.getReferenciaPago()==null || datosCliente.getReferenciaPago().isBlank() ){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,

                    "La referencia  de pago del cliente es obligatorio"
            );
        }
        Cliente clienteQueGuardoElRepo=this.repositorio.save(datosCliente);
        if(clienteQueGuardoElRepo==null){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al guardar el cliente en la base de datos"
            );

        }

        return this.mapa.convertir_cliente_a_clientedto(clienteQueGuardoElRepo);

    }

}
