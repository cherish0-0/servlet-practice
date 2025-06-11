package servlet.servlet_practice.web.frontcontroller.v2.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servlet.servlet_practice.web.frontcontroller.MyView;
import servlet.servlet_practice.web.frontcontroller.v2.ControllerV2;

import java.io.IOException;

/**
 * Controller for displaying the member registration form in V2 architecture
 * Implements the ControllerV2 interface which returns a MyView object
 * This controller is responsible for showing the form for creating new members
 */
public class MemberFormControllerV2 implements ControllerV2 {

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return new MyView("/WEB-INF/views/new-form.jsp");
    }
}
