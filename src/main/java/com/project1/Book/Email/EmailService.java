package com.project1.Book.Email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine springTemplateEngine;

    @Async
    public void sentEmail(
            String to,String userName,EmailTemplateName emailTemplateName,String confirmationUrl, String activationCode, String subject) throws MessagingException {
        String templateName ;
        if(emailTemplateName == null){
            templateName = "confirm-email";
        }
        else{
            templateName = emailTemplateName.getName();
        }
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,MimeMessageHelper.MULTIPART_MODE_MIXED, StandardCharsets.UTF_8.name());
        Map<String,Object> properties  = new HashMap<>();
        properties.put("userName",userName);
        properties.put("confirmationUrl",confirmationUrl);
        properties.put("activation_code",activationCode);

        Context context = new Context();
        context.setVariables(properties);

        helper.setFrom("kvntarun13@gmail.com");
        helper.setTo(to);
        helper.setSubject(subject);

        String template = springTemplateEngine.process(templateName,context);

        helper.setText(template, true);

        mailSender.send(mimeMessage);

    }
}
