package club.zby.newplan.EmailTemplate;


import club.zby.newplan.Entity.MailBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
public class HttpEmail implements HttpEmailFace {

    @Autowired
    private EmailTemplateFace emailTemplateFace;
    @Autowired
    private TemplateEngine templateEngine;

    public void httpEmail(String id,String email){

        Context context = new Context();
        context.setVariable("id", id);
        MailBean mailBean = new MailBean();
        mailBean.setRecipient(email);
        mailBean.setSubject("zby,激活账号");
        String emailContent = templateEngine.process("emailTemplate", context);
        mailBean.setContent(emailContent);
        emailTemplateFace.sendHtmlMail(mailBean);

    }


}
