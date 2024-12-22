package com.back.assessment.service.impl;

import com.back.assessment.service.AbstractMailContext;
import org.springframework.stereotype.Service;

public class RegistrationMailContext extends AbstractMailContext {
    @Override
    public void setContext() {
        context.setVariable("subject", "REGISTER");
        context.setVariable("src","https://img.zcool.cn/community/019b615de70a5ba8012138534fa69f.jpg??imageMogr2/format/webp");
    }
}
