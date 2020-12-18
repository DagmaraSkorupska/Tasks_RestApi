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
    private static final String ONETASK = "Currently in database you got one task";
    private static final String MANYTASK = "Currently number of tasks in your database: ";

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
            return ONETASK;
        }
        return MANYTASK + size;
    }
}
