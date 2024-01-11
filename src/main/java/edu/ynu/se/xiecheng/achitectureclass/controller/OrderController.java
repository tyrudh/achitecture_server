package edu.ynu.se.xiecheng.achitectureclass.controller;

import edu.ynu.se.xiecheng.achitectureclass.common.controller.LogicController;
import edu.ynu.se.xiecheng.achitectureclass.dao.OrderDao;
import edu.ynu.se.xiecheng.achitectureclass.dao.ShopDao;
import edu.ynu.se.xiecheng.achitectureclass.entity.Order;
import edu.ynu.se.xiecheng.achitectureclass.entity.Shop;
import edu.ynu.se.xiecheng.achitectureclass.service.OrderService;
import edu.ynu.se.xiecheng.achitectureclass.service.ShopService;
import edu.ynu.se.xiecheng.achitectureclass.utils.ResponeCode;
import edu.ynu.se.xiecheng.achitectureclass.utils.Response;
import io.swagger.annotations.Api;
import lombok.Getter;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "订单的方法")
@RestController
@RequestMapping("/orders")
public class OrderController extends LogicController<OrderService, OrderDao, Order,Long> {
    public OrderController(@Autowired OrderService ls) {
        super(ls);
    }
    @GetMapping("/payOrder")
    @Secured("Role_customer")
    public Response payOrder(@RequestParam Long orderId){
        service.payOrder(orderId);
        return new Response<>(ResponeCode.SUCCESS,"支付成功");
    }
    @GetMapping("/deleteOrder")
    @Secured({"Role_customer","Role_business"})
    public Response deleteOrder(@RequestParam Long orderId){
        service.deleteOrder(orderId);
        return new Response<>(ResponeCode.SUCCESS,"删除成功");
    }
    @GetMapping("/getCategory")
    @Secured("Role_customer")
    public Response getCustomerShopOrder(@RequestParam Long customerId,@RequestParam Long shopId){
        List<Order> orderByShop = service.getCancelOrderByShop(customerId, shopId, "未支付");
        return new Response<>(ResponeCode.SUCCESS,"获取成功",orderByShop);
    }
    @GetMapping("/getCustomerOrder")
    @Secured("Role_customer")
    public Response getCustomerOrder(@RequestParam Long customerId){
        List<Order> orderList = service.getCustomerOrder(customerId);
        return new Response<>(ResponeCode.SUCCESS,"获取成功",orderList);
    }
    @GetMapping("/sureOrder")
    @Secured("Role_business")
    public Response sureOrder(@RequestParam Long orderId){
        service.sureOrder(orderId);
        return new Response<>(ResponeCode.SUCCESS,"确认成功");
    }
    @GetMapping("/cancelOrder")
    @Secured("Role_business")
    public Response cancelOrder(@RequestParam Long orderId){
        service.cancelOrder(orderId);
        return new Response<>(ResponeCode.SUCCESS,"退款成功");
    }

}
