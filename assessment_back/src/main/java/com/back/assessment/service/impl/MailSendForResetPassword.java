package com.back.assessment.service.impl;

import com.back.assessment.service.AbstractMailSend;
import org.springframework.stereotype.Service;

@Service
public class MailSendForResetPassword extends AbstractMailSend {
    @Override
    public void setContext() {
        context=new ResetPasswordMailContext();
        context.setContext();
    }

    @Override
    public void setRequiredInformation(String to) {
        message.setRequiredInformation(to,"项目考核系统重置密码验证码");
    }
}
