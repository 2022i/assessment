package com.back.assessment.service;

import com.back.assessment.service.impl.MessageHelperImp;
import com.back.assessment.service.impl.RedisCacheServiceImpl;
import jakarta.annotation.Resource;
import lombok.Data;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;
/**
 * @author lzz
 */
@Data
@Service
public abstract class AbstractMailSend {
    @Resource
    private RedisCacheServiceImpl redisCacheService;
    @Resource
    protected MessageHelperImp message;
    protected AbstractMailContext context;

    public void sendMail(String to) {
        try {
            setRequiredInformation(to);
            message.setMessageHelper();
            setContext();
            message.setText(context.getContext());
            message.send();
            redisCacheService.set(to,getCode(),60, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public abstract void setContext();

    public String getCode() {
        return message.getRequiredInformationForEmail().getCode();
    }

    public abstract void setRequiredInformation(String to);
}
