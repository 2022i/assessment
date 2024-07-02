package com.back.assessment.mail;

import com.back.assessment.service.impl.RedisCacheServiceImpl;
import jakarta.annotation.Resource;
import jakarta.mail.internet.MimeMessage;
import lombok.Data;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


/**
 * @author lzz
 */
@Data
@Service
public class MailMail{
    @Resource
    private JavaMailSender mailSender;
    @Resource
    private TemplateEngine templateEngine;
    @Resource
    private RedisCacheServiceImpl redisCacheService;

    public void sendMail(String from, String to, String subject) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            message.setTo(to);
            message.setFrom(from);
            message.setSubject(subject);
            Random random = new Random();
            String code = String.valueOf(random.nextInt(900000) + 100000);
            System.out.println(code);
            Context context = new Context();
            context.setVariable("code", code);
            String text = templateEngine.process("email.html", context);

            message.setText(text, true);
            redisCacheService.set(to,code,60, TimeUnit.SECONDS);

            mailSender.send(mimeMessage);
        } catch (Exception e) {
            // handle exception
            e.printStackTrace();
        }
    }

    public void mailSend(String to) {
        sendMail("2383195232@qq.com",to,"项目考核系统注册验证码");
    }
}