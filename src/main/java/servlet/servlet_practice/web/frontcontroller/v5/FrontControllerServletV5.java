package servlet.servlet_practice.web.frontcontroller.v5;

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
import servlet.servlet_practice.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Front Controller Servlet for V5 architecture
 * This servlet implements the Front Controller pattern with Adapter pattern,
 * allowing it to support multiple controller versions simultaneously.
 *
 * All requests matching the URL pattern "/front-controller/v5/*" are processed here
 * before being delegated to the appropriate controller via adapters.
 *
 * The V5 version improves on previous versions by:
 * - Supporting multiple controller types with different interfaces
 * - Using handler adapters to convert between different controller interfaces
 * - Allowing easy addition of new controller types without modifying the front controller
 * - Implementing a flexible plugin architecture for controllers and adapters
 */
@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {
    // Map to store URL paths and their corresponding handlers (controllers)
    private final Map<String, Object> handlerMappingMap = new HashMap<>();

    // List of available handler adapters
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    /**
     * Constructor that initializes the handler mappings and adapters
     * This setup enables the plugin architecture of the V5 front controller
     */
    public FrontControllerServletV5() {
        initHandlerMappingMap();
        initHandlerAdapters();
    }

    /**
     * Initialize the handler mapping registry
     * Maps URL patterns to their respective controller handlers
     * Note that handlers can be of any controller type (V1, V2, V3, V4, etc.)
     */
    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());
    }

    /**
     * Initialize the list of handler adapters
     * Each adapter supports a specific controller version
     * Adding new adapters enables support for new controller types
     */
    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
    }

    /**
     * Service method that handles all incoming requests
     * This method implements the core workflow of the front controller:
     * 1. Find the appropriate handler for the request
     * 2. Find an adapter that supports the handler
     * 3. Use the adapter to process the request
     * 4. Resolve the view and render it with the model data
     */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Find the handler (controller) for this request
        // MemberFormControllerV3, MemberSaveControllerV3, MemberListControllerV3
        Object handler = getHandler(request);

        if (handler == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // Find an adapter that can handle this controller type
        // ControllerV3HandlerAdapter
        MyHandlerAdapter adapter = getHandlerAdapter(handler);

        // Use the adapter to process the request through the handler
        ModelView mv = adapter.handle(request, response, handler);

        // Get the logical view name from the ModelView
        String viewName = mv.getViewName();

        // Resolve the physical view path and create a view object
        MyView view = viewResolver(viewName);

        // Render the view with the model data
        view.render(mv.getModel(), request, response);
    }

    /**
     * Finds the appropriate handler for the current request
     * Uses the request URI to lookup the handler in the mapping registry
     */
    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return handlerMappingMap.get(requestURI);
    }

    /**
     * Finds a handler adapter that supports the given handler type
     * Iterates through registered adapters and checks if they support the handler
     */
    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        for (MyHandlerAdapter adapter : handlerAdapters) {
            if (adapter.supports(handler)) {
                return adapter;
            }
        }
        throw new IllegalArgumentException("No handler adapter found for " + handler);
    }

    /**
     * View resolver that converts logical view names to physical view paths
     * This decouples controllers from the physical structure of views
     */
    private static MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}
