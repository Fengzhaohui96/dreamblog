package com.example.bolg.web.admin;

import com.example.bolg.po.Tag;
import com.example.bolg.po.Type;
import com.example.bolg.service.BlogService;
import com.example.bolg.service.TagsService;
import com.example.bolg.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class blogByTagsController {
    @Autowired
    private TagsService tagsService;
    @Autowired
    private BlogService blogService;
    @GetMapping("/tags/{id}")
    public String tags(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable, Model model, @PathVariable Long id) {
        List<Tag> tagList = tagsService.listTagTop(1000);
        if(id==-1){
            id=tagList.get(0).getId();
        }
        model.addAttribute("tags",tagList);
        model.addAttribute("page",blogService.listBlog(id,pageable));
        model.addAttribute("activeTypeId",id);
        return "blogtags";
    }






}
