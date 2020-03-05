package club.zby.excel.service;

import club.zby.excel.Dao.Dao;
import club.zby.excel.entity.Movieinfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import club.zby.excel.poi.Testpoi;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Author: 赵博雅
 * @Date: 2020/3/5 22:41
 */
@Component
@Order(value = 1)
public class service implements ApplicationRunner {
    @Autowired
    private Dao dao;
    @Autowired
    private Testpoi testpoi;
    @Value("${excel.url}")
    private String url;
    @Autowired
    private static final Logger LOGGER = LoggerFactory.getLogger(service.class);

    @Override
    @Transactional
    public void run(ApplicationArguments applicationArguments) throws Exception {


        int num = dao.qerAll();
        LOGGER.info("数据库总数据量：【{}】",num);
        LOGGER.info("初始化文件解析器，待解析文件--->【{}】",url);
        List<String> list = testpoi.perExcel(url);
        List<Movieinfo> all = dao.findAll();
        for (int i = 0; i < all.size(); i++) {
            all.get(i).setPicture(list.get(i));
        }
        List<Movieinfo> movieinfos = dao.saveAll(all);
        LOGGER.info("数据修改成功 ！ 共修改【{}】条数据",movieinfos.size());

    }
}
