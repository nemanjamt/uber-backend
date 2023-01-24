package faculty.project.uber.service.implementation;

import faculty.project.uber.model.users.Client;
import faculty.project.uber.service.ConfirmationTokenService;
import faculty.project.uber.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    private JavaMailSender javaMailSender;

    private ConfirmationTokenService confirmationTokenService;

    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender, ConfirmationTokenService confirmationTokenService) {
        this.confirmationTokenService = confirmationTokenService;
        this.javaMailSender = javaMailSender;

    }

    @Override
    @Async
    public void sendRegistrationEmail(Client c) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(c.getEmail());
        mailMessage.setSubject("Complete Registration");
        mailMessage.setFrom("uberovicuber9@gmail.com");
        mailMessage.setText("To confirm your account, please click here : "
                +"http://localhost:4200/auth/confirm-registration?token="+confirmationTokenService.createToken(c).getConfirmationToken());


        javaMailSender.send(mailMessage);
    }


}
