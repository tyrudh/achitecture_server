package edu.ynu.se.xiecheng.achitectureclass;

import edu.ynu.se.xiecheng.achitectureclass.dao.OrderDao;
import edu.ynu.se.xiecheng.achitectureclass.dao.ShopItemDao;
import edu.ynu.se.xiecheng.achitectureclass.entity.Order;
import edu.ynu.se.xiecheng.achitectureclass.entity.Shop;
import edu.ynu.se.xiecheng.achitectureclass.entity.ShopItem;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class AchitectureClassApplicationTests {

    @Resource
    ShopItemDao shopItemDao;
    @Resource
    OrderDao orderDao;
    @Test
    void contextLoads() {

        Shop shop = new Shop();
        shop.setId(2L);
        List<ShopItem> shopItem = shopItemDao.findByShop(shop);
        System.out.println(shopItem);
    }
    @Test
    void testOrder(){


        List<Order> orderList = orderDao.findAll();

        System.out.println(orderList);

        System.out.println("");
    }

}
