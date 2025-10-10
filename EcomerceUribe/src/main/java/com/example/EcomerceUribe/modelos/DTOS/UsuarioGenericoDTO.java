package com.example.EcomerceUribe.modelos.DTOS;

import com.example.EcomerceUribe.ayudas.EstadosUsuario;

import java.time.LocalDate;

public class UsuarioGenericoDTO {
    private  String nombres;
    private String correo;
    private EstadosUsuario estado;
    private LocalDate fechaNacimiento;
    private String documento;

    public UsuarioGenericoDTO() {
    }

    public UsuarioGenericoDTO(String nombres, String correo, EstadosUsuario estado, LocalDate fechaNacimiento, String documento) {
        this.nombres = nombres;
        this.correo = correo;
        this.estado = estado;
        this.fechaNacimiento = fechaNacimiento;
        this.documento = documento;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public EstadosUsuario getEstado() {
        return estado;
    }

    public void setEstado(EstadosUsuario estado) {
        this.estado = estado;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }
}
