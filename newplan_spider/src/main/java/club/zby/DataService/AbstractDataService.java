package club.zby.DataService;

import club.zby.Entity.SpiderData;
import club.zby.Loguntil.MyLogger;
import club.zby.mybatis.mapper.SpiderDataMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Author: 赵博雅
 * @Date: 2019/10/25 16:08
 */

public class AbstractDataService implements DataService<SpiderData> {

    private static SqlSessionFactory sqlSessionFactory;

    @Override
    public void init() throws IOException {
        String resource = "mybatis-config.xml";
        System.out.println("创建数据服务，用来保存数据至服务器");
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        MyLogger.log("YouthNewsService init over");
    }

    @Override
    public void closed() {

    }

    @Override
    public int add(SpiderData obj) {
        SqlSession session = sqlSessionFactory.openSession(true);
        SpiderDataMapper spiderDataMapper = session.getMapper(SpiderDataMapper.class);
        spiderDataMapper.insert(obj);
        session.close();
        return obj.getId();
    }

    @Override
    public void adds(List<SpiderData> obj) {
        SqlSession session = sqlSessionFactory.openSession(true);
        SpiderDataMapper spiderDataMapper = session.getMapper(SpiderDataMapper.class);
        spiderDataMapper.inserts(obj);
        session.close();
    }



    @Override
    public SpiderData get(int id) {
        return null;
    }

    @Override
    public void update(SpiderData obj) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<SpiderData> selectList() {
        SqlSession session = sqlSessionFactory.openSession(true);
        SpiderDataMapper spiderDataMapper = session.getMapper(SpiderDataMapper.class);
        List<SpiderData> youthNews = spiderDataMapper.selectList();
        session.close();
        return youthNews;
    }

}

