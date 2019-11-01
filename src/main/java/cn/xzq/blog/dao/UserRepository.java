package cn.xzq.blog.dao;

import cn.xzq.blog.po.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Create by XuZhiQiang on 2019/10/21
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUsernameAndPassword(String username, String password);

    User findUserByUsername(String username);
}
