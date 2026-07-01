package com.personal.taller.request;

import com.personal.taller.dto.ClienteDto;
import com.personal.taller.dto.OrdenTrabajoDto;

import java.util.Set;

public class VehiculoRequest {

    private long id;
    private boolean habilitado;
    private String marca;
    private String modelo;
    private String patente;
    private String oldPatente;
    private String anio;
    private String numeroMotor;
    private String numeroChasis;
    private String rutDueno;
    private String color;
    private String kilometraje;

    private Set<ClienteDto> cliente;

    private Set<OrdenTrabajoDto> ordenTrabajo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getOldPatente() {
        return oldPatente;
    }

    public void setOldPatente(String oldPatente) {
        this.oldPatente = oldPatente;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getNumeroMotor() {
        return numeroMotor;
    }

    public void setNumeroMotor(String numeroMotor) {
        this.numeroMotor = numeroMotor;
    }

    public String getNumeroChasis() {
        return numeroChasis;
    }

    public void setNumeroChasis(String numeroChasis) {
        this.numeroChasis = numeroChasis;
    }

    public String getRutDueno() {
        return rutDueno;
    }

    public void setRutDueno(String rutDueno) {
        this.rutDueno = rutDueno;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(String kilometraje) {
        this.kilometraje = kilometraje;
    }

    public Set<ClienteDto> getCliente() {
        return cliente;
    }

    public void setCliente(Set<ClienteDto> cliente) {
        this.cliente = cliente;
    }

    public Set<OrdenTrabajoDto> getOrdenTrabajo() {
        return ordenTrabajo;
    }

    public void setOrdenTrabajo(Set<OrdenTrabajoDto> ordenTrabajo) {
        this.ordenTrabajo = ordenTrabajo;
    }

}
