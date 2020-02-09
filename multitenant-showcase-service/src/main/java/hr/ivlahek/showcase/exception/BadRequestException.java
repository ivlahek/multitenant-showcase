package hr.ivlahek.showcase.exception;


import hr.ivlahek.showcase.exception.messages.ExceptionLogable;

public class BadRequestException extends AppException {
    public BadRequestException(ExceptionLogable exceptionMessage) {
        super(exceptionMessage.getErrorCode(), 400, exceptionMessage.getMessage());
    }
}
