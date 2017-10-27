package cn.free.domain;

import java.util.List;

public class User {
    private String uname;       //用户名(唯一)
    private String gender;      //性别
    private String nickname;    //昵称
    private String password;    //密码
    private String email;       //邮箱
    private String phone;       //电话号码
    private String photo;       //照片
    private String signment;    //签名档
    private List<Post> posts;   //发的帖子
    private List<Comment> comments; //回的帖子
    private List<Forum> forums;     //关注的吧

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getSignment() {
        return signment;
    }

    public void setSignment(String signment) {
        this.signment = signment;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Forum> getForums() {
        return forums;
    }

    public void setForums(List<Forum> forums) {
        this.forums = forums;
    }

}
