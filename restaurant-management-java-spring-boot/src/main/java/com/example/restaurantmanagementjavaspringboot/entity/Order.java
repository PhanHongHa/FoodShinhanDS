package com.example.restaurantmanagementjavaspringboot.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "customer_note")
    private String customerNote;

    @Column(name = "total_price")
    private float totalPrice;

    @Column(name = "payment_status")
    private String paymentStatus;

    @Column(name = "payment_date")
    private String paymentDate;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "ship_address")
    private String shipAddress;

    private String note;
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", insertable = false , updatable = false)
    private Account account;

    @OneToMany(mappedBy = "order")
    private Set<Bill> billSet;

}
