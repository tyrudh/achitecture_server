package edu.ynu.se.xiecheng.achitectureclass.service;

import edu.ynu.se.xiecheng.achitectureclass.common.service.LogicService;
import edu.ynu.se.xiecheng.achitectureclass.dao.OrderDao;
import edu.ynu.se.xiecheng.achitectureclass.dao.ShopDao;
import edu.ynu.se.xiecheng.achitectureclass.dao.ShopItemDao;
import edu.ynu.se.xiecheng.achitectureclass.entity.Business;
import edu.ynu.se.xiecheng.achitectureclass.entity.Order;
import edu.ynu.se.xiecheng.achitectureclass.entity.Shop;
import edu.ynu.se.xiecheng.achitectureclass.entity.ShopItem;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShopService extends LogicService<ShopDao, Shop,Long> {

    @Resource
    ShopItemDao shopItemDao;

    @Resource
    ShopDao shopDao;

    @Resource
    OrderDao orderDao;

    public ShopService(ShopDao shopDao) {
        super(shopDao);
    }

    public void setShop(Shop shop){

    }

    public List<ShopItem> getShopItem(Long shopId){

        Shop shop = new Shop();
        shop.setId(shopId);
        List<ShopItem> shopItems = shopItemDao.findByShop(shop);

        return shopItems;
    }

    public List<Order> getBusinessOrder(Long businessId){
        Business business = new Business();
        business.setId(businessId);
        List<Shop> shopList = shopDao.findByBusiness(business);
        List<Order> orderList = new ArrayList<>();
        for (Shop shop:shopList
             ) {
            List<Order> byShop = orderDao.findByShop(shop);
            orderList.addAll(byShop);
        }
        return orderList;
    }

    public List<Order> getShopOrder(Long shopId){

        Shop shop = new Shop();
        shop.setId(shopId);
        List<Order> orderList = orderDao.findByShop(shop);
        return orderList;
    }

    /**
     * 确认已支付的订单，订单状态变为“已确认”
     */
    public void sureOrder(Long orderId){

        Optional<Order> order = orderDao.findById(orderId);

        if (order.isPresent()){
            Order order1 = order.get();
            order1.setOrderStatus("已确认");
            orderDao.save(order1);
        }

    }

    /**
     * 已退款
     */
    public void cancelPayment(Long orderId){

        Optional<Order> order = orderDao.findById(orderId);

        if (order.isPresent()){
            Order order1 = order.get();
            order1.setOrderStatus("已退款");
            orderDao.save(order1);
        }

    }
}
