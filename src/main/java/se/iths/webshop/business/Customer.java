package se.iths.webshop.business;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer {
    @Id
    @GeneratedValue
    @NotNull
    private Long id;

    private String adress;

    private String name;

    private String password;

    private String email;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<CustomerOrder> orders;

    public Customer(String name, String password) {
        this.name = name;
        this.password = password;
        this.orders = new ArrayList<>();
    }

    public Customer() {
    }

    public void addOrder(CustomerOrder corder){
        orders.add(corder);
    }

    public List<CustomerOrder> getCustomersOrders(){
        return orders;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String mail) {
        this.email = mail;
    }

    public String getPassword() {
        return password;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
