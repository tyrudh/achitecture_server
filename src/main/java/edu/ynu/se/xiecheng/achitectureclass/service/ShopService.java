package edu.ynu.se.xiecheng.achitectureclass.service;
import edu.ynu.se.xiecheng.achitectureclass.common.service.LogicService;
import edu.ynu.se.xiecheng.achitectureclass.dao.BusinessDao;
import edu.ynu.se.xiecheng.achitectureclass.dao.OrderDao;
import edu.ynu.se.xiecheng.achitectureclass.dao.ShopDao;
import edu.ynu.se.xiecheng.achitectureclass.dao.ShopItemDao;
import edu.ynu.se.xiecheng.achitectureclass.entity.Business;
import edu.ynu.se.xiecheng.achitectureclass.entity.Order;
import edu.ynu.se.xiecheng.achitectureclass.entity.Shop;
import edu.ynu.se.xiecheng.achitectureclass.entity.ShopItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
@Service
public class ShopService extends LogicService<ShopDao, Shop,Long> {
    private final Path rootLocation = Paths.get("E:/upload/shopImg/"); // 文件存储位置
    @Resource
    ShopItemDao shopItemDao;
    @Resource
    ShopDao shopDao;
    @Resource
    BusinessDao businessDao;
    @Resource
    OrderDao orderDao;
    public ShopService(@Autowired ShopDao lr) {
        super(lr);
    }
    /**
     * 商人创建店铺
     * @param businessId
     * @param shop
     * @param file
     */
    public void setShop(Long businessId, Shop shop, MultipartFile file) {
        Optional<Business> businessOptional = businessDao.findById(businessId);
        if (businessOptional.isPresent()) {
            Business business = businessOptional.get();
            shop.setBusiness(business);
        }
        String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename(); // 生成唯一文件名
        try {
            Files.copy(file.getInputStream(), this.rootLocation.resolve(filename));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        shop.setImageUrl(filename);
        dao.save(shop);
    }

    /**
     * 获取所有的店铺列表，分页显示
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public Page<Shop> getShopList(Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
        // 从repository获取Page对象
        Page<Shop> shopPage = shopDao.findAll(pageRequest);
        return shopPage;
    }
    /**
     * 获取对应的商人的店铺
     * @param pageNumber
     * @param pageSize
     * @param businessId
     * @return
     */
    public Page<Shop> getBusinessShopList(Integer pageNumber, Integer pageSize, Long businessId) {
        Business business = new Business();
        business.setId(businessId);
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
        // 从repository获取Page对象
        Page<Shop> shopPage = shopDao.findByBusiness(business, pageRequest);
        return shopPage;
    }
    /**
     * 获取对应的商店的商品
     * @param shopId
     * @param pageNum
     * @param pageSize
     * @return
     */
    public Page<ShopItem> getShopItem(Long shopId, Integer pageNum, Integer pageSize) {
        Shop shop = new Shop();
        shop.setId(shopId);
        // 创建PageRequest对象，pageNum是页码（通常从0开始），pageSize是每页大小
        PageRequest pageRequest = PageRequest.of(pageNum - 1, pageSize);
        // 执行分页查询
        Page<ShopItem> shopItemsPage = shopItemDao.findByShop(shop, pageRequest);
        // 获取并返回分页查询结果的列表
        return shopItemsPage;
    }
    /**
     * 获取商店的所有Order
     * @param shopId
     * @return
     */
    public List<Order> getShopOrder(Long shopId) {
        Shop shop = new Shop();
        shop.setId(shopId);
        List<Order> orderList = orderDao.findByShop(shop);
        return orderList;
    }

}

