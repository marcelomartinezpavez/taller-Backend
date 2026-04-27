package com.personal.taller.repository;

import com.personal.taller.dto.OrdenTrabajoDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdenTrabajoRepository extends JpaRepository<OrdenTrabajoDto, Long> {

    @Query(value = "select * from ORDEN_TRABAJO ot where ot.numero_orden = :numeroOrden", nativeQuery = true)
    OrdenTrabajoDto findByNumeroOrden(Long numeroOrden);

    @Query(value = "select * from ORDEN_TRABAJO ot where ot.estado = :estado and ot.fecha_ingreso like concat('%', :fecha, '%')", nativeQuery = true)
    List<OrdenTrabajoDto> findByEstadoAndFechaContiene(String estado, String fecha);

    @Query(value = "select * from ORDEN_TRABAJO ot where ot.patente_vehiculo = :patente", nativeQuery = true)
    List<OrdenTrabajoDto> findByPatenteVehiculo(String patente);

    @Query(value = "select * from ORDEN_TRABAJO ot where ot.rut_cliente = :rutCliente", nativeQuery = true)
    List<OrdenTrabajoDto> findByRutCliente(String rutCliente);

    @Query(value = "select * from ORDEN_TRABAJO ot where ot.fecha_ingreso like concat('%', :fecha, '%')", nativeQuery = true)
    List<OrdenTrabajoDto> findByFechaContiene(String fecha);

}
