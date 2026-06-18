package com.personal.taller.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@JsonIgnoreProperties({ "hibernateLazyInitializer" })
@Entity
@Table(name = "ordenTrabajo")
public class OrdenTrabajoDto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "numeroOrden", unique = true, nullable = false)
    private long numeroOrden;
    @Column(name = "habilitado")
    private boolean habilitado;
    @Column(name = "fechaIngreso")
    private String fechaIngreso;
    @Column(name = "rutCliente")
    private String rutCliente;
    @Column(name = "patenteVehiculo")
    private String patenteVehiculo;
    @Column(name = "codigo")
    private String codigo;
    @Column(name = "valorOt")
    private long valorOt;
    @Column(name = "estado")
    private String estado;
    @Column(name = "fechaCerrado")
    private String fechaCerrado;
    @Column(name = "kilometrajeVehiculoActual")
    private String kilometrajeVehiculoActual;

    @Column(name = "observaciones", length = 4000)
    private String observaciones;

    @Column(name = "nivelCombustible")
    private Integer nivelCombustible;

    @ManyToOne
    @JoinColumn(name = "vehiculo_patente", referencedColumnName = "patente")
    @JsonIgnoreProperties({"cliente", "ordenTrabajo", "hibernateLazyInitializer"})
    private VehiculoDto vehiculo;

    @OneToMany(mappedBy = "ordenTrabajo", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    @OrderBy("id ASC")
    @JsonManagedReference
    private List<RepuestosOrdenDto> repuestosOrden = new ArrayList<>();

    @OneToOne(mappedBy = "ordenTrabajo", fetch = FetchType.EAGER,
              cascade = CascadeType.ALL, orphanRemoval = true)
                @JsonManagedReference
    private DetalleRepuestosDto detalleRepuestos;

    @OneToMany(mappedBy = "ordenTrabajo", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    @OrderBy("id ASC")
    @JsonManagedReference
    private List<TrabajosTercerosDto> trabajosTerceros = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "cliente_rut", referencedColumnName = "rut")
    @JsonIgnoreProperties({"ordenTrabajo", "vehiculo", "hibernateLazyInitializer"})
    private ClienteDto cliente;


    @PrePersist
    public void prePersist() {
    }

    @PostPersist
    public void postPersist() {
        this.numeroOrden = this.id;
    }


    public OrdenTrabajoDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(long numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getRutCliente() {
        return rutCliente;
    }

    public void setRutCliente(String rutCliente) {
        this.rutCliente = rutCliente;
    }

    public String getPatenteVehiculo() {
        return patenteVehiculo;
    }

    public void setPatenteVehiculo(String patenteVehiculo) {
        this.patenteVehiculo = patenteVehiculo;
    }

    public DetalleRepuestosDto getDetalleRepuestos() {
        return detalleRepuestos;
    }

    public void setDetalleRepuestos(DetalleRepuestosDto detalleRepuestos) {
        this.detalleRepuestos = detalleRepuestos;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigoRepuestos(String codigoRepuestos) {
        this.codigo = codigoRepuestos;
    }

    public long getValorOt() {
        return valorOt;
    }

    public void setValorOt(long valorOt) {
        this.valorOt = valorOt;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public VehiculoDto getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(VehiculoDto vehiculo) {
        this.vehiculo = vehiculo;
    }

    public ClienteDto getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDto cliente) {
        this.cliente = cliente;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFechaCerrado() {
        return fechaCerrado;
    }

    public void setFechaCerrado(String fechaCerrado) {
        this.fechaCerrado = fechaCerrado;
    }

    public String getKilometrajeVehiculoActual() {
        return kilometrajeVehiculoActual;
    }

    public void setKilometrajeVehiculoActual(String kilometrajeVehiculoActual) {
        this.kilometrajeVehiculoActual = kilometrajeVehiculoActual;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Integer getNivelCombustible() {
        return nivelCombustible;
    }

    public void setNivelCombustible(Integer nivelCombustible) {
        this.nivelCombustible = nivelCombustible;
    }

    public List<RepuestosOrdenDto> getRepuestosOrden() {
        return repuestosOrden;
    }

    public void setRepuestosOrden(List<RepuestosOrdenDto> repuestosOrden) {
        this.repuestosOrden = repuestosOrden;
    }

    public List<TrabajosTercerosDto> getTrabajosTerceros() {
        return trabajosTerceros;
    }

    public void setTrabajosTerceros(List<TrabajosTercerosDto> trabajosTerceros) {
        this.trabajosTerceros = trabajosTerceros;
    }

}