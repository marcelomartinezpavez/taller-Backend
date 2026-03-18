package com.personal.taller.repository;

import com.personal.taller.dto.DetalleRepuestosDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleRepuestoRepository extends JpaRepository<DetalleRepuestosDto, Long> {

}
