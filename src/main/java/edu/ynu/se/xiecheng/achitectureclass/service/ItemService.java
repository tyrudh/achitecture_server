package edu.ynu.se.xiecheng.achitectureclass.service;
import edu.ynu.se.xiecheng.achitectureclass.common.service.LogicService;
import edu.ynu.se.xiecheng.achitectureclass.dao.BusinessDao;
import edu.ynu.se.xiecheng.achitectureclass.dao.ItemDao;
import edu.ynu.se.xiecheng.achitectureclass.dao.ShopDao;
import edu.ynu.se.xiecheng.achitectureclass.entity.Business;
import edu.ynu.se.xiecheng.achitectureclass.entity.Item;
import edu.ynu.se.xiecheng.achitectureclass.entity.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;
@Service
public class ItemService extends LogicService<ItemDao, Item,Long> {
    private final Path rootLocation = Paths.get("E:/upload/foodImg/"); // 文件存储位置
    public ItemService(@Autowired ItemDao itemDao) {
        super(itemDao);
    }
    @Resource
    BusinessDao businessDao;
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
            throw new RuntimeException("Item not found");
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
    /**
     * 获取商家的所有的商品
     * @param pageNumber
     * @param pageSize
     * @param businessId
     * @return
     */
    public Page<Item> getBusinessItem(Integer pageNumber , Integer pageSize, Long businessId){
        Business business = new Business();
        business.setId(businessId);
        PageRequest pageRequest = PageRequest.of(pageNumber - 1 , pageSize);
        // 从repository获取Page对象
        Page<Item> itemPage = dao.findByBusiness(business,pageRequest);
        return itemPage;
    }
    /**
     * 新建对应的商品，并且使用UUID将图片的名字存储
     * @param businessId
     * @param item
     * @param file
     */
    public void setItem(Long businessId, Item item, MultipartFile file) {
        Optional<Business> businessOptional = businessDao.findById(businessId);
        if (businessOptional.isPresent()) {
            Business business = businessOptional.get();
            item.setBusiness(business);
        }
        String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename(); // 生成唯一文件名
        try {
            Files.copy(file.getInputStream(), this.rootLocation.resolve(filename));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        item.setIsOnSale(false);
        item.setImage(filename);
        dao.save(item);
    }

}
