package africa.semicolon.registrationandlogin.appuser;

import africa.semicolon.registrationandlogin.exceptions.AppUserException;
import africa.semicolon.registrationandlogin.registration.token.ConfirmationToken;
import africa.semicolon.registrationandlogin.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.aspectj.weaver.patterns.IToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       return appUserRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("User name not found"));
    }

    public String signUpUser(AppUser appUser) throws AppUserException {
       Optional<AppUser> userExist= appUserRepository.findByEmail(appUser.getEmail());
       if(userExist.isPresent()){
           throw new AppUserException("email already exist",400);
       }
       String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
       appUser.setPassword(encodedPassword);
       appUserRepository.save(appUser);

       String  token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser
        );
        confirmationTokenService.saveToken(confirmationToken);

        return token;
     }
     public int enableAppUser(String email){
        return appUserRepository.enableAppUser(email);
     }


}
