package es.regueiro.collectionManager.service;

import java.util.List;

import es.regueiro.collectionManager.model.user.User;

public interface UserService {

    User findByUsername(String username);
    User findById(long id);
    void saveUser(User user);
    void deleteUser(User user);
    List<User> findUsers(String user);
    List<User> listAll();
    void registerUser(User user);
}