package edu.ynu.se.xiecheng.achitectureclass.controller;

import edu.ynu.se.xiecheng.achitectureclass.common.controller.LogicController;
import edu.ynu.se.xiecheng.achitectureclass.common.respone.ResponeCode;
import edu.ynu.se.xiecheng.achitectureclass.common.respone.Response;
import edu.ynu.se.xiecheng.achitectureclass.dao.ItemDao;
import edu.ynu.se.xiecheng.achitectureclass.dao.ShopDao;
import edu.ynu.se.xiecheng.achitectureclass.entity.Item;
import edu.ynu.se.xiecheng.achitectureclass.entity.Shop;
import edu.ynu.se.xiecheng.achitectureclass.service.ItemService;
import edu.ynu.se.xiecheng.achitectureclass.service.ShopService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "商品")
@RestController
@RequestMapping("/item")
public class ItemController extends LogicController<ItemService, ItemDao, Item,Long> {
    public ItemController(ItemService service) {
        super(service);
    }

    @PutMapping("/up/{id}")
    public Response upItem(@PathVariable Long id) {
        try {
            service.UpItem(id);
            return new Response<>(ResponeCode.SUCCESS,"上架成功");
        } catch (RuntimeException e) {
            return new Response<>(ResponeCode.FAIL,"上架失败");
        }
    }
    @PutMapping("/down/{id}")
    public Response DownItem(@PathVariable Long id) {
        try {
            service.DownItem(id);
            return new Response<>(ResponeCode.SUCCESS,"下架成功");
        } catch (RuntimeException e) {
            return new Response<>(ResponeCode.FAIL,"下架失败");
        }
    }
}
