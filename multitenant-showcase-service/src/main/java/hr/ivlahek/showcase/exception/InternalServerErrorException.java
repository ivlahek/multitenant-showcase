package hr.ivlahek.showcase.exception;


import hr.ivlahek.showcase.exception.messages.ExceptionLogable;

public class InternalServerErrorException extends AppException {

    public InternalServerErrorException(ExceptionLogable exceptionMessage) {
        super(exceptionMessage.getErrorCode(), 500, exceptionMessage.getMessage());
    }
}
