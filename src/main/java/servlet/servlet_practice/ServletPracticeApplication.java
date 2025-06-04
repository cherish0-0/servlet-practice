package servlet.servlet_practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan // This annotation enables scanning for servlet components like @WebServlet, @WebFilter, etc. automatically.
@SpringBootApplication
public class ServletPracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServletPracticeApplication.class, args);
	}

}
