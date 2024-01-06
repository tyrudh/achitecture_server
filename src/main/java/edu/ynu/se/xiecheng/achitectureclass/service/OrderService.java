package edu.ynu.se.xiecheng.achitectureclass.service;

import edu.ynu.se.xiecheng.achitectureclass.common.service.LogicService;
import edu.ynu.se.xiecheng.achitectureclass.dao.ItemDao;
import edu.ynu.se.xiecheng.achitectureclass.dao.OrderDao;
import edu.ynu.se.xiecheng.achitectureclass.entity.Item;
import edu.ynu.se.xiecheng.achitectureclass.entity.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderService extends LogicService<OrderDao, Order,Long> {
    public OrderService(OrderDao lr) {
        super(lr);
    }
}
