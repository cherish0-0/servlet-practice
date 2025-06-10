package servlet.servlet_practice.web.frontcontroller.v1;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Controller interface for Front Controller pattern V1
 * This interface defines the contract that all controllers must implement
 * in the first version of the front controller architecture.
 *
 * Controllers implementing this interface are responsible for:
 * - Processing HTTP requests
 * - Handling business logic
 * - Managing view selection
 */
public interface ControllerV1 {

    void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
