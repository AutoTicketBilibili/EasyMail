package org.mossmc.mosscg.EasyMail.Mail;

import org.mossmc.mosscg.EasyMail.BasicInfo;

import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;

public class MailSend {
    public static void sendMail(String title, String content, String senderName) {
        try {
            MimeMessage message = new MimeMessage(MailMain.mailSession);
            message.setFrom(new InternetAddress(MailMain.mailAccount, senderName, "UTF-8"));
            message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(MailMain.mailReceive, MailMain.mailReceive, "UTF-8"));
            message.setSubject(title, "UTF-8");
            message.setContent(content, "text/html;charset=UTF-8");
            message.setSentDate(new Date());
            message.saveChanges();
            Transport mailTransport = MailMain.mailSession.getTransport();
            mailTransport.connect(MailMain.mailAccount, MailMain.mailPassword);
            mailTransport.sendMessage(message, message.getAllRecipients());
            mailTransport.close();
        } catch (Exception e) {
            BasicInfo.logger.sendException(e);
        }
    }
}
