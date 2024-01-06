package edu.ynu.se.xiecheng.achitectureclass.service.Impl;

import edu.ynu.se.xiecheng.achitectureclass.common.service.LogicService;
import edu.ynu.se.xiecheng.achitectureclass.dao.BusinessDao;
import edu.ynu.se.xiecheng.achitectureclass.dao.ItemDao;
import edu.ynu.se.xiecheng.achitectureclass.dao.ShopDao;
import edu.ynu.se.xiecheng.achitectureclass.dao.ShopItemDao;
import edu.ynu.se.xiecheng.achitectureclass.entity.Business;
import edu.ynu.se.xiecheng.achitectureclass.entity.Item;
import edu.ynu.se.xiecheng.achitectureclass.entity.Shop;
import edu.ynu.se.xiecheng.achitectureclass.entity.ShopItem;
import edu.ynu.se.xiecheng.achitectureclass.service.BusinessService;
import edu.ynu.se.xiecheng.achitectureclass.service.ShopItemService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class BusinessServiceImpl extends LogicService<BusinessDao, Business,Long> implements BusinessService {

    @Resource
    ShopItemDao shopItemDao;

    @Resource
    ShopDao shopDao;
    @Resource
    ItemDao itemDao;

    public BusinessServiceImpl(BusinessDao lr) {
        super(lr);
    }

    public void setShopItem(Item item){
        itemDao.save(item);
    }

    /**
     * 上架下架商品
     * @param itemId
     */
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

}
