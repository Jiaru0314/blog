package cn.xzq.blog.service;

import cn.xzq.blog.po.User;

import java.util.List;

/**
 * Create by XuZhiQiang on 2019/10/21
 */
public interface UserService {
    //用户注册
    User addUser(User user);

    //用户登录检查
    User checkUser(String username, String password);

    //判断用户名是否存在
    User checkUsername(String username);

    //修改用户信息
    User modifyUserInfo(User user);

    //查看所有用户
    List<User> fingAllUser();

    //通过id查询用户
    User findUserById(Long id);

}
