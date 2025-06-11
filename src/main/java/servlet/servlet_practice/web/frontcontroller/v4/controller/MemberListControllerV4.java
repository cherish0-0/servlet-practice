package servlet.servlet_practice.web.frontcontroller.v4.controller;

import servlet.servlet_practice.domain.member.Member;
import servlet.servlet_practice.domain.member.MemberRepository;
import servlet.servlet_practice.web.frontcontroller.v4.ControllerV4;

import java.util.List;
import java.util.Map;

/**
 * Controller for displaying the member list in V4 architecture
 * Implements the ControllerV4 interface using a simplified approach
 * Directly updates the provided model map and returns view name as a String
 */
public class MemberListControllerV4 implements ControllerV4 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        List<Member> members = memberRepository.findAll();

        // Add the members list to the provided model map
        // This approach is simpler than creating a ModelView object like in V3
        model.put("members", members);
        
        // Simply return the logical view name
        return "members";
    }
}
