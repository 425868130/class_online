package top.peaktop.Controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import top.peaktop.Exception.ServiceException;
import top.peaktop.Model.User;
import top.peaktop.ServiceInterface.UserService;
import top.peaktop.Utils.EmailSender;
import top.peaktop.Utils.MD5Util;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    public final static String USER_SESSION_ATTR = "LoginUser";
    @Autowired
    UserService userService;
    @Autowired
    Gson gson;

    /*用户登录*/
    @RequestMapping(value = "userLogin", method = RequestMethod.POST)
    public String userLogin(HttpSession httpSession, Model model,
                            @RequestParam("account") String account, @RequestParam("passWord") String passWord) {
        User user = userService.userLoginCheck(account, MD5Util.encode2hex(passWord.trim()));
        if (user == null) {
            model.addAttribute("msg", "用户名密码错误！");
        } else if (!user.getIsAuthentication()) {
            model.addAttribute("msg", "用户未验证，登录失败！");
        } else if (user.getIsBlocked()) {
            model.addAttribute("msg", "账号被禁用，无法登录！");
        } else {
            userService.userLogin(user);
            httpSession.setAttribute(USER_SESSION_ATTR, user);
            model.addAttribute("user", user);
        }
        return "user";
    }

    @RequestMapping(value = "userLoginOut", method = RequestMethod.GET)
    public String userLoginOut(HttpSession httpSession) {
        userService.userLoginOut(httpSession);
        return "index";
    }

    /*用户注册*/
    @RequestMapping(value = "userRegister", method = RequestMethod.POST)
    public String userRegister(Model model, @RequestParam("account") String account,
                               @RequestParam("passWord") String passWord, @RequestParam("email") String email, @RequestParam("telephone") String telephone) {
        if (account.length() < 6) {
            model.addAttribute("regMsg", "账号长度不能小于6位");
            return "user";
        } else if (passWord.length() < 8) {
            model.addAttribute("regMsg", "密码长度不能小于8位");
            return "user";
        }
        /*判断账号是否被注册*/
        if (userService.getIdByAccount(account.trim()) == null) {
            User user = new User();
            user.setAccount(account.trim());
            /*加密密码*/
            user.setPassword(MD5Util.encode2hex(passWord.trim()));
            user.setEmail(email.trim());
            user.setTelephone(telephone.trim());
           /* 判断数据是否插入成功*/
            if (userService.userRegister(user)) {
                model.addAttribute("regMsg", "注册成功");
                mail(user);
            } else {
                model.addAttribute("regMsg", "注册失败");
            }
        } else {
            model.addAttribute("regMsg", "该账号已经被注册！");
        }
        return "user";
    }

    /*用户邮箱验证*/
    @RequestMapping(value = "userAuthentication", method = RequestMethod.GET)
    public String userAuthentication(Model model, @RequestParam("action") String action, @RequestParam("account") String account,
                                     @RequestParam("email") String email, @RequestParam("validateCode") String validateCode) {
        /*判断是否为激活请求*/
        if ("activate".endsWith(action)) {
            /*通过邮箱获取要激活的用户id*/
            Long activateUserId = userService.getIdByAccount(account);
            /*判断用户是否为空,然后通过用户id获取要验证的用户对象*/
            if (activateUserId != null) {
                User activateUser = userService.selectByPrimaryKey(activateUserId);
                try {
                    /*验证用户账户*/
                    if (userService.userAuthentication(activateUser, email, validateCode)) {
                        model.addAttribute("result", "验证成功,请返回首页进行登录");
                    } else {
                        model.addAttribute("result", "验证失败，请重试!");
                    }
                } catch (ServiceException e) {
                    model.addAttribute("msg", e.getException_code() + ":" + e.getMessage());
                }
            }
        }
        return "userAuthentication";
    }


    public void mail(User user) {
        StringBuilder content = new StringBuilder("点击下面链接激活账号,24小时有效,链接只能使用一次，请尽快激活!\n");
        content.append("http://localhost:/userAuthentication?");
        content.append("action=activate");
        content.append("&account=" + user.getAccount());
        content.append("&email=" + user.getEmail());
        content.append("&validateCode=" + user.getValidateCode());
        content.append("\n请不要把邮件内容泄露给其他人!\n[Tips]如果链接无法直接点击请复制到浏览器中地址栏中打开。");

        EmailSender.InitProperties();
        EmailSender.sendEmail(content.toString(), user.getEmail());
    }
}
