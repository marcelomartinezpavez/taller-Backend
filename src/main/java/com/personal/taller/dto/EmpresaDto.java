package com.personal.taller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@JsonIgnoreProperties({ "hibernateLazyInitializer" })
@Entity
@Table(name = "empresa")
public class EmpresaDto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "rut")
    private String rut;

    // @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval =
    // true)
    // @JoinColumn(name = "users_id")
    // private UsersDto users;

    @OneToMany(fetch = FetchType.EAGER)
    @Column(name = "proveedor")
    private Set<ProveedorDto> proveedor;

    @OneToMany(fetch = FetchType.EAGER)
    @Column(name = "repuesto")
    private Set<RepuestoDto> repuesto;

    @OneToMany(fetch = FetchType.EAGER)
    @Column(name = "ordenTrabajo")
    private Set<OrdenTrabajoDto> ordenTrabajo;

    @OneToMany(fetch = FetchType.EAGER)
    @Column(name = "clientes")
    private Set<ClienteDto> clientes;

    public EmpresaDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    /*
     * public UsersDto getUsers() {
     * return users;
     * }
     * 
     * public void setUsers(UsersDto users) {
     * this.users = users;
     * }
     */
}
