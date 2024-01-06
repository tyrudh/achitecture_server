package edu.ynu.se.xiecheng.achitectureclass.controller;

import edu.ynu.se.xiecheng.achitectureclass.common.controller.LogicController;
import edu.ynu.se.xiecheng.achitectureclass.dao.CustomerDao;
import edu.ynu.se.xiecheng.achitectureclass.entity.Customer;
import edu.ynu.se.xiecheng.achitectureclass.service.CustomerService;
import edu.ynu.se.xiecheng.achitectureclass.service.Impl.CustomerServiceImpl;
import edu.ynu.se.xiecheng.achitectureclass.utils.ResponeCode;
import edu.ynu.se.xiecheng.achitectureclass.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "顾客的方法")
@RestController
@RequestMapping("/customer")
public class CustomerController extends LogicController<CustomerServiceImpl, CustomerDao, Customer,Long> {


    @Autowired
    CustomerDao customerDao;

    @Autowired
    CustomerService customerService;
    public CustomerController(@Autowired CustomerServiceImpl service) {
        super(service);
    }

    @PostMapping("/login")
    public Response customerLogin(@RequestBody Customer customer){

        Customer customer1 = customerService.getCustomerByName(customer);
        System.out.println(customer1.getPassword());
        System.out.println(customer.getPassword());
        if (customer1 == null){
            return new Response<>(ResponeCode.FAIL,"该账户不存在");
        }else if (customer1.getPassword().equals(customer.getPassword())){
            return new Response<>(ResponeCode.SUCCESS,"登录成功",customer1);

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

}
