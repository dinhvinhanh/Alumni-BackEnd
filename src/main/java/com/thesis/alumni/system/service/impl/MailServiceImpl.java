package com.thesis.alumni.system.service.impl;

import com.thesis.alumni.system.entity.User;
import com.thesis.alumni.system.enums.MailType;
import com.thesis.alumni.system.model.Mail;
import com.thesis.alumni.system.service.MailService;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class MailServiceImpl implements MailService {
    private final JavaMailSender emailSender;
    private final SpringTemplateEngine templateEngine;
    private final Mail mail;

    @Override
    public void sendMail(Mail mail) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
        Context context = new Context();
        context.setVariables(mail.getProps());

        String html = templateEngine.process(mail.getTemplateName(), context);

        helper.setTo(mail.getMailTo());
        helper.setText(html, true);
        helper.setSubject(mail.getSubject());
        helper.setFrom(mail.getFrom());

        new Thread(() -> {
            emailSender.send(message);
        }).start();
    }

    @Override
    public Mail createMailActiveAccount(String email, String token) {
        mail.setMailTo(email.toLowerCase());
        mail.setSubject("Mail kích hoạt tài khoản");
        mail.setTemplateName("active-account");
        Map<String, Object> props = new HashMap<>();
        props.put("link", mail.getDomain() + "/api/accounts/active?token=" + token);
        props.put("expire", "2 giờ");
        mail.setProps(props);
        return mail;
    }
}
