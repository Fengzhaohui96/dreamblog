package com.example.bolg.web.admin;

import com.example.bolg.po.Comment;
import com.example.bolg.po.User;
import com.example.bolg.service.BlogService;
import com.example.bolg.service.commentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class CommentController {

    @Autowired
    private commentService commentService;
    @Autowired
    private BlogService blogService;

    @Value("${Comment.avatar}")
    private String avatar;
    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable Long blogId, Model model){
        model.addAttribute("comments",commentService.listCommentByBlogId(blogId));
        return "bloginfo :: commentList";
    }
   @PostMapping("/comments")
    public String post(Comment comment, HttpSession session){
        Long blogId=comment.getBlog().getId();
         User user= (User) session.getAttribute("user");
        comment.setBlog(blogService.getBlog(blogId));

    if(user!=null){
        comment.setAvatar(user.getAvatar());
        comment.setAdminComment(true);
        comment.setNickname(user.getNickname());
    }else {
        comment.setAvatar(avatar);
        comment.setAdminComment(false);
    }
       commentService.SaveComment(comment);
        return "redirect:/comments/"+comment.getBlog().getId();
    }

}

