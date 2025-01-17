package com.merco.dealership.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_customers_address", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "street", "number", "district", "city", "state", "cep", "country" }) })
public class CustomerAddress extends Address implements Serializable {
	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy = "id.customerAddress", orphanRemoval = true)
	private Set<CustomerAddressMapping> customerAddressesMapping = new HashSet<>();

	public CustomerAddress() {

	}

	public CustomerAddress(String id, String street, int number, String district, String city, String state,
			String country, String cep, String complement) {
		super(id, street, number, district, city, state, country, cep, complement);
	}

	public Set<CustomerAddressMapping> getCustomerAddressesMapping() {
		return customerAddressesMapping;
	}

}
