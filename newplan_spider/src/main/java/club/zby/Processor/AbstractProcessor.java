package club.zby.Processor;

import java.io.File;
import java.util.List;

/**
 * @Author: 赵博雅
 * @Date: 2019/10/25 15:53
 */
public abstract class AbstractProcessor<T> implements Processor<T> {

    protected String name;

    @Override
    public File parseToFile(File downloadFile) throws Exception {
        return null;
    }

    @Override
    public List<T> parseToList(File downloadFile) throws Exception {
        return null;
    }
}
