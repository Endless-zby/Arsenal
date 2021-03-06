package club.zby.newplan.EmailTemplate;

import club.zby.newplan.Entity.MailBean;
import org.springframework.stereotype.Component;

@Component
public interface EmailTemplateFace {

    void sendSimpleMail(MailBean mailBean);
    void sendAttachmentMail(MailBean mailBean);
    void sendHtmlMail(MailBean mailBean);

}
