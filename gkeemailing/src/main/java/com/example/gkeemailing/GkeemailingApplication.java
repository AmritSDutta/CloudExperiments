package com.example.gkeemailing;

import com.sendgrid.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * ##Example:1
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
 * ##Example:2
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
 * ##Example:3
 * {
 *     "subject": "This is subject of Email",
 *     "senderEmail": "iAmTheSender@mycompany.com",
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
 */

@SpringBootApplication
@RestController
public class GkeemailingApplication {

	public static void main(String[] args) {
		SpringApplication.run(GkeemailingApplication.class, args);
	}
	@GetMapping("/getEmailReq")
	public ResponseEntity<EmailReq> getMailReq() {
		EmailReq emailReq = new EmailReq();
		emailReq.setSubject("This is subject of Email");
		emailReq.setSenderEmail("iAmTheSender@mycompany.com");

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
		String fromEmail = "jshanka@jci.com";
		if(emailReq.getSenderEmail() != null)
			fromEmail = emailReq.getSenderEmail();

		Email from = new Email(fromEmail);

		String subject = "[SendGrid] Testing !";
		if(emailReq.getSubject() != null)
			subject = emailReq.getSubject();

		Content content = new Content("text/plain", "This is to inform you i am experimenting SendGrid, Please put a filter if it bothers you.");
		Mail mail = new Mail(from, subject, to, content);
		Personalization personalization= new Personalization();

		System.out.println("TO : "+ emailReq.getEmailReceiver().get(0));
		for(String toEmail : emailReq.getEmailReceiver())
			personalization.addTo(new Email(toEmail));

		if(emailReq.getEmailCcReceiver() != null && emailReq.getEmailCcReceiver().size() > 0)
		{
			System.out.println("CC : "+ emailReq.getEmailCcReceiver().get(0));
			for(String ccEmail : emailReq.getEmailCcReceiver())
				personalization.addCc(new Email(ccEmail));
		}

		if(emailReq.getEmailBccReceiver() != null && !emailReq.getEmailBccReceiver().isEmpty())
		{
			System.out.println("BCC : "+ emailReq.getEmailBccReceiver().get(0));
			for(String bccEmails : emailReq.getEmailBccReceiver())
				personalization.addBcc(new Email(bccEmails));
		}
		mail.addPersonalization(personalization);

		// Instantiates SendGrid client.
		SendGrid sendgrid = new SendGrid("XXXXX");

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


			if (response.getStatusCode() != 200) {
				System.out.println(String.format("An error occurred: %s", response.getStatusCode()));
				return new ResponseEntity(response.getBody(), HttpStatus.INTERNAL_SERVER_ERROR);
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
