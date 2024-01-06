package edu.ynu.se.xiecheng.achitectureclass.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.ynu.se.xiecheng.achitectureclass.common.entity.LogicEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Getter
@Setter
@Where(clause = "is_deleted = 0")
@Table(name = "orders")
public class Order extends LogicEntity {
    @Column
    private Double totalPrice;
    @Column
    private Date paymentDate;
    @Column
    private String customerNotes;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnoreProperties({"customer", "shop"})
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    @JsonIgnoreProperties({"customer", "shop"})
    private Shop shop;

    @OneToMany(mappedBy = "order",
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    @JsonIgnoreProperties("order")
    private List<LineItem> lineItems;

    private String orderStatus;

    public List<LineItem> getLineItems() {
        return lineItems;
    }
}
