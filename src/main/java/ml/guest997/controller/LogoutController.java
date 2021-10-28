package ml.guest997.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class LogoutController {
    @RequestMapping("/logout")
    public String logout(HttpSession httpSession) {
        //使 session 失效
        httpSession.invalidate();
        return "redirect:/index.html";
    }
}
