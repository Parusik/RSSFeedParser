package main.feed;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * Created by Parus on 28.05.2017.
 */
@Controller
public class MainController {

    @GetMapping("/")
    public String home(ModelMap modelMap) {
        return "main";
    }
}