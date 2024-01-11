package edu.ynu.se.xiecheng.achitectureclass.dao;
import edu.ynu.se.xiecheng.achitectureclass.common.dao.LogicDAO;
import edu.ynu.se.xiecheng.achitectureclass.entity.Customer;
import edu.ynu.se.xiecheng.achitectureclass.entity.Order;
import edu.ynu.se.xiecheng.achitectureclass.entity.Shop;

import java.util.List;
public interface OrderDao extends LogicDAO<Order,Long> {
        /**
         * 通过店铺以及用户来查找订单
         * @param shop
         * @param customer
         * @return
         */
        public Order findByShopAndCustomerAndOrderStatus(Shop shop, Customer customer,String statue);
        /**
         * 查找该店铺的订单
         * @param shop
         * @return
         */
        public List<Order> findByShop(Shop shop);
        /**
         * 找到没有所有没有付款的订单
         */
        public List<Order> findByCustomerAndOrderStatus(Customer customer, String orderStatus);
        /**
         * 查找对应的顾客在商店的各种支付状态的订单
         * @param customer
         * @param orderStatus
         * @param shop
         * @return
         */
        public List<Order> findByCustomerAndOrderStatusAndShop(Customer customer, String orderStatus,Shop shop);
        /**
         * 查找对应的用户的订单
         * @param customer
         * @return
         */
        public List<Order> findOrdersByCustomer(Customer customer);
}
