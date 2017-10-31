package cn.free.domain;

import java.util.List;

public class Post {
    private String pid;         //帖子唯一标识
    private String title;       //标题
    private String content;     //帖子内容
    private String category;    //分类
    private String postTime;    //发帖时间
    private String lastReplyTime;//最后回复时间
    private String pcount;      //回复数
    private List<Comment> commentList;  //所有楼层
    private Forum forum;        //属于的贴吧
    private User user;          //楼主
    private String lastReplyUser;      //最后一个回复的用户
    private String uname;       //楼主用户名
    private int maxFloor;       //当前帖子最大楼层

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public String getLastReplyTime() {
        return lastReplyTime;
    }

    public void setLastReplyTime(String lastReplyTime) {
        this.lastReplyTime = lastReplyTime;
    }

    public String getPcount() {
        return pcount;
    }

    public void setPcount(String pcount) {
        this.pcount = pcount;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public Forum getForum() {
        return forum;
    }

    public void setForum(Forum forum) {
        this.forum = forum;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getLastReplyUser() {
        return lastReplyUser;
    }

    public void setLastReplyUser(String lastReplyUser) {
        this.lastReplyUser = lastReplyUser;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public int getMaxFloor() {
        return maxFloor;
    }

    public void setMaxFloor(int maxFloor) {
        this.maxFloor = maxFloor;
    }
}
