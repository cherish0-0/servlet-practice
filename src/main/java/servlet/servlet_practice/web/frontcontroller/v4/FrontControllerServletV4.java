package servlet.servlet_practice.web.frontcontroller.v4;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servlet.servlet_practice.web.frontcontroller.ModelView;
import servlet.servlet_practice.web.frontcontroller.MyView;
import servlet.servlet_practice.web.frontcontroller.v3.ControllerV3;
import servlet.servlet_practice.web.frontcontroller.v3.controller.MemberFormControllerV3;
import servlet.servlet_practice.web.frontcontroller.v3.controller.MemberListControllerV3;
import servlet.servlet_practice.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import servlet.servlet_practice.web.frontcontroller.v4.controller.MemberFormControllerV4;
import servlet.servlet_practice.web.frontcontroller.v4.controller.MemberListControllerV4;
import servlet.servlet_practice.web.frontcontroller.v4.controller.MemberSaveControllerV4;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Front Controller Servlet for V4 architecture
 * This servlet implements the Front Controller pattern
 * which centralizes request handling for the entire application.
 *
 * All requests matching the URL pattern "/front-controller/v4/*" are processed here
 * before being delegated to the appropriate controller.
 *
 * The V4 version improves on V3 by:
 * - Further simplifying controller implementation
 * - Providing a model map directly to controllers instead of having them create ModelView objects
 * - Controllers return only the view name as a String, not a ModelView object
 * - Clearer separation between input parameters and output model
 */
@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {

    // Map that stores URL paths and their corresponding controllers
    // This acts as a registry of all available controllers in the application
    private Map<String, ControllerV4> controllerMap = new HashMap<>();

    public FrontControllerServletV4() {
        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Extract the request URI to determine which controller to use
        String requestURI = request.getRequestURI();

        // Lookup the appropriate controller based on the URI
        ControllerV4 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // Convert request parameters to a simple Map for the controller
        // This abstracts the HTTP request details from controllers
        Map<String, String> paramMap = createParamMap(request);

        // Create model map to be populated by the controller
        // This is a key improvement in V4 - the model is passed to the controller
        Map<String, Object> model = new HashMap<>();

        // Process the request through the controller
        // Controller directly updates the model map and returns view name as a String
        String viewName = controller.process(paramMap, model);

        // Convert logical view name to a physical view using the view resolver
        MyView view = viewResolver(viewName);

        // Render the view with the model data
        view.render(model, request, response);
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
