package com.personal.taller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

//@Document("clientes")

@JsonIgnoreProperties({ "hibernateLazyInitializer" })
@Entity
@Table(name = "clientes")
public class ClienteDto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "habilitado")
    private boolean habilitado;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    // @Id
    @Column(name = "rut")
    private String rut;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "comuna")
    private String comuna;
    @Column(name = "ciudad")
    private String ciudad;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "email")
    private String email;

    @OneToMany(fetch = FetchType.EAGER)
    @Column(name = "ordenTrabajo")
    private Set<OrdenTrabajoDto> ordenTrabajo;

    @ManyToMany(fetch = FetchType.EAGER)
    @Column(name = "vehiculo")
    private Set<VehiculoDto> vehiculo;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "empresa_id")
    private EmpresaDto empresa;

    public ClienteDto() {
    }

    public ClienteDto(long id, boolean habilitado, String nombre, String apellido, String rut, String direccion,
            String comuna,
            String ciudad, String telefono, String email) {
        super();
        this.id = id;
        this.habilitado = habilitado;
        this.nombre = nombre;
        this.apellido = apellido;
        this.rut = rut;
        this.direccion = direccion;
        this.comuna = comuna;
        this.ciudad = ciudad;
        this.telefono = telefono;
        this.email = email;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<OrdenTrabajoDto> getOrdenTrabajo() {
        return ordenTrabajo;
    }

    public void setOrdenTrabajo(Set<OrdenTrabajoDto> ordenTrabajo) {
        this.ordenTrabajo = ordenTrabajo;
    }

    public Set<VehiculoDto> getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Set<VehiculoDto> vehiculo) {
        this.vehiculo = vehiculo;
    }

    public EmpresaDto getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaDto empresa) {
        this.empresa = empresa;
    }
}
