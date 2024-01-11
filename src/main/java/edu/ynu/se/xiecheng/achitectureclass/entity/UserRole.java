package edu.ynu.se.xiecheng.achitectureclass.entity;

import edu.ynu.se.xiecheng.achitectureclass.common.entity.LogicEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Where(clause = "is_deleted = 0")
public class UserRole extends LogicEntity {

    @Column
    private String roleName;
    @ManyToMany(mappedBy = "roles")  // "roles" 是 User 中定义的集合字段名
    private Set<User> users = new HashSet<>();  // 存储 User 实体的集合

}
