package edu.ynu.se.xiecheng.achitectureclass.entity;

import edu.ynu.se.xiecheng.achitectureclass.common.entity.LogicEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;
import javax.persistence.*;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@Getter
@Setter
@Where(clause = "is_deleted = 0")
public class Shop extends LogicEntity {
    @Column
    private String shopName;
    @Column
    private String location;
    @Column
    private String description;

    // 多对一关系定义
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_id")
    @JsonIgnoreProperties(value = {"shops","business","category"})// 外键列的名称
    private Business business;

    // 门店与门店商品的一对多关系
    @OneToMany(mappedBy = "shop", fetch = FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value = {"shop", "business"})
    private Set<ShopItem> shopItems;  // 使用集合类型 Set<ShopItem>


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties({"shops","business", "category"})
    private Category category;
}
