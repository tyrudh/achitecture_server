package edu.ynu.se.xiecheng.achitectureclass.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@DiscriminatorValue("0")
public class Customer extends User{
    @Column
    private String customerName;
    @Column
    private String phone;
    @Column
    private String email;
    @Column
    private String address;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"customer","orders"})
    private List<Order> orders;

}
