package com.amrit.emailing;

import com.sendgrid.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * {
 *     "emailReceiver": [
 *         "amrit.dambul@gmail.com",
 *         "amrit08ju@gmail.com"
 *     ],
 *     "emailCcReceiver": [
 *         "amrit.dambul@gmail.com",
 *         "amrit08ju@gmail.com"
 *     ],
 *     "emailBccReceiver": [
 *         "amrit.dambul@gmail.com",
 *         "amrit08ju@gmail.com"
 *     ]
 * }
 *
 *{
 *     "emailReceiver": [
 *         "jshakan@jci.com",
 *         "jmishra1@jci.com"
 *     ],
 *     "emailCcReceiver": [
 *         "jjosejo@jci.com"
 *     ],
 *     "emailBccReceiver": [
 *         "jshanka@jci.com"
 *     ]
 * }
 *
 */

@SpringBootApplication
@RestController
public class EmailingApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailingApplication.class, args);
	}


    @GetMapping("/getEmailReq")
    public ResponseEntity<EmailReq> getMailReq() {
        EmailReq emailReq = new EmailReq();
        List<String> toEmails = new ArrayList<>();
        toEmails.add("amrit.dambul@gmail.com");
        toEmails.add("amrit08ju@gmail.com");
        emailReq.setEmailReceiver(toEmails);

        List<String> ccEmils = new ArrayList<>();
        ccEmils.add("amrit.dambul@gmail.com");
        ccEmils.add("amrit08ju@gmail.com");
        emailReq.setEmailCcReceiver(ccEmils);

        List<String> bccEmails = new ArrayList<>();
        bccEmails.add("amrit.dambul@gmail.com");
        bccEmails.add("amrit08ju@gmail.com");
        emailReq.setEmailBccReceiver(bccEmails);
        return new ResponseEntity<EmailReq>(emailReq, HttpStatus.OK);
    }

	@PostMapping("/domail")
	public void sendMyMail(@RequestBody EmailReq receiverEmail) {
		System.out.println("hi : "+ receiverEmail);
		send(receiverEmail);
	}

	private ResponseEntity<String>  send(EmailReq emailReq) {

		// Set content for request.
		Email to = new Email(emailReq.getEmailReceiver().get(0));
		Email from = new Email("jdixita1@jci.com");
		String subject = "[SendGrid] Testing !";
		Content content = new Content("text/plain", "This is to inform you i am experimenting SendGrid, Please put a filter if it bothers you.");
		Mail mail = new Mail(from, subject, to, content);
		Personalization personalization= new Personalization();

        System.out.println("TO : "+ emailReq.getEmailReceiver().get(0));
        System.out.println("CC : "+ emailReq.getEmailCcReceiver().get(0));
        System.out.println("BCC : "+ emailReq.getEmailBccReceiver().get(0));

        for(String toEmail : emailReq.getEmailReceiver())
            personalization.addTo(new Email(toEmail));

        for(String ccEmail : emailReq.getEmailCcReceiver())
            personalization.addCc(new Email(ccEmail));

        for(String bccEmails : emailReq.getEmailBccReceiver())
		    personalization.addBcc(new Email(bccEmails));

        mail.addPersonalization(personalization);


		// Instantiates SendGrid client.
		SendGrid sendgrid = new SendGrid("XXXXXX");

		// Instantiate SendGrid request.
		Request request = new Request();
        Response response;
        try {
			// Set request configuration.
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());

			// Use the client to send the API request.
            response= sendgrid.api(request);

            String str = response.getBody();


			if (response.getStatusCode() != 202) {
				System.out.println(String.format("An error occurred: %s", response.getStatusCode()));
                return new ResponseEntity(response.getBody(),HttpStatus.INTERNAL_SERVER_ERROR);
			}

			// Print response.
			System.out.println("Email sent. " + str);
		} catch (IOException e) {
			System.out.println("SendGrid I/O error " + e.getStackTrace().toString());
		}
        catch (Exception e) {
            System.out.println("SendGrid error " + e.getStackTrace().toString());
        }


        return new ResponseEntity("Email sent.",HttpStatus.OK);
    }

}
