package se.iths.webshop.business;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CustomerOrder {

    @GeneratedValue
    @Id
    @NotNull
    private Long id;

    private LocalDateTime timeOfOrder;

    boolean delivered;

    @ManyToOne
    Customer customer;

    @OneToMany(cascade = CascadeType.ALL)
    List<OrderLine> orderContent;

    public CustomerOrder(LocalDateTime timeOfOrder, boolean delivered, Customer customer, ArrayList<OrderLine> orderContent) {
        this.timeOfOrder = timeOfOrder;
        this.delivered = delivered;
        this.customer = customer;
        this.orderContent = orderContent;
    }

    public CustomerOrder(Long id, LocalDateTime timeOfOrder, boolean delivered, Customer customer) {
        this.id = id;
        this.timeOfOrder = timeOfOrder;
        this.delivered = delivered;
        this.customer = customer;
        this.orderContent = new ArrayList<>();
    }

    public CustomerOrder() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    public void setTimeOfOrder(LocalDateTime timeOfOrder) {
        this.timeOfOrder = timeOfOrder;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setOrderContent(ArrayList<OrderLine> orderContent) {
        this.orderContent = orderContent;
    }

    public LocalDateTime getTimeOfOrder() {
        return timeOfOrder;
    }
}

