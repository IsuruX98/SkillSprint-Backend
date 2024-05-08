package com.skillsprint.notificationservice.service;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSendService {

    public static void sendEmail(String to, String subject, String body) {
        // Email configuration
        String username = "greenmart227@gmail.com";
        String password = "zspzyrgpkofyguya";

        // SMTP server properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com"); // Replace with your SMTP server host
        props.put("mail.smtp.port", "587"); // Replace with your SMTP server port
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        // Create a session with authentication
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create a MimeMessage object
            Message message = new MimeMessage(session);

            // Set From: header field
            message.setFrom(new InternetAddress(username));

            // Set To: header field
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

            // Set Subject: header field
            message.setSubject(subject);

            // Set Content: text/plain
            message.setText(body);

            // Send message
            Transport.send(message);

            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }
}
