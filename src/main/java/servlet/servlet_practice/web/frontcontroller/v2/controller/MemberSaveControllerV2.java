package servlet.servlet_practice.web.frontcontroller.v2.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servlet.servlet_practice.domain.member.Member;
import servlet.servlet_practice.domain.member.MemberRepository;
import servlet.servlet_practice.web.frontcontroller.MyView;
import servlet.servlet_practice.web.frontcontroller.v2.ControllerV2;

import java.io.IOException;

/**
 * Controller for saving member information in V2 architecture
 * Implements the ControllerV2 interface which returns a MyView object
 * This controller processes form submissions to save member data
 */
public class MemberSaveControllerV2 implements ControllerV2 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        request.setAttribute("member", member);

        return new MyView("/WEB-INF/views/save-result.jsp");
    }
}
