package servlet.servlet_practice.web.springmvc.old;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import java.io.IOException;

// Registers this class as a Spring bean with the specified name
@Component("/springmvc/request-handler")
// MyHttpRequestHandler implements the HttpRequestHandler interface for handling HTTP requests
public class MyHttpRequestHandler implements HttpRequestHandler {
    // Handles HTTP requests; called by the Spring DispatcherServlet
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("MyHttpRequestHandler.handleRequest called");
    }
}
