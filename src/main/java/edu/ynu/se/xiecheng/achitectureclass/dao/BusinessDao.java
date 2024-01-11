package edu.ynu.se.xiecheng.achitectureclass.dao;
import edu.ynu.se.xiecheng.achitectureclass.common.dao.LogicDAO;
import edu.ynu.se.xiecheng.achitectureclass.entity.Business;
public interface BusinessDao extends LogicDAO<Business,Long> {
    /**
     * 通过用户名查找店铺
     * @param username
     * @return
     */
    Business findByUsername(String username);
}
