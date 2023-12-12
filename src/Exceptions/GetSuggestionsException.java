package Exceptions;

import java.security.spec.ECFieldF2m;

public class GetSuggestionsException extends Exception {
    public String getMessage(){
        String message = new String("Failed to get the suggestions");
        return message;
    }
}
