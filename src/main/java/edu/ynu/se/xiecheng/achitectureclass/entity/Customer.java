package edu.ynu.se.xiecheng.achitectureclass.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;
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
    /**
     * 获取用户的未支付订单，以及获取用户的已支付
     */
//    public List<Order> getOrderUnPay(){
//
//        List<Order> orderList = orders.stream()
//                .filter(order -> order.getOrderStatus()=="未支付")
//                .collect(Collectors.toList());
//
//        return orderList;
//    }
//    public List<Order> getOrderPay(){
//
//        List<Order> orderList = orders.stream()
//                .filter(order -> order.getOrderStatus()=="已支付")
//                .collect(Collectors.toList());
//        return orderList;
//    }
//    /**
//     * 获取该用户的Order
//     */
//    public List<Order> getCustomerOrder(){
//        return this.orders;
//    }

}
