package edu.ynu.se.xiecheng.achitectureclass.controller;

import edu.ynu.se.xiecheng.achitectureclass.common.controller.LogicController;
import edu.ynu.se.xiecheng.achitectureclass.dao.CategoryDao;
import edu.ynu.se.xiecheng.achitectureclass.entity.Category;
import edu.ynu.se.xiecheng.achitectureclass.service.CategoryService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(tags = "店铺的分类")
@RestController
@RequestMapping("/category")
public class CategoryController extends LogicController<CategoryService, CategoryDao, Category,Long> {

    public CategoryController(@Autowired CategoryService categoryService) {
        super(categoryService);
    }
}
