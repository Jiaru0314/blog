package cn.xzq.blog.service;

import cn.xzq.blog.po.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Create by XuZhiQiang on 2019/10/21
 */
public interface BlogService {
    //新增Blog
    Blog addBlog(Blog blog);

    //修改Blog
    Blog modifyBlog(Blog blog);

    //删除Blog
    void deleteBlog(Long id);

    //查询所有的Blog以时间排序
    List<Blog> findAllBlogByCreateTime();

    //查询所有的Blog以浏览量
    List<Blog> findAllBlogByViews();

    List<Blog> findBlogByUserId(Long id);

    //通过博客ID查询
    Blog findBlogById(Long id);
}
