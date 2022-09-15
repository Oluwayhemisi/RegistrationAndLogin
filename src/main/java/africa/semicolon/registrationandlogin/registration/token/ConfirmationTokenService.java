package africa.semicolon.registrationandlogin.registration.token;


import africa.semicolon.registrationandlogin.exceptions.AppUserException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;

    public void  saveToken(ConfirmationToken token){
        confirmationTokenRepository.save(token);
    }

    public ConfirmationToken getToken(String token) throws AppUserException {
       return confirmationTokenRepository.findByToken(token).orElseThrow(()-> new AppUserException("token not found",404));

    }

    public int setConfirmedAt(String token) {
        return confirmationTokenRepository.updateConfirmedAt(token, LocalDateTime.now());
    }
}
