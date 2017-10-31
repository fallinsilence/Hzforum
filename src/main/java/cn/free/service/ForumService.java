package cn.free.service;

import cn.free.dao.ForumDao;
import cn.free.domain.Comment;
import cn.free.domain.Forum;
import cn.free.domain.Post;
import cn.free.utils.UUIDUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ForumService {

    @Resource
    private ForumDao dao;
    public Forum selectForum(String selectForum) {
        return dao.selectForum(selectForum);
    }

    public void addForum(String fname) {
        //获取当前时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String time = df.format(new Date());// new Date()为获取当前系统时间
        Map map = new HashMap();
        map.put("createTime", time);
        map.put("fname", fname);
        map.put("fcount", 0);
        dao.addForum(map);
    }

    public Forum queryPosts(String selectForum) {
        return dao.queryPosts(selectForum);
    }

    public void addNewPost(String uname, String fname, String title, String content) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String postTime = df.format(new Date());// new Date()为获取当前系统时间
        String pid = UUIDUtil.getUUID();
        Map map = new HashMap();
        map.put("uname", uname);
        map.put("fname", fname);
        map.put("pid", pid);
        map.put("title", title);
        map.put("content", content);
        map.put("postTime", postTime);
        map.put("lastReplyTime", postTime);
        map.put("lastReplyUser", uname);
        dao.addNewPost(map);
    }

    public List<Comment> queryComments(String pid) {
        return dao.queryComments(pid);
    }

    public Post queryPost(String pid) {
        return dao.queryPost(pid);
    }

    public void addComment(String comment, String uname, int maxFloor, String pid, int pcount) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String lastReplyTime = df.format(new Date());// new Date()为获取当前系统时间
        String cid = UUIDUtil.getUUID();    //回帖唯一标识
        //获取帖子当前最大楼层，加一，并在数据库更新最大楼层数,最后回帖人和最后回帖时间
        int floor = maxFloor + 1;
        Map updateMap = new HashMap();
        updateMap.put("pid", pid);
        updateMap.put("maxFloor", floor);
        updateMap.put("lastReplyTime", lastReplyTime);
        updateMap.put("lastReplyUser", uname);
        updateMap.put("pcount", pcount+1);
        dao.updatePost(updateMap);
        //向评论表插入数据,并向ucp表添加数据
        Map insertIntoComment = new HashMap();
        insertIntoComment.put("cid", cid);
        insertIntoComment.put("pid", pid);
        insertIntoComment.put("floor", floor);
        insertIntoComment.put("uname", uname);
        insertIntoComment.put("comment", comment);
        insertIntoComment.put("commentTime", lastReplyTime);
        insertIntoComment.put("commentUser", uname);
        dao.addComment(insertIntoComment);
    }
}
