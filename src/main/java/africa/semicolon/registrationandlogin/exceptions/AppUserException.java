package africa.semicolon.registrationandlogin.exceptions;

public class AppUserException extends Exception {
    private int statusCode;
    public AppUserException(String message, int statusCode){
        super(message);
        this.statusCode = statusCode;
    }
    public int getStatusCode(){
        return statusCode;
    }
}
