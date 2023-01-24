package faculty.project.uber.exceptions;

public class UsernameAlreadyExists extends RuntimeException{
    public UsernameAlreadyExists(String message){
        super(message);
    }
}
