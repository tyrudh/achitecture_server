package edu.ynu.se.xiecheng.achitectureclass.entity;

import edu.ynu.se.xiecheng.achitectureclass.common.entity.LogicEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.DigestUtils;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Inheritance
@Where(clause = "is_deleted = 0")
@DiscriminatorColumn(name = "type")
public abstract class User extends LogicEntity {
    @Column
    protected String username;
    @Column
    protected String password;
    @Column
    private String image;
    @Column String token;
    /**
     * 创建所有User时，密码经过BCrypt加密
     * @param psd
     */
    public void setPassword(String psd){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(psd);
    }
    @Column(updatable = false, insertable = false)
    protected Integer type;
    @ManyToMany
    @JoinTable(
            name = "user_role_mapping",  // 定义映射表的表名
            joinColumns = @JoinColumn(name = "user_id"),  // 定义映射到 User 的外键
            inverseJoinColumns = @JoinColumn(name = "role_id")  // 定义映射到 UserRole 的外键
    )
    private Set<UserRole> roles = new HashSet<>();  // 存储 UserRole 实体的集合
}
