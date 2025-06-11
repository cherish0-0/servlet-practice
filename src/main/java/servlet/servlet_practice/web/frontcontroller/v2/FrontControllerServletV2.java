package servlet.servlet_practice.web.frontcontroller.v2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servlet.servlet_practice.web.frontcontroller.MyView;
import servlet.servlet_practice.web.frontcontroller.v2.controller.MemberFormControllerV2;
import servlet.servlet_practice.web.frontcontroller.v2.controller.MemberListControllerV2;
import servlet.servlet_practice.web.frontcontroller.v2.controller.MemberSaveControllerV2;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Front Controller Servlet for V2 architecture
 * This servlet implements the Front Controller pattern
 * which centralizes request handling for the entire application.
 *
 * All requests matching the URL pattern "/front-controller/v2/*" are processed here
 * before being delegated to the appropriate controller.
 *
 * The V2 version improves on V1 by using the MyView class
 * to encapsulate view rendering logic, reducing duplication in controllers.
 */
@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerServletV2 extends HttpServlet {

    // Map that stores URL paths and their corresponding controllers
    // This acts as a registry of all available controllers in the application
    private Map<String, ControllerV2> controllerMap = new HashMap<>();

    public FrontControllerServletV2() {
        controllerMap.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerMap.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerMap.put("/front-controller/v2/members", new MemberListControllerV2());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();

        ControllerV2 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // Delegate request processing to the selected controller
        // The controller returns a MyView object which is responsible for rendering
        MyView view = controller.process(request, response);

        // Render the view, which forwards to the JSP
        view.render(request, response);
    }
}
