package hr.ivlahek.showcase.exception;

import hr.ivlahek.showcase.exception.messages.ExceptionLogable;

public class NotFoundException extends AppException {
    public NotFoundException(ExceptionLogable exceptionMessage) {
        super(exceptionMessage.getErrorCode(), 404, exceptionMessage.getMessage());
    }
}
