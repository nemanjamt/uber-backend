package faculty.project.uber.service.implementation;

import faculty.project.uber.model.users.Client;
import faculty.project.uber.model.users.User;
import faculty.project.uber.service.ConfirmationTokenService;
import faculty.project.uber.service.EmailService;
import faculty.project.uber.service.ResetPasswordTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    private JavaMailSender javaMailSender;

    private ConfirmationTokenService confirmationTokenService;
    private ResetPasswordTokenService resetPasswordTokenService;
    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender, ConfirmationTokenService confirmationTokenService, ResetPasswordTokenService resetPasswordTokenService) {
        this.confirmationTokenService = confirmationTokenService;
        this.javaMailSender = javaMailSender;
        this.resetPasswordTokenService = resetPasswordTokenService;
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

    @Override
    public void sendPasswordEmail(User u) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(u.getEmail());
        mailMessage.setSubject("Forgotten password");
        mailMessage.setFrom("uberovicuber9@gmail.com");
        mailMessage.setText("To reset your password, please click here : "
                +"http://localhost:4200/auth/create-new-password?token="+resetPasswordTokenService.createToken(u).getToken());


        javaMailSender.send(mailMessage);

    }

}
