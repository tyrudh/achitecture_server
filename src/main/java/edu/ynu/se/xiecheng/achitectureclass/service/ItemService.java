package edu.ynu.se.xiecheng.achitectureclass.service;

import edu.ynu.se.xiecheng.achitectureclass.common.service.LogicService;
import edu.ynu.se.xiecheng.achitectureclass.dao.ItemDao;
import edu.ynu.se.xiecheng.achitectureclass.dao.ShopDao;
import edu.ynu.se.xiecheng.achitectureclass.entity.Item;
import edu.ynu.se.xiecheng.achitectureclass.entity.Shop;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemService extends LogicService<ItemDao, Item,Long> {
    public ItemService(ItemDao itemDao) {
        super(itemDao);
    }

    /**
     * 将商品修改为上架
     */
    public void UpItem(Long id){
        // 查找商品
        Optional<Item> itemOptional = dao.findById(id);
        if(itemOptional.isPresent()){
            Item item = itemOptional.get();
            // 修改商品状态为上架
            item.up();
            // 保存更改
            dao.save(item);
        } else {
            throw new RuntimeException("Item not found"); // 或者使用自定义异常
        }
    }
    public void DownItem(Long id){
        // 查找商品
        Optional<Item> itemOptional = dao.findById(id);
        if(itemOptional.isPresent()){
            Item item = itemOptional.get();
            // 修改商品状态为上架
            item.down();
            // 保存更改
            dao.save(item);
        } else {
            throw new RuntimeException("Item not found"); // 或者使用自定义异常
        }
    }
}
