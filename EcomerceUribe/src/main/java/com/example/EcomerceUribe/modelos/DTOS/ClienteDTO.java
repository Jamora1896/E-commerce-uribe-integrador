package com.example.EcomerceUribe.modelos.DTOS;

public class ClienteDTO {

    private String direccion;
    private Double calificacion;
    private String referenciaPago;

    public ClienteDTO() {
    }

    public ClienteDTO(String direccion, Double calificacion, String referenciaPago) {
        this.direccion = direccion;
        this.calificacion = calificacion;
        this.referenciaPago = referenciaPago;
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
}
