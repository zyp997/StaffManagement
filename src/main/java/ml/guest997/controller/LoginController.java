package ml.guest997.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class LoginController {

    @RequestMapping("/login")
    public String userLogin(@RequestParam("username") String username, @RequestParam("password") String password, Model model, HttpSession httpSession) {
        if (!StringUtils.isEmpty(username) && "123456".equals(password)) {
            //拦截器用到的属性
            httpSession.setAttribute("loginUser", username);
            //为什么不直接 return"dashboard"，而是使用重定向？是因为前端成功登录后跳转到新页面，地址栏中会有请求参数，这样不好看也不安全。
            //templates 文件夹下的页面不能直接访问，需要在自定义视图解析器中添加相应的映射跳转到 dashboard。
            return "redirect:/dashboard.html";
        } else {
            model.addAttribute("msg", "账号或密码错误！");
            return "index";
        }
    }
}