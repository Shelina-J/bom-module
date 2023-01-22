package com.airbnb.bom_module_project.service;

import java.time.LocalDate;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airbnb.bom_module_project.dto.InwardMaterial;
import com.airbnb.bom_module_project.dto.Production;
import com.airbnb.bom_module_project.dto.Supplier;
import com.airbnb.bom_module_project.repository.InwardMaterialRepository;

@Service
public class InwardMaterialService {

	@Autowired
	private InwardMaterialRepository inwardMaterialRepository;
	
	public List<Supplier> findMaterial(int supplierId, String materialId) {
		return inwardMaterialRepository.findMaterial(supplierId,materialId);
		 
	}
	

	public InwardMaterial addInwardMaterial(InwardMaterial inwardMaterial, Supplier foundSupplier) {
		InwardMaterial newInwardMaterial= new InwardMaterial();
		newInwardMaterial.setMaterialId(foundSupplier.getMaterialId());
		newInwardMaterial.setMaterialName(inwardMaterial.getMaterialName());
		newInwardMaterial.setSupplierId(foundSupplier);
		newInwardMaterial.setQuantity(inwardMaterial.getQuantity());
		newInwardMaterial.setDateOfReceipt(inwardMaterial.getDateOfReceipt());
		LocalDate setDate=inwardMaterial.getDateOfReceipt();
		int days=LocalDate.now().getDayOfYear()-setDate.getDayOfYear();
		newInwardMaterial.setShelflife(days);
		return inwardMaterialRepository.addInwardMaterial(newInwardMaterial);
	}


	public InwardMaterial sendToProduction(InwardMaterial inwardMaterial, Supplier foundSupplier) {
		InwardMaterial toUpdateMaterial= new InwardMaterial();
		toUpdateMaterial.setIssuedQuantityForProduction(inwardMaterial.getIssuedQuantityForProduction());
		List<Long> listOfAllMaterial=inwardMaterial.getProductionTransaction();
		long mrn=332602010; 
		ListIterator<Long> li=listOfAllMaterial.listIterator(); 
		if (li.hasPrevious()) {
			long lastTrx=li.previous();
			long last=(mrn%10)+1;
			mrn+=last;
			listOfAllMaterial.add(mrn);
		}else {
			listOfAllMaterial.add(mrn);
		}
		toUpdateMaterial.setQuantity(inwardMaterial.getQuantity()-inwardMaterial.getIssuedQuantityForProduction());
		return inwardMaterialRepository.sendToProduction(toUpdateMaterial);
		
	}


	public List<InwardMaterial> findMaterialByProdMrn(long mrn) {
		return inwardMaterialRepository.findMaterialByProd(mrn);
	}

}
