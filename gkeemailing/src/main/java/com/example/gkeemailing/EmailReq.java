package com.example.gkeemailing;

import org.springframework.lang.NonNull;

import java.util.List;

public class EmailReq {
    String subject;
    String senderEmail;
    @NonNull
    List<String> emailReceiver;

    List<String> emailCcReceiver;
    List<String> emailBccReceiver;

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

    @Override
    public String toString() {
        return "EmailReq{" +
                "emailReceiver=" + emailReceiver +
                ", emailCcReceiver=" + emailCcReceiver +
                ", emailBccReceiver=" + emailBccReceiver +
                '}';
    }
}
