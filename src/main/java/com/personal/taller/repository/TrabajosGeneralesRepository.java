package com.personal.taller.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personal.taller.dto.TrabajosGeneralesDto;

@Repository
public interface TrabajosGeneralesRepository extends JpaRepository<TrabajosGeneralesDto, Long> {

}
