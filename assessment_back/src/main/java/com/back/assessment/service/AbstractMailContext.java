package com.back.assessment.service;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

@Data
@Component
public abstract class AbstractMailContext {
    protected Context context=new Context();
    public abstract void setContext();
    public void setCode(String code) {
        context.setVariable("code", code);
    }
}
