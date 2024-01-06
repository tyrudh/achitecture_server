package edu.ynu.se.xiecheng.achitectureclass.service;

import edu.ynu.se.xiecheng.achitectureclass.common.service.LogicService;
import edu.ynu.se.xiecheng.achitectureclass.dao.LineItemDao;
import edu.ynu.se.xiecheng.achitectureclass.dao.OrderDao;
import edu.ynu.se.xiecheng.achitectureclass.entity.LineItem;
import edu.ynu.se.xiecheng.achitectureclass.entity.Order;
import org.springframework.stereotype.Service;

@Service
public class LineItemService extends LogicService<LineItemDao, LineItem,Long> {
    public LineItemService(LineItemDao lr) {
        super(lr);
    }
}
