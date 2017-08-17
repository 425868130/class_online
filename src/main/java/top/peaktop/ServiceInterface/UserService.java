package top.peaktop.ServiceInterface;

import top.peaktop.Exception.ServiceException;
import top.peaktop.Model.User;

import javax.servlet.http.HttpSession;

/**
 * 用户相关业务接口
 */
public interface UserService {
    /**
     * 用户登录检查
     *
     * @param account  用户账号
     * @param password 用户输入的密码
     * @return 返回登录成功的用户对象
     */
    User userLoginCheck(String account, String password);

    /**
     * 用户登录，修改用户在线状态和登录时间等
     *
     * @param user 要登录的用户
     */
    void userLogin(User user);

    /**
     * 用户注销
     *
     * @param session 会话
     */
    void userLoginOut(HttpSession session);

    /**
     * 通过用户id查找用户信息
     *
     * @param id 用户id
     * @return 返回用户对象
     */
    User selectByPrimaryKey(Long id);

    /**
     * 通过用户账号查找用户对象
     *
     * @param account 用户账号
     * @return 返回用户对象
     */
    Long getIdByAccount(String account);

    /**
     * 通过账号获取用户对象
     *
     * @param account 用户账号
     * @return 用户对象
     */
    User selectByAccount(String account) throws ServiceException;

    /**
     * 用户注册
     *
     * @param user User对象
     */
    boolean userRegister(User user);

    /**
     * 更新用户个人信息
     *
     * @param newUserInfo 新的用户对象
     */
    boolean updateUserInfo(User newUserInfo) throws ServiceException;

    /**
     * 修改用户密码
     *
     * @param newPassWord
     */
    boolean updatePassWord(String newPassWord) throws ServiceException;

    /**
     * 用户审核,修改用户状态为验证
     *
     * @param user               待校验的用户
     * @param param_email        待校验的邮箱
     * @param param_validateCode 待校验的验证码
     */
    boolean userAuthentication(User user, String param_email, String param_validateCode) throws ServiceException;

    /**
     * 用户账号封禁
     *
     * @param id 用户账号ID
     */
    boolean userBlock(Long id) throws ServiceException;

    /**
     * 用户账号解封
     *
     * @param id 用户账号id
     */
    boolean userUnBlock(Long id) throws ServiceException;

}
