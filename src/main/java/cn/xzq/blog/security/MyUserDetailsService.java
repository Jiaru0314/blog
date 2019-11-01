/*
package cn.xzq.blog.security;

import cn.xzq.blog.dao.UserRepository;
import cn.xzq.blog.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

*/
/**
 * @program: blog
 * @description:
 * @author: XZQ
 * @create: 2019-10-31 20:06
 **//*

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username + "用户不存在");
        }
        return null;
    }
}
*/
