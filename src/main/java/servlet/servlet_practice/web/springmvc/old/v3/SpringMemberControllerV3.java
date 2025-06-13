package servlet.servlet_practice.web.springmvc.old.v3;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import servlet.servlet_practice.domain.member.Member;
import servlet.servlet_practice.domain.member.MemberRepository;

import java.util.List;

// Marks this class as a Spring MVC Controller.
// Spring will detect this class during component scanning and register it as a controller bean.
@Controller
// Class-level RequestMapping annotation sets the base URL path for all methods in this controller.
// All handler methods in this class will be mapped relative to "/springmvc/v3/members".
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    // Handles HTTP GET requests to "/springmvc/v3/members/new-form".
    // Displays the form for creating a new member.
    // @GetMapping is a specialized version of @RequestMapping that narrows to GET method only.
    @GetMapping("new-form")
    public String newForm() {
        // Returns a view name (logical name of the view template).
        // Spring will resolve this view name to an actual view (e.g., a JSP file).
        // This is different from previous versions which returned ModelAndView.
        return "new-form";
    }

    // Handles HTTP GET requests to the base path "/springmvc/v3/members".
    // Lists all members in the repository.
    @GetMapping
    public String members(Model model) {
        List<Member> members = memberRepository.findAll();

        model.addAttribute("members", members);

        return "members";
    }

    // Handles HTTP POST requests to "/springmvc/v3/members/save".
    // Processes the form submission to save a new member.
    // @PostMapping ensures this method only responds to POST requests.
    @PostMapping("save")
    public String save(
            // @RequestParam annotation binds request parameters to method parameters.
            // This extracts the "username" parameter from the request.
            @RequestParam("username") String username,
            // Extracts the "age" parameter and automatically converts it to an int.
            @RequestParam("age") int age,
            // Spring provides a Model object for adding attributes to the model.
            Model model) {

        Member member = new Member(username, age);
        memberRepository.save(member);

        model.addAttribute("member", member);
        return "save-result";
    }
}

/*
 * Note: RequestMappingHandlerMapping, RequestMappingHandlerAdapter, and ViewResolver are automatically configured in Spring MVC framework.
 * Client request reaches DispatcherServlet
 * RequestMappingHandlerMapping retrieves the appropriate RequestMappingHandlerAdapter
 * based on the @Controller annotation.
 * RequestMappingHandlerAdapter maps the request to the appropriate controller method
 * based on the URL and HTTP method in @GetMapping, @PostMapping, etc.
 * Logical view name is returned to the physical view through ViewResolver.
 * The view is rendered, and the response is sent back to the client.
 */