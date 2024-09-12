package org.mossmc.mosscg.EasyMail.Mail;

import org.mossmc.mosscg.EasyMail.BasicInfo;

import javax.mail.Session;
import java.util.Properties;

public class MailMain {
    public static String mailAccount = "";
    public static String mailPassword = "";
    public static String mailSMTPHost = "";
    public static String mailReceive = "";
    public static Session mailSession;
    public static void initMail() {
        mailAccount = BasicInfo.config.getString("mailAccount");
        mailPassword = BasicInfo.config.getString("mailPassword");
        mailSMTPHost = BasicInfo.config.getString("mailSMTPHost");
        mailReceive = BasicInfo.config.getString("mailReceive");
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.host", mailSMTPHost);
        props.setProperty("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", true);
        mailSession = Session.getInstance(props);
        mailSession.setDebug(BasicInfo.debug);
    }
}
