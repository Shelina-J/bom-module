package com.airbnb.bom_module_project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.airbnb.bom_module_project.dto.Supplier;
import com.airbnb.bom_module_project.exception.IdNotFoundException;
import com.airbnb.bom_module_project.respose.ResponseStructure;
import com.airbnb.bom_module_project.service.SupplierService;

@RestController
public class SupplierController {
	
	@Autowired
	private SupplierService supplierService;

	@PostMapping("/supplier")
	public ResponseEntity<ResponseStructure<Supplier>> addSupplier(@RequestBody Supplier supplier) {
		Supplier addedSupplier=supplierService.addSupplier(supplier);
		ResponseStructure<Supplier> responseStructure= new ResponseStructure<>();
		if (addedSupplier!=null) {
			responseStructure.setStatus(HttpStatus.CREATED.value());
			responseStructure.setMessage("Supplier added");
			responseStructure.setData(addedSupplier);
			ResponseEntity<ResponseStructure<Supplier>> responseEntity = new ResponseEntity<ResponseStructure<Supplier>>(responseStructure, HttpStatus.CREATED);
			return responseEntity;
		}else {
			responseStructure.setStatus(HttpStatus.BAD_REQUEST.value());
			responseStructure.setMessage("Supplier added");
			responseStructure.setData(addedSupplier);
			
			ResponseEntity<ResponseStructure<Supplier>> responseEntity = new ResponseEntity<ResponseStructure<Supplier>>(responseStructure, HttpStatus.BAD_REQUEST);
			return responseEntity;
		}
	}
	
	@GetMapping("/find-supplier/{supplierId}")
	public ResponseEntity<ResponseStructure<Supplier>> findSupplier(@PathVariable("supplierId") int supplierId) {
		Supplier foundSupplier=supplierService.findSupplierById(supplierId);
		ResponseStructure<Supplier> responseStructure = new ResponseStructure<>();
		if (foundSupplier!=null) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("data found");
			responseStructure.setData(foundSupplier);
			
			ResponseEntity<ResponseStructure<Supplier>> responseEntity= new ResponseEntity<ResponseStructure<Supplier>>(responseStructure, HttpStatus.OK);
			return responseEntity;
		}else {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("data not found");
			responseStructure.setData(foundSupplier);
			
			ResponseEntity<ResponseStructure<Supplier>> responseEntity= new ResponseEntity<ResponseStructure<Supplier>>(responseStructure, HttpStatus.OK);
			return responseEntity;
		}
	}
	
	@GetMapping("/remove-supplier/{supplierId}")
	public ResponseEntity<ResponseStructure<Supplier>> removeSupplier(@PathVariable("supplierId") int supplierId) {
		Supplier supplierToBeDeleted=supplierService.findSupplierById(supplierId);
		ResponseStructure<Supplier> responseStructure= new ResponseStructure<>();
		if (supplierToBeDeleted!=null) {
			Supplier deletedSupplier=supplierService.deleteSupplier(supplierToBeDeleted);
			if (deletedSupplier==null) {
				responseStructure.setStatus(HttpStatus.OK.value());
				responseStructure.setMessage("Supplier deleted");
				responseStructure.setData(deletedSupplier);
				
				ResponseEntity<ResponseStructure<Supplier>> responseEntity= new ResponseEntity<ResponseStructure<Supplier>>(responseStructure, HttpStatus.OK);
				return responseEntity;
			}else {
				responseStructure.setStatus(HttpStatus.OK.value());
				responseStructure.setMessage("Supplier not deleted");
				responseStructure.setData(deletedSupplier);
				
				ResponseEntity<ResponseStructure<Supplier>> responseEntity= new ResponseEntity<ResponseStructure<Supplier>>(responseStructure, HttpStatus.OK);
				return responseEntity;
			}
		}else {
			throw new IdNotFoundException("supplier id not found");
		}
	}
	
	@PatchMapping("/update-supplier/{id}")
	public ResponseEntity<ResponseStructure<Supplier>> updateSupplier(@RequestBody Supplier supplier, @PathVariable("id") int id) {
		Supplier updatedSupplier=supplierService.updateSupplier(supplier,id);
		ResponseStructure<Supplier> responseStructure= new ResponseStructure<>();
		if (updatedSupplier!=null) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Supplier deleted");
			responseStructure.setData(updatedSupplier);
			
			ResponseEntity<ResponseStructure<Supplier>> responseEntity= new ResponseEntity<ResponseStructure<Supplier>>(responseStructure, HttpStatus.OK);
			return responseEntity;
			
		}else {
			throw new IdNotFoundException("supplier id not found");
		}
		
	}
	
	@GetMapping("/all-suppliers")
	public ResponseEntity<ResponseStructure<List<Supplier>>> viewAllSupplier() {
		List<Supplier> allSuppliers=supplierService.viewAllSuppliers();
		ResponseStructure<List<Supplier>> responseStructure= new ResponseStructure<>();
		if (allSuppliers.isEmpty()) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("No suppliers found");
			responseStructure.setData(allSuppliers);
			
			ResponseEntity<ResponseStructure<List<Supplier>>> responseEntity= new ResponseEntity<ResponseStructure<List<Supplier>>>(responseStructure, HttpStatus.OK);
			return responseEntity;
		}else {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("suppliers found");
			responseStructure.setData(allSuppliers);
			
			ResponseEntity<ResponseStructure<List<Supplier>>> responseEntity= new ResponseEntity<ResponseStructure<List<Supplier>>>(responseStructure, HttpStatus.OK);
			return responseEntity;
		}
	}

}
