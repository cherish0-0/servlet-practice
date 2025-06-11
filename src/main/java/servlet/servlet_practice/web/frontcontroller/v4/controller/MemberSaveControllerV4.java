package servlet.servlet_practice.web.frontcontroller.v4.controller;

import servlet.servlet_practice.domain.member.Member;
import servlet.servlet_practice.domain.member.MemberRepository;
import servlet.servlet_practice.web.frontcontroller.v4.ControllerV4;

import java.util.Map;

/**
 * Controller for saving member information in V4 architecture
 * Implements the ControllerV4 interface using a simplified approach
 * Directly updates the provided model map and returns view name as a String
 */
public class MemberSaveControllerV4 implements ControllerV4 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        // Extract parameters from the paramMap (converted from request by front controller)
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        // Add the member object to the provided model map
        // This is simpler than creating a ModelView object like in V3
        model.put("member", member);

        // Simply return the logical view name
        return "save-result";
    }
}
