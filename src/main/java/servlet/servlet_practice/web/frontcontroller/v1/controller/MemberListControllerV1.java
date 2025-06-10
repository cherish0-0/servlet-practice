package servlet.servlet_practice.web.frontcontroller.v1.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servlet.servlet_practice.domain.member.Member;
import servlet.servlet_practice.domain.member.MemberRepository;
import servlet.servlet_practice.web.frontcontroller.v1.ControllerV1;

import java.io.IOException;
import java.util.List;

/**
 * Controller for displaying the member list in V1 architecture
 * Implements the ControllerV1 interface to work with the front controller pattern
 * Responsible for retrieving all members and forwarding to the list view
 */
public class MemberListControllerV1 implements ControllerV1 {

    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Member> members = memberRepository.findAll();

        request.setAttribute("members", members);

        String viewPath = "/WEB-INF/views/members.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }
}
