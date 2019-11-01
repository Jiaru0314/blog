package cn.xzq.blog.web;

import cn.xzq.blog.po.User;
import cn.xzq.blog.service.UserService;
import cn.xzq.blog.util.MD5Utils;
import cn.xzq.blog.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private UserService userService;


    @GetMapping("")
    public String index(HttpSession session) {
        //如果用户已经登录
        if (session.getAttribute("user") != null) {
            return "redirect:/users/index";
        }
        return "index";
    }

    //登录界面
    @GetMapping("login")
    public String toLogin(HttpSession session) {
        //如果用户已经登录
        if (session.getAttribute("user") != null) {
            return "redirect:/users/index";
        }
        return "login";
    }

    //注册界面
    @GetMapping("register")
    public String toRegister(HttpSession session) {
        //如果用户已经登录
        if (session.getAttribute("user") != null) {
            return "redirect:/users/index";
        }
        return "register";
    }

    //处理登录
    @PostMapping("login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes) {

        User user1 = userService.checkUsername(username);
        String[] message = null;
        //如果用户不存在
        if (user1 == null) {
            message = new String[]{"用户不存在", "User does not exist"};
            Message.setMessage(message, attributes);
            return "redirect:/login";
        } else {
            User user = userService.checkUser(username, MD5Utils.code(password));
            //如果用户不存在 但密码错误
            if (user == null) {
                message = new String[]{"密码错误,请重新输入", "Password error, please retype"};
                Message.setMessage(message, attributes);
                return "redirect:/login";
            } else {
                //用户存在 登录成功 将用户放入session
                session.setAttribute("user", user);
                return "redirect:/users/" + user.getId();
            }
        }
    }

    //处理注册
    @PostMapping("register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           RedirectAttributes attributes) {
        User user = userService.checkUsername(username);
        String[] message = new String[]{"用户名已经存在，请换一个", "The username already exists"};
        //如果用户名或密码为空
        if (username.equals("") || password.equals("")) {
            attributes.addFlashAttribute("message", "用户名或密码为空");
            return "redirect:/register";
        }
        //如果用户名存在
        if (user != null) {
            Message.setMessage(message, attributes);
            return "redirect:/register";
        } else {
            //用户名不存在 注册用户
            user = new User();
            user.setUsername(username);
            user.setPassword(MD5Utils.code(password));
            user.setAvatar("/img/avatar.jpg");
            userService.addUser(user);
        }
        return "redirect:/login";
    }

    //处理注销
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/";
    }
}
