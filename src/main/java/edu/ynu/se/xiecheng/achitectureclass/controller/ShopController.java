package edu.ynu.se.xiecheng.achitectureclass.controller;
import edu.ynu.se.xiecheng.achitectureclass.common.controller.LogicController;
import edu.ynu.se.xiecheng.achitectureclass.common.respone.ResponeCode;
import edu.ynu.se.xiecheng.achitectureclass.common.respone.Response;
import edu.ynu.se.xiecheng.achitectureclass.dao.BusinessDao;
import edu.ynu.se.xiecheng.achitectureclass.dao.ShopDao;
import edu.ynu.se.xiecheng.achitectureclass.entity.Business;
import edu.ynu.se.xiecheng.achitectureclass.entity.Order;
import edu.ynu.se.xiecheng.achitectureclass.entity.Shop;
import edu.ynu.se.xiecheng.achitectureclass.entity.ShopItem;
import edu.ynu.se.xiecheng.achitectureclass.service.ShopService;
import io.swagger.annotations.Api;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
@Api(tags = "店铺的方法")
@RestController
@RequestMapping("/shop")
public class ShopController extends LogicController<ShopService,ShopDao, Shop,Long> {
    public ShopController(@Autowired ShopService ls) {
        super(ls);
    }
    //商家创建自己的店铺
    @PostMapping("/setBusinessShop")
    @Secured("ROLE_business")
    public Response<String> setBusinessShop(
            @RequestParam("businessId") Long businessId,
            @RequestParam("shopName") String shopName,
            @RequestParam("location") String location,
            @RequestParam("description") String description,
            @RequestParam("file") MultipartFile file
    ){
        Shop shop = new Shop();
        shop.setShopName(shopName);
        shop.setDescription(description);
        shop.setLocation(location);
        service.setShop(businessId,shop,file);
        return new Response<>(ResponeCode.SUCCESS);
    }
    @GetMapping("/getShopItem")
    @Secured("ROLE_business")
    public Response getShopItem(@RequestParam Long shopId,@RequestParam Integer pageNum ,@RequestParam Integer pageSize){
        Page<ShopItem> shopItemPage =  service.getShopItem(shopId,pageNum, pageSize);

        return new Response<>(ResponeCode.SUCCESS,"获取成功",shopItemPage);
    }
    @GetMapping("/getShopOrder")
    @Secured("ROLE_business")
    public Response getShopOrder(@RequestParam Long shopId){
        List<Order> orderList = service.getShopOrder(shopId);
        return new Response<>(ResponeCode.SUCCESS,"获取成功",orderList);
    }
    @GetMapping("/list")
    @Secured({"Role_customer","Role_business"})
    public Response getList(@RequestParam Integer pageNum ,@RequestParam Integer pageSize){
        Page<Shop> stringObjectMap =  service.getShopList(pageNum, pageSize);
        return new Response<>(ResponeCode.SUCCESS,"获取成功",stringObjectMap);
    }
    @GetMapping("/getBusinessShop")
    @Secured("ROLE_business")
    public Response getBusinessShop(@RequestParam Integer pageNum ,@RequestParam Integer pageSize,@RequestParam Long businessId){
        Page<Shop> businessShopList = service.getBusinessShopList(pageNum, pageSize, businessId);
        return new Response<>(ResponeCode.SUCCESS,"获取成功",businessShopList);
    }
}
