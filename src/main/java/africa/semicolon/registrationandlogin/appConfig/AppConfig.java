package africa.semicolon.registrationandlogin.appConfig;


import africa.semicolon.registrationandlogin.registration.RegistrationRequest;
import africa.semicolon.registrationandlogin.registration.RegistrationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class AppConfig {


//    @Bean
//    public RegistrationService registrationService(){
//        return new RegistrationService();
//    }

    @Bean
    public JavaMailSender javaMailSender(){
        return new JavaMailSenderImpl();
    }
}
