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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
@Api(tags = "商品")
@RestController
@RequestMapping("/item")
public class ItemController extends LogicController<ItemService, ItemDao, Item,Long> {
    public ItemController(@Autowired ItemService service) {
        super(service);
    }
    @GetMapping("/up")
    @Secured("Role_business")
    public Response upItem(@RequestParam Long id) {
        try {
            service.UpItem(id);
            return new Response<>(ResponeCode.SUCCESS,"上架成功");
        } catch (RuntimeException e) {
            return new Response<>(ResponeCode.FAIL,"上架失败");
        }
    }
    @GetMapping("/down")
    @Secured("Role_business")
    public Response DownItem(@RequestParam Long id) {
        try {
            service.DownItem(id);
            return new Response<>(ResponeCode.SUCCESS,"下架成功");
        } catch (RuntimeException e) {
            return new Response<>(ResponeCode.FAIL,"下架失败");
        }
    }
    @GetMapping("/getBusinessItem")
    @Secured("Role_business")
    public Response getBusinessItem(@RequestParam Long businessId,@RequestParam Integer pageNum ,@RequestParam Integer pageSize){
        Page<Item> businessItem = service.getBusinessItem(pageNum, pageSize, businessId);
        return new Response<>(ResponeCode.SUCCESS,"获取成功",businessItem);
    }
    //商家创建自己的商品
    @PostMapping("/setItem")
    @Secured("Role_business")
    public Response<String> setBusinessShop(
            @RequestParam("businessId") Long businessId,
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("price") Double price,
            @RequestParam("file") MultipartFile file
    ){
        Item item = new Item();
        item.setName(name);
        item.setDescription(description);
        item.setPrice(price);
        service.setItem(businessId,item,file);
        return new Response<>(ResponeCode.SUCCESS);
    }
}
