package com.example.bolg.web;

import com.example.bolg.dao.BlogDao;
import com.example.bolg.po.Type;
import com.example.bolg.service.BlogService;
import com.example.bolg.service.TypeService;
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
public class blogByTypeController {
    @Autowired
    private TypeService typeService;
   @Autowired
    private BlogService blogService;
    @GetMapping("/types/{id}")
    public String types(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable, Model model, @PathVariable Long id) {
        List<Type> typeList = typeService.listTypeTop(10000);
           if(id==-1){
               id=typeList.get(0).getId();
           }
           BlogQuery blogQuery=new BlogQuery();
           blogQuery.setTypeId(id);
           model.addAttribute("types",typeList);
           model.addAttribute("page",blogService.ListBlog(pageable,blogQuery));
           model.addAttribute("activeTypeId",id);
           return "blogtypes";
    }


}
