package com.airbnb.bom_module_project.dto;

import java.io.Serializable;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "production_table")
public class Production implements Serializable{

	@Id
	@GenericGenerator(name = "ProductionGenerator", strategy = "com.airbnb.bom_module_project.dao.ProductionGenerator")
	@GeneratedValue(generator = "ProductionGenerator")
	@Column(name="product_id")
	private int productId; 
	
	@Column(name="product_name")
	private String productName;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="material_id")
	private InwardMaterial material;
	
	@Column(name="used_quantity")
	private double usedQuantity;
	
	@Column(name="stageof_prod")
	private List<String> stageOfProduction; 
	
	@Column(name="fg_transaction")
	private long fgTransaction; 
	
	@Column(name="production_transaction")
	private List<Long> productionTransaction; 
	
	@Column(name="produced_quantity")
	private double producedQuantity; 
	
	@Column(name="transferred_quantity")
	private double transferredQuantity;
	
	
	@Column(name="inward_return_transaction")
	private List<Long> inwardReturnTransaction;


	
	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public int getProductId() {
		return productId;
	}


	public void setProductId(int productId) {
		this.productId = productId;
	}


	public InwardMaterial getMaterial() {
		return material;
	}


	public void setMaterial(InwardMaterial material) {
		this.material = material;
	}


	public double getUsedQuantity() {
		return usedQuantity;
	}


	public void setUsedQuantity(double usedQuantity) {
		this.usedQuantity = usedQuantity;
	}


	public List<String> getStageOfProduction() {
		return stageOfProduction;
	}


	public void setStageOfProduction(List<String> stageOfProduction) {
		this.stageOfProduction = stageOfProduction;
	}


	public long getFgTransaction() {
		return fgTransaction;
	}


	public void setFgTransaction(long fgTransaction) {
		this.fgTransaction = fgTransaction;
	}


	public List<Long> getProductionTransaction() {
		return productionTransaction;
	}


	public void setProductionTransaction(List<Long> productionTransaction) {
		this.productionTransaction = productionTransaction;
	}


	public double getProducedQuantity() {
		return producedQuantity;
	}


	public void setProducedQuantity(double producedQuantity) {
		this.producedQuantity = producedQuantity;
	}


	public double getTransferredQuantity() {
		return transferredQuantity;
	}


	public void setTransferredQuantity(double transferredQuantity) {
		this.transferredQuantity = transferredQuantity;
	}


	public List<Long> getInwardReturnTransaction() {
		return inwardReturnTransaction;
	}


	public void setInwardReturnTransaction(List<Long> inwardReturnTransaction) {
		this.inwardReturnTransaction = inwardReturnTransaction;
	}

}
