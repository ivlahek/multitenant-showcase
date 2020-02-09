package hr.ivlahek.showcase.exception.messages;

public enum ExceptionMessage implements ExceptionLogable {

    ORGANIZATION_NOT_FOUND(0, "Organization not found"),
    MOBILE_APP_NOT_FOUND(1, "Mobile application not found"),
    USER_ACCOUNT_NOT_FOUND(2, "User account not found"), INTERNAL_ERROR(-1, "Internal error!");
    private int errorCode;

    private String message;

    ExceptionMessage(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    @Override
    public int getErrorCode() {
        return 0;
    }

    @Override
    public String getMessage() {
        return null;
    }

}
