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
import java.util.Optional;

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

    //Buscar un producto por Id
    public  ProductoDTO buscarProductoPorId (Integer id){
        Optional<Producto> productoQueEstoyBuscando=this.repositorio.findById(id);
        if(!productoQueEstoyBuscando.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontro ningun usuario con el id "+id+" suministrado"
            );
        }
        Producto productoEncontrado = productoQueEstoyBuscando.get();
        return this.mapa.convertir_producto_a_productodto(productoEncontrado);
    }

    //Buscar un producto por fotografia
    public  ProductoDTO buscarProductoGenericoPorFotografia (String fotografia){
        Optional<Producto> productoQueEstoyBuscando=this.repositorio.findByFotografia(fotografia);
        if(!productoQueEstoyBuscando.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontro ningun producto con la fotografía "+fotografia+" suministrado"
            );
        }
        Producto productoEncontrado = productoQueEstoyBuscando.get();
        return this.mapa.convertir_producto_a_productodto(productoEncontrado);
    }

    //Eliminar un producto
    public void eliminarProducto(Integer id){
        Optional<Producto> productoQueEstoyBuscando=this.repositorio.findById(id);
        if(!productoQueEstoyBuscando.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontro ningun producto con el id " + id + " suministrado"
            );
        }
        Producto productoEncontrado = productoQueEstoyBuscando.get();
        try {
            this.repositorio.delete(productoEncontrado);
        } catch (Exception error) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "No se pudo eliminar el producto" +error.getMessage()
            );

        }

    }


    // Modificar algunos datos de un producto
    public ProductoDTO actualizarProducto(Integer id, Producto datosActualizados) {
        Optional<Producto> productoQueEstoyBuscando = this.repositorio.findById(id);
        if (!productoQueEstoyBuscando.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontro ningun usuario con el id " + id + " suministrado"
            );

        }
        Producto productoEncontrado = productoQueEstoyBuscando.get();

        // aplicar validaciones


        // actualizo los campos que se permitieron modificar

        productoEncontrado.setNombre(datosActualizados.getNombre());
        productoEncontrado.setFotografia(datosActualizados.getFotografia());


        //concluyo la operacion en la bd
        Producto productoActualizado = this.repositorio.save(productoEncontrado);


        if (productoActualizado == null) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al actualizar el usuario en la base de datos, intenta nuevamente"
            );

        }

        return this.mapa.convertir_producto_a_productodto(productoActualizado);
    }
}
