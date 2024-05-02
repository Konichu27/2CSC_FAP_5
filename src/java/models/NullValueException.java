package models;

public class NullValueException extends Exception { 
    public NullValueException(String errorMessage) {
        super(errorMessage);
    }
}