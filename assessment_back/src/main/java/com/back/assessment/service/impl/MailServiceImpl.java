package com.back.assessment.service.impl;

import com.back.assessment.service.MailService;
import jakarta.annotation.Resource;
import lombok.Data;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;


/**
 * @author lzz
 */
@Data
@Service
public class MailServiceImpl implements MailService {

    @Resource
    private RedisCacheServiceImpl redisCacheService;
    @Resource
    private MessageHelperImp message;
    private Context context;

    @Override
    public void sendMail(String to, String subject) {
        try {
            message.setRequiredInformation(to, subject);
            message.setMessageHelper();
            setContext(subject);
            message.setText(context);
            message.send();
            redisCacheService.set(to,getCode(),60, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void mailSendForRegister(String to) {
        sendMail(to,"REGISTER");
    }

    @Override
    public void mailSendForForgetPassword(String to) {
        sendMail( to, "RESET");
    }

    @Override
    public void setContext(String subject) {
        context=new Context();
        context.setVariable("code", getCode());
        context.setVariable("subject", subject);
        if(subject.equals("REGISTER")){
            context.setVariable("src","https://img.zcool.cn/community/019b615de70a5ba8012138534fa69f.jpg??imageMogr2/format/webp");
        }else{
            context.setVariable("src","https://img.zcool.cn/community/013063604068a611013f3745c7f003.png@3000w_1l_2o_100sh.png");
        }
    }

    @Override
    public String getCode() {
        return message.getRequiredInformationForEmail().getCode();
    }
}