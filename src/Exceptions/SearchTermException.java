package Exceptions;

public class SearchTermException extends Exception{
    public String getMessage(){
        String message = new String("Insertion of Search Term Has Failed");
        return message;
    }
}
