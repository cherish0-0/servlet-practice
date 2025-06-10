package servlet.servlet_practice.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servlet.servlet_practice.domain.member.Member;
import servlet.servlet_practice.domain.member.MemberRepository;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Servlet for displaying the list of all members
 * This servlet retrieves all members from the repository
 * and generates an HTML table to display their information
 * URL pattern: /servlet/members
 */
@WebServlet(name = "memberListServlet", urlPatterns = "/servlet/members")
public class MemberListServlet extends HttpServlet {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Member> members = memberRepository.findAll();

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        PrintWriter w = response.getWriter();
        w.write("<html>");
        w.write("<head>");
        w.write("   <meta charset=\"UTF-8\">");
        w.write("   <title>Title</title>");
        w.write("</head>");
        w.write("<body>");
        w.write("<a href=\"/index.html\">메인</a>");
        w.write("<table>");
        w.write("   <thead>");
        w.write("   <th>id</th>");
        w.write("   <th>username</th>");
        w.write("   <th>age</th>");
        w.write("   </thead>");
        w.write("   <tbody>");

        for (Member member : members) {
            w.write("   <tr>");
            w.write("     <td>" + member.getId() + "</td>");
            w.write("     <td>" + member.getUsername() + "</td>");
            w.write("     <td>" + member.getAge() + "</td>");
            w.write("   </tr>");
        }
        w.write("   </tbody>");
        w.write("</table>");
        w.write("</body>");
        w.write("</html>");
    }
}
