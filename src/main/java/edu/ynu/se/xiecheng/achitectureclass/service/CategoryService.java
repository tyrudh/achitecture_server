package edu.ynu.se.xiecheng.achitectureclass.service;

import edu.ynu.se.xiecheng.achitectureclass.common.entity.LogicEntity;
import edu.ynu.se.xiecheng.achitectureclass.common.service.LogicService;
import edu.ynu.se.xiecheng.achitectureclass.dao.CategoryDao;
import edu.ynu.se.xiecheng.achitectureclass.entity.Category;
import org.springframework.stereotype.Service;

@Service
public class CategoryService extends LogicService<CategoryDao, Category,Long> {
    public CategoryService(CategoryDao categoryDao) {
        super(categoryDao);
    }
}
