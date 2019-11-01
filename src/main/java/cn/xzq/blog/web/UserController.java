package cn.xzq.blog.web;

import cn.xzq.blog.po.Blog;
import cn.xzq.blog.po.User;
import cn.xzq.blog.service.BlogService;
import cn.xzq.blog.service.UserService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/users")
    public String users(Model model) {
        List<User> users = userService.fingAllUser();
        model.addAttribute("users", users);
        return "users/users";
    }

    @GetMapping("/{id}")
    public String user(@PathVariable Long id, Model model) {
        List<Blog> blogs = blogService.findBlogByUserId(id);
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("blogs", blogs);
        model.addAttribute("id", id);
        return "users/user";
    }

    @GetMapping("/index")
    public String index() {
        return "users/index";
    }

    @GetMapping("/editInfo/{id}")
    public String editInfo(@PathVariable Long id, Model model) {
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        return "users/info";
    }


    @PostMapping("/editInfo")
    public String editInfo(@RequestParam MultipartFile file,
                           @RequestParam String username,
                           @RequestParam String nickname,
                           @RequestParam Long id,
                           HttpServletRequest request,
                           HttpSession session) throws IOException {
        if (!file.isEmpty()) {
            //上传文件路径
            String path = "/image/";
            //上传文件名
            String filename = file.getOriginalFilename();
            File file2 = new File(path, filename);
            byte[] bytes = file.getBytes();
            FileUtils.writeByteArrayToFile(file2, bytes);
            User sessionUser = (User) session.getAttribute("user");
            User user = new User(username, nickname, sessionUser.getPassword(), path + filename);
            user.setId(id);
            userService.modifyUserInfo(user);
        }
        return "redirect:/users/" + id;
    }
}
