package cn.free.dao;

import cn.free.domain.Ip;
import cn.free.domain.User;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class UserDao extends SqlSessionDaoSupport{

    @Resource()
    public void setSF(SqlSessionFactory factory) {
        super.setSqlSessionFactory(factory);
    }

    public User checkUsername(String username) {
        return getSqlSession().selectOne("cn.free.domain.User.queryUser", username);
    }

    public User login(String username, String password) {
        Map map = new HashMap();
        map.put("username", username);
        map.put("password", password);
        return getSqlSession().selectOne("cn.free.domain.User.queryOneUser", map);
    }

    public void updateIP(String ip) {
        getSqlSession().update("cn.free.domain.User.updateIP", ip);
    }

    //用户每次登录将此次登录信息存入数据库
    public void insertIP(Map map) {
        getSqlSession().insert("cn.free.domain.User.insertIP", map);
    }

    //查询用户详细登录信息
    public List<Ip> queryLoginDetailsByPage(Map map) {
        return getSqlSession().selectList("cn.free.domain.User.queryLoginDetailsByPage", map);
    }

    //查询登录记录总条数
    public int queryLoginRecordCounts(String uname) {
        return getSqlSession().selectOne("cn.free.domain.User.queryLoginRecordCounts", uname);
    }

    //添加新用户
    public void register(Map map) {
        getSqlSession().insert("cn.free.domain.User.register", map);
    }

    //删除多于100条的登录记录
    public void deleteLoginRecord(Map deleteMap) {
        getSqlSession().delete("cn.free.domain.User.deleteLoginRecord", deleteMap);
    }

/*    public List selectPhoto() {
        return getSqlSession().selectList("cn.free.domain.User.selectPhoto");
    }*/

    //更新照片
    public void updatePhoto(Map map) {
        getSqlSession().update("cn.free.domain.User.updatePhoto", map);
    }

    //更新昵称
    public void updateNickname(Map map) {
         getSqlSession().update("cn.free.domain.User.updateNickname", map);
    }

    //更新签名
    public void updateSignment(Map map) {
        getSqlSession().update("cn.free.domain.User.updateSignment", map);
    }
}
