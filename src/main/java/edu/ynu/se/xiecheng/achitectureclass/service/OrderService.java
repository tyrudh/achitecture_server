package edu.ynu.se.xiecheng.achitectureclass.service;
import edu.ynu.se.xiecheng.achitectureclass.common.service.LogicService;
import edu.ynu.se.xiecheng.achitectureclass.dao.ItemDao;
import edu.ynu.se.xiecheng.achitectureclass.dao.OrderDao;
import edu.ynu.se.xiecheng.achitectureclass.entity.Customer;
import edu.ynu.se.xiecheng.achitectureclass.entity.Item;
import edu.ynu.se.xiecheng.achitectureclass.entity.Order;
import edu.ynu.se.xiecheng.achitectureclass.entity.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class OrderService extends LogicService<OrderDao, Order,Long> {
    public OrderService(@Autowired OrderDao lr) {
        super(lr);
    }
    public List<Order> getOrderByOrderState(Long customerId, String orderState){
        Customer customer = new Customer();
        customer.setId(customerId);
        List<Order> orderList = dao.findByCustomerAndOrderStatus(customer,orderState);
        return orderList;
    }
    public void payOrder(Long orderId){
        Optional<Order> daoById = dao.findById(orderId);
        if (daoById.isPresent()){
            Order order = daoById.get();
            order.setOrderStatus("已支付");
            dao.save(order);
        }
    }
    public void cancelOrder(Long orderId){
        Optional<Order> daoById = dao.findById(orderId);
        if (daoById.isPresent()){
            Order order = daoById.get();
            order.setCancelOrder(1);
            dao.save(order);
        }
    }
    public void sureOrder(Long orderId){
        Optional<Order> daoById = dao.findById(orderId);

        if (daoById.isPresent()){
            Order order = daoById.get();
            order.setCancelOrder(0);
            dao.save(order);
        }
    }

    public void deleteOrder(Long orderId){
        Optional<Order> optionalOrder = dao.findById(orderId);
        if (optionalOrder.isPresent()){
            Order order = optionalOrder.get();
            order.setIsDeleted(1);
            dao.save(order);
        }

    }
    public List<Order> getCancelOrderByShop(Long customerId,Long shopId,String orderState){
        Customer customer = new Customer();
        customer.setId(customerId);
        Shop shop = new Shop();
        shop.setId(shopId);
        List<Order> orders = dao.findByCustomerAndOrderStatusAndShop(customer,orderState,shop);
        return orders;
    }
    public List<Order> getCustomerOrder(Long customerId){
        Customer customer = new Customer();
        customer.setId(customerId);
        List<Order> orderList = dao.findOrdersByCustomer(customer);
        return orderList;
    }

}
