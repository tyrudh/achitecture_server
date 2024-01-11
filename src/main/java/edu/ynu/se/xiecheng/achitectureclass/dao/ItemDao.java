package edu.ynu.se.xiecheng.achitectureclass.dao;
import edu.ynu.se.xiecheng.achitectureclass.common.dao.LogicDAO;
import edu.ynu.se.xiecheng.achitectureclass.entity.Business;
import edu.ynu.se.xiecheng.achitectureclass.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
public interface ItemDao extends LogicDAO<Item,Long> {
    /**
     * 通过business和分页请求查找对应的商家商品
     * @param business
     * @param pageRequest
     * @return
     */
    Page<Item> findByBusiness(Business business, PageRequest pageRequest);
}
