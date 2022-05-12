package com.anhduc.noti_bank.sendmesenger;

import com.anhduc.noti_bank.model.AccountBalance;
import com.anhduc.noti_bank.serivce.ServiceAccountBalance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@Component
public class GmailBot {

    private static Logger logger = LoggerFactory.getLogger(TelegramBot.class);

    @Autowired
    ServiceAccountBalance blance;
    @Value("${spring.mail.username}")
    private String fromEmail;
    // Mat khai email cua ban
    @Value("${spring.mail.password}")
    private String password;

    @Value("${spring.mail.host}")
    private String  host;

    @Value("${spring.mail.port}")
    private String port;

    public void sendMailTest(String toEmails) throws MessagingException, UnsupportedEncodingException {

        // dia chi email nguoi nhan
        final String toEmail = toEmails;
        final String subject = "from AnhDucBank";


        List<AccountBalance> balances = blance.getNoti();

       // final String body = balances;

        Properties props = new Properties();
        props.put("mail.smtp.host", host); //SMTP Host
        props.put("mail.smtp.port", port); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true");//enable STARTTLS

        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
        Session session = Session.getInstance(props, auth);

        MimeMessage msg = new MimeMessage(session);
        //set message headers
        msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
        msg.addHeader("format", "flowed");
        msg.addHeader("Content-Transfer-Encoding", "8bit");

        msg.setFrom(new InternetAddress(fromEmail, "Thông báo biến động số dư"));
        msg.setReplyTo(InternetAddress.parse(fromEmail, false));
        msg.setSubject(subject, "UTF-8");

        msg.setText(balances.toString(), "UTF-8");

        msg.setSentDate(new Date());

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
        Transport.send(msg);
        System.out.println("Gui mail thanh cong");
    }

}
