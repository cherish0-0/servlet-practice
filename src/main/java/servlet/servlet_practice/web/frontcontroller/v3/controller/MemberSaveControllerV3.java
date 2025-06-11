package servlet.servlet_practice.web.frontcontroller.v3.controller;

import servlet.servlet_practice.domain.member.Member;
import servlet.servlet_practice.domain.member.MemberRepository;
import servlet.servlet_practice.web.frontcontroller.ModelView;
import servlet.servlet_practice.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

/**
 * Controller for saving member information in V3 architecture
 * Implements the ControllerV3 interface which is independent of Servlet API
 * Processes member data from request parameters and saves it to the repository
 */
public class MemberSaveControllerV3 implements ControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        // Extract parameters from the paramMap (converted from request by front controller)
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        ModelView mv = new ModelView("save-result");
        mv.getModel().put("member", member);
        return mv;
    }
}
