package servlet.servlet_practice.web.frontcontroller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

/**
 * MyView class represents a view in the MVC architecture
 * This class encapsulates the view handling logic, including path management
 * and rendering with or without model data
 */
public class MyView {
    private String viewPath;

    public MyView(String viewPath) {
        this.viewPath = viewPath;
    }

    /*
    Renders the view without additional model data
    Used when the controller doesn't need to pass any data to the view
     */
    public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }

    /*
    Renders the view with model data
    Converts model data to request attributes before forwarding to the view
     */
    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Convert all model attributes to request attributes
        modelToRequestAttribute(model, request);

        // Forward to the view resource
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }

    /*
    Helper method to convert model data to request attributes
     */
    private static void modelToRequestAttribute(Map<String, Object> model, HttpServletRequest request) {
        model.forEach((key, value) -> request.setAttribute(key, value));
    }
}
