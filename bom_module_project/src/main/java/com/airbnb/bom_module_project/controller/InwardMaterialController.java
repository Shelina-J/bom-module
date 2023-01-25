package com.airbnb.bom_module_project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.airbnb.bom_module_project.dto.InwardMaterial;
import com.airbnb.bom_module_project.dto.Supplier;
import com.airbnb.bom_module_project.exception.IdNotFoundException;
import com.airbnb.bom_module_project.respose.ResponseStructure;
import com.airbnb.bom_module_project.service.InwardMaterialService;
import com.airbnb.bom_module_project.service.SupplierService;

@RestController
public class InwardMaterialController {

	@Autowired
	private InwardMaterialService inwardMaterialService;
	
	@Autowired
	private SupplierService supplierService;
	
//	public void searchMaterial(@PathVariable("supplierId") int supplierId, @PathVariable("materrialId") String materialId) {
//		List<Supplier> foundMaterial=inwardMaterialService.findMaterial(supplierId,materialId);
//		ResponseStructure<List<Supplier>> responseStructure= new ResponseStructure<>();
//		if (foundMaterial.isEmpty()) {
//			responseStructure.setStatus(HttpStatus.OK.value());
//			res
//		}
//	}
	
	@PostMapping("/material")
	public ResponseEntity<ResponseStructure<InwardMaterial>> addInwardMaterial(@RequestBody InwardMaterial inwardMaterial) {
		Supplier getSupplierId=inwardMaterial.getSupplierId();
		Supplier foundSupplier=supplierService.findSupplierById(getSupplierId.getSupplierId());
		ResponseStructure<InwardMaterial> responseStructure= new ResponseStructure<>();
		if (foundSupplier!=null) {
			InwardMaterial addedMaterial=inwardMaterialService.addInwardMaterial(inwardMaterial,foundSupplier);
			if (addedMaterial!=null) {
				responseStructure.setStatus(HttpStatus.CREATED.value());
				responseStructure.setMessage("Material Added to stock");
				responseStructure.setData(addedMaterial);
				
				ResponseEntity<ResponseStructure<InwardMaterial>> responseEntity= new ResponseEntity<ResponseStructure<InwardMaterial>>(responseStructure, HttpStatus.CREATED);
				return responseEntity;
			}else {
				responseStructure.setStatus(HttpStatus.CREATED.value());
				responseStructure.setMessage("Material Not Added to stock");
				responseStructure.setData(addedMaterial);
				
				ResponseEntity<ResponseStructure<InwardMaterial>> responseEntity= new ResponseEntity<ResponseStructure<InwardMaterial>>(responseStructure, HttpStatus.CREATED);
				return responseEntity;
			}
		}else {
			throw new IdNotFoundException("Supplier id not found");
		}
			
	}	
	
	@PatchMapping("/material-transaction")
	public ResponseEntity<ResponseStructure<InwardMaterial>> sendToProduction(@RequestBody InwardMaterial inwardMaterial) {
		Supplier getSupplierId=inwardMaterial.getSupplierId();
		Supplier foundSupplier=supplierService.findSupplierById(getSupplierId.getSupplierId());
		ResponseStructure<InwardMaterial> responseStructure= new ResponseStructure<>();
		if (foundSupplier!=null) {
			InwardMaterial foundInwardMaterial=inwardMaterialService.findMaterialById(inwardMaterial.getMaterialId());
			InwardMaterial transactionToProd=inwardMaterialService.sendToProduction(inwardMaterial,foundSupplier,foundInwardMaterial);
			
			if (transactionToProd!=null) {
				responseStructure.setStatus(HttpStatus.OK.value());
				responseStructure.setMessage("Transaction successfull"); 
				responseStructure.setData(transactionToProd);
				
				ResponseEntity<ResponseStructure<InwardMaterial>> responseEntity= new ResponseEntity<ResponseStructure<InwardMaterial>>(responseStructure, HttpStatus.OK);
				return responseEntity;
			}else {
				responseStructure.setStatus(HttpStatus.BAD_REQUEST.value());
				responseStructure.setMessage("Transaction Not successfull"); 
				responseStructure.setData(transactionToProd);
				
				ResponseEntity<ResponseStructure<InwardMaterial>> responseEntity= new ResponseEntity<ResponseStructure<InwardMaterial>>(responseStructure, HttpStatus.BAD_REQUEST);
				return responseEntity;
			}
		}else {
			throw new IdNotFoundException("Supplier not found");
		}
	}
	
	
	
	@GetMapping("/find-material-by-mrn/{mrn}")
	public ResponseEntity<ResponseStructure<List<InwardMaterial>>> findMaterialByProdMrn(@PathVariable("mrn") long mrn) {
		List<InwardMaterial> foundMaterial=inwardMaterialService.findMaterialByProdMrn(mrn);
		ResponseStructure<List<InwardMaterial>> responseStructure= new ResponseStructure<>();
		if (foundMaterial.isEmpty()) {
			responseStructure.setStatus(HttpStatus.OK.value()); 
			responseStructure.setMessage("Material transactions not found");
			responseStructure.setData(foundMaterial);
			
			ResponseEntity<ResponseStructure<List<InwardMaterial>>> responseEntity = new ResponseEntity<ResponseStructure<List<InwardMaterial>>>(responseStructure, HttpStatus.OK);
			return responseEntity;
		}else {
			responseStructure.setStatus(HttpStatus.OK.value()); 
			responseStructure.setMessage("Material transactions found");
			responseStructure.setData(foundMaterial);
			
			ResponseEntity<ResponseStructure<List<InwardMaterial>>> responseEntity = new ResponseEntity<ResponseStructure<List<InwardMaterial>>>(responseStructure, HttpStatus.OK);
			return responseEntity;
		}
	}
	
	
	@GetMapping("find-material/{id}")
	public ResponseEntity<ResponseStructure<InwardMaterial>> findMaterialById(@PathVariable ("mid") String mid) {
		InwardMaterial foundInwardMaterial=inwardMaterialService.findMaterialById(mid);
		ResponseStructure<InwardMaterial> responseStructure = new ResponseStructure<>();
		if (foundInwardMaterial!=null) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Material found");
			responseStructure.setData(foundInwardMaterial);
			
			ResponseEntity<ResponseStructure<InwardMaterial>> responseEntity= new ResponseEntity<ResponseStructure<InwardMaterial>>(responseStructure, HttpStatus.OK);
			return responseEntity;
		}else {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Material not found");
			responseStructure.setData(foundInwardMaterial);
			
			ResponseEntity<ResponseStructure<InwardMaterial>> responseEntity= new ResponseEntity<ResponseStructure<InwardMaterial>>(responseStructure, HttpStatus.OK);
			return responseEntity;
		}
	}
	
}
