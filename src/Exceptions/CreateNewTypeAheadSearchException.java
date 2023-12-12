package Exceptions;

public class CreateNewTypeAheadSearchException extends Exception{
    public String getMessage(){
        String str = new String("Unable to create a new Type Ahead Search System");
        return str;
    }
}
