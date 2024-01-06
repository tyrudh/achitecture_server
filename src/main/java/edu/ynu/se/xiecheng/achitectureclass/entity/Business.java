package edu.ynu.se.xiecheng.achitectureclass.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.ynu.se.xiecheng.achitectureclass.common.entity.LogicEntity;
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
}
