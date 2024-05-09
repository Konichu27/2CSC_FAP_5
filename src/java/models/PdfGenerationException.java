package models;

public class PdfGenerationException extends Exception { 
    public PdfGenerationException(String errorMessage) {
        super(errorMessage);
    }
}