package club.zby.elasticsearch.Dao;

import club.zby.elasticsearch.Entity.EsFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Author: 赵博雅
 * @Date: 2019/10/24 9:36
 */


public interface EsDao extends ElasticsearchRepository<EsFile,String> {

    Page<EsFile> findAllByFilename(String filename, Pageable pageable);

}