package com.personal.taller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vehiculo_id", referencedColumnName = "id")
    private VehiculoDto vehiculo;

@OneToMany(mappedBy = "ordenTrabajo", fetch = FetchType.EAGER,
               cascade = CascadeType.ALL, orphanRemoval = true)
               @JsonManagedReference
    private Set<RepuestosOrdenDto> repuestosOrden = new HashSet<>();

    @OneToMany(mappedBy = "ordenTrabajo", fetch = FetchType.EAGER,
               cascade = CascadeType.ALL, orphanRemoval = true)
               @JsonManagedReference
    private Set<DetalleRepuestosDto> detalleRepuestos = new HashSet<>();

    @OneToMany(mappedBy = "ordenTrabajo", fetch = FetchType.EAGER,
               cascade = CascadeType.ALL, orphanRemoval = true)
               @JsonManagedReference
    private Set<TrabajosTercerosDto> trabajosTerceros = new HashSet<>();


    // @OneToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "vehiculo_id")
    // private VehiculoDto vehiculo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private ClienteDto cliente;


    @PrePersist
    public void prePersist() {
        // cuando se inserta, el id aún no está disponible
        // pero puedes asignar numeroOrden después en el servicio
    }

    @PostPersist
    public void postPersist() {
        // aquí ya tienes el id generado
        this.numeroOrden = this.id;
    }


    public OrdenTrabajoDto() {
    }

    public OrdenTrabajoDto(long id, boolean habilitado, long numeroOrden, String fechaIngreso, String rutCliente,
            String patenteVehiculo, Set<DetalleRepuestosDto> detalleRepuestos, String codigoRepuestos) {
        super();
        this.id = id;
        this.numeroOrden = numeroOrden;
        this.habilitado = habilitado;
        this.fechaIngreso = fechaIngreso;
        this.rutCliente = rutCliente;
        this.patenteVehiculo = patenteVehiculo;
        this.detalleRepuestos = detalleRepuestos;
        this.codigo = codigoRepuestos;
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

    public Set<DetalleRepuestosDto> getDetalleRepuestosDtos() {
        return detalleRepuestos;
    }

    public void setDetalle(Set<DetalleRepuestosDto> detalleRepuestos) {
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

    /**
     * @return String return the observaciones
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * @param observaciones the observaciones to set
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Integer getNivelCombustible() {
        return nivelCombustible;
    }

    public void setNivelCombustible(Integer nivelCombustible) {
        this.nivelCombustible = nivelCombustible;
    }

    /**
     * @return Set<RepuestosOrdenDto> return the repuestosOrden
     */
    public Set<RepuestosOrdenDto> getRepuestosOrden() {
        return repuestosOrden;
    }

    /**
     * @param repuestosOrden the repuestosOrden to set
     */
    public void setRepuestosOrden(Set<RepuestosOrdenDto> repuestosOrden) {
        this.repuestosOrden = repuestosOrden;
    }

    /**
     * @param detalleRepuestos the detalleRepuestos to set
     */
    public void setDetalleRepuestos(Set<DetalleRepuestosDto> detalleRepuestos) {
        this.detalleRepuestos = detalleRepuestos;
    }

    /**
     * @return Set<TrabajoTercerosDto> return the trabajoTerceros
     */
    public Set<TrabajosTercerosDto> getTrabajosTerceros() {
        return trabajosTerceros;
    }

    /**
     * @param trabajoTerceros the trabajoTerceros to set
     */
    public void setTrabajosTerceros(Set<TrabajosTercerosDto> trabajosTerceros) {
        this.trabajosTerceros = trabajosTerceros;
    }

}
