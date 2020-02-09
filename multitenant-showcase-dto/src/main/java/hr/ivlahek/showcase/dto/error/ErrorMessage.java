package hr.ivlahek.showcase.dto.error;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ErrorMessage {
    private Integer code;
    private List<String> messages = new ArrayList<>();

    public ErrorMessage(Integer code, List<String> message) {
        this.code = code;
        this.messages = message;
    }

    public ErrorMessage(Integer code, String message) {
        this.code = code;
        this.messages.add(message);
    }

    public ErrorMessage(Integer code) {
        this.code = code;
    }

    public ErrorMessage() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorMessage that = (ErrorMessage) o;
        return Objects.equals(code, that.code) &&
                Objects.equals(messages, that.messages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, messages);
    }
}
