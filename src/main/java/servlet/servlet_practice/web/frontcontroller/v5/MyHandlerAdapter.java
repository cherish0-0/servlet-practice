package servlet.servlet_practice.web.frontcontroller.v5;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servlet.servlet_practice.web.frontcontroller.ModelView;

import java.io.IOException;

/**
 * Interface for Handler Adapter in Front Controller pattern V5
 * Implements the Adapter pattern to allow different types of controllers (handlers)
 * to work with the same front controller.
 *
 * This interface enables the front controller to:
 * - Support multiple controller versions simultaneously (V1, V2, V3, V4)
 * - Add new controller types without modifying the front controller
 * - Delegate the handling details to specific adapter implementations
 */
public interface MyHandlerAdapter {

    boolean supports(Object handler);

    ModelView handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
