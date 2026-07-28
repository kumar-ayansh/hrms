package com.example.hrms.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.hrms.dto.ResponseDto;
import com.example.hrms.model.JobInfo;
import com.example.hrms.model.Response;
import com.example.hrms.model.User;
import com.example.hrms.repo.JobInfoRepo;
import com.example.hrms.repo.ResponseRepo;
import com.example.hrms.repo.UserRepo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	@Autowired
	JobInfoRepo jrepo;
	@Autowired
	UserRepo urepo;
	@Autowired
	ResponseRepo rrepo;
	
	@GetMapping("/user/userdash")
	public String userDashboard(HttpSession session, Model model) {
		if (session.getAttribute("user") == null) {
			return "redirect:/login";
		}
		User user = (User) session.getAttribute("user");
		model.addAttribute("user", user);
		model.addAttribute("jobCount", jrepo.count());
		model.addAttribute("responseCount", rrepo.count());
		return "user/userdash";
	}

	@GetMapping("/user/giveresponse")
	public String showGiveResponse(HttpSession session, Model model) {
		if (session.getAttribute("user") == null) {
			return "redirect:/login";
		}
		User user = (User) session.getAttribute("user");
		model.addAttribute("user", user);
		ResponseDto rdto = new ResponseDto();
		model.addAttribute("rdto", rdto);
		List<Response> resList = (user != null && user.getEmailaddress() != null)
				? rrepo.findByEmailaddressOrderByIdDesc(user.getEmailaddress())
				: java.util.Collections.emptyList();
		model.addAttribute("resList", resList);

		long feedbackCount = resList.stream().filter(r -> r.getResponsetype() != null && "Feedback".equalsIgnoreCase(r.getResponsetype())).count();
		long suggestionCount = resList.stream().filter(r -> r.getResponsetype() != null && "Suggestion".equalsIgnoreCase(r.getResponsetype())).count();
		long complaintCount = resList.stream().filter(r -> r.getResponsetype() != null && "Complaint".equalsIgnoreCase(r.getResponsetype())).count();

		model.addAttribute("feedbackCount", feedbackCount);
		model.addAttribute("suggestionCount", suggestionCount);
		model.addAttribute("complaintCount", complaintCount);

		return "user/giveresponse";
	}

	@PostMapping("/user/giveresponse")
	public String saveResponse(HttpServletRequest request, HttpSession session, RedirectAttributes attrib) {
		if (session.getAttribute("user") == null) {
			return "redirect:/login";
		}
		User user = (User) session.getAttribute("user");
		String responsetype = request.getParameter("responsetype");
		String subject = request.getParameter("subject");
		String responsetext = request.getParameter("responsetext");

		Response res = new Response();
		res.setUserid(user != null ? user.getUserid() : 0);
		res.setName(user != null && user.getName() != null ? user.getName() : "Job Seeker");
		res.setEmailaddress(user != null && user.getEmailaddress() != null ? user.getEmailaddress() : "");
		res.setContactno(user != null && user.getContactno() != null ? user.getContactno() : "");
		res.setResponsetype(responsetype);
		res.setSubject(subject);
		res.setResponsetex(responsetext);
		res.setStatus("Pending");
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy, hh:mm a");
		res.setPosteddate(formatter.format(new Date()));
		
		rrepo.save(res);
		attrib.addFlashAttribute("msg", "Your response has been submitted successfully!");
		return "redirect:/user/giveresponse";
	}

	@Transactional
	@GetMapping("/user/deleteresponse")
	public String deleteResponse(@RequestParam(name = "id", required = false) Integer id, HttpSession session, RedirectAttributes attrib) {
		if (session.getAttribute("user") == null) {
			return "redirect:/login";
		}
		User user = (User) session.getAttribute("user");
		if (id == null) {
			attrib.addFlashAttribute("msg", "Invalid response ID.");
			return "redirect:/user/giveresponse";
		}
		try {
			Response res = rrepo.findById(id).orElse(null);
			if (res != null && res.getEmailaddress() != null && res.getEmailaddress().equalsIgnoreCase(user.getEmailaddress())) {
				rrepo.deleteById(id);
				attrib.addFlashAttribute("msg", "Response deleted successfully!");
			} else {
				attrib.addFlashAttribute("msg", "Unauthorized to delete this response.");
			}
		} catch (Exception e) {
			attrib.addFlashAttribute("msg", "Error deleting response: " + e.getMessage());
		}
		return "redirect:/user/giveresponse";
	}

	@GetMapping("/user/viewjobs")
	public String viewJobs(HttpSession session, Model model) {
		if (session.getAttribute("user") == null) {
			return "redirect:/login";
		}
		List<JobInfo> jinfo = jrepo.findAll();
		model.addAttribute("jinfo", jinfo);
		return "user/viewjobs";
	}

	@GetMapping("/user/changepwd")
	public String changePwd(HttpSession session) {
		if (session.getAttribute("user") == null) {
			return "redirect:/login";
		}
		return "user/changepwd";
	}

	@GetMapping("/user/logout")
	public String logout(HttpSession session) {
		if (session.getAttribute("user") == null) {
			return "redirect:/login";
		}
		session.removeAttribute("user");
		return "redirect:/login";
	}

	@PostMapping("/user/changepwd")
	public String changePwd(HttpSession session, HttpServletRequest request, RedirectAttributes attrib) {
		if (session.getAttribute("user") == null) {
			return "redirect:/login";
		}
		
		String oldpassword = request.getParameter("oldpassword");
		String newpassword = request.getParameter("newpassword");
		String confirmpassword = request.getParameter("confirmpassword");
		if (!newpassword.equals(confirmpassword)) {
			attrib.addFlashAttribute("msg", "New Password and Confirmpassword not match");
			return "redirect:/user/changepwd";
		}
		try {
			User user = (User) session.getAttribute("user");
			if (!user.getPassword().equals(oldpassword)) {
				attrib.addFlashAttribute("msg", "Oldpassword is match");
				return "redirect:/user/changepwd";
			}
			user.setPassword(newpassword);
			urepo.save(user);
			return "redirect:/user/logout";
		} catch (Exception e) {
			attrib.addFlashAttribute("msg", "User Id is not matched");
			return "redirect:/user/changepwd";
		}
	}
}










