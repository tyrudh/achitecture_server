package edu.ynu.se.xiecheng.achitectureclass.dto;
import edu.ynu.se.xiecheng.achitectureclass.entity.Business;
import edu.ynu.se.xiecheng.achitectureclass.entity.Customer;
import edu.ynu.se.xiecheng.achitectureclass.entity.User;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class UserDto {
    private Long id;
    private String username;
    private String token;
    private Integer type;
    // 从实体创建DTO的构造器或工厂方法
    public UserDto(Customer user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.token = user.getToken();
    }
    public UserDto(Business user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.token = user.getToken();
    }
    public UserDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.token = user.getToken();
        this.type = user.getType();
    }
}
