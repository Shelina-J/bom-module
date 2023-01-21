package com.airbnb.bom_module_project.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="inward_material")
public class InwardMaterial implements Serializable {

	@Autowired
	private Supplier material;
	
	@Id
//	@OneToOne(cascade = CascadeType.ALL)
	@Column(name="material_id")
	private String materialId=material.getMaterialId(); 
	
	@Column(name="material_name")
	private String materialName; 
	
	@Column(name="quantity")
	private double quantity; 
	
	@Column(name="issued_quantity_for_prod")
	private double issuedQuantityForProduction;
	
	@Column(name="date_of_receipt")
	private LocalDate dateOfReceipt; 
	
	@Column(name="shelf_life")
	private double shelflife; 
	
	@OneToMany(mappedBy = "productionTransaction")
	@Column(name="prod_transaction")
	private List<Production> productionTransaction; 
	
//	@OneToOne(cascade = CascadeType.ALL)
	@Column(name="inward_return_transaction")
	private Production inwardReturnTransaction;
	
	@ManyToOne( cascade = CascadeType.ALL)
	@JoinColumn(name="supplier_id")
	private Supplier supplierId;

	public String getMaterialId() {
		return materialId;
	}

	public void setMaterialId(Supplier materialId) {
		this.materialId = materialId.getMaterialId();
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getIssuedQuantityForProduction() {
		return issuedQuantityForProduction;
	}

	public void setIssuedQuantityForProduction(double issuedQuantityForProduction) {
		this.issuedQuantityForProduction = issuedQuantityForProduction;
	}

	public LocalDate getDateOfReceipt() {
		return dateOfReceipt;
	}

	public void setDateOfReceipt(LocalDate dateOfReceipt) {
		this.dateOfReceipt = dateOfReceipt;
	}

	public double getShelflife() {
		return shelflife;
	}

	public void setShelflife(double shelflife) {
		this.shelflife = shelflife;
	}

	public List<Production> getProductionTransaction() {
		return productionTransaction;
	}

	public void setProductionTransaction(List<Production> productionTransaction) {
		this.productionTransaction = productionTransaction;
	}

	public Production getInwardReturnTransaction() {
		return inwardReturnTransaction;
	}

	public void setInwardReturnTransaction(Production inwardReturnTransaction) {
		this.inwardReturnTransaction = inwardReturnTransaction;
	}

	public Supplier getMaterial() {
		return material;
	}

	public void setMaterial(Supplier material) {
		this.material = material;
	}

	public Supplier getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Supplier supplierId) {
		this.supplierId = supplierId;
	}

	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}

	@Override
	public String toString() {
		return "InwardMaterial [material=" + material + ", materialId=" + materialId + ", materialName=" + materialName
				+ ", quantity=" + quantity + ", issuedQuantityForProduction=" + issuedQuantityForProduction
				+ ", dateOfReceipt=" + dateOfReceipt + ", shelflife=" + shelflife + ", productionTransaction="
				+ productionTransaction + ", inwardReturnTransaction=" + inwardReturnTransaction + ", supplierId="
				+ supplierId + "]";
	}

		
	

		
	
	
}
