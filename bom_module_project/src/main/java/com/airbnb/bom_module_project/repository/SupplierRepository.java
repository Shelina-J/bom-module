package com.airbnb.bom_module_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.airbnb.bom_module_project.dto.Supplier;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public interface SupplierRepository  extends JpaRepository<Supplier, Integer>{

	
}
