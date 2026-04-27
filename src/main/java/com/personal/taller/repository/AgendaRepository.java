package com.personal.taller.repository;

import com.personal.taller.dto.AgendaDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgendaRepository extends JpaRepository<AgendaDto, Long> {

    @Query(value = "select * from agenda a where a.habilitado = true", nativeQuery = true)
    List<AgendaDto> findAllActive();

    @Query(value = "select * from agenda a where a.cliente_id = :clienteId and a.habilitado = true", nativeQuery = true)
    List<AgendaDto> findByClienteId(Long clienteId);

    @Query(value = "select * from agenda a where a.vehiculo_id = :vehiculoId and a.habilitado = true", nativeQuery = true)
    List<AgendaDto> findByVehiculoId(Long vehiculoId);

    @Query(value = "select * from agenda a where a.estado = :estado and a.habilitado = true", nativeQuery = true)
    List<AgendaDto> findByEstado(String estado);

    @Query(value = "select * from agenda a where a.fecha_hora_reserva like concat('%', :fecha, '%') and a.habilitado = true", nativeQuery = true)
    List<AgendaDto> findByFechaReservaContains(String fecha);
}