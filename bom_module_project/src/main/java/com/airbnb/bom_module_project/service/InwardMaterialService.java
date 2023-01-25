package com.airbnb.bom_module_project.service;

import java.time.LocalDate;
import java.util.ArrayList;
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
		System.out.println(inwardMaterial.getDateOfReceipt());
		newInwardMaterial.setDateOfReceipt(inwardMaterial.getDateOfReceipt());
		LocalDate setDate=inwardMaterial.getDateOfReceipt();
		int days=LocalDate.now().getDayOfYear()-setDate.getDayOfYear();
		System.out.println("The days is "+days);
		int shelfLife=45-days;
		newInwardMaterial.setShelflife(shelfLife);
		return inwardMaterialRepository.addInwardMaterial(newInwardMaterial);
	}


	public InwardMaterial sendToProduction(InwardMaterial inwardMaterial, Supplier foundSupplier, InwardMaterial foundInwardMaterial) {
		InwardMaterial toUpdateMaterial= new InwardMaterial();
		toUpdateMaterial.setSupplierId(foundSupplier);
		toUpdateMaterial.setMaterialId(foundInwardMaterial.getMaterialId());
		toUpdateMaterial.setMaterialName(foundInwardMaterial.getMaterialName());
		toUpdateMaterial.setDateOfReceipt(foundInwardMaterial.getDateOfReceipt());
		toUpdateMaterial.setShelflife(foundInwardMaterial.getShelflife());
		toUpdateMaterial.setIssuedQuantityForProduction(inwardMaterial.getIssuedQuantityForProduction());
		List<Long> listOfAllMaterial=foundInwardMaterial.getProductionTransaction();	
//		System.out.println(listOfAllMaterial+" initial list");
		long mrn=332602010; 
		
		if (listOfAllMaterial== null) {
			listOfAllMaterial= new ArrayList<>();
				listOfAllMaterial.add(mrn);
		}
		
		if (listOfAllMaterial.isEmpty()) {
			
		}else {
				ListIterator<Long> li=listOfAllMaterial.listIterator(listOfAllMaterial.size()); 
				System.out.println(li);
//				li.next();
				System.out.println(li.hasPrevious());
				if (li.hasPrevious()) {
					long lastTrx=li.previous();
					System.out.println(lastTrx);
					long last=(lastTrx%10)+1;
//					System.out.println(last);
					mrn+=last;
//					System.out.println(mrn+" new mrn");
					listOfAllMaterial.add(mrn);
			}
		}
		toUpdateMaterial.setProductionTransaction(listOfAllMaterial);
//		System.out.println(toUpdateMaterial.getProductionTransaction()+" list of mrns");
		System.out.println(toUpdateMaterial.getProductionTransaction());
//		System.out.println(toUpdateMaterial.getQuantity()+" before");
//		System.out.println(inwardMaterial.getIssuedQuantityForProduction()+" after");
		toUpdateMaterial.setQuantity(inwardMaterial.getQuantity()-inwardMaterial.getIssuedQuantityForProduction());
//		System.out.println(toUpdateMaterial.getQuantity()+" after issue");
		return inwardMaterialRepository.sendToProduction(toUpdateMaterial);
		
	}


	public List<InwardMaterial> findMaterialByProdMrn(long mrn) {
		return inwardMaterialRepository.findMaterialByProd(mrn);
	}


	public InwardMaterial findMaterialById(String mid) {
		return inwardMaterialRepository.findMaterialById(mid);
	}

}
