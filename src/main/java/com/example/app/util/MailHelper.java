package com.example.app.util;

import org.apache.log4j.Logger;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MailHelper {
    private static final Logger LOGGER = Logger.getLogger(MailHelper.class);
    private static MailHelper instance = null;
    private static final String propertiesFileName = "mail.properties";
    private final String from;
    private final String host;
    private final String user;
    private final String password;
    private final Session session;

    private MailHelper(){
        LOGGER.info("Initializing MailProperties class");
        Properties properties = new Properties();
        try(InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propertiesFileName)) {
            if (inputStream != null) {
                properties.load(inputStream);

            } else { LOGGER.error("Mail property file not found on the classpath"); }

        }
        catch (IOException e) { LOGGER.error(e.getMessage()); }
        from = properties.getProperty("from");
        host = properties.getProperty("host");
        user = properties.getProperty("user");
        password = properties.getProperty("password");
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user, password);
                    }
                });

    }
    public static synchronized MailHelper getInstance() {
        if(instance == null) { instance = new MailHelper(); }
        return instance;
    }

    public void send(String to, String subject, String text){
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setText(text);
            Transport.send(message);
        } catch (Exception e) { LOGGER.error(e.getMessage()); }
    }

}