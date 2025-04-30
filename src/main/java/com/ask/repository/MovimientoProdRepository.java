package com.ask.repository;

import com.ask.model.MovimientoProd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimientoProdRepository extends JpaRepository<MovimientoProd, String> {
}