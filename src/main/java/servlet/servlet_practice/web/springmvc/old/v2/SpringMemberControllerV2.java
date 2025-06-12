package servlet.servlet_practice.web.springmvc.old.v2;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import servlet.servlet_practice.domain.member.Member;
import servlet.servlet_practice.domain.member.MemberRepository;

import java.util.List;

// Marks this class as a Spring MVC Controller.
// Spring will automatically detect this class during component scanning and register it as a controller bean.
@Controller
// Class-level RequestMapping annotation sets the base URL path for all methods in this controller.
// All handler methods in this class will be mapped relative to "/springmvc/v2/members".
@RequestMapping("/springmvc/v2/members")
public class SpringMemberControllerV2 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping("new-form")
    public ModelAndView newForm() {
        // Returns a ModelAndView that references the "new-form" view template.
        // This will render the form for member creation.
        return new ModelAndView("new-form");
    }

    @RequestMapping
    public ModelAndView members() {
        List<Member> members = memberRepository.findAll();

        // Creates a ModelAndView with the view name "members".
        ModelAndView mv = new ModelAndView("members");
        // Adds the list of members to the model, making it available to the view.
        mv.addObject("members", members);

        // Returns the ModelAndView that will render the members list page.
        return mv;
    }

    @RequestMapping("save")
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
        // Retrieve the 'username' parameter from the HTTP request.
        String username = request.getParameter("username");
        // Retrieve the 'age' parameter from the HTTP request and convert it to an integer.
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        // Create a ModelAndView that references the "save-result" view template.
        ModelAndView mv = new ModelAndView("save-result");
        // Add the saved member to the model so it can be displayed in the view.
        mv.addObject("member", member);
        // Return the ModelAndView to render the save confirmation page.
        return mv;
    }
}
