package top.peaktop.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.peaktop.Controller.UserController;
import top.peaktop.Exception.ServiceException;
import top.peaktop.Exception.USER_EXCEPTION_CODE;
import top.peaktop.Exception.UserServiceException;
import top.peaktop.Model.Mapper.UserMapper;
import top.peaktop.Model.User;
import top.peaktop.ServiceInterface.UserService;
import top.peaktop.Utils.DateUtil;
import top.peaktop.Utils.MD5Util;

import javax.servlet.http.HttpSession;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    /**
     * @param account  用户账号
     * @param password 用户输入的密码
     * @return
     */
    @Override
    public User userLoginCheck(String account, String password){
        return userMapper.userLoginCheck(account, password);
    }

    /**
     * 用户登录
     *
     * @param user 要登录的用户
     */
    @Override
    public void userLogin(User user){
        /*如果查找到对应用户名密码的账号，则将账号状态改为在线状态*/
        user.setIsOnline(true);
        /*设置登录时间*/
        user.setLoginTime(DateUtil.getCurrentTime());
        user.setGmtModified(DateUtil.getCurrentTime());
        /*增加登录次数*/
        user.setLoginCount(user.getLoginCount() + 1);
        userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 用户注销
     *
     * @param session 会话
     */
    @Override
    public void userLoginOut(HttpSession session) {
        /*获取当前在线用户*/
        User loginUser = (User) session.getAttribute(UserController.USER_SESSION_ATTR);
        if (loginUser != null) {
        /*创建一个临时用户用于更新，只设置要更新的属性*/
            User user = new User();
            user.setId(loginUser.getId());
            user.setIsOnline(false);
            user.setGmtModified(DateUtil.getCurrentTime());
            userMapper.updateByPrimaryKeySelective(user);
        /*从session中移除用户*/
            session.removeAttribute(UserController.USER_SESSION_ATTR);
        }
    }

    /**
     * 通过主键id查找用户
     *
     * @param id 用户id
     * @return 返回用户对象
     */
    @Override
    public User selectByPrimaryKey(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    /**
     * 通过用户账号查询用户id
     *
     * @param account 用户账号
     * @return 返回用户id
     */
    @Override
    public Long getIdByAccount(String account) {
        return userMapper.getIdByAccount(account);
    }

    /**
     * 通过账号获取用户对象
     *
     * @param account 用户账号
     * @return
     */
    @Override
    public User selectByAccount(String account) throws ServiceException {
        return userMapper.selectByAccount(account);
    }

    /**
     * 用户注册
     * 新账号默认为未验证
     *
     * @param user User对象,该对象由控制器构造并传入，应填充用户填写的基本注册信息
     */
    @Override
    public boolean userRegister(User user) {
        /*填充剩余数据*/
        user.setIsAuthentication(false);
        user.setGmtCreate(DateUtil.getCurrentTime());
        user.setGmtModified(DateUtil.getCurrentTime());
            /*用户注册时给定一个激活码，将邮箱和当前时间同时进行MD5运算防止重复*/
        user.setValidateCode(MD5Util.encode2hex(user.getEmail() + DateUtil.getCurrentTime().toString()));
        /*insertSelective插入时可以自动应用数据库中的default值*/
        if (userMapper.insertSelective(user) == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 用户信息修改
     *
     * @param newUserInfo 新的用户对象
     * @return
     */
    @Override
    public boolean updateUserInfo(User newUserInfo) throws ServiceException {
        if (userMapper.updateByPrimaryKeySelective(newUserInfo) == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 用户密码修改
     *
     * @param newPassWord
     * @return
     */
    @Override
    public boolean updatePassWord(String newPassWord) throws ServiceException {

        return false;
    }

    /**
     * 用户校验
     *
     * @param user               待校验的用户
     * @param param_email        待校验的邮箱
     * @param param_validateCode 待校验的验证码
     * @return
     * @throws ServiceException service层异常
     */
    @Override
    public boolean userAuthentication(User user, String param_email, String param_validateCode) throws ServiceException {
        /*验证用户是否为空,或者账户已验证*/
        if (user == null) {
            /*异常抛出时方法已返回*/
            throw new UserServiceException(USER_EXCEPTION_CODE.AUTHENTICATION_FAILED, "用户不存在，验证失败！");
        }
        if (user.getIsAuthentication()) {
            throw new UserServiceException(USER_EXCEPTION_CODE.AUTHENTICATION_FAILED, "该用户已验证，请勿重复操作！");
        }
            /*验证时间*/
        if (DateUtil.TheHourUpToNow(user.getGmtCreate()) > 24) {
            throw new UserServiceException(USER_EXCEPTION_CODE.AUTHENTICATION_FAILED, "验证码已过期,请重新注册！");
        }
        /*同时验证邮箱和验证码*/
        //System.out.println("待验证用户邮箱：" + user.getEmail() + "待验证用户校验码：" + user.getValidateCode() + "\n" + "邮箱：" + param_email + "校验码：" + param_validateCode);
        if (param_email.equals(user.getEmail()) && param_validateCode.equals(user.getValidateCode())) {
            /*验证通过更新用户账户*/
            User temp = new User();
            temp.setId(user.getId());
            temp.setGmtModified(DateUtil.getCurrentTime());
            temp.setIsAuthentication(true);
            if (userMapper.updateByPrimaryKeySelective(temp) == 1) {
                return true;
            } else {
                return false;
            }
        } else {
            throw new UserServiceException(USER_EXCEPTION_CODE.AUTHENTICATION_FAILED, "验证码或邮箱错误，验证失败！");
        }
    }

    /**
     * 禁用账户
     *
     * @param id 用户账号ID
     * @return
     */
    @Override
    public boolean userBlock(Long id) throws ServiceException {
        User temp = new User();
        temp.setId(id);
        temp.setIsBlocked(true);
        temp.setGmtModified(DateUtil.getCurrentTime());
        if (userMapper.updateByPrimaryKeySelective(temp) == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 解禁账户
     *
     * @param id 用户账号id
     * @return
     */
    @Override
    public boolean userUnBlock(Long id) throws ServiceException {
        User temp = new User();
        temp.setId(id);
        temp.setIsBlocked(false);
        temp.setGmtModified(DateUtil.getCurrentTime());
        if (userMapper.updateByPrimaryKeySelective(temp) == 1) {
            return true;
        } else {
            return false;
        }
    }
}
