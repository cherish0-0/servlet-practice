package servlet.servlet_practice.web.frontcontroller.v4;

import java.util.Map;

/**
 * Controller interface for Front Controller pattern V4
 * This interface represents a further evolution from V3 with a more simplified
 * approach to controller implementation.
 *
 * Key improvements in V4:
 * - Controllers directly use a provided modelMap instead of creating ModelView
 * - Controllers return the view name as a String, further simplifying implementation
 * - Separated param and model maps for clearer distinction between input and output
 */
public interface ControllerV4 {

    String process(Map<String, String> paramMap, Map<String, Object> modelMap);
}
