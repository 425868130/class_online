package top.peaktop.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping({"/", "/class_online", "/index"})
    public String DispatchPath() {
        return "index";
    }
}
