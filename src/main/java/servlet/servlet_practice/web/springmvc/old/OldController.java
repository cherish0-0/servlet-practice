package servlet.servlet_practice.web.springmvc.old;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

// Registers this class as a Spring bean with the specified name
@Component("/springmvc/old-controller")
// OldController implements the Spring Controller interface (pre-annotation style)
public class OldController implements Controller {
    // This method handles HTTP requests and returns a ModelAndView object
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("OldController.handleRequest called");
        return new ModelAndView("new-form");
    }
}
