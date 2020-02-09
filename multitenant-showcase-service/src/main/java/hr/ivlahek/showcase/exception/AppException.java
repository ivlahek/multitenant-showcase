package hr.ivlahek.showcase.exception;

import java.util.ArrayList;
import java.util.List;

public class AppException extends RuntimeException {

    private Integer httpStatus;
    private Integer code;
    private List<String> messageList = new ArrayList<>();

    public AppException(Integer httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
        this.code = httpStatus;
        this.messageList.add(message);
    }

    public AppException(Integer code, Integer httpStatus, String message) {
        super(message);
        this.code = code;
        this.httpStatus = httpStatus;
        this.messageList.add(message);
    }

    public AppException(Integer code, Integer httpStatus, List<String> message) {
        super(String.join(",", message));
        this.code = code;
        this.httpStatus = httpStatus;
        this.messageList.addAll(message);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<String> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<String> messageList) {
        this.messageList = messageList;
    }

    public Integer getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(Integer httpStatus) {
        this.httpStatus = httpStatus;
    }

    @Override
    public String toString() {
        return "AppException{" +
                "httpStatus=" + httpStatus +
                ", code=" + code +
                ", messageList=" + messageList +
                '}';
    }
}
