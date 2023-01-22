package com.airbnb.bom_module_project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airbnb.bom_module_project.dto.Production;
import com.airbnb.bom_module_project.repository.ProductionRepository;

@Service
public class ProductionService {

	@Autowired
	private ProductionRepository productionRepository;
	
	public Production addMaterialToProduction(long mrn, Production production) {
		Production newProd= new Production();
		newProd.setProductName(production.getProductName());
		List<Long> trxFromInward=production.getInwardReturnTransaction();
		trxFromInward.add(mrn);
		newProd.setProductionTransaction(trxFromInward);
		newProd.setMaterial(production.getMaterial());
		newProd.setStageOfProduction(production.getStageOfProduction());
		newProd.setUsedQuantity(production.getUsedQuantity());
		newProd.setProducedQuantity(production.getProducedQuantity());
		return productionRepository.addMaterialToProduction(newProd);
	}
	
}
