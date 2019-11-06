package club.zby.main;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: 赵博雅
 * @Date: 2019/11/4 11:51
 */
public class TouTiaoList implements Serializable {

    private String title;
    private String gallary_flag;
    private List image_list;
    private String article_url;
    private String cover_image_url;
    private String gallery_image_count;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGallary_flag() {
        return gallary_flag;
    }

    public void setGallary_flag(String gallary_flag) {
        this.gallary_flag = gallary_flag;
    }

    public List getImage_list() {
        return image_list;
    }

    public void setImage_list(List image_list) {
        this.image_list = image_list;
    }

    public String getArticle_url() {
        return article_url;
    }

    public void setArticle_url(String article_url) {
        this.article_url = article_url;
    }

    public String getCover_image_url() {
        return cover_image_url;
    }

    public void setCover_image_url(String cover_image_url) {
        this.cover_image_url = cover_image_url;
    }

    public String getGallery_image_count() {
        return gallery_image_count;
    }

    public void setGallery_image_count(String gallery_image_count) {
        this.gallery_image_count = gallery_image_count;
    }
}
