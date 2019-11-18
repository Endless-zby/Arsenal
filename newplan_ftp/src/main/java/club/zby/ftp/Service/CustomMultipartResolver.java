package club.zby.ftp.Service;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: 赵博雅
 * @Date: 2019/11/18 18:06
 */
@Component
public class CustomMultipartResolver extends CommonsMultipartResolver {

    @Autowired
    private FileUploadProgressListener listener;

    @Override
    protected MultipartParsingResult parseRequest(HttpServletRequest request) throws MultipartException {
        String encoding = determineEncoding(request);
        FileUpload fileUpload = prepareFileUpload(encoding);

        fileUpload.setProgressListener(listener);
        listener.setSession(request.getSession());
        try {
            List<FileItem> fileItems = ((ServletFileUpload) fileUpload).parseRequest(request);
            return parseFileItems(fileItems, encoding);
        }catch (FileUploadException ex) {
            throw new MultipartException("Failed to parse multipart servlet request", ex);
        }
    }
}
