package edu.ynu.se.xiecheng.achitectureclass.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.ynu.se.xiecheng.achitectureclass.common.entity.LogicEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Where(clause = "is_deleted = 0")
public class ShopItem extends LogicEntity {
    // 门店商品与门店的多对一关系
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    @JsonIgnoreProperties(value = {"shopItems", "business"})
    private Shop shop;

    // 门店商品与商品的多对一关系
    @ManyToOne
    @JoinColumn(name = "item_id")
    @JsonIgnoreProperties(value = {"shopItems", "business"})
    private Item item;

    @Column(nullable = false)
    private int stock; // 库存数量

    @Column(nullable = false)
    private Boolean isAvailable; // 是否上架
    @Column(nullable = false)
    private Double price;


    public void up(){
        if (!this.isAvailable){
            this.isAvailable = true;
        }
    }

    public void down(){
        if (this.isAvailable){
            this.isAvailable = false;
        }
    }
}
