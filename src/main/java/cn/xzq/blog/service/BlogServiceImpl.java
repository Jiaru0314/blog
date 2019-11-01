package cn.xzq.blog.service;

import cn.xzq.blog.dao.BlogRepository;
import cn.xzq.blog.po.Blog;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create by XuZhiQiang on 2019/10/21
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public Blog addBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    public Blog modifyBlog(Blog blog) {
        Blog blog1 = blogRepository.findById(blog.getId()).get();
        if (blog1 != null) {
            BeanUtils.copyProperties(blog, blog1);
            return blogRepository.save(blog1);
        }
        return null;
    }

    @Override
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }

    @Override
    public List<Blog> findAllBlogByCreateTime() {
        return blogRepository.findDistinctByCreateTime();
    }

    @Override
    public List<Blog> findAllBlogByViews() {
        return blogRepository.findDistinctByCounts();
    }

    @Override
    public List<Blog> findBlogByUserId(Long id) {
        return blogRepository.findBlogsByUserId(id);
    }

    @Override
    public Blog findBlogById(Long id) {
        return blogRepository.findBlogById(id);
    }
}
