package dao.impl;

import dao.UserDao;
import model.User;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("userDao")
public class UserDaoImpl implements UserDao {

    private HibernateTemplate hibernateTemplate;

    @Override
    public void save(User user) {
        hibernateTemplate.save(user);
    }

    @Override
    public User get(String username) {
        List<User> user = (List<User>) hibernateTemplate.find("from User user where user.username = " + username);
        for (User u : user) {
            System.out.println(u);
        }
        if (user.size() == 1) {
            return user.get(0);
        } else {
            return null;
        }
    }

    @Override
    public User get(int id) {
        return hibernateTemplate.get(User.class, id);
    }

    @Override
    public void delete(User user) {
        hibernateTemplate.delete(user);
    }

    @Override
    public void delete(String username) {
        User user = get(username);
        hibernateTemplate.delete(user);
    }

    @Override
    public void delete(int id) {
        User user = get(id);
        hibernateTemplate.delete(user);
    }

    @Override
    public User update(User newUser) {
        User user = hibernateTemplate.get(User.class, newUser.getId());
        hibernateTemplate.update(user);
        return user;
    }

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    @Resource
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }
}

