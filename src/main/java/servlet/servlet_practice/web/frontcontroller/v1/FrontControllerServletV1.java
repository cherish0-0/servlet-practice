package servlet.servlet_practice.web.frontcontroller.v1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servlet.servlet_practice.web.frontcontroller.v1.controller.MemberFormControllerV1;
import servlet.servlet_practice.web.frontcontroller.v1.controller.MemberListControllerV1;
import servlet.servlet_practice.web.frontcontroller.v1.controller.MemberSaveControllerV1;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Front Controller Servlet for V1 architecture
 * This servlet implements the Front Controller pattern
 * which centralizes request handling for the entire application.
 *
 * All requests matching the URL pattern "/front-controller/v1/*" are processed here
 * before being delegated to the appropriate controller.
 */
@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {

    // Map that stores URL paths and their corresponding controllers
    // This acts as a registry of all available controllers in the application
    private Map<String, ControllerV1> controllerMap = new HashMap<>();

    public FrontControllerServletV1() {
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service");

        String requestURI = request.getRequestURI();

        ControllerV1 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // Delegate request processing to the selected controller
        controller.process(request, response);
    }
}
