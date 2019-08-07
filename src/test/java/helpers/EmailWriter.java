package helpers;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class EmailWriter extends ParentHelper
{
    private String fromEmail;

    private Properties properties;
    private MimeMessage mimeMessage;
    private MimeBodyPart bodyPart;
    private Multipart multipart;

    private List<String> allEmails;
    private Address[] addresses;

    private int countEmails;

    public EmailWriter(String email, String password) throws IOException
    {
        fromEmail = email;

        properties = new Properties();
        properties.put("mail.smtp.host", "smtp.mail.yahoo.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.user", fromEmail);
        properties.put("mail.password", password);

        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, password);
            }
        };

        mimeMessage = new MimeMessage(Session.getInstance(properties, auth));

        // reads from file emails
        allEmails = Files.readAllLines(Paths.get(new File("./src/test/resources/emails.txt").getAbsolutePath()));
        countEmails = allEmails.size();
    }

    public void sent
    (
        String subject,
        String body,
        String filePath
    )
    throws Exception
    {
        log.info("Email is sent...");

        mimeMessage.addHeader("Content-type", "text/HTML; charset=UTF-8");
        mimeMessage.addHeader("format", "flowed");
        mimeMessage.addHeader("Content-Transfer-Encoding", "8bit");

        mimeMessage.setSubject(subject, "UTF-8");

        mimeMessage.setFrom(new InternetAddress(fromEmail));
        mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(fromEmail));

        // Create the message body part
        bodyPart = new MimeBodyPart();

        // Fill the message
        bodyPart.setText(body);

        // Create a multipart message for attachment
        multipart = new MimeMultipart();

        // Set text message part
        multipart.addBodyPart(bodyPart);

        // Second part is attachment
        bodyPart = new MimeBodyPart();
        bodyPart.attachFile(filePath);
        multipart.addBodyPart(bodyPart);

        // Send the complete message parts
        mimeMessage.setContent(multipart);

        addresses = new Address[countEmails];
        for (int numAdress = 0; numAdress < countEmails; numAdress++)
        {
            addresses[numAdress] = new InternetAddress(allEmails.get(numAdress));
        }

        // Send message to emails
        Transport.send(mimeMessage,  addresses);

        log.info("Email sent successfully");
    }
}
