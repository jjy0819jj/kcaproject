package member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import member.dao.LoginDAO;

@Controller
public class LoginController {
	
	//1.로그인 페이지 호출
	@RequestMapping(value = "login.do")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
	 	// 데이터
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		String member_code = request.getParameter("member_code");
		// DB
		LoginDAO loginDAO = new LoginDAO();
		String name = loginDAO.login(email, pwd);
		ModelAndView modelAndView = new ModelAndView();
		
		if(name == null) {	// 로그인 실패
			modelAndView.setViewName("login.jsp");
		} else {			// 로그인 성공
			HttpSession session = request.getSession();
			session.setAttribute("email", email);
			session.setAttribute("name", name);
			session.setAttribute("member_code", member_code);
			modelAndView.setViewName("index2.jsp");				
		}
		return modelAndView;
	}
	
	//2.로그아웃 페이지 호출
	@RequestMapping(value = "logout.do")
	public ModelAndView logout(HttpSession session) {
		LoginDAO loginDAO = new LoginDAO();
		loginDAO.logout(session);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("logout.jsp");
		return modelAndView;
	}
}
