package com.airbnb.bom_module_project.dto;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "production_table")
public class Production implements Serializable{

	@Id
	private int productId; 
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="material_id")
	private InwardMaterial material;
	
	private double usedQuantity;
	
	private List<String> stageOfProduction; 
	
	private long fgTransaction; 
	
	@ManyToOne
	@JoinColumn(name="production_transaction")
	private InwardMaterial productionTransaction; 
	
	@Column(name="produced_quantity")
	private double producedQuantity; 
	
	
	@Column(name="transferred_quantity")
	private double transferredQuantity;
	
	
	@Column(name="inward_return_transaction")
	private long inwardReturnTransaction;


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


	public InwardMaterial getProductionTransaction() {
		return productionTransaction;
	}


	public void setProductionTransaction(InwardMaterial productionTransaction) {
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


	public long getInwardReturnTransaction() {
		return inwardReturnTransaction;
	}


	public void setInwardReturnTransaction(long inwardReturnTransaction) {
		this.inwardReturnTransaction = inwardReturnTransaction;
	}

	
	
	
	
}
