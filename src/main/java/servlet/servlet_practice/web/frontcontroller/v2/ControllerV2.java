package servlet.servlet_practice.web.frontcontroller.v2;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servlet.servlet_practice.web.frontcontroller.MyView;

import java.io.IOException;

public interface ControllerV2 {
    public interface ControllerV1 {

        MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    }
}
