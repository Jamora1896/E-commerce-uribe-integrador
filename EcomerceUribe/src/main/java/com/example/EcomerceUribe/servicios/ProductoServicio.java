package com.example.EcomerceUribe.servicios;

import com.example.EcomerceUribe.modelos.DTOS.PedidoDTO;
import com.example.EcomerceUribe.modelos.DTOS.ProductoDTO;
import com.example.EcomerceUribe.modelos.DTOS.UsuarioGenericoDTO;
import com.example.EcomerceUribe.modelos.Pedido;
import com.example.EcomerceUribe.modelos.Producto;
import com.example.EcomerceUribe.modelos.Usuario;
import com.example.EcomerceUribe.modelos.mapas.IPedidoMapa;
import com.example.EcomerceUribe.modelos.mapas.IProductoMapa;
import com.example.EcomerceUribe.repositorios.IPedidoRepositorio;
import com.example.EcomerceUribe.repositorios.IProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductoServicio {

    @Autowired
    private IProductoRepositorio repositorio;

    @Autowired
    private IProductoMapa mapa;

    public ProductoDTO guardarProducto (Producto datosProducto){

        if(datosProducto.getNombre()==null || datosProducto.getNombre().isBlank() ) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,

                    "El nombre del producto es obligatorio"
            );
        }
        Producto productoQueGuardoElRepo=this.repositorio.save(datosProducto);
        if(productoQueGuardoElRepo==null){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al guardar el pedido en la base de datos"
            );

        }

        return this.mapa.convertir_producto_a_productodto(productoQueGuardoElRepo);

    }
    //Buscar todos los productos
    public List<ProductoDTO> buscarTodosLosProductos(){
        List<Producto> listaDeProductosConsultados=this.repositorio.findAll();
        return this.mapa.convetir_lista_a_listaproductodto(listaDeProductosConsultados);
    }

    //Buscar un usuario por Id


    //Eliminar un usuario


    // Modificar algunos datos de un usuario
}
