package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {

    @Autowired
    private SimpleEmailService simpleEmailService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AdminConfig adminConfig;

    private static final String SUBJECT = "Tasks: Once a day email";
    private static final String TASK = "Currently in your database u got: ";

    //@Scheduled(cron = "0 0 10 * * *")
    @Scheduled(fixedDelay = 10000)
    private void sendInformationEmail(){
        simpleEmailService.send(prepareMail());
    }
    private Mail prepareMail(){
        long size = taskRepository.count();
        return new Mail(adminConfig.getAdminMail(), SUBJECT, formatMessage(size), null);
    }

    private String formatMessage(long size){
        if( size == 1){
            return TASK + size +" task";
        }
        return TASK + size + " tasks";
    }
}
