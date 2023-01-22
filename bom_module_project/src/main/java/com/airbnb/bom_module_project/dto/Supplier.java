package com.airbnb.bom_module_project.dto;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name="supplier_table")
public class Supplier implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="supplier_id")
	private int supplierId; 
	
	
	@Column(name="supplier_name")
	private String supplierName; 
	
//	@Pattern(regexp = "^[a-zA-Z ]{5,20}$")
	@Column(name="material_id")
	private String materialId;
	
	@OneToMany(mappedBy = "materialId",cascade = CascadeType.ALL)
	@Column(name="material")
	private List<InwardMaterial> material;

	public int getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getMaterialId() {
		return materialId;
	}

	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}

	public List<InwardMaterial> getMaterial() {
		return material;
	}

	public void setMaterial(List<InwardMaterial> material) {
		this.material = material;
	}

	@Override
	public String toString() {
		return "Supplier [supplierId=" + supplierId + ", supplierName=" + supplierName + ", materialId=" + materialId
				+ ", material=" + material + "]";
	}

	
	
	
}
