package club.zby.DataService.Example;

import club.zby.DataService.AbstractDataService;
import club.zby.Entity.SpiderData;
import club.zby.Loguntil.MyLogger;
import java.io.IOException;
import java.util.List;

/**
 * @Author: 赵博雅
 * @Date: 2019/10/28 9:52
 */
public class exampleDataService extends AbstractDataService {



    @Override
    public void init() throws IOException {
        MyLogger.log("初始化数据服务。。。。。。");
        super.init();
    }

    @Override
    public void adds(List<SpiderData> data){

        MyLogger.log("使用【自定义】dataService。。。。。。");
        super.adds(data);

    }

}
