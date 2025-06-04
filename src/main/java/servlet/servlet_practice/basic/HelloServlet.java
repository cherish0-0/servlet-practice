package servlet.servlet_practice.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * A simple servlet that handles HTTP requests and responds.
 * This class demonstrates the basic structure and lifecycle of a servlet.
 */
@WebServlet(name = "helloServlet", urlPatterns = {"/hello"})
// @WebServlet annotation maps this servlet to the URL pattern "/hello"
// When a request is made to "/hello", this servlet will be invoked
public class HelloServlet extends HttpServlet {

    /**
     * The service method handles all HTTP requests (GET, POST, etc.) sent to this servlet.
     * This is the main entry point for request processing.
     */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        // Extract the "username" parameter from the request
        // For example, if the URL is "/hello?username=John", username will be "John"
        String username = request.getParameter("username");
        System.out.println("username = " + username);

        // Write the response body
        // This will be sent back to the client's browser
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("hello " + username);
    }
}
