package cn.free.controller;

import cn.free.domain.Comment;
import cn.free.domain.Forum;
import cn.free.domain.Post;
import cn.free.service.ForumService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

@Controller
@RequestMapping("/forum")
public class ForumController {

    @Resource
    private ForumService service;

    //查询贴吧中的所有帖子
    @RequestMapping("/selectForum.action")
    public String selectForum(String selectForum, HttpServletRequest request){
        //查找该贴吧存不存在
        Forum forum = service.selectForum(selectForum);
        HttpSession session = request.getSession();
        //如果不存在
        if (forum == null) {
            session.setAttribute("fName", selectForum);
            return "/forum/addForum";
        }
        //贴吧存在
        Forum wholeForum = service.queryPosts(selectForum);
        //如果贴吧中没有帖子
        if (wholeForum == null){
            session.setAttribute("forum", forum);
            session.setAttribute("info", "该帖吧还没有帖子");
        } else {
            session.setAttribute("forum", wholeForum);
            session.setAttribute("info", null);
        }
        return "/forum/home";
    }

    //创建贴吧
    @RequestMapping("/addForum.action")
    public String addForum(String fname, HttpServletRequest request){
        service.addForum(fname);
        request.setAttribute("msg","此贴吧创建成功");
        return null;
    }

    //查询该贴吧的所有帖子
    @RequestMapping("/queryPosts.action")
    public String queryPosts(String fname, HttpServletRequest request) throws UnsupportedEncodingException {
        //解码
        fname = URLDecoder.decode(fname, "utf-8");
        Forum forum = service.queryPosts(fname);
        request.getSession().setAttribute("forum", forum);
        return "/forum/home";
    }

    //添加新帖子
    @RequestMapping("/addNewPost.action")
    public String addNewPost(String title, String content, String uname, String fname, HttpServletRequest request) throws UnsupportedEncodingException {
        HttpSession session = request.getSession();
        //如果用户未登录
        if (session.getAttribute("user") == null){
            return "user/login";
        }
        //用户已登录
        service.addNewPost(uname, fname, title, content);
        //编码
        fname = URLEncoder.encode(fname, "utf-8");
        return "redirect:/forum/queryPosts.action?fname=" + fname;
    }


    //查看帖子详情
    @RequestMapping("/postDetail.action")
    public String postDetail(String pid, HttpServletRequest request){
        Post post = service.queryPost(pid);
        List<Comment> comments = service.queryComments(pid);
        request.getSession().setAttribute("post", post);
        request.getSession().setAttribute("comments", comments);
        return "/forum/postDetail";
    }

    //添加新评论
    @RequestMapping("/addComment.action")
    public String addComment(String comment, String uname, int maxFloor, String pid, int pcount, HttpServletRequest request){
        HttpSession session = request.getSession();
        //如果用户未登录
        if (session.getAttribute("user") == null){
            return "user/login";
        }
        //添加新评论
        service.addComment(comment, uname, maxFloor, pid, pcount);
        return "redirect:/forum/postDetail.action?pid="+ pid;
    }

}
