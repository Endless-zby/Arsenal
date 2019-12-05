package club.zby.ftp.Service;


import club.zby.commen.Config.IdWorker;
import club.zby.ftp.Dao.FileDao;
import club.zby.ftp.Entity.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Map;

/**
 * @Author: 赵博雅
 * @Date: 2019/10/23 9:19
 */
@Service
public class DbService {

    @Autowired
    private FileDao fileDao;
    @Autowired
    private IdWorker idWorker;
    @Value("${page.size}")
    private int size;


    /**
     * 保存文件信息
     * @param userid
     * @param filename
     * @param filesize
     * @param filepath
     * @return
     */
    public String uploadPic(String userid,String filename,Long filesize,String filepath){
        FileInfo fileInfo = new FileInfo();
        fileInfo.setId(idWorker.nextId() + "");
        fileInfo.setUserid(userid);
        fileInfo.setFilename(filename);
        fileInfo.setFilesize(filesize);
        fileInfo.setFiledownnum(0);
        fileInfo.setTimestamp(new Date().getTime());
        fileInfo.setFilepath(filepath);
        fileInfo.setFiletag(0);
        FileInfo save = fileDao.save(fileInfo);
        if(save.getUserid() != null){
            return save.getFilepath();
        }
        return null;
    }

    /**
     * 查询文件的所属关系
     * @param fileId
     * @param userid
     * @return
     */
    public FileInfo findIdByFileName(String fileId,String userid){
        return fileDao.findAllById(fileId, userid);
    }

    /**
     * 删除文件（改文件状态）
     * @param fileId
     * @return
     */
    @Transactional
    public int deleteFile(String fileId){
        return fileDao.deleteByFileName(fileId);
    }

    /**
     * 修改文件下载次数 +1
     * @param fileName
     * @return
     */
    @Transactional
    public int downNumAddOne(String fileName){
        return fileDao.downNumAddOne(fileName);
    }

    /**
     * 分页查询
     * @return
     */
    public Page<Map> basefileList(Integer page){
        PageRequest pagerequest = PageRequest.of(page-1, size);
        return fileDao.selectByFiletag(pagerequest);
    }

    /**
     * 根据文件名查询文件的归属人昵称
     * @param fileid
     * @return
     */
    public String selectNickNameByFileId(String fileid){
        return fileDao.selectNickNameByFileId(fileid);
    }

    /**
     * 根据文件id查询文件名
     * @param fileid
     * @return
     */
    public String selectFileNameByFileId(String fileid){
        return fileDao.selectFileNameByFileId(fileid);
    }
}
