package cn.xzq.blog.dao;

import cn.xzq.blog.po.Blog;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Create by XuZhiQiang on 2019/10/21
 */
@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
    List<Blog> findBlogsByUserId(Long id);

    @Query("select b from Blog b order by createTime desc ")
    List<Blog> findDistinctByCreateTime();

    @Query("select b from Blog b order by counts desc ")
    List<Blog> findDistinctByCounts();

    Blog findBlogById(Long id);
}
