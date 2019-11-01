package cn.xzq.blog.service;

import cn.xzq.blog.dao.UserRepository;
import cn.xzq.blog.po.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create by XuZhiQiang on 2019/10/21
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User checkUser(String username, String password) {
        return userRepository.findUserByUsernameAndPassword(username, password);
    }

    @Override
    public User checkUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public User modifyUserInfo(User user) {
        User user1 = userRepository.findById(user.getId()).get();
        if (user1 != null) {
            BeanUtils.copyProperties(user, user1);
            return userRepository.save(user1);
        }
        return null;
    }

    @Override
    public List<User> fingAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).get();
    }
}
