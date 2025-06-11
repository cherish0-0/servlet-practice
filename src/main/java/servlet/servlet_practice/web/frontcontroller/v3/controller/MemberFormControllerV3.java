package servlet.servlet_practice.web.frontcontroller.v3.controller;

import servlet.servlet_practice.web.frontcontroller.ModelView;
import servlet.servlet_practice.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

/**
 * Controller for displaying the member registration form in V3 architecture
 * Implements the ControllerV3 interface which is independent of Servlet API
 * Returns a ModelView with logical view name without any direct HTTP dependencies
 */
public class MemberFormControllerV3 implements ControllerV3 {
    @Override
    public ModelView process(Map<String, String> paramMap) {
        // Return a ModelView with the logical view name "new-form"
        // The logical view name will be converted to a physical view path by the front controller
        return new ModelView("new-form");
    }
}
