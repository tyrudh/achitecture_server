package edu.ynu.se.xiecheng.achitectureclass.dao;
import edu.ynu.se.xiecheng.achitectureclass.common.dao.LogicDAO;
import edu.ynu.se.xiecheng.achitectureclass.entity.Business;
import edu.ynu.se.xiecheng.achitectureclass.entity.Shop;
import edu.ynu.se.xiecheng.achitectureclass.entity.ShopItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
public interface ShopDao extends LogicDAO<Shop,Long> {
    /**
     * 找到对应的商人的店铺
     * @param business
     * @return
     */
    List<Shop> findByBusiness(Business business);
    Page<Shop> findByBusiness(Business business, Pageable pageable);
}
