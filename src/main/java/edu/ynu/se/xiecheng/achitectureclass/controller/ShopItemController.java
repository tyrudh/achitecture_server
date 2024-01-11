package edu.ynu.se.xiecheng.achitectureclass.controller;

import edu.ynu.se.xiecheng.achitectureclass.common.controller.LogicController;
import edu.ynu.se.xiecheng.achitectureclass.dao.ShopDao;
import edu.ynu.se.xiecheng.achitectureclass.dao.ShopItemDao;
import edu.ynu.se.xiecheng.achitectureclass.entity.Shop;
import edu.ynu.se.xiecheng.achitectureclass.entity.ShopItem;
import edu.ynu.se.xiecheng.achitectureclass.service.ShopItemService;
import edu.ynu.se.xiecheng.achitectureclass.service.ShopService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "店铺的方法")
@RestController
@RequestMapping("/shopItem")
public class ShopItemController  extends LogicController<ShopItemService, ShopItemDao, ShopItem,Long> {
    public ShopItemController(@Autowired ShopItemService ls) {
        super(ls);
    }


}
