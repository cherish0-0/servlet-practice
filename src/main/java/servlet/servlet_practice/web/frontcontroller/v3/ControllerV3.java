package servlet.servlet_practice.web.frontcontroller.v3;

import servlet.servlet_practice.web.frontcontroller.ModelView;

import java.util.Map;

/**
 * Controller interface for Front Controller pattern V3
 * This interface represents a significant evolution from V2 by completely
 * decoupling controllers from the Servlet API.
 *
 * Key improvements in V3:
 * - Controllers receive a simple parameter map instead of HttpServletRequest/Response
 * - Controllers return a ModelView object containing both view name and model data
 * - No servlet dependencies in controller implementations, allowing for better testability
 * - Fully separated controller logic from HTTP infrastructure
 */
public interface ControllerV3 {
    ModelView process(Map<String, String> paramMap);
}
