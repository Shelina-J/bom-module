package com.airbnb.bom_module_project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airbnb.bom_module_project.dao.SupplierDao;
import com.airbnb.bom_module_project.dto.Supplier;

@Service
public class SupplierService {

	@Autowired
	private SupplierDao supplierDao;
	
	public Supplier addSupplier(Supplier supplier) {
		Supplier newSupplier=new Supplier();
		
		newSupplier.setSupplierName(supplier.getSupplierName());
		newSupplier.setMaterialId(supplier.getMaterialId());
		return supplierDao.addSupplier(newSupplier);
	}

	public Supplier findSupplierById(int supplierId) {
		return supplierDao.findSupplierById(supplierId);
	}

	public Supplier deleteSupplier(Supplier supplierToBeDeleted) {
		return supplierDao.deleteSupplier(supplierToBeDeleted);
	}

	public Supplier updateSupplier(Supplier supplier, int id) {
		Supplier updateSupplier=findSupplierById(id);
		
		updateSupplier.setMaterialId(supplier.getMaterialId());
		
		return supplierDao.updateSupplier(updateSupplier);
	}

	public List<Supplier> viewAllSuppliers() {
		return supplierDao.viewAllSuppliers();
	}

}
