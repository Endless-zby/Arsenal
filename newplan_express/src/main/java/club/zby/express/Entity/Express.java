package club.zby.express.Entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 快鸟快递
 */
@Configuration
public class Express {
    @Value("${Express.EBusinessID}")
    private String EBusinessID;
    @Value("${Express.AppKey}")
    private String AppKey;
    @Value("${Express.ReqURL}")
    private String ReqURL;

    public String getEBusinessID() {
        return EBusinessID;
    }

    public void setEBusinessID(String EBusinessID) {
        this.EBusinessID = EBusinessID;
    }

    public String getAppKey() {
        return AppKey;
    }

    public void setAppKey(String appKey) {
        AppKey = appKey;
    }

    public String getReqURL() {
        return ReqURL;
    }

    public void setReqURL(String reqURL) {
        ReqURL = reqURL;
    }
}
