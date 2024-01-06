package edu.ynu.se.xiecheng.achitectureclass.dao;

import edu.ynu.se.xiecheng.achitectureclass.common.dao.LogicDAO;
import edu.ynu.se.xiecheng.achitectureclass.entity.Item;
import edu.ynu.se.xiecheng.achitectureclass.entity.Shop;
import edu.ynu.se.xiecheng.achitectureclass.entity.ShopItem;

import java.util.List;
import java.util.Optional;

public interface ShopItemDao extends LogicDAO<ShopItem,Long> {

    /**
     * 根据shopId和itemId查找ShopItem
     * @return 返回找到的ShopItem的Optional对象
     */
    Optional<ShopItem> findByShopAndItem(Shop shop, Item item);

    List<ShopItem> findByShop(Shop shop);

}
