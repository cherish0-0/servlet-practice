package servlet.servlet_practice.web.frontcontroller.v3;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servlet.servlet_practice.web.frontcontroller.ModelView;
import servlet.servlet_practice.web.frontcontroller.MyView;
import servlet.servlet_practice.web.frontcontroller.v3.controller.MemberFormControllerV3;
import servlet.servlet_practice.web.frontcontroller.v3.controller.MemberListControllerV3;
import servlet.servlet_practice.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Front Controller Servlet for V3 architecture
 * This servlet implements the Front Controller pattern
 * which centralizes request handling for the entire application.
 *
 * All requests matching the URL pattern "/front-controller/v3/*" are processed here
 * before being delegated to the appropriate controller.
 *
 * The V3 version improves on V2 by:
 * - Completely decoupling controllers from the Servlet API
 * - Using logical view names instead of physical view paths
 * - Introducing a ModelView object to carry both view and model data
 * - Implementing a view resolver to convert logical view names to physical paths
 */
@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    // Map that stores URL paths and their corresponding controllers
    // This acts as a registry of all available controllers in the application
    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Extract the request URI to determine which controller to use
        String requestURI = request.getRequestURI();

        // Lookup the appropriate controller based on the URI
        ControllerV3 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // Convert request parameters to a simple Map for the controller
        // This abstracts the HTTP request details from controllers
        Map<String, String> paramMap = createParamMap(request);

        // Process the request through the controller
        // Controller returns a ModelView with logical view name and model data
        ModelView mv = controller.process(paramMap);

        // Get the logical view name from the ModelView
        String viewName = mv.getViewName();

        // Convert logical view name to a physical view using the view resolver
        MyView view = viewResolver(viewName);

        // Render the view, which forwards to the JSP
        view.render(mv.getModel(), request, response);
    }

    /**
     * Helper method to convert HttpServletRequest parameters to a Map
     * This allows controllers to work with a simple Map instead of the HttpServletRequest
     *
     * @param request The HttpServletRequest containing parameters
     * @return Map containing request parameters as key-value pairs
     */
    private static Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramname -> paramMap.put(paramname, request.getParameter(paramname)));
        return paramMap;
    }

    /**
     * View resolver that converts logical view names to physical view paths
     * This decouples controllers from the physical structure of views
     *
     * @param viewName The logical view name
     * @return MyView object with the resolved physical view path
     */
    private static MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}
