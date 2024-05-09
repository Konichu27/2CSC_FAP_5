package models;

public class InvalidRequestException extends Exception { 
    public InvalidRequestException(String errorMessage) {
        super(errorMessage);
    }
}