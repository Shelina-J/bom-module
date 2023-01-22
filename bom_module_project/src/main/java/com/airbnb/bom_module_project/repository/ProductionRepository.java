package com.airbnb.bom_module_project.repository;

import org.springframework.stereotype.Repository;

import com.airbnb.bom_module_project.dto.Production;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceUnit;
import jakarta.transaction.Transactional;

@Repository
public class ProductionRepository {

	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	public Production addMaterialToProduction(Production newProd) {
		try {
			entityManager.persist(newProd);
			return newProd ;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

}
