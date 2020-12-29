package com.example.bolg.web;

import com.example.bolg.service.BlogService;
import com.example.bolg.service.TagsService;
import com.example.bolg.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class IndexController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagsService tagsService;
    @GetMapping("/")
    public String index(@PageableDefault(size=8,sort = {"updateTime"},direction= Sort.Direction.DESC)Pageable pageable, Model model){
     model.addAttribute("page",blogService.listBlog(pageable));
     model.addAttribute("types",typeService.listTypeTop(6));
     model.addAttribute("tags",tagsService.listTagTop(10));
     model.addAttribute("recommendBlogs",blogService.listRecommendBlogTop(8));
     /*   String blog=null;
        if(blog==null){

            throw new NotFoundException("博客不存在");
        }*/
        System.out.println("---------index---------------");
        return "index";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id,Model model) {
        model.addAttribute("blog", blogService.getAndConvert(id));
        return "bloginfo";
    }
    @PostMapping("/search")
    public String search(@PageableDefault(size=8,sort = {"updateTime"},direction= Sort.Direction.DESC)Pageable pageable,
                         @RequestParam String query, Model model){
        model.addAttribute("page",blogService.listBlog("%"+query+"%",pageable));
        model.addAttribute("query",query);
        return "search";

    }


}
