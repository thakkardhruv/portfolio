package form;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



public class EmailMe {
	 String to = "dhruvthakkar1996@gmail.com";
	 String from = "thakkardhruv1996@gmail.com";
	
	 String password = "hellogmail";

	 public int send(String title, String msg, String phone, String email, String name){
		 String content = "Hello Dhruv,\nFollowing is request made from your website:\n\n"
				 + "Name : "+name+"\n"
				+ "Email : "+email+"\n"
		 		+ "Phone : "+phone+"\n"
		 		+ "Message : "+msg+"\n"
		 		+"\n\n\nRegards,\n#WebsiteAssistant Dhruv";
		 Properties props = new Properties();
		 props.put("mail.smtp.host", "smtp.gmail.com");    
         props.put("mail.smtp.socketFactory.port", "465");    
         props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");    
         props.put("mail.smtp.auth", "true");    
         props.put("mail.smtp.port", "465");
         
         Session session = Session.getInstance(props, 
        		 new javax.mail.Authenticator() { 
        	  		protected PasswordAuthentication getPasswordAuthentication() {
        	  			 return new PasswordAuthentication(from,password);  
        	  		}
         }
        );
         
         try{
        	 MimeMessage message = new MimeMessage(session);
        	 message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
//        	 message.setContent(content, "text/html; charset=utf-8");
        	 message.setSubject(title);
        	 message.setText(content);
        	 Transport.send(message); 
        	 return 1;
         } catch (Exception e) {
        	 return 0;
         }
	 }
}
