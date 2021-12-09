package uk.ac.reigate.onlineapplications.service.communication

import javax.mail.internet.MimeMessage

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.MailException
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service

@Service
class EmailService {
    
    @Autowired
    JavaMailSender mailSender

    public void sendMessage(String to, String subject, String text) throws MailException {
        MimeMessage message = mailSender.createMimeMessage()
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8")
        helper.setTo(to)
        helper.setBcc("mis.mailings@reigate.ac.uk")
        helper.setFrom("Reigate College Admission <admissions@reigate.ac.uk>")
        helper.setReplyTo("admissions.reigate.ac.uk")
        helper.setSubject(subject)
        helper.setText(text, true)
        mailSender.send(message)
    }
}
