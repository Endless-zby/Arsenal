package club.zby.elasticsearch.Service;

import club.zby.elasticsearch.Dao.EsDao;
import club.zby.elasticsearch.Entity.EsFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * @Author: 赵博雅
 * @Date: 2019/10/24 9:40
 */
@Service
public class EsService {

    @Autowired
    private EsDao esDao;

    public Page<EsFile> findAllByFilename(String filename, int start, int pageSize){
        PageRequest pageRequest = PageRequest.of(start, pageSize);
        return esDao.findAllByFilename(filename,pageRequest);

    }
}
