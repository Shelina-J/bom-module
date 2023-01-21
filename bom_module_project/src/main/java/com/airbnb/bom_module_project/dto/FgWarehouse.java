package com.airbnb.bom_module_project.dto;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="fg_warehouse")
public class FgWarehouse implements Serializable {

	@Id
	@Column(name="fg_id")
	private int fgId; 
	
	@OneToOne(cascade = CascadeType.ALL)
	private Production productionId;
	
	@Column(name="fg_name")
	private String fgName; 
	
	@Column(name="accepted_quantity")
	private double acceptedQuantity;
	
	@Column(name="on_hand_quantity")
	private double onHandQuantity;
	
	@Column(name="stock_status")
	private String stockStatus; 
	
	@Column(name="material_status")
	private String materialStatus; 
	
	@Column(name="dispatch_transaction")
	private long dispatchTransaction;

	public int getFgId() {
		return fgId;
	}

	public void setFgId(int fgId) {
		this.fgId = fgId;
	}

	public Production getProductionId() {
		return productionId;
	}

	public void setProductionId(Production productionId) {
		this.productionId = productionId;
	}

	public String getFgName() {
		return fgName;
	}

	public void setFgName(String fgName) {
		this.fgName = fgName;
	}

	public double getAcceptedQuantity() {
		return acceptedQuantity;
	}

	public void setAcceptedQuantity(double acceptedQuantity) {
		this.acceptedQuantity = acceptedQuantity;
	}

	public double getOnHandQuantity() {
		return onHandQuantity;
	}

	public void setOnHandQuantity(double onHandQuantity) {
		this.onHandQuantity = onHandQuantity;
	}

	public String getStockStatus() {
		return stockStatus;
	}

	public void setStockStatus(String stockStatus) {
		this.stockStatus = stockStatus;
	}

	public String getMaterialStatus() {
		return materialStatus;
	}

	public void setMaterialStatus(String materialStatus) {
		this.materialStatus = materialStatus;
	}

	public long getDispatchTransaction() {
		return dispatchTransaction;
	}

	public void setDispatchTransaction(long dispatchTransaction) {
		this.dispatchTransaction = dispatchTransaction;
	}

	@Override
	public String toString() {
		return "FgWarehouse [fgId=" + fgId + ", productionId=" + productionId + ", fgName=" + fgName
				+ ", acceptedQuantity=" + acceptedQuantity + ", onHandQuantity=" + onHandQuantity + ", stockStatus="
				+ stockStatus + ", materialStatus=" + materialStatus + ", dispatchTransaction=" + dispatchTransaction
				+ "]";
	}

	
	
	
}
