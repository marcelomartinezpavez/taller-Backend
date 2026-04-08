package com.personal.taller.dto;

//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

//@Document("proveedores")
@Entity
@Table(name = "proveedores")
public class ProveedorDto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "habilitado")
    private boolean habilitado;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "rut")
    private String rut;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "comuna")
    private String comuna;
    @Column(name = "cuidad")
    private String ciudad;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "email")
    private String email;

/*    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id")
    private EmpresaDto empresa;*/

    @ManyToMany(fetch = FetchType.EAGER)
    @Column(name = "repuesto")
    private Set<RepuestoDto> repuesto;


    public ProveedorDto(){}

    public ProveedorDto(long id, boolean habilitado, String nombre, String apellido, String direccion, String comuna,
                      String ciudad, String telefono, String email) {
        super();
        this.id = id;
        this.habilitado = habilitado;
        this.nombre = nombre;
        this.apellido = apellido;
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

    public Set<RepuestoDto> getRepuesto() {
        return repuesto;
    }

    public void setRepuesto(Set<RepuestoDto> repuesto) {
        this.repuesto = repuesto;
    }
}
