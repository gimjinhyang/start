package start.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class IndexController {

    @GetMapping("/index")
    fun getCalendar(model: Model): String {
        return "index"
    }

}