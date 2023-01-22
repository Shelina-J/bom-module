package com.airbnb.bom_module_project.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.airbnb.bom_module_project.dto.InwardMaterial;
import com.airbnb.bom_module_project.dto.Production;
import com.airbnb.bom_module_project.respose.ResponseStructure;
import com.airbnb.bom_module_project.service.InwardMaterialService;
import com.airbnb.bom_module_project.service.ProductionService;

@RestController
public class ProductionController {

	@Autowired
	private InwardMaterialService inwardMaterialService;
	
	@Autowired
	private ProductionService productionService;
	
	@PostMapping("/production/{mrn}")
	public void addMaterialToProduction(@PathVariable("mrn") long mrn,@RequestBody Production production) {
		List<InwardMaterial> foundMaterial=inwardMaterialService.findMaterialByProdMrn(mrn);
		if (foundMaterial.isEmpty()) {
			
		}else {
			Production addedProdcut=productionService.addMaterialToProduction(mrn,production);
		}
	}
}
