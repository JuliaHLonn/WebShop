package se.iths.webshop.business;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class OrderLine {

    @ManyToOne
    Product product;
    int quantity;

    double subTotal;


    @Id
    @GeneratedValue
    @NotNull
    private Long id;

    public OrderLine(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public OrderLine() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "OrderLine{" +
                "product=" + product +
                ", quantity=" + quantity +
                ", id=" + id +
                '}';
    }


    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;

    }
}
