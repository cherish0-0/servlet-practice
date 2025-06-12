package servlet.servlet_practice.web.springmvc.old.v1;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import servlet.servlet_practice.domain.member.Member;
import servlet.servlet_practice.domain.member.MemberRepository;

import java.util.Map;

// Marks this class as a Spring MVC Controller.
// Spring will detect this class and register it as a controller bean.
@Controller
public class SpringMemberSaveControllerV1 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    // Handles HTTP requests to "/springmvc/v1/members/save".
    // This method processes form submissions for saving a new member.
    @RequestMapping("/springmvc/v1/members/save")
    public ModelAndView process(HttpServletRequest request, HttpServletResponse response) {
        // Retrieve the 'username' parameter from the HTTP request.
        String username = request.getParameter("username");
        // Retrieve the 'age' parameter from the HTTP request and convert it to an integer.
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        ModelAndView mv = new ModelAndView("save-result");
        mv.addObject("member", member);
        return mv;
    }
}
