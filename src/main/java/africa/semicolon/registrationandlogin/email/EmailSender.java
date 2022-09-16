package africa.semicolon.registrationandlogin.email;

import africa.semicolon.registrationandlogin.exceptions.AppUserException;

public interface EmailSender {
    void send(String to, String email) throws AppUserException;
}
