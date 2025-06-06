package servlet.servlet_practice.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * Servlet for handling HTTP request parameters (query parameters).
 */
@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("--- Request All Parameters - start ---");

        request.getParameterNames().asIterator()
                .forEachRemaining(paramName ->
                        System.out.println(paramName + "=" + request.getParameter(paramName)));

        System.out.println("--- Request All Parameters - end ---");
        System.out.println();

        System.out.println("--- Request Specific Parameters - start ---");
        String username = request.getParameter("username");
        String age = request.getParameter("age");

        System.out.println("username = " + username);
        System.out.println("age = " + age);
        System.out.println("--- Request Specific Parameters - end ---");
        System.out.println();

        // Handling multiple values for the same parameter
        System.out.println("--- Request Parameter Values - start ---");
        String[] usernames = request.getParameterValues("username");
        Arrays.stream(usernames)
                .forEach(name -> System.out.println("username = " + name));
        System.out.println("--- Request Parameter Values - end ---");
    }
}
