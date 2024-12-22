package com.back.assessment.service.impl;

import com.back.assessment.service.AbstractMailSend;
import org.springframework.stereotype.Service;

@Service
public class MailSendForRegister extends AbstractMailSend {
    @Override
    public void setContext() {
        context=new RegistrationMailContext();
        context.setContext();
    }

    @Override
    public void setRequiredInformation(String to) {
        message.setRequiredInformation(to,"项目考核系统注册验证码");
    }
}
