package com.example.simplespringboot.business;

import com.example.simplespringboot.exception.EmailException;
import com.iamnbty.training.common.EmailRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Service
@Log4j2
public class EmailBusiness {

    @Value("${spring.mail.username}")
    private String form;
    private final JavaMailSender mailSender;
    private  final KafkaTemplate<String, EmailRequest> kafkaEmailTemplate;
    public EmailBusiness(JavaMailSender mailSender, KafkaTemplate<String, EmailRequest> kafkaEmailTemplate) {
        this.mailSender = mailSender;

        this.kafkaEmailTemplate = kafkaEmailTemplate;
    }

    public  void sendActivateUserEmail(String email,String name,String token) throws EmailException {

        String html = null;
        try{
             html  = readEmailTemplate("email-activate-user.html");
        }catch (IOException e){
            throw EmailException.templateNotFound();
        }


        log.info("Token= " + token);

        String finalLink = "http://localhost:4200/activate/"+token;
//        html = html.replace("${P_NAME}",name);
//        html = html.replace("${P_LINK}",finalLink);
        log.info("finalLink"+finalLink);
        EmailRequest request = new EmailRequest();
        request.setTo(email);
        request.setContent(finalLink);
        request.setSubject("Please activate your account");

        send(request.getTo(),request.getSubject(),request.getContent());
//        ListenableFuture<SendResult<String,EmailRequest>> future = kafkaEmailTemplate.send("activation-email",request);
//        future.addCallback(new ListenableFutureCallback<>() {
//            @Override
//            public void onFailure(Throwable throwable) {
//                log.error("Kafka send fail");
//                log.error(throwable);
//            }
//
//            @Override
//            public void onSuccess(SendResult<String, EmailRequest> result) {
//                log.info("Kafka send success");
//                log.info(result);
//            }
//        });


    }

    private String readEmailTemplate(String filename) throws IOException {
       File file= ResourceUtils.getFile("classpath:email/"+filename);
       return FileCopyUtils.copyToString(new FileReader(file));
    }

    public void send(String to,String subject , String html){
        System.out.println("MockSendEmail");
        MimeMessagePreparator message = mimeMessage -> {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
            helper.setFrom(form);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(html,true);
        };
        mailSender.send(message);
        log.info("MockSendEmail");
    }
}
