package com.example.EcomerceUribe.modelos;

import com.example.EcomerceUribe.ayudas.Departamentos;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "clientes")
public class Cliente {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String direccion;
    private Double calificacion;
    private String referenciaPago;
    private Departamentos departamentos;
    private String ciudad;

    @OneToMany (mappedBy = "cliente")
    @JsonManagedReference(value = "relacionclientepedido")
    private List<Pedido> pedidos;


    public Cliente() {
    }

    public Cliente(Integer id, String direccion, Double calificacion, String referenciaPago, Departamentos departamentos, String ciudad) {
        this.id = id;
        this.direccion = direccion;
        this.calificacion = calificacion;
        this.referenciaPago = referenciaPago;
        this.departamentos = departamentos;
        this.ciudad = ciudad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }

    public String getReferenciaPago() {
        return referenciaPago;
    }

    public void setReferenciaPago(String referenciaPago) {
        this.referenciaPago = referenciaPago;
    }

    public Departamentos getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(Departamentos departamentos) {
        this.departamentos = departamentos;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}
