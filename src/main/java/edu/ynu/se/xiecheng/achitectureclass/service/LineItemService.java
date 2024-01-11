package edu.ynu.se.xiecheng.achitectureclass.service;
import edu.ynu.se.xiecheng.achitectureclass.common.service.LogicService;
import edu.ynu.se.xiecheng.achitectureclass.dao.LineItemDao;
import edu.ynu.se.xiecheng.achitectureclass.entity.LineItem;
import edu.ynu.se.xiecheng.achitectureclass.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class LineItemService extends LogicService<LineItemDao, LineItem,Long> {
    public LineItemService(@Autowired LineItemDao lr) {
        super(lr);
    }
    /**
     * 进行对应的lineItem的加减
     * @param lineItemId
     * @param type 为1是进行增加，为0是进行减少
     */
    public void plusLineItem(Long lineItemId,Integer type){
        Optional<LineItem> lineItem = dao.findById(lineItemId);
        if (lineItem.isPresent()){
            LineItem lineItem1 = lineItem.get();
            if (type == 1){
                lineItem1.setQuantity(lineItem1.getQuantity()+1);
                lineItem1.setTotalPrice(lineItem1.getQuantity() * lineItem1.getPrice());
                Order order = lineItem1.getOrder();
                order.setTotalPrice(order.getTotalPrice()+lineItem1.getPrice());
                dao.save(lineItem1);
            }else {
                if (lineItem1.getQuantity() == 1){
                    dao.delete(lineItem1);
                }else {
                    lineItem1.setQuantity(lineItem1.getQuantity()-1);
                    lineItem1.setTotalPrice(lineItem1.getQuantity() * lineItem1.getPrice());
                    Order order = lineItem1.getOrder();
                    order.setTotalPrice(order.getTotalPrice()-lineItem1.getTotalPrice());
                    dao.save(lineItem1);
                }
            }
        }
    }
}
