package cn.free.service;

import cn.free.dao.UserDao;
import cn.free.domain.Page;
import cn.free.domain.User;
import cn.free.utils.IPUtil;
import cn.free.utils.UUIDUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class UserService {

    @Resource
    private UserDao dao;

    public String checkUsername(String username) {
        User user = dao.checkUsername(username);
        if (user == null){
            return "✔";
        }
        return "该用户名已被使用";
    }

    public User login(String username, String password) {
        return dao.login(username, password);
    }

    public void updateIP(String ip) {
        dao.updateIP(ip);
    }

    public void insertIP(String ip, String uname) {
        IPUtil.load("/Hzforum/src/main/java/cn/free/utils/17monipdb.dat");
        //获取当前城市
        String location = Arrays.toString(IPUtil.find(ip));
        //获取当前时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String time = df.format(new Date());// new Date()为获取当前系统时间
        //将数据存入map
        Map map = new HashMap();
        map.put("uname", uname);
        map.put("loginTime", time);
        map.put("location", location);
        //将IP信息插入数据库
        dao.insertIP(map);

        //查询数据库中所有的登录记录
        int totalCount = dao.queryLoginRecordCounts(uname);
        //需要删除的登录记录
        int deleteCount = 0;
        //只保存100条
        if (totalCount > 100){
            deleteCount = totalCount - 100;
        }
        //数据存入map
        Map deleteMap = new HashMap();
        deleteMap.put("uname", uname);
        deleteMap.put("deleteCount", deleteCount);
        dao.deleteLoginRecord(deleteMap);
    }

    public Page queryLoginDetailsByPage(String uname, int page) {
        Page p = new Page();
        //设置要显示的所有条目数
        int totalItems = dao.queryLoginRecordCounts(uname);
        p.setTotalItems(totalItems);
        //一页显示十条记录
        int limit = 10;
        p.setLimit(limit);
        //设置显示的总页数
        int totalPages = 0;
        if (totalItems % limit == 0) {
            totalPages = totalItems / limit;
        } else {
            totalPages = totalItems / limit + 1;
        }
        p.setTotalPages(totalPages);
        p.setPage(page);

        //设置Limit的两个参数,end就是limit
        int start = (page - 1) * limit;

        //要要查询的条件存入Map
        Map map = new HashMap();
        map.put("uname", uname);
        map.put("start", start);
        map.put("limit", limit);

        //查询第page页的数据
        p.setIpList(dao.queryLoginDetailsByPage(map));

        return p;
    }

    //注册用户
    public void register(String username, String gender, String nickname, String cellphone, String email, String password) {
        //将信息存入Map
        Map map = new HashMap();
        map.put("uname", username);
        map.put("gender", gender);
        map.put("nickname", nickname);
        map.put("phone", cellphone);
        map.put("password", password);
        dao.register(map);
    }

/*    public List selectPhoto() {
        return dao.selectPhoto();
    }*/

    public User updatePhoto(MultipartFile file, String uname, String realPath, String projectName) {
        //图片原先的文件名
        String originalName = file.getOriginalFilename();
        //获取图片的格式（带.）
        int index = originalName.lastIndexOf('.');
        String suffix = originalName.substring(index);

        //使用UUID类生成的新文件名
        String fileName = UUIDUtil.getUUID() + suffix;
        //存入服务器的位置
        String filePath = realPath + "\\" + fileName;
        //读取图片的路径
        String readPath = projectName + "/photo/" + fileName;

        //把文件存入服务器
        File newFile = new File(filePath);
        //把文件存入源文件
        File backup = new File("D:/Hzforum/src/main/webapp/photo/" + fileName);
        try {
            file.transferTo(newFile);
            file.transferTo(backup);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map map = new HashMap();
        map.put("uname", uname);
        map.put("photo", readPath);
        dao.updatePhoto(map);
        return dao.checkUsername(uname);
    }

    public User updatePhoto(String uname, String path) {
        Map map = new HashMap();
        map.put("uname", uname);
        map.put("photo", path);
        dao.updatePhoto(map);
        return dao.checkUsername(uname);
    }

    public User updateNickname(String uname, String nick) {
        Map map = new HashMap();
        map.put("uname", uname);
        map.put("nickname", nick);
        dao.updateNickname(map);
        return dao.checkUsername(uname);
    }

    public User updateSignment(String uname, String signment) {
        Map map = new HashMap();
        map.put("uname", uname);
        map.put("signment", signment);
        dao.updateSignment(map);
        return dao.checkUsername(uname);
    }
}
