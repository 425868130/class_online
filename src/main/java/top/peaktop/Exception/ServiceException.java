package top.peaktop.Exception;

/**
 * 统一Service层异常接口
 */
public abstract class ServiceException extends Exception {
    /*异常代码*/
    private EXCEPTION_CODE exception_code;

    public ServiceException() {
    }

    public ServiceException(EXCEPTION_CODE exception_code) {
        this.exception_code = exception_code;
    }

    public ServiceException(EXCEPTION_CODE exception_code, String message) {
        super(message);
        this.exception_code = exception_code;
    }

    public EXCEPTION_CODE getException_code() {
        return exception_code;
    }

    public void setException_code(EXCEPTION_CODE exception_code) {
        this.exception_code = exception_code;
    }
}
