package com.personal.taller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@JsonIgnoreProperties({ "hibernateLazyInitializer" })
@Entity
@Table(name = "vehiculo")
public class VehiculoDto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "habilitado")
    private boolean habilitado;
    @Column(name = "marca")
    private String marca;
    @Column(name = "modelo")
    private String modelo;
    @Column(name = "patente")
    private String patente;
    @Column(name = "anio")
    private String anio;
    @Column(name = "numeroMotor")
    private String numeroMotor;
    @Column(name = "numeroChasis")
    private String numeroChasis;
    @Column(name = "rutDueno")
    private String rutDueno;
    @Column(name = "color")
    private String color;
    @Column(name = "kilometraje")
    private String kilometraje;

    @ManyToMany(fetch = FetchType.EAGER)
    @Column(name = "cliente")
    private Set<ClienteDto> cliente;

    @OneToMany(fetch = FetchType.EAGER)
    @Column(name = "ordenTrabajo")
    private Set<OrdenTrabajoDto> ordenTrabajo;

    public VehiculoDto() {
    }

    public VehiculoDto(long id, boolean habilitado, String marca, String modelo, String patente, String anio,
            String numeroMotor, String numeroChasis, String rutDueno, String color, String kilometraje) {
        super();
        this.id = id;
        this.habilitado = habilitado;
        this.marca = marca;
        this.modelo = modelo;
        this.patente = patente;
        this.anio = anio;
        this.numeroMotor = numeroMotor;
        this.numeroChasis = numeroChasis;
        this.rutDueno = rutDueno;
        this.color = color;
        this.kilometraje = kilometraje;
    }

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
