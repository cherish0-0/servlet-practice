package servlet.servlet_practice.web.springmvc.old.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

// Marks this class as a Spring MVC Controller.
// Spring will automatically detect this class during component scanning and register it as a controller bean.
@Controller
public class SpringMemberFormControllerV1 {
    // Maps HTTP requests with the specified URL to this method.
    // When a client accesses "/springmvc/v1/members/new-form", this method will be invoked.
    @RequestMapping("/springmvc/v1/members/new-form")
    public ModelAndView process() {
        // Returns a ModelAndView object.
        // "new-form" is the logical name of the view that should be rendered.
        // Spring will resolve this view name to an actual view (e.g., a JSP file).
        return new ModelAndView("new-form");
    }
}
