package servlet.servlet_practice.web.frontcontroller.v4.controller;

import servlet.servlet_practice.web.frontcontroller.v4.ControllerV4;

import java.util.Map;

/**
 * Controller for displaying the member registration form in V4 architecture
 * Implements the ControllerV4 interface which uses a simplified approach
 * Returns logical view name directly as a String instead of ModelView object
 */
public class MemberFormControllerV4 implements ControllerV4 {
    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> modelMap) {
        // Simply return the logical view name
        return "new-form";
    }
}
