package com.personal.taller.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personal.taller.dto.RepuestosOrdenDto;

@Repository
public interface RepuestosOrdenRepository extends JpaRepository<RepuestosOrdenDto, Long> {

}
