package edu.ynu.se.xiecheng.achitectureclass.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.ynu.se.xiecheng.achitectureclass.common.entity.LogicEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;
import javax.persistence.*;
import java.util.Set;
@Entity
@Getter
@Setter
@Where(clause = "is_deleted = 0")
public class Item extends LogicEntity {
    @Column(nullable = false)
    private String name;
    @Column
    private String description;
    @Column(nullable = false)
    private double price;
    @Column
    private String image;
    @Column(nullable = false)
    private Boolean isOnSale; // 使用包装类 Boolean
    // 商品与门店商品的一对多关系
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value = {"item"})
    private Set<ShopItem> shopItems;
    // 商品与商家的多对一关系
    @ManyToOne
    @JoinColumn(name = "business_id")
    @JsonIgnoreProperties(value = {"item"})
    private Business business;
    public void up(){
        if (!this.isOnSale){
            this.isOnSale = true;
        }
    }
    public void down(){
        if (this.isOnSale){
            this.isOnSale = false;
        }
    }
    /**
     * 设置商品的商家
     */
    public void setBusiness(Business business) {
        this.business = business;
    }
}
