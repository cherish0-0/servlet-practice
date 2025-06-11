package servlet.servlet_practice.web.frontcontroller.v3.controller;

import servlet.servlet_practice.domain.member.Member;
import servlet.servlet_practice.domain.member.MemberRepository;
import servlet.servlet_practice.web.frontcontroller.ModelView;
import servlet.servlet_practice.web.frontcontroller.v3.ControllerV3;

import java.util.List;
import java.util.Map;

/**
 * Controller for displaying the member list in V3 architecture
 * Implements the ControllerV3 interface which is independent of Servlet API
 * Retrieves all members from the repository and passes them to the view
 */
public class MemberListControllerV3 implements ControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        List<Member> members = memberRepository.findAll();

        ModelView mv = new ModelView("members");
        mv.getModel().put("members", members);
        return mv;
    }
}
