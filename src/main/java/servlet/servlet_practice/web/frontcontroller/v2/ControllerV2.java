package servlet.servlet_practice.web.frontcontroller.v2;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servlet.servlet_practice.web.frontcontroller.MyView;

import java.io.IOException;

/**
 * Controller interface for Front Controller pattern V2
 * This interface represents an evolution from V1 by returning a view object
 * instead of directly handling request forwarding.
 *
 * The key improvement in V2 is that controllers now return a MyView object,
 * which encapsulates the view path and rendering logic, allowing for:
 * - Better separation of concerns
 * - Reduced code duplication
 * - More consistent view handling
 */
public interface ControllerV2 {

    MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}

