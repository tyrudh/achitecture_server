package edu.ynu.se.xiecheng.achitectureclass.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.ynu.se.xiecheng.achitectureclass.common.entity.LogicEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
@Getter
@Setter
@Where(clause = "is_deleted = 0")
public class LineItem extends LogicEntity {
    @Column
    private Integer quantity;
    @Column
    private Double price;
    @Column
    private Double totalPrice;
    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonIgnoreProperties("lineItems") // 避免序列化时的循环引用
    private Order order;
    @ManyToOne
    @JoinColumn(name = "shopItem_id")
    @JsonIgnoreProperties("lineItems") // 避免序列化时的循环引用
    private ShopItem shopItem;
    /**
     * 设置lineItem的基础属性
     */
    public void setLineItem(){
        this.price = shopItem.getPrice();
        this.quantity = 1;
        this.totalPrice = this.quantity * this.price;
    }
    /**
     * 设置lineItem归属的Order
     */
    public void setOrder(Order order){
        this.order = order;
    }
}

