package com.cg.bookstore.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "orderdetailsdb")
@ApiModel(description = "Order details")
public class OrderBook implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
    @JsonIgnore
	@ApiModelProperty(notes = "Composite key of the bookid and orderid")
    private OrderBookPK pk;

    @Column(nullable = false) 
	@ApiModelProperty(notes = "Quantity of each book")
    private Integer quantity;
    
	@ApiModelProperty(notes = "Subtotal of each Book")
    @Column(name="subtotal")
    private Double subTotal;

    public OrderBook() {
        super();
    }

    public OrderBook(Order order, Book book, Integer quantity) {
        pk = new OrderBookPK();
        pk.setOrder(order);
        pk.setBook(book);
        this.quantity = quantity;
    }

    @Transient
    public Book getBook() {
        return this.pk.getBook();
    }

    @Transient
    public Double getSubTotalPrice() {
    	this.subTotal=getBook().getPrice() * getQuantity();;
        return getBook().getPrice() * getQuantity();
    }
    public void setSubTotalPrice(Double subTotal) {
    	this.subTotal=subTotal;
    }

    public OrderBookPK getPk() {
        return pk;
    }

    public void setPk(OrderBookPK pk) {
        this.pk = pk;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((pk == null) ? 0 : pk.hashCode());

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        OrderBook other = (OrderBook) obj;
        if (pk == null) {
            if (other.pk != null) {
                return false;
            }
        } else if (!pk.equals(other.pk)) {
            return false;
        }

        return true;
    }
}