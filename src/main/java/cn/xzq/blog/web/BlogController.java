package cn.xzq.blog.web;

import cn.xzq.blog.po.Blog;
import cn.xzq.blog.po.User;
import cn.xzq.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/posts")
public class BlogController {

    @Autowired
    private BlogService blogService;

    //返回postEditor页面
    @GetMapping("/postEditor")
    public String postEditor(Model model) {
        Blog blog = new Blog();
        blog.setId(0l);
        model.addAttribute("blog", blog);
        return "posts/postEditor";
    }

    @GetMapping("/postEditor/{id}")
    public String editEditor(@PathVariable Long id, Model model) {
        Blog blog = blogService.findBlogById(id);
        model.addAttribute("blog", blog);
        return "posts/postEditor";
    }

    @GetMapping("/manage")
    public String manage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        Long id = user.getId();
        List<Blog> blogs = blogService.findBlogByUserId(id);
        model.addAttribute("blogs", blogs);
        return "posts/manage";
    }

    @GetMapping("/latestPosts")
    public String latestPosts(Model model) {
        List<Blog> blogs = blogService.findAllBlogByCreateTime();
        model.addAttribute("blogs", blogs);
        model.addAttribute("state", "time");
        return "posts/latestPosts";
    }

    @GetMapping("/hotestPosts")
    public String hotestPosts(Model model) {
        List<Blog> blogs = blogService.findAllBlogByViews();
        model.addAttribute("blogs", blogs);
        model.addAttribute("state", "view");
        return "posts/latestPosts";
    }

    //处理Blog新增/修改
    @ResponseBody
    @PostMapping("/postPost")
    public String postPost(@RequestParam Long id,
                           @RequestParam String title,
                           @RequestParam String content,
                           HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (id != 0) {//修改
            Blog blog1 = blogService.findBlogById(id);
            System.out.println(blog1);
            blog1.setTitle(title);
            blog1.setContent(content);
            blogService.addBlog(blog1);
        } else {
            Blog blog2 = new Blog(title, content, new Date(), new Date(), user);
            blogService.addBlog(blog2);
        }
        return "/users/" + user.getId();
    }

    @GetMapping("/{id}")
    public String post(@PathVariable Long id, Model model) {
        Blog blog = blogService.findBlogById(id);
        blog.setCounts(blog.getCounts() + 1);
        blog = blogService.modifyBlog(blog);
        model.addAttribute("blog", blog);
        return "posts/blog";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        blogService.deleteBlog(id);
        return "redirect:/posts/manage";
    }
}
