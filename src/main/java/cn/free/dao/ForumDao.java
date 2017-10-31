package cn.free.dao;

import cn.free.domain.Comment;
import cn.free.domain.Forum;
import cn.free.domain.Post;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class ForumDao extends SqlSessionDaoSupport{

    @Resource()
    public void setSF(SqlSessionFactory factory) {
        super.setSqlSessionFactory(factory);
    }

    public Forum selectForum(String selectForum) {
        return getSqlSession().selectOne("cn.free.domain.Forum.selectForum", selectForum);
    }

    public void addForum(Map map) {
        getSqlSession().insert("cn.free.domain.Forum.addForum", map);
    }

    public Forum queryPosts(String selectForum) {
        return getSqlSession().selectOne("cn.free.domain.Forum.queryPosts", selectForum);
    }

    public void addNewPost(Map map) {
        getSqlSession().insert("cn.free.domain.Forum.addNewPost", map);
    }

    public List<Comment> queryComments(String pid) {
        return getSqlSession().selectList("cn.free.domain.Comment.queryComments", pid);
    }

    public Post queryPost(String pid) {
        return getSqlSession().selectOne("cn.free.domain.Forum.queryPost", pid);
    }

    public void updatePost(Map updateMap) {
        getSqlSession().update("cn.free.domain.Forum.updatePost", updateMap);
    }

    public void addComment(Map insertIntoComment) {
        getSqlSession().insert("cn.free.domain.Comment.newComment", insertIntoComment);
    }
}
