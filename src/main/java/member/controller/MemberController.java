package member.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import member.bean.MemberDTO;

@Controller
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value = "/join.do")
	public ModelAndView joinForm() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("join.jsp");
		return modelAndView;  
	}
	
	@RequestMapping(value = "/joinOk.do")
	public ModelAndView memberJoin(HttpServletRequest request) {
		
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		String tel = request.getParameter("tel");
		String tel2 = request.getParameter("tel2");
		String tel3 = request.getParameter("tel3");
		String addr = request.getParameter("addr");
		
		// DB
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setEmail(email);
		memberDTO.setName(name);
		memberDTO.setPwd(pwd);
		memberDTO.setTel(tel);
		memberDTO.setTel2(tel2);
		memberDTO.setTel3(tel3);
		memberDTO.setAddr(addr);
		
		int su = memberService.MemberJoin(memberDTO);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("su", su);
		modelAndView.setViewName("joinOk.jsp");
		return modelAndView; 
	}
	
	@RequestMapping(value = "/mypage.do")
	public ModelAndView memberView(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		String name = (String) session.getAttribute("name");
		
		MemberDTO memberDTO = memberService.memberView(email);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("email",email);
		modelAndView.addObject("name",name);
		modelAndView.addObject("memberDTO",memberDTO);
		modelAndView.setViewName("mypage.jsp");
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/memberUpdateForm.do")
	public ModelAndView memberUpdateForm(HttpServletRequest request) {
		String email = request.getParameter("email");
		
		MemberDTO memberDTO = memberService.memberView(email);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("email",email);
		modelAndView.addObject("memberDTO",memberDTO);
		modelAndView.setViewName("memberUpdateForm.jsp");
		return modelAndView;
	}
	
	@RequestMapping(value = "/memberUpdate.do")
	public ModelAndView memberUpdate(HttpServletRequest request) {
		
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		String tel = request.getParameter("tel");
		String tel2 = request.getParameter("tel2");
		String tel3 = request.getParameter("tel3");
		int member_code =Integer.parseInt(request.getParameter("member_code"));
		String addr = request.getParameter("addr");
		
		// DB
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setPwd(pwd);
		memberDTO.setTel(tel);
		memberDTO.setTel2(tel2);
		memberDTO.setTel3(tel3);
		memberDTO.setMember_code(member_code);
		memberDTO.setAddr(addr);
		memberDTO.setEmail(email);
		
		int su = memberService.memberUpdate(memberDTO);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("su", su);
		modelAndView.addObject("email", email);
		modelAndView.setViewName("memberUpdate.jsp");
		return modelAndView; 
	}
	
	@RequestMapping(value = "memberDelete.do")
	public ModelAndView memberDelete(HttpServletRequest request) {
		String email = request.getParameter("email"); 
		
		int su = memberService.memberDelete(email);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("su", su);
		modelAndView.addObject("email", email);
		modelAndView.setViewName("memberDelete.jsp");
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/applicationInsert.do")
	public ModelAndView fileUpload(HttpServletRequest request,MultipartFile uploadFile) throws UnsupportedEncodingException{
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		String filePath = "C:\\Users\\hiiam\\bigdata_yjh\\workspace_s\\TeamProject\\src\\main\\webapp\\resources\\resume";
		String fileName = new String(uploadFile.getOriginalFilename().getBytes("8859_1"),"UTF-8");
		
		File file = new File(filePath,fileName);
		
		try {
			FileCopyUtils.copy(uploadFile.getInputStream(), new FileOutputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setFile_name(fileName);
		memberDTO.setEmail(email);
		int su = memberService.fileUpload(memberDTO);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("su", su);
		modelAndView.setViewName("applicationInsert.jsp");
		return modelAndView;
		
	}
	
	@RequestMapping(value = "/resumeView.do")
	public ModelAndView resumeHit(HttpServletRequest request) {
		String email = request.getParameter("email");
		String file_name = request.getParameter("file_name");
		
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setFile_name(file_name);
		memberDTO.setEmail(email);
		
		int su = memberService.resumeHit(memberDTO);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("su", su);
		modelAndView.addObject("file_name", file_name);
		modelAndView.setViewName("resumeView.jsp");
		return modelAndView;
	}
}
