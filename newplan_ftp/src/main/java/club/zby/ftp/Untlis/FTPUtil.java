package club.zby.ftp.Untlis;

import club.zby.commen.Config.Result;
import club.zby.commen.Config.StatusCode;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;

/**
 * @Author: 赵博雅
 * @Date: 2019/10/22 14:51
 */
@Component
public class FTPUtil {

    @Value("${FTP.host}")
    private String host;
    @Value("${FTP.port}")
    private int port;
    @Value("${FTP.username}")
    private String username;
    @Value("${FTP.password}")
    private String password;
    @Value("${FTP.basePath}")
    private String basePath;

    /**
     * Description: 向FTP服务器上传文件
     * host FTP服务器hostname
     * port FTP服务器端口
     * username FTP登录账号
     * password FTP登录密码
     * basePath FTP服务器基础目录
     *
     * @param filePath FTP服务器文件存放路径。例如分日期存放：/2015/01/01。文件的路径为basePath+filePath
     * @param filename 上传到FTP服务器上的文件名
     * @param input    输入流
     * @return 成功返回true，否则返回false
     */
    public Result uploadFile(String filePath, String filename, InputStream input) {
        boolean result = false;
        FTPClient ftp = new FTPClient();

        try {
            int reply;
            ftp.connect(host, port);// 连接FTP服务器
            // 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
            ftp.login(username, password);// 登录
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return new Result(result, StatusCode.ERROR, "TFP连接失败", null);
            }
            //切换到上传目录
            if (!ftp.changeWorkingDirectory(basePath + filePath)) {
                //如果目录不存在创建目录
                String[] dirs = filePath.split("/");
                String tempPath = basePath;
                for (String dir : dirs) {
                    if (null == dir || "".equals(dir)) continue;
                    tempPath += "/" + dir;
                    if (!ftp.changeWorkingDirectory(tempPath)) {
                        if (!ftp.makeDirectory(tempPath)) {
                            return new Result(result, StatusCode.ERROR, "上传路径错误", null);
                        } else {
                            ftp.changeWorkingDirectory(tempPath);
                        }
                    }
                }
            }
            //设置上传文件的类型为二进制类型
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            //上传文件
            if (!ftp.storeFile(filename, input)) {
                return new Result(result, StatusCode.ERROR, "文件上传失败", null);
            }
            input.close();
            ftp.logout();
            result = true;
        } catch (IOException e) {
            return new Result(result, StatusCode.ERROR, e.getMessage(), null);
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                    return new Result(result, StatusCode.ERROR, ioe.getMessage(), null);
                }
            }
        }
        return new Result(result, StatusCode.OK, "上传成功", null);
    }


    /**
     * Description: 获取FTP文件列表
     * host FTP服务器host
     * port FTP服务器端口
     * username FTP登录账号
     * password FTP登录密码
     * remotePath FTP服务器上的相对路径
     *
     * @return
     */
    public Result fileList() {
        boolean success = false;
        FTPClient ftp = new FTPClient();
        FTPFile[] fs = new FTPFile[0];
        try {
            int reply;
            ftp.connect(host, port);
            //如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
            ftp.login(username, password);//登录
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return new Result(success, StatusCode.ERROR, "FTP连接失败", null);
            }
            ftp.changeWorkingDirectory("/");//转移到FTP服务器目录
            fs = ftp.listFiles();
            ftp.logout();
            success = true;
        } catch (IOException e) {
            return new Result(success, StatusCode.ERROR, e.getMessage(), null);
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                    return new Result(success, StatusCode.ERROR, ioe.getMessage(), null);
                }
            }
        }
        return new Result(success, StatusCode.OK, "获取列表成功", fs);
    }


    /**
     * Description: 从FTP服务器下载文件
     *
     * host        FTP服务器host
     * port       FTP服务器端口
     * username   FTP登录账号
     * password   FTP登录密码
     * remotePath FTP服务器上的相对路径
     * @param fileName   要下载的文件名
     * @param localPath  下载后保存到本地的路径
     * @return
     */

    public Result downFile(String fileName, String localPath) {
        boolean success = false;
        FTPClient ftp = new FTPClient();
        try {
            int reply;
            ftp.connect(host, port);
            //如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
            ftp.login(username, password);//登录
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return new Result(success, StatusCode.ERROR, "FTP连接失败", null);
            }
            ftp.changeWorkingDirectory("/");//转移到FTP服务器目录
            FTPFile[] fs = ftp.listFiles();
            for (FTPFile ff : fs) {
                if (ff.getName().equals(fileName)) {
                    File localFile = new File(localPath + "/" + ff.getName());

                    OutputStream is = new FileOutputStream(localFile);
                    ftp.retrieveFile(ff.getName(), is);
                    is.close();
                }
            }

            ftp.logout();
            success = true;
        } catch (IOException e) {
            return new Result(success, StatusCode.ERROR, e.getMessage(), null);
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return new Result(success, StatusCode.OK, "下载成功", fileName);
    }


    public Result deleteFile(String fileName) {
        boolean success = false;
        FTPClient ftp = new FTPClient();
        ftp.setControlEncoding("GBK");
        try {
            int reply;
            ftp.connect(host, port);// 连接FTP服务器
            ftp.login(username, password);// 登录
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return new Result(success, StatusCode.ERROR, "FTP连接失败", null);
            }
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftp.makeDirectory(basePath);
            ftp.changeWorkingDirectory(basePath);
            success = ftp.deleteFile(fileName);
            ftp.logout();
        } catch (IOException e) {
            return new Result(success, StatusCode.ERROR, e.getMessage(), null);
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                    return new Result(success, StatusCode.ERROR, ioe.getMessage(), null);
                }
            }
        }
        return new Result(success, StatusCode.OK, "删除成功", fileName);
    }


}
