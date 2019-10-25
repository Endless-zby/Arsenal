package club.zby.DataService;

import club.zby.Loguntil.MyLogger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.IOException;

/**
 * @Author: 赵博雅
 * @Date: 2019/10/25 16:08
 */
public class AbstractDataService implements DataService {

    private static JpaRepository jpaRepository;

    @Override
    public void init() throws IOException {

        MyLogger.log("初始化数据服务（mysql）");

    }


}

