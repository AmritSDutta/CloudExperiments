package com.amrit.emailing;

import org.springframework.lang.NonNull;

import java.util.List;

public class EmailReq {
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

    @Override
    public String toString() {
        return "EmailReq{" +
                "emailReceiver=" + emailReceiver +
                ", emailCcReceiver=" + emailCcReceiver +
                ", emailBccReceiver=" + emailBccReceiver +
                '}';
    }
}
