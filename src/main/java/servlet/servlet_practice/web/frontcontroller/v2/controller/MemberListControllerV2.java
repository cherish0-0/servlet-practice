package servlet.servlet_practice.web.frontcontroller.v2.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servlet.servlet_practice.domain.member.Member;
import servlet.servlet_practice.domain.member.MemberRepository;
import servlet.servlet_practice.web.frontcontroller.MyView;
import servlet.servlet_practice.web.frontcontroller.v2.ControllerV2;

import java.io.IOException;
import java.util.List;

/**
 * Controller for displaying the member list in V2 architecture
 * Implements the ControllerV2 interface which returns a MyView object
 * This controller is responsible for retrieving all members and preparing data for view
 */
public class MemberListControllerV2 implements ControllerV2 {

    private MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Member> members = memberRepository.findAll();

        request.setAttribute("members", members);

        return new MyView("/WEB-INF/views/members.jsp");
    }
}
