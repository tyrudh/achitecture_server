package edu.ynu.se.xiecheng.achitectureclass.controller;

import edu.ynu.se.xiecheng.achitectureclass.common.controller.LogicController;
import edu.ynu.se.xiecheng.achitectureclass.common.respone.ResponeCode;
import edu.ynu.se.xiecheng.achitectureclass.common.respone.Response;
import edu.ynu.se.xiecheng.achitectureclass.dao.ShopDao;
import edu.ynu.se.xiecheng.achitectureclass.entity.Shop;
import edu.ynu.se.xiecheng.achitectureclass.entity.ShopItem;
import edu.ynu.se.xiecheng.achitectureclass.service.ShopService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(tags = "店铺的方法")
@RestController
@RequestMapping("/shop")
public class ShopController extends LogicController<ShopService,ShopDao, Shop,Long> {
    public ShopController(ShopService ls) {
        super(ls);
    }

    //商家创建自己的店铺
    @PostMapping("/setBusinessShop")
    public Response<String> setBusinessShop(){
        return new Response<>(ResponeCode.SUCCESS);
    }
    @GetMapping("/getShopItem")
    public Response getShopItem(@RequestParam Long shopId){
        List<ShopItem> shopItems = service.getShopItem(shopId);
        return new Response<>(ResponeCode.SUCCESS,"获取成功",shopItems);
    }
}
