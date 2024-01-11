package edu.ynu.se.xiecheng.achitectureclass.service;

import edu.ynu.se.xiecheng.achitectureclass.common.service.LogicService;
import edu.ynu.se.xiecheng.achitectureclass.dao.CustomerDao;
import edu.ynu.se.xiecheng.achitectureclass.dao.LineItemDao;
import edu.ynu.se.xiecheng.achitectureclass.dao.OrderDao;
import edu.ynu.se.xiecheng.achitectureclass.dao.ShopItemDao;
import edu.ynu.se.xiecheng.achitectureclass.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;


@Service
public class CustomerService extends LogicService<CustomerDao, Customer,Long>{
    @Resource
    ShopItemDao shopItemDao;
    @Resource
    OrderDao orderDao;
    @Resource
    LineItemDao lineItemDao;
    public CustomerService(@Autowired CustomerDao dao) {
        super(dao);
    }
    public Customer getCustomerByName(Customer customer) {

        return dao.findByUsername(customer.getUsername());
    }
    /**
     * 添加购物车，同时产生lineItem和order(如果不存在的话)
     * @param customerId
     * @param shopItemId
     */
    public void selectItem(Long customerId,Long shopItemId){
        //获取添加购物车的shopItem的基础属性
        Optional<ShopItem> optionalShopItem = shopItemDao.findById(shopItemId);
        ShopItem shopItem = new ShopItem();
        if (optionalShopItem.isPresent()){
            shopItem = optionalShopItem.get();
        }
        //构建订单Order的查询条件
        Customer customer = new Customer();
        customer.setId(customerId);
        Shop shop = new Shop();
        shop = shopItem.getShop();
        Order order = new Order();
        order = orderDao.findByShopAndCustomerAndOrderStatus(shop,customer,"未支付");
        LineItem lineItem = new LineItem();
        lineItem.setShopItem(shopItem);
        //给lineItem赋值shopItem的基础属性
        lineItem.setLineItem();
        //如果在该店铺存在order则直接进入
        if (order != null){
         order.setTotalPrice(order.getTotalPrice()+lineItem.getTotalPrice());
         lineItem.setOrder(order);
        }else {
            //如果在这个店铺本身不存在order则进行创建
            Order order1 = new Order();
            order1.setCustomer(customer);
            order1.setShop(shop);
            order1.setOrderStatus("未支付");
            order1.setTotalPrice(lineItem.getTotalPrice());
            orderDao.save(order1);
            lineItem.setOrder(order1);
        }
        lineItemDao.save(lineItem);
    }
}
