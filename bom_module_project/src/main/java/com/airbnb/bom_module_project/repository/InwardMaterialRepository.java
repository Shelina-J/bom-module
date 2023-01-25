package com.airbnb.bom_module_project.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.airbnb.bom_module_project.dto.InwardMaterial;
import com.airbnb.bom_module_project.dto.Supplier;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceUnit;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
public class InwardMaterialRepository {

	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Transactional
	public List<Supplier> findMaterial(int supplierId, String materialId) {
		String ql="FROM InwardMaterial WHERE materialId =?1 AND supplierId =?2 ";
		Query query=entityManager.createQuery(ql);
		query.setParameter(1, supplierId);
		query.setParameter(2, materialId);
		
		return query.getResultList();
		
	}

	
	@Transactional
	public InwardMaterial addInwardMaterial(InwardMaterial newInwardMaterial) {
		try {
		entityManager.persist(newInwardMaterial);
		return newInwardMaterial;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Transactional
	public InwardMaterial sendToProduction(InwardMaterial toUpdateMaterial) {
		try {
			InwardMaterial updatedMaterial=entityManager.merge(toUpdateMaterial);
			return updatedMaterial;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Transactional
	public List<InwardMaterial> findMaterialByProd(long mrn) {
		try {
			String ql="FROM InwardMaterial WHERE productionTransaction ?1";
			Query query= entityManager.createQuery(ql);
			return query.getResultList();
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	@Transactional
	public InwardMaterial findMaterialById(String mid) {
		try {
			return entityManager.find(InwardMaterial.class, mid);
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}	
	
	
//	@Query
//	public InwardMaterial findMaterialBySupplierAndMaterial(int supplierId, String materialId) {
//		String query=""
//	}

}
