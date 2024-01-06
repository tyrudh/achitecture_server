package edu.ynu.se.xiecheng.achitectureclass.dao;

import edu.ynu.se.xiecheng.achitectureclass.common.dao.LogicDAO;
import edu.ynu.se.xiecheng.achitectureclass.entity.Customer;
import edu.ynu.se.xiecheng.achitectureclass.entity.Order;
import edu.ynu.se.xiecheng.achitectureclass.entity.Shop;

import java.util.List;

public interface OrderDao extends LogicDAO<Order,Long> {

        public Order findByShopAndCustomer(Shop shop, Customer customer);
        public List<Order> findByShop(Shop shop);

}
