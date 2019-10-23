package club.zby.ftp.Service;


import club.zby.commen.Config.IdWorker;
import club.zby.ftp.Dao.FileDao;
import club.zby.ftp.Entity.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

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
        fileInfo.setFileid(idWorker.nextId() + "");
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
     * @param fileName
     * @param userid
     * @return
     */
    public int findIdByFileName(String fileName,String userid){
        return fileDao.findAllById(fileName, userid);
    }

    /**
     * 删除文件（改文件状态）
     * @param fileName
     * @return
     */
    @Transactional
    public int deleteFile(String fileName){
        return fileDao.deleteByFileName(fileName);
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


}
