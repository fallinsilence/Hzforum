package cn.free.domain;

import java.util.List;

public class Forum {
    private String fid;       //贴吧唯一标识
    private String fname;     //贴吧名
    private String category;  //所属于的分类
    private int fcount;       //帖子数
    private List<Post> posts; //所有帖子
    private int attention;    //是否关注，0为不关注，1为关注(可能不用)
    private List<User> users; //关注的用户

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getFcount() {
        return fcount;
    }

    public void setFcount(int fcount) {
        this.fcount = fcount;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public int getAttention() {
        return attention;
    }

    public void setAttention(int attention) {
        this.attention = attention;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
