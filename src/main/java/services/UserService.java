package services;

import dao.UserDao;
import model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    private UserDao userDao;

    public boolean exist(String username) {
        if (userDao.get(username) != null) {
            return true;
        } else {
            return false;
        }
    }

    public User get(String username) {
        return userDao.get(username);
    }

    public UserDao getUserDao() {
        return userDao;
    }

    @Resource
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
