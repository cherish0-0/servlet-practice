package servlet.servlet_practice.web.frontcontroller.v5.adapter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servlet.servlet_practice.web.frontcontroller.ModelView;
import servlet.servlet_practice.web.frontcontroller.v4.ControllerV4;
import servlet.servlet_practice.web.frontcontroller.v5.MyHandlerAdapter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Handler Adapter for ControllerV4 implementations
 * This adapter allows ControllerV4 controllers to work with the V5 front controller
 * by converting between the different interfaces.
 *
 * The key challenge this adapter solves is adapting the return value:
 * - ControllerV4 returns a String view name and updates a model directly
 * - The front controller expects a ModelView object
 */
public class ControllerV4HandlerAdapter implements MyHandlerAdapter {

    /**
     * Checks if this adapter can handle the given controller
     * Returns true only if the handler is an instance of ControllerV4
     */
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV4);
    }

    /**
     * Adapts a ControllerV4 handler to work with the V5 front controller
     * This method:
     * 1. Casts the handler to ControllerV4
     * 2. Converts request parameters to a parameter map
     * 3. Creates a model map for the controller to update
     * 4. Processes the request through the controller, getting a view name
     * 5. Creates and returns a ModelView object with the view name and model data
     */
    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        // Cast the handler to ControllerV4 type
        ControllerV4 controller = (ControllerV4) handler;

        // Convert request parameters to a map for the controller
        Map<String, String> paramMap = createParamMap(request);

        // Create a model map for the controller to populate
        HashMap<String, Object> model = new HashMap<>();

        // Process the request through the controller
        // The controller updates the model map and returns a view name
        String viewName = controller.process(paramMap, model);

        // Create a ModelView from the view name and model
        // This is the key adaptation between ControllerV4 and front controller
        ModelView mv = new ModelView(viewName);
        mv.setModel(model);

        return mv;
    }

    /**
     * Helper method to convert HttpServletRequest parameters to a Map
     * This allows controllers to work with a simple Map instead of the HttpServletRequest
     */
    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramname -> paramMap.put(paramname, request.getParameter(paramname)));
        return paramMap;
    }
}
