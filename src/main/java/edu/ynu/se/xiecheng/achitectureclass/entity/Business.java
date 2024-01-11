package edu.ynu.se.xiecheng.achitectureclass.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
@Entity
@Getter
@Setter
@Where(clause = "is_deleted = 0")
@DiscriminatorValue("1")
public class Business extends User {
    @Column
    private String businessName;
    @Column
    private String ownerName;
    @Column
    private String email;
    @Column
    private String contactNumber;
    @Column
    private String address;
    @Column
    private String description;
    // 一对多关系定义
    @OneToMany(mappedBy = "business",
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"business"})
    private Set<Shop> shops;
    /**
     * 获取商家的店铺
     */
    private List<Shop> getBusinessShop(){
        List<Shop> shopList = new ArrayList<>(this.shops);
        return shopList;
    }
    /**
     * 新增商人的店铺
     * @param shop
     */
    private void setShop(Shop shop){
        this.shops.add(shop);
    }
}
