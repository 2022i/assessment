package com.back.assessment.service.impl;

import com.back.assessment.service.MailService;
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
public class MailServiceImpl implements MailService {
    @Resource
    private JavaMailSender mailSender;
    @Resource
    private TemplateEngine templateEngine;
    @Resource
    private RedisCacheServiceImpl redisCacheService;

    @Override
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
            context.setVariable("subject", subject);
            if(subject.equals("项目考核系统：用户注册验证码")){
                context.setVariable("src","https://img.zcool.cn/community/019b615de70a5ba8012138534fa69f.jpg??imageMogr2/format/webp");
            }else{
                context.setVariable("src","https://img.zcool.cn/community/013063604068a611013f3745c7f003.png@3000w_1l_2o_100sh.png");
            }

            String text = templateEngine.process("email.html", context);

            message.setText(text, true);
            redisCacheService.set(to,code,60, TimeUnit.SECONDS);

            mailSender.send(mimeMessage);
        } catch (Exception e) {
            // handle exception
            e.printStackTrace();
        }
    }

    @Override
    public void mailSendForRegister(String to) {
        sendMail("2383195232@qq.com",to,"项目考核系统：用户注册验证码");
    }

    @Override
    public void mailSendForForgetPassword(String to) {
        sendMail("2383195232@qq.com", to, "项目考核系统：忘记密码验证码");
    }
}