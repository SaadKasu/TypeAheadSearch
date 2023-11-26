package Exceptions;

public class PerformDecayException extends Exception{
    public String getMessage(){
        String message = new String("Decay operation has failed");
        return message;
    }
}
