package com.airbnb.bom_module_project.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.airbnb.bom_module_project.dto.Supplier;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceUnit;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
public class SupplierDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;

	@Transactional
	public Supplier addSupplier(Supplier newSupplier) {
		try {
			entityManager.persist(newSupplier);
			return newSupplier;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Transactional
	public Supplier findSupplierById(int supplierId) {
		try {
		Supplier foundSupplier=entityManager.find(Supplier.class, supplierId);
		return foundSupplier;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Transactional
	public Supplier deleteSupplier(Supplier supplierToBeDeleted) {
		try {
		entityManager.remove(supplierToBeDeleted);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return supplierToBeDeleted;
	}

	@Transactional
	public Supplier updateSupplier(Supplier updateSupplier) {
		Supplier updatedSupplier=entityManager.merge(updateSupplier);
		return updatedSupplier;
	}

	@Transactional
	public List<Supplier> viewAllSuppliers() {
		String ql="FROM Supplier";
		Query query=entityManager.createQuery(ql);
		return query.getResultList();
	}
}
