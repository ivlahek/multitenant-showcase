package hr.ivlahek.showcase.exception.handler;

import hr.ivlahek.showcase.dto.error.ErrorMessage;
import hr.ivlahek.showcase.exception.AppException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class RestErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({AppException.class})
    public ResponseEntity<Object> handleAppException(AppException appException, WebRequest request) {
        logger.error("AppException", appException);
        ErrorMessage responseMessage = new ErrorMessage(appException.getCode(), appException.getMessageList());
        HttpStatus responseStatus = HttpStatus.resolve(appException.getHttpStatus());
        return new ResponseEntity<>(responseMessage, responseStatus);
    }

    @ExceptionHandler({Throwable.class})
    public ResponseEntity<Object> handleEverythingElse(Throwable e, WebRequest request) {
        logger.error("Error", e);
        ErrorMessage responseMessage = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        HttpStatus responseStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(responseMessage, responseStatus);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleEverythingElse(ex, request);
    }

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(new ErrorMessage(HttpStatus.BAD_REQUEST.value(),
                ex.getBindingResult().getFieldErrors().stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.toList())), HttpStatus.BAD_REQUEST);

    }

}
