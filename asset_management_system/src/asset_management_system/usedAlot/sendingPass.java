/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asset_management_system.usedAlot;

import asset_management_system.usedAlot.escapeChar;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Node;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import static jdk.nashorn.internal.objects.NativeString.toUpperCase;

public class sendingPass {

    escapeChar nw = new escapeChar();
    String userName = "root";
    String password = "";
    public Connection connection;
    String senderMail = "johnruben150@gmail.com";
    String pass = "106316008";

    public void position(String email, String position) {
        escapeChar escapee = new escapeChar();
        String Rmail = escapee.escapeChar1(email);

        try {
            String url = "jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=convertToNull";
            connection = DriverManager.getConnection(url, userName, password);
            Statement statement = connection.createStatement();
            Class.forName("com.mysql.jdbc.Driver");

            String details = "SELECT * FROM `asset_management_system`.`worker_details` WHERE  (workerEmail LIKE '%" + Rmail + "%')";
            ResultSet rs = connection.createStatement().executeQuery(details);

            while (rs.next()) {
                String sql = "INSERT INTO `asset_management_system`.`position` (`workerID`, `position`) VALUES (" + rs.getString(1) + ",'" + toUpperCase(position) + "' );";
                statement.executeUpdate(sql);
            }

        } catch (ClassNotFoundException ex) {

            System.err.println("Error: " + ex);
        } catch (SQLException ex) {
            Logger.getLogger(sendingPass.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void DBfunction(String ReMail, int pass) throws SQLException {
        try {
            String url = "jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=convertToNull";
            connection = DriverManager.getConnection(url, userName, password);
            Statement statement = connection.createStatement();
            Class.forName("com.mysql.jdbc.Driver");

            String account = "UPDATE `asset_management_system`.`worker_details` SET pass_word='" + pass + "' WHERE  (workerEmail LIKE '%" + ReMail + "%')";
            statement.executeUpdate(account);

            String workingAccount = "UPDATE `asset_management_system`.`current_workers` SET pass_word='" + pass + "' WHERE  (workerEmail LIKE '%" + ReMail + "%')";
            statement.executeUpdate(workingAccount);

        } catch (ClassNotFoundException ex) {

            System.err.println("Error: " + ex);
        }
    }

    public void NewAcc(String recipientMail) throws SQLException, ClassNotFoundException {
        int min = 100001;
        int max = 9999999;
        int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
        escapeChar escapee = new escapeChar();
        String Rmail = escapee.escapeChar1(recipientMail);

        DBfunction(Rmail, randomNum);

        // Recipient's email ID needs to be mentioned.
        String to = recipientMail;

        // Sender's email ID needs to be mentioned
        String from = senderMail;

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(senderMail, "106316008");

            }

        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("ACCOUNT SUCCESFULLY CREATED ");

            // Now set the actual message
            message.setText("YOUR ACCOUNT PASSOWRD IS : " + randomNum + " YOU CAN CHANGE IT WITHIN THE PROFILE SECTION AFTER A SUCCESFUL LOGIN");

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    public void recoverAcc(String recipientMail,Node node) throws SQLException {
        notification nww = new notification();

        int min = 100001;
        int max = 9999999;
        int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
        String Rmail = toUpperCase(nw.escapeChar1(recipientMail));

        DBfunction(Rmail, randomNum);

        // Recipient's email ID needs to be mentioned.
        String to = recipientMail;

        // Sender's email ID needs to be mentioned
        String from = senderMail;

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(senderMail, "106316008");

            }

        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("ACCOUNT RECOVERY ");

            // Now set the actual message
            message.setText("YOUR NEW ACCOUNT PASSOWRD IS : " + randomNum + " YOU CAN CHANGE IT WITHIN THE PROFILE SECTION AFTER A SUCCESFUL LOGIN");

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
            nww.flash(node, "YOUR NEW PASSWORD HAS BEEN SENT TO YOUR EMAIL.");
        } catch (MessagingException mex) {
            mex.printStackTrace();
            nww.flash(node, "AN ERROR OCCURED WHEN TRYING TO SEND YOU AN EMAIL,CHECK NETWORK CONNECTION.");
        }
    }

    public void sendAttachment(String sendTo, String subject, String text, File f, Node node) throws IOException {

        notification nw = new notification();

        // Recipient's email ID needs to be mentioned.
        String to = sendTo;

        // Sender's email ID needs to be mentioned
        String from = senderMail;

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass 
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(senderMail, "106316008");

            }

        });
        //session.setDebug(true);
        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject(subject);

            Multipart multipart = new MimeMultipart();

            MimeBodyPart attachmentPart = new MimeBodyPart();

            MimeBodyPart textPart = new MimeBodyPart();

            try {

                //File f =new File("H:\\pepipost_tutorials\\javaemail1.PNG");
                attachmentPart.attachFile(f);
                textPart.setText(text + " (File name " + f.getName() + ")");
                multipart.addBodyPart(textPart);
                multipart.addBodyPart(attachmentPart);

            } catch (IOException e) {

                e.printStackTrace();

            }

            message.setContent(multipart);

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
            nw.flash(node, "FILE HAS BEEN SENT");
        } catch (MessagingException mex) {
            mex.printStackTrace();
            nw.flash(node, "AN ERROR OCCURED FILE WASNT SENT.");
        }

    }

}
