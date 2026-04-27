package com.personal.taller.dto;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "hibernateLazyInitializer" })
@Entity
@Table(name = "repuesto")
public class RepuestoDto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "habilitado")
    private boolean habilitado;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "codigo")
    private String codigo;
    @Column(name = "marca")
    private String marca;
    @Column(name = "modelo")
    private String modelo;
    @Column(name = "anio")
    private String anio;
    @Column(name = "rutProveedor")
    private String rutProveedor;
    @Column(name = "valor")
    private long valor;

    //@OneToOne
    //@JoinColumn(name = "detalle_id")
    //private DetalleRepuestosDto detalle;

    @ManyToMany(fetch = FetchType.EAGER)
    @Column(name = "proveedor")
    private Set<ProveedorDto> proveedor;

    public RepuestoDto() {
    }

    public RepuestoDto(long id, boolean habilitado, String marca, String modelo, String nombre, String anio,
            String codigo, String rutProveedor, long valor) {
        super();
        this.id = id;
        this.habilitado = habilitado;
        this.marca = marca;
        this.modelo = modelo;
        this.nombre = nombre;
        this.anio = anio;
        this.codigo = codigo;
        this.rutProveedor = rutProveedor;
        this.valor = valor;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getRutProveedor() {
        return rutProveedor;
    }

    public void setRutProveedor(String rutProveedor) {
        this.rutProveedor = rutProveedor;
    }

    public long getValor() {
        return valor;
    }

    public void setValor(long valor) {
        this.valor = valor;
    }

    /*public DetalleRepuestosDto getDetalle() {
        return detalle;
    }

    public void setDetalle(DetalleRepuestosDto detalle) {
        this.detalle = detalle;
    }*/

    public Set<ProveedorDto> getProveedor() {
        return proveedor;
    }

    public void setProveedor(Set<ProveedorDto> proveedor) {
        this.proveedor = proveedor;
    }
}
