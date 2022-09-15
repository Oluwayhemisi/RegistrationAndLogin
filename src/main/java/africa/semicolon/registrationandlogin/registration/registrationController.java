package africa.semicolon.registrationandlogin.registration;

import africa.semicolon.registrationandlogin.exceptions.AppUserException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/")
@AllArgsConstructor

public class registrationController {

    private final RegistrationService registrationService;


    @PostMapping("registration/")
    public String register(@RequestBody RegistrationRequest request) throws AppUserException {
        return registrationService.registerUser(request);
    }
    @GetMapping("confirm")
    public String confirm(@RequestParam("token") String token) throws AppUserException {
        return registrationService.confirmToken(token);
    }
}
