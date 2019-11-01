package club.zby.DataService;


import club.zby.Entity.NewsContext;
import club.zby.Loguntil.MyLogger;
import club.zby.mybatis.mapper.NewsContextMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Author: 赵博雅
 * @Date: 2019/10/31 18:40
 */
public class NewsContextService implements DataService<NewsContext> {

    private static SqlSessionFactory sqlSessionFactory;

    @Override
    public void init() throws IOException {
        String resource = "properties/mybatis-config.xml";
        System.out.println("创建数据服务，用来保存数据至服务器");
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        MyLogger.log("YouthNewsService init over");
    }

    @Override
    public void closed() {

    }

    @Override
    public int add(NewsContext obj) {
        return 0;
    }

    @Override
    public void adds(List<NewsContext> obj) throws ClassNotFoundException {
        SqlSession session = sqlSessionFactory.openSession(true);
        NewsContextMapper newsContextMapper = session.getMapper(NewsContextMapper.class);
        newsContextMapper.inserts(obj);
        session.close();
    }


    @Override
    public NewsContext get(int id) {
        return null;
    }

    @Override
    public void update(NewsContext obj) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<NewsContext> selectList() {
        return null;
    }
}
