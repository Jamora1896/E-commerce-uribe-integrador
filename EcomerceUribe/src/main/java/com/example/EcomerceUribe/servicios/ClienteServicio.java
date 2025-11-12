package com.example.EcomerceUribe.servicios;

import com.example.EcomerceUribe.modelos.Cliente;
import com.example.EcomerceUribe.modelos.DTOS.ClienteDTO;
import com.example.EcomerceUribe.modelos.DTOS.ProductoDTO;
import com.example.EcomerceUribe.modelos.DTOS.UsuarioGenericoDTO;
import com.example.EcomerceUribe.modelos.Producto;
import com.example.EcomerceUribe.modelos.Usuario;
import com.example.EcomerceUribe.modelos.mapas.IClienteMapa;
import com.example.EcomerceUribe.modelos.mapas.IUsuarioMapa;
import com.example.EcomerceUribe.repositorios.IClienteRepositorio;
import com.example.EcomerceUribe.repositorios.IUsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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
    //Buscar todos los clientes
    public List<ClienteDTO> buscarTodosLosClientes(){
        List<Cliente> listaDeClientesConsultados=this.repositorio.findAll();
        return this.mapa.convetir_lista_a_listaclientedto(listaDeClientesConsultados);
    }
    //Buscar por Id
    public  ClienteDTO buscarClientePorId (Integer id){
        Optional<Cliente> clienteQueEstoyBuscando=this.repositorio.findById(id);
        if(!clienteQueEstoyBuscando.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontro ningun cliente con el id "+id+" suministrado"
            );
        }
        Cliente clienteEncontrado = clienteQueEstoyBuscando.get();
        return this.mapa.convertir_cliente_a_clientedto(clienteEncontrado);
    }


    //Eliminar
    public void eliminarCliente(Integer id){
        Optional<Cliente> clienteQueEstoyBuscando=this.repositorio.findById(id);
        if(!clienteQueEstoyBuscando.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontro ningun cliente con el id " + id + " suministrado"
            );
        }
        Cliente clienteEncontrado = clienteQueEstoyBuscando.get();
        try {
            this.repositorio.delete(clienteEncontrado);
        } catch (Exception error) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "No se pudo eliminar el cliente" +error.getMessage()
            );

        }

    }


    // Modificar algunos datos
    public ClienteDTO actualizarCliente(Integer id, Cliente datosActualizados) {
        Optional<Cliente> clienteQueEstoyBuscando = this.repositorio.findById(id);
        if (!clienteQueEstoyBuscando.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontro ningun cliente con el id " + id + " suministrado"
            );

        }
        Cliente clienteEncontrado = clienteQueEstoyBuscando.get();

        // aplicar validaciones


        // actualizo los campos que se permitieron modificar

        clienteEncontrado.setDireccion(datosActualizados.getDireccion());
        clienteEncontrado.setReferenciaPago(datosActualizados.getReferenciaPago());


        //concluyo la operacion en la bd
        Cliente clienteActualizado = this.repositorio.save(clienteEncontrado);


        if (clienteActualizado == null) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al actualizar el cliente en la base de datos, intenta nuevamente"
            );

        }

        return this.mapa.convertir_cliente_a_clientedto(clienteActualizado);
    }

}
