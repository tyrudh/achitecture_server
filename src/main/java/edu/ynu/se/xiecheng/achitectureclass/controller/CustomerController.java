package edu.ynu.se.xiecheng.achitectureclass.controller;
import edu.ynu.se.xiecheng.achitectureclass.common.controller.LogicController;
import edu.ynu.se.xiecheng.achitectureclass.dao.CustomerDao;
import edu.ynu.se.xiecheng.achitectureclass.entity.Customer;
import edu.ynu.se.xiecheng.achitectureclass.service.CustomerService;
import edu.ynu.se.xiecheng.achitectureclass.service.LineItemService;
import edu.ynu.se.xiecheng.achitectureclass.utils.ResponeCode;
import edu.ynu.se.xiecheng.achitectureclass.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
@Api(tags = "顾客的方法")
@RestController
@RequestMapping("/customer")
public class CustomerController extends LogicController<CustomerService, CustomerDao, Customer,Long> {
    @Autowired
    CustomerDao customerDao;
    @Resource
    LineItemService lineItemService;
    public CustomerController(@Autowired CustomerService service) {
        super(service);
    }
    @PostMapping("/login")
    public Response customerLogin(Customer customer){
        Customer customer1 = service.getCustomerByName(customer);
        System.out.println(customer1.getPassword());
        System.out.println(customer.getPassword());
        if (customer1 == null){
            return new Response<>(ResponeCode.FAIL,"该账户不存在");
        }else if (customer1.getPassword().equals(customer.getPassword())){
            return new Response<>(ResponeCode.SUCCESS,"登录成功");
        }else {
            return new Response<>(ResponeCode.FAIL,"密码错误");
        }
    }
    @ApiOperation("顾客注册")
    @PostMapping("/register")
    public Response customerRegister(@RequestBody Customer customer){
        customerDao.save(customer);
        return new Response<>(ResponeCode.SUCCESS,"注册成功");
    }
    @GetMapping("/setOrder")
    @Secured("Role_customer")
    public Response setOrder(@RequestParam Long customerId,@RequestParam Long shopItemId){
        service.selectItem(customerId,shopItemId);
        return new Response<>(ResponeCode.SUCCESS,"创建成功");
    }
    @GetMapping("/change")
    @Secured("Role_customer")
    public Response setLineItemNum(@RequestParam Long lineItemId , @RequestParam Integer type){
        lineItemService.plusLineItem(lineItemId,type);
        return new Response<>(ResponeCode.SUCCESS,"修改成功");
    }
}
