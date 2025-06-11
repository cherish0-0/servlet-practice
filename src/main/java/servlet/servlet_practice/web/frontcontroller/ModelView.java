package servlet.servlet_practice.web.frontcontroller;

import java.util.HashMap;
import java.util.Map;

/**
 * ModelView class for the front controller pattern
 * This class encapsulates both the view name and model data,
 * separating the view selection from HTTP request/response details.
 *
 * It represents an evolution in the MVC architecture by allowing controllers
 * to be independent of the Servlet API.
 */
public class ModelView {
    private String viewName;
    private Map<String, Object> model = new HashMap<>();

    public ModelView(String viewName) {
        this.viewName = viewName;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public ModelView(Map<String, Object> model) {
        this.model = model;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public void setModel(Map<String, Object> model) {
        this.model = model;
    }
}
