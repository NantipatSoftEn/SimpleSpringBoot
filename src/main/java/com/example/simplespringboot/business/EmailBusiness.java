package com.example.simplespringboot.business;

import com.example.common.EmailRequest;
import com.example.simplespringboot.exception.EmailException;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Service
@Log4j2
public class EmailBusiness {

    private  final KafkaTemplate<String, EmailRequest> kafkaEmailTemplate;
    public EmailBusiness( KafkaTemplate<String, EmailRequest> kafkaEmailTemplate) {

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
        html = html.replace("${P_NAME}",name);
        html = html.replace("${P_LINK}",finalLink);
        log.info("html"+html);
        EmailRequest request = new EmailRequest();
        request.setTo(email);
        request.setContent(html);
        request.setSubject("Please activate your account");


        ListenableFuture<SendResult<String,EmailRequest>> future = kafkaEmailTemplate.send("activation-email",request);
        future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onFailure(Throwable throwable) {
                log.error("Kafka send fail");
                log.error(throwable);
            }

            @Override
            public void onSuccess(SendResult<String, EmailRequest> result) {
                log.info("Kafka send success");
                log.info(result);
            }
        });


    }

    private String readEmailTemplate(String filename) throws IOException {
       File file= ResourceUtils.getFile("classpath:email/"+filename);
        return FileCopyUtils.copyToString(new FileReader(file));
    }
}
