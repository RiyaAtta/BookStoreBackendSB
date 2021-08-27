package com.cg.bookstore.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.Valid;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orderstabledb")
@ApiModel(description = "Order")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "orderBooks")
public class Order {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(notes = "OrderId of the Customer")
	private Long id;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@ApiModelProperty(notes = "Date of the Order Placed")
	private LocalDate dateCreated;

	@Column(name = "status")
	@ApiModelProperty(notes = "Status of the order")
	private String status;

	@ApiModelProperty(notes = "List of the Books added")
	@OneToMany(mappedBy = "pk.order", cascade = CascadeType.ALL)
	@Valid
	private List<OrderBook> orderBooks = new ArrayList<>();

	@ApiModelProperty(notes = "Recipent Name")
	@Column(name = "recipentname")
	private String recipientName;
	
	@ApiModelProperty(notes = "Customer Email")
	@Column(name = "customeremail")
	private String customerEmail;

	@ApiModelProperty(notes = "Recipent Phone")
	@Column(name = "recipentphone")
	private String recipientPhone;

	@ApiModelProperty(notes = "Title of the Book")
	@Column(name = "shippingaddress")
	private String shippingAddress;

	@ApiModelProperty(notes = "Shipping address of the Customer")
	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	private Double totalPrice;

	@Transient
	public Double getTotalPrice() {
		List<OrderBook> orderBooks = getOrderBooks();
		this.totalPrice = 0D;
		for (OrderBook op : orderBooks) {
			totalPrice += op.getSubTotalPrice();
		}
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDate dateCreated) {
		this.dateCreated = dateCreated;
	}

	public List<OrderBook> getOrderBooks() {
		return orderBooks;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public void setOrderBooks(List<OrderBook> orderBooks) {
		this.orderBooks = orderBooks;
	}

	@Transient
	public int getNumberOfBooks() {
		return this.orderBooks.size();
	}


	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public String getRecipientPhone() {
		return recipientPhone;
	}

	public void setRecipientPhone(String recipientPhone) {
		this.recipientPhone = recipientPhone;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateCreated == null) ? 0 : dateCreated.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((orderBooks == null) ? 0 : orderBooks.hashCode());
		// result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (dateCreated == null) {
			if (other.dateCreated != null)
				return false;
		} else if (!dateCreated.equals(other.dateCreated))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (orderBooks == null) {
			if (other.orderBooks != null)
				return false;
		} else if (!orderBooks.equals(other.orderBooks))
			return false;

		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;

		return true;
	}
}
