package edu.ynu.se.xiecheng.achitectureclass.controller;


import edu.ynu.se.xiecheng.achitectureclass.common.controller.LogicController;
import edu.ynu.se.xiecheng.achitectureclass.common.respone.ResponeCode;
import edu.ynu.se.xiecheng.achitectureclass.common.respone.Response;
import edu.ynu.se.xiecheng.achitectureclass.dao.BusinessDao;
import edu.ynu.se.xiecheng.achitectureclass.entity.Business;
import edu.ynu.se.xiecheng.achitectureclass.entity.Shop;
import edu.ynu.se.xiecheng.achitectureclass.service.BusinessService;
import edu.ynu.se.xiecheng.achitectureclass.service.Impl.BusinessServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "商家的方法")
@RestController
@RequestMapping("/business")
public class BusinessController extends LogicController<BusinessServiceImpl, BusinessDao, Business,Long> {

    public BusinessController(BusinessServiceImpl businessService) {
        super(businessService);
    }

    /**
     * 店铺商品的上架和下架
     */
    @PostMapping("/up")
    public Response upItem(@RequestParam Long itemId,@RequestParam Long shopId) {
        try {
            service.UpItem(itemId,shopId);
            return new Response<>(ResponeCode.SUCCESS,"上架成功");
        } catch (RuntimeException e) {
            return new Response<>(ResponeCode.FAIL,"上架失败");
        }
    }
    @PostMapping("/down")
    public Response DownItem(@RequestParam Long shopItemId) {
        try {
            service.DownItem(shopItemId);
            return new Response<>(ResponeCode.SUCCESS,"下架成功");
        } catch (RuntimeException e) {
            return new Response<>(ResponeCode.FAIL,"下架失败");
        }
    }
}
