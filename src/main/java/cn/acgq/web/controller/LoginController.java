package cn.acgq.web.controller;

import cn.acgq.model.JsonMsg;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/hrms")
public class LoginController {
    @RequestMapping("/test")
    public String test(){
        return "test";
    }

    /**
     * 登陆页面
     * @return
     */
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    /**
     * 跳转到主页面
     * @return
     */
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(){
        return "main";
    }

    /**
     * 退出登录：从主页面跳转到登录页面
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(){
        return "login";
    }

    @RequestMapping(value = "/dologin",method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg doLogin(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if ("admin".equals(username)&&"1234".equals(password)){
            request.getSession().setAttribute("user",username);
            return JsonMsg.success();
        }
        return JsonMsg.fail().addInfo("login_error","用户名或密码错误");
    }
}
