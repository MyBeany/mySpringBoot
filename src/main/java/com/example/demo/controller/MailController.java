package com.example.demo.controller;

import com.example.demo.core.constant.MailConstant;
import com.example.demo.core.ret.RetResponse;
import com.example.demo.core.ret.RetResult;
import com.example.demo.core.utils.ApplicationUtils;
import com.example.demo.model.Mail;
import com.example.demo.service.MailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/mail")
public class MailController {

    @Resource
    private MailService mailService;

    /**
     * 发送注册验证码
     * @param mail
     * @return 验证码
     * @throws Exception
     */
    @PostMapping("/sendTemplateMail")
    public RetResult<String> sendTemplateMail(Mail mail) throws Exception {
        String identifyingCode = ApplicationUtils.getNumStringRandom(6);
        mail.setSubject("欢迎注册初晨");
        mail.setTemplateName(MailConstant.RETGISTEREMPLATE);
        Map<String,String> map = new HashMap<>();
        map.put("identifyingCode",identifyingCode);
        map.put("to",mail.getTo()[0]);
        mail.setTemplateModel(map);
        mailService.sendTemplateMail(mail);

        return RetResponse.makeOKRsp(identifyingCode);
    }

    @PostMapping("/sendAttachmentsMail")
    public RetResult<String> sendAttachmentsMail(Mail mail,HttpServletRequest request) throws Exception {
        mail.setSubject("测试附件");
        mailService.sendAttachmentsMail(mail, request);
        return RetResponse.makeOKRsp();
    }
}
