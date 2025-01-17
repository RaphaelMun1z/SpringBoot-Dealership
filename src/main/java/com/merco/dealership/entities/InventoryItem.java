package com.merco.dealership.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tb_inventory")
public class InventoryItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	private LocalDate stockEntryDate;
	private LocalDate stockExitDate;
	private Double acquisitionPrice;
	private Double profitMargin;
	private String supplier;
	private String licensePlate;
	private String chassis;

	@JsonIgnore
	@OneToOne(mappedBy = "inventoryItem")
	private Sale sale;

	@NotNull(message = "Required field")
	@ManyToOne
	@JoinColumn(name = "vehicle_id")
	private Vehicle vehicle;

	@JsonIgnore
	@OneToMany(mappedBy = "id.inventoryItem", orphanRemoval = true)
	private Set<InventoryItemCommitment> inventoryItemCommitments = new HashSet<>();

	public InventoryItem() {
	}

	public InventoryItem(String id, @NotNull(message = "Required field") Vehicle vehicle, LocalDate stockEntryDate,
			LocalDate stockExitDate, Double acquisitionPrice, Double profitMargin, String supplier, String licensePlate,
			String chassis) {
		super();
		this.id = id;
		this.vehicle = vehicle;
		this.stockEntryDate = stockEntryDate;
		this.stockExitDate = stockExitDate;
		this.acquisitionPrice = acquisitionPrice;
		this.profitMargin = profitMargin;
		this.supplier = supplier;
		this.licensePlate = licensePlate;
		this.chassis = chassis;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public LocalDate getStockEntryDate() {
		return stockEntryDate;
	}

	public void setStockEntryDate(LocalDate stockEntryDate) {
		this.stockEntryDate = stockEntryDate;
	}

	public LocalDate getStockExitDate() {
		return stockExitDate;
	}

	public void setStockExitDate(LocalDate stockExitDate) {
		this.stockExitDate = stockExitDate;
	}

	public Double getAcquisitionPrice() {
		return acquisitionPrice;
	}

	public void setAcquisitionPrice(Double acquisitionPrice) {
		this.acquisitionPrice = acquisitionPrice;
	}

	public Double getProfitMargin() {
		return profitMargin;
	}

	public void setProfitMargin(Double profitMargin) {
		this.profitMargin = profitMargin;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public String getChassis() {
		return chassis;
	}

	public void setChassis(String chassis) {
		this.chassis = chassis;
	}

	public Set<InventoryItemCommitment> getInventoryItemCommitments() {
		return inventoryItemCommitments;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InventoryItem other = (InventoryItem) obj;
		return Objects.equals(id, other.id);
	}

}
