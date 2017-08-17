package top.peaktop.Exception;

/**
 * 用户相关异常代码
 */
public enum USER_EXCEPTION_CODE implements EXCEPTION_CODE {
    /*用户已离线*/
    OFFLINE(1),
    /*权限不足*/
    PERMISSSION_DENIDE(2),
    /*用户被锁定*/
    LOCKED(3),

    /*认证失败*/
    AUTHENTICATION_FAILED(4),
    /*用户未认证*/
    UN_AUTHENTICATION(5),;

    private String exceptionCode;

    USER_EXCEPTION_CODE(int code) {
        this.exceptionCode = "USER_EXCEPTION_" + code;
    }

    @Override
    public String toString() {
        return exceptionCode;
    }
}
