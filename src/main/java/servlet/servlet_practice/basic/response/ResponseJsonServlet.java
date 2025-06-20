package servlet.servlet_practice.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servlet.servlet_practice.basic.HelloData;

import java.io.IOException;

@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set content type to JSON
        // Content-Type: application/json

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        HelloData helloData = new HelloData();
        helloData.setUsername("hello");
        helloData.setAge(20);

        // Convert HelloData object to JSON string
        // {"username": "hello", "age": 20}
        String result = objectMapper.writeValueAsString(helloData);

        // Write JSON string to response
        response.getWriter().write(result);
    }
}
