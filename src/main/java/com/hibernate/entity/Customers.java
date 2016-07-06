package com.hibernate.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * A POJO class represents the customer entity.
 * @author WYou
 * @since 16.5.0
 */
@Entity
@Table(name = "Customers")
public class Customers {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_ID")
	private int customerID;
	@Column(name = "name", length = 30)
	private String customerName;
	@Column(name = "contact", length = 30)
	private String contactInformation;
	@Column(name = "Address", length = 100)
	private String address;
	@OneToMany(fetch = FetchType.LAZY, targetEntity = Orders.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_ID", referencedColumnName = "customer_ID")
	private Set<Orders> order;

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getContactInformation() {
		return contactInformation;
	}

	public void setContactInformation(String contactInformation) {
		this.contactInformation = contactInformation;
	}

	public Set<Orders> getOrder() {
		return order;
	}

	public void setOrder(Set<Orders> order) {
		this.order = order;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
