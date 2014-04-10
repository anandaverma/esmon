package com.fiberlink.elasticsearch.batchmonitor.util;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

public class MailClient {
	static Properties prop = new Properties();
	private static Logger logger = Logger.getLogger(MailClient.class);
	
	public static void loadProperties() {
		// load es-batch-monitor properties file
				try {
					prop.load(new FileInputStream(
							"resources//mail-client.properties"));
				} catch (FileNotFoundException e) {
					logger.error("mail-client.properties file not found ", e);
				} catch (IOException e) {
					logger.error("error reading properties file", e);
				}
	}

    public static boolean sendMail(String receiverEmailID,
                    String messageSubject, String messageBody) {
    		loadProperties();
            String subject = new String(messageSubject);
            String body = new String(messageBody);

            try {
                    Session sessionemail = Session.getDefaultInstance(prop);

                    Message message = new MimeMessage(sessionemail);
                    message.setRecipients(Message.RecipientType.TO,
                                    InternetAddress.parse(receiverEmailID));
                    message.setSubject(subject);
                    message.setText(body);
                    Transport.send(message);
                    return true;
            } catch (Exception e) {
            		logger.error("mail send falied", e);
                    return false;
            }

    }

    public static boolean sendHTMLEmail(String receiverEmailID,
                    String messageSubject, String messageBody/*, String senderEmailID,
                    String senderPassword*/) {
            /*final String from = new String(senderEmailID);
            final String pwd = new String(senderPassword); */
            String subject = new String(messageSubject);
            String body = new String(messageBody);
            
            loadProperties();

            try {
                    Session sessionemail = Session.getDefaultInstance(prop/*,
                                    new javax.mail.Authenticator() {
                                            protected PasswordAuthentication getPasswordAuthentication() {
                                                    return new PasswordAuthentication(from, pwd);
                                            }
                                    }*/);

                    Message message = new MimeMessage(sessionemail);
                    //message.setFrom(new InternetAddress(from));
                    message.setRecipients(Message.RecipientType.TO,
                                    InternetAddress.parse(receiverEmailID));
                    message.setSubject(subject);
                    message.setContent(body, "text/html");
                    Transport.send(message);
                    return true;
            } catch (Exception e) {
            	logger.error("mail send falied", e);
                return false;
            }
    }
    
    
    public static String formatMail(String msg) {
    	String header = "<html><head><head><body>";
    	String footer = "<br /><hr />This is auto generated mail, please do not reply to this mail.</body></html>";
    	String formattedMail = header + msg + footer;
    	return formattedMail; 
    }
}
