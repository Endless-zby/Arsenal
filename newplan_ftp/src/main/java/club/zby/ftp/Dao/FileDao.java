package club.zby.ftp.Dao;

import club.zby.ftp.Entity.FileInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;


/**
 * @Author: 赵博雅
 * @Date: 2019/10/23 10:09
 */
public interface FileDao extends JpaRepository<FileInfo,String>, JpaSpecificationExecutor<FileInfo> {

    /**
     * 查询状态为0的文件的所属关系
     * @param fileId
     * @param id
     * @return
     */
    @Query(nativeQuery = true,value = "select * from tb_file where id = ?1 and userid = ?2 and filetag = 0")
    FileInfo findAllById(String fileId,String id);

    /**
     * 根据文件名删除文件，改变数据filetag状态为：1（失效）
     * @param fileId
     * @return
     */
    @Modifying
    @Query(nativeQuery = true,value = "update tb_file t set t.filetag = 1 where id = ?")
    int deleteByFileName(String fileId);

    /**
     * 下载次数加一
     * @param fileName
     * @return
     */
    @Modifying
    @Query(nativeQuery = true,value = "update tb_file t set t.filedownnum = t.filedownnum + 1 where filename = ?")
    int downNumAddOne(String fileName);

    /**
     * 分页查询
     * @param pageable
     * @return
     */
//    @Query(nativeQuery = true,value = "select * from tb_file where filetag = '0' ORDER BY timestamp desc")
    @Query(nativeQuery = true,value = "select tb_file.userid,tb_file.id,tb_file.filename,tb_file.filesize,tb_file.filedownnum,tb_file.timestamp,tb_file.filepath,tb_file.filetag,tb_user.nickname from tb_user , tb_file where tb_user.id = tb_file.userid and tb_file.filetag = '0' ORDER BY tb_file.timestamp desc")
    Page<Map> selectByFiletag(Pageable pageable);

    /**
     * 根据文件名查询文件的归属人昵称
     * @param fileId
     * @return
     */
    @Query(nativeQuery = true,value = "select tb_user.nickname from tb_user , tb_file where tb_user.id = tb_file.userid and tb_file.filetag = '0' and tb_file.id = ?")
    String selectNickNameByFileId(String fileId);

    /**
     * 根据文件id查询文件名
     * @param fileId
     * @return
     */
    @Query(nativeQuery = true,value = "select tb_file.filename from tb_file where tb_file.id = ? and tb_file.filetag = '0'")
    String selectFileNameByFileId(String fileId);
}
