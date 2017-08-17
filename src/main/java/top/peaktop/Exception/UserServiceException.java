package top.peaktop.Exception;

/**
 * 用户相关异常
 */
public class UserServiceException extends ServiceException {

    public UserServiceException() {
        super();
    }

    public UserServiceException(EXCEPTION_CODE exception_code, String msg) {
        super(exception_code, msg);
    }
}
