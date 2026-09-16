package utils;

/**
 * Exception for when a file is not valid or not found.
 */
public class BadFileException extends Exception {
  public BadFileException(String errorMessage) {
    super(errorMessage);
  }
}
