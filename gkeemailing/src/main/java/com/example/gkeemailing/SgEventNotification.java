package com.example.gkeemailing;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.bind.annotation.Mapping;

/**
 * {
 *       "email":"example@test.com",
 *       "timestamp":1513299569,
 *       "smtp-id":"<14c5d75ce93.dfd.64b469@ismtpd-555>",
 *       "event":"processed",
 *       "category":"cat facts",
 *       "sg_event_id":"sg_event_id",
 *       "sg_message_id":"sg_message_id"
 *    }
 */
public class SgEventNotification {
    @JsonProperty(value = "email")
    String toEmail;
    String timestamp;
    @JsonProperty(value = "smtp-id")
    String smtpId;
    String event;
    String category;
    @JsonProperty(value = "sg_event_id")
    String sgEventId;

    @JsonProperty(value = "sg_message_id")
    String sgMessageId;
    @JsonProperty(value = "MailId")
    String mailId;

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSmtpId() {
        return smtpId;
    }

    public void setSmtpId(String smtpId) {
        this.smtpId = smtpId;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSgEventId() {
        return sgEventId;
    }

    public void setSgEventId(String sgEventId) {
        this.sgEventId = sgEventId;
    }

    public String getSgMessageId() {
        return sgMessageId;
    }

    public void setSgMessageId(String sgMessageId) {
        this.sgMessageId = sgMessageId;
    }

    public String getMailId() {
        return mailId;
    }

    public void setMailId(String mailId) {
        this.mailId = mailId;
    }

    @Override
    public String toString() {
        return "SgEventNotification{" +
                "toEmail='" + toEmail + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", event='" + event + '\'' +
                ", mailId='" + mailId + '\'' +
                '}';
    }
}
