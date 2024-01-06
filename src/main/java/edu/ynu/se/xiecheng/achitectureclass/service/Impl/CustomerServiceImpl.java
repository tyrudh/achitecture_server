package edu.ynu.se.xiecheng.achitectureclass.service.Impl;

import edu.ynu.se.xiecheng.achitectureclass.common.service.LogicService;
import edu.ynu.se.xiecheng.achitectureclass.dao.CustomerDao;
import edu.ynu.se.xiecheng.achitectureclass.dao.LineItemDao;
import edu.ynu.se.xiecheng.achitectureclass.dao.OrderDao;
import edu.ynu.se.xiecheng.achitectureclass.dao.ShopItemDao;
import edu.ynu.se.xiecheng.achitectureclass.entity.*;
import edu.ynu.se.xiecheng.achitectureclass.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

import static edu.ynu.se.xiecheng.achitectureclass.entity.OrderStatus.UNPAID;

@Service
public class CustomerServiceImpl extends LogicService<CustomerDao, Customer,Long> implements CustomerService {
    @Resource
    ShopItemDao shopItemDao;

    @Resource
    OrderDao orderDao;

    @Resource
    LineItemDao lineItemDao;
    public CustomerServiceImpl(@Autowired CustomerDao dao) {
        super(dao);
    }
    @Override
    public Customer getCustomerByName(Customer customer) {

        return dao.findByUsername(customer.getUsername());
    }

    /**
     * 添加购物车，同时产生lineItem和order(如果不存在的话)
     * @param customerId
     * @param shopItemId
     */
    public void selectItem(Long customerId,Long shopItemId){

        Customer customer = new Customer();
        customer.setId(customerId);
        Optional<ShopItem> optionalShopItem = shopItemDao.findById(shopItemId);
        ShopItem shopItem = new ShopItem();
        if (optionalShopItem.isPresent()){
            shopItem = optionalShopItem.get();
        }
        Shop shop = new Shop();
        shop = shopItem.getShop();

        Order order = new Order();
        order = orderDao.findByShopAndCustomer(shop,customer);
        LineItem lineItem = new LineItem();
        lineItem.setShopItem(shopItem);
        if (order != null){
         lineItem.setOrder(order);
        }else {
            order.setCustomer(customer);
            order.setShop(shop);
            order.setOrderStatus("未支付");
            orderDao.save(order);
            lineItem.setOrder(order);
        }
        lineItem.setLineItem();
        lineItemDao.save(lineItem);
    }

    public void payOrder(Long orderId){
        Optional<Order> daoById = orderDao.findById(orderId);
        if (daoById.isPresent()){
            Order order = daoById.get();
            order.setOrderStatus("已支付");
            orderDao.save(order);
        }

        /**
         * 取消订单
         */

    }
}
