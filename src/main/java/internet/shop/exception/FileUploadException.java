package internet.shop.exception;

public class FileUploadException extends ValidationException {

    public FileUploadException(String propertyName, String message) {
        this.add(propertyName, message);
    }
}
