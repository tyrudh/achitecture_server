package edu.ynu.se.xiecheng.achitectureclass.service;

import edu.ynu.se.xiecheng.achitectureclass.common.service.LogicService;
import edu.ynu.se.xiecheng.achitectureclass.dao.*;
import edu.ynu.se.xiecheng.achitectureclass.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BusinessService extends LogicService<BusinessDao, Business,Long>{
    @Resource
    ShopItemDao shopItemDao;
    @Resource
    ShopDao shopDao;
    @Resource
    ItemDao itemDao;
    @Resource
    OrderDao orderDao;

    public BusinessService(@Autowired BusinessDao lr) {
        super(lr);
    }
    /**
     * 上架下架商品
     * @param itemId
     */
    @Transactional
    public void UpItem(Long itemId, Long shopId){
        Shop shop = shopDao.findById(shopId).orElseThrow(() -> new RuntimeException("Shop not found"));
        Item item = itemDao.findById(itemId).orElseThrow(() -> new RuntimeException("Item not found"));
        Optional<ShopItem> shopItemOptional = shopItemDao.findByShopAndItem(shop, item);
        ShopItem shopItem = shopItemOptional.orElseGet(() -> {
            ShopItem newShopItem = new ShopItem();
            newShopItem.setShop(shop);
            newShopItem.setItem(item);
            return newShopItem;
        });
//        shopItem.setShopItem();
        shopItem.up();
        shopItemDao.save(shopItem);
    }
    public void DownItem(Long shopItemId){
        Optional<ShopItem> optionalShopItem = shopItemDao.findById(shopItemId);
        if (optionalShopItem.isPresent()){
            ShopItem shopItem = optionalShopItem.get();
            shopItem.down();
            shopItemDao.save(shopItem);
        }
    }
    /**
     * 创建shop
     */
    public void setShop(Shop shop,Long businessId){
        Optional<Business> businessOptional = dao.findById(businessId);
        Business business = businessOptional.get();
//        business.setShop(shop);
        shopDao.save(shop);
    }
    public List<Order> getBusinessOrder(Long businessId) {
        Business business = new Business();
        business.setId(businessId);
        List<Shop> shopList = shopDao.findByBusiness(business);
        List<Order> orderList = new ArrayList<>();
        for (Shop shop : shopList
        ) {
            List<Order> byShop = orderDao.findByShop(shop);
            orderList.addAll(byShop);
        }
        return orderList;
    }

}
