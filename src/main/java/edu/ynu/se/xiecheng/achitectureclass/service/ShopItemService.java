package edu.ynu.se.xiecheng.achitectureclass.service;

import edu.ynu.se.xiecheng.achitectureclass.common.service.LogicService;
import edu.ynu.se.xiecheng.achitectureclass.dao.ShopDao;
import edu.ynu.se.xiecheng.achitectureclass.dao.ShopItemDao;
import edu.ynu.se.xiecheng.achitectureclass.entity.Item;
import edu.ynu.se.xiecheng.achitectureclass.entity.Shop;
import edu.ynu.se.xiecheng.achitectureclass.entity.ShopItem;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShopItemService extends LogicService<ShopItemDao, ShopItem,Long> {
    public ShopItemService(ShopItemDao lr) {
        super(lr);
    }
    public void UpItem(Long id){
        // 查找商品
        Optional<ShopItem> itemOptional = dao.findById(id);
        if(itemOptional.isPresent()){
            ShopItem shopItem = itemOptional.get();
            // 修改商品状态为上架
            shopItem.up();
            // 保存更改
            dao.save(shopItem);
        } else {
            throw new RuntimeException("Item not found"); // 或者使用自定义异常
        }
    }
    public void DownItem(Long id){
        // 查找商品
        Optional<ShopItem> itemOptional = dao.findById(id);
        if(itemOptional.isPresent()){
            ShopItem shopItem = itemOptional.get();
            // 修改商品状态为上架
            shopItem.up();
            // 保存更改
            dao.save(shopItem);
        } else {
            throw new RuntimeException("Item not found"); // 或者使用自定义异常
        }
    }
}
