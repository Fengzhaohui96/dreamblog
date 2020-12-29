package com.example.bolg.service;

import com.example.bolg.po.Blog;
import com.example.bolg.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BlogService {
    Blog getBlog( long id);
    Blog getAndConvert(Long id);
    Page<Blog> ListBlog(Pageable pageable, BlogQuery blogQuery );
    Page<Blog> listBlog(Pageable pageable);
    Page<Blog> listBlog(String query,Pageable pageable);
    Page<Blog> listBlog(Long id,Pageable pageable);

    Blog saveBlog(Blog blog);
    Blog updateBlog( Long id ,Blog blog);
    void deleteBlog(Long id);
    List<Blog> listRecommendBlogTop(Integer size);
}
