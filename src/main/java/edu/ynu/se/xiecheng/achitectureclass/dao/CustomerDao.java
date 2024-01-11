package edu.ynu.se.xiecheng.achitectureclass.dao;
import edu.ynu.se.xiecheng.achitectureclass.common.dao.LogicDAO;
import edu.ynu.se.xiecheng.achitectureclass.entity.Customer;
public interface CustomerDao extends LogicDAO<Customer,Long> {
    /**
     * 通过用户名查找用户
     * @param username
     * @return
     */
    Customer findByUsername(String username);
}
