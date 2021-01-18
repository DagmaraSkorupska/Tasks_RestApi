package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Service
public class EmailSchedulerService {
    @Autowired
    AdminConfig adminConfig;

    @Autowired
    CompanyConfig companyConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;


    public String buildTrelloCardEmailOnlyOnceDay(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/tasks_frontend/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getSecondUserName());
        context.setVariable("goodbye", "Good day!");
        context.setVariable("preview", "Information Email on Trello Card!");
        context.setVariable("nameCompany",companyConfig.getCompanyName());
        context.setVariable("emailCompany",companyConfig.getCompanyEmail());
        context.setVariable("phoneCompany",companyConfig.getCompanyPhone());
        context.setVariable("show_button", true);
        context.setVariable("is_friend", false);
        context.setVariable("admin_config", adminConfig);

        return templateEngine.process("mail/created-trello-card-mail-onlyDay", context);
    }

}
