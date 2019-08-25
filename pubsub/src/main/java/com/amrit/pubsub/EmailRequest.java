/**************************************************************************************
 * Copyright (c) 2019 Sensormatic Electronics, LLC.  All rights reserved.
 * Reproduction is forbidden without written approval of Sensormatic Electronics, LLC.
 **************************************************************************************/
package com.amrit.pubsub;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

public class EmailRequest {
    String subject;

    @NotEmpty
    String senderEmail;

    @NotEmpty
    List<String> emailReceiver;
    List<String> emailCcReceiver;
    List<String> emailBccReceiver;

    String sendGridTemplateId;
    Map<String, String> dynamicTemplateData;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public List<String> getEmailReceiver() {
        return emailReceiver;
    }

    public void setEmailReceiver(List<String> emailReceiver) {
        this.emailReceiver = emailReceiver;
    }

    public List<String> getEmailCcReceiver() {
        return emailCcReceiver;
    }

    public void setEmailCcReceiver(List<String> emailCcReceiver) {
        this.emailCcReceiver = emailCcReceiver;
    }

    public List<String> getEmailBccReceiver() {
        return emailBccReceiver;
    }

    public void setEmailBccReceiver(List<String> emailBccReceiver) {
        this.emailBccReceiver = emailBccReceiver;
    }

    public String getSendGridTemplateId() {
        return sendGridTemplateId;
    }

    public void setSendGridTemplateId(String sendGridTemplateId) {
        this.sendGridTemplateId = sendGridTemplateId;
    }

    public Map<String, String> getDynamicTemplateData() {
        return dynamicTemplateData;
    }

    public void setDynamicTemplateData(Map<String, String> dynamicTemplateData) {
        this.dynamicTemplateData = dynamicTemplateData;
    }

    @Override
    public String toString() {
        return "EmailRequest{" +
                "subject='" + subject + '\'' +
                ", senderEmail='" + senderEmail + '\'' +
                ", emailReceiver=" + emailReceiver +
                ", emailCcReceiver=" + emailCcReceiver +
                ", emailBccReceiver=" + emailBccReceiver +
                ", sendGridTemplateId='" + sendGridTemplateId + '\'' +
                '}';
    }
}
