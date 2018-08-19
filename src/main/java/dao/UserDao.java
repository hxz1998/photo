package dao;

import model.User;

public interface UserDao {

    void save(User user);

    User get(String username);

    User get(int id);

    void delete(User user);

    void delete(String username);

    void delete(int id);

    User update(User newUser);
}
