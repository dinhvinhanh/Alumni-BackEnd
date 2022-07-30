package com.thesis.alumni.system.service;

import com.thesis.alumni.system.entity.User;
import com.thesis.alumni.system.enums.MailType;
import com.thesis.alumni.system.model.Mail;

import javax.mail.MessagingException;

public interface MailService {
    void sendMail(Mail mail) throws MessagingException;
    Mail createMailActiveAccount(String email, String token);
}
