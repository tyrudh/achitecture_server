package edu.ynu.se.xiecheng.achitectureclass.service;

import edu.ynu.se.xiecheng.achitectureclass.common.service.LogicService;
import edu.ynu.se.xiecheng.achitectureclass.dao.UserDao;
import edu.ynu.se.xiecheng.achitectureclass.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserService extends LogicService<UserDao,User,Long> {

    public UserService(@Autowired UserDao dao) {
        super(dao);
    }

    /**
     *  TODO
     * @param username
     * @param password
     * @return
     */
    public String login(String username, String password){
        return  null;
    }
}
