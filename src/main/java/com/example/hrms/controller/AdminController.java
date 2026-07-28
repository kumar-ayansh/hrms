package com.example.hrms.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.hrms.dto.JobInfoDto;
import com.example.hrms.model.AdminInfo;
import com.example.hrms.model.Enquiry;
import com.example.hrms.model.JobInfo;
import com.example.hrms.model.Response;
import com.example.hrms.model.User;
import com.example.hrms.repo.AdminInfoRepo;
import com.example.hrms.repo.EnquiryRepo;
import com.example.hrms.repo.JobInfoRepo;
import com.example.hrms.repo.ResponseRepo;
import com.example.hrms.repo.UserRepo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {

	@Autowired
	UserRepo urepo;
	@Autowired
	JobInfoRepo jrepo;
	@Autowired
	EnquiryRepo erepo;
	@Autowired
	AdminInfoRepo airepo;
	@Autowired
	ResponseRepo rrepo;

	@GetMapping("/admin/admindashboard")
	public String showAdminDashboard(HttpSession session) {
		if (session.getAttribute("admin") == null) {
			return "redirect:/adminlogin";
		} else {
			return "/admin/admindashboard";
		}
	}

	@GetMapping("/admin/viewfeedback")
	public String viewFeedback(HttpSession session, Model model) {
		if (session.getAttribute("admin") == null) {
			return "redirect:/adminlogin";
		}
		List<Response> resList = rrepo.findAllByOrderByIdDesc();
		model.addAttribute("resList", resList);
		return "admin/viewfeedback";
	}

	@Transactional
	@GetMapping("/admin/deletefeedback")
	public String deleteFeedback(@RequestParam(name = "id", required = false) Integer id, HttpSession session,
			RedirectAttributes attrib) {
		if (session.getAttribute("admin") == null) {
			return "redirect:/adminlogin";
		}
		if (id == null) {
			attrib.addFlashAttribute("msg", "Response ID is required.");
			return "redirect:/admin/viewfeedback";
		}
		try {
			rrepo.deleteById(id);
			attrib.addFlashAttribute("msg", "User response deleted successfully!");
		} catch (Exception e) {
			attrib.addFlashAttribute("msg", "Error deleting response: " + e.getMessage());
		}
		return "redirect:/admin/viewfeedback";
	}

	@Transactional
	@PostMapping("/admin/replyfeedback")
	public String replyFeedback(@RequestParam("id") Integer id, @RequestParam("status") String status,
			@RequestParam("adminreply") String adminreply, HttpSession session, RedirectAttributes attrib) {
		if (session.getAttribute("admin") == null) {
			return "redirect:/adminlogin";
		}
		try {
			Response res = rrepo.findById(id).orElse(null);
			if (res != null) {
				res.setStatus(status);
				res.setAdminreply(adminreply);
				rrepo.save(res);
				attrib.addFlashAttribute("msg", "Response status & reply updated successfully!");
			} else {
				attrib.addFlashAttribute("msg", "Response not found.");
			}
		} catch (Exception e) {
			attrib.addFlashAttribute("msg", "Error updating reply: " + e.getMessage());
		}
		return "redirect:/admin/viewfeedback";
	}

	@GetMapping("/admin/jobseeker")
	public String viewUser(HttpSession session, Model model) {
		if (session.getAttribute("admin") == null) {
			return "redirect:/adminlogin";
		}
		List<User> users = urepo.findAll();
		model.addAttribute("users", users);
		return "/admin/jobseeker";
	}

	@GetMapping("/admin/logout")
	public String logout(HttpSession session) {
		if (session.getAttribute("admin") == null) {
			return "redirect:/adminlogin";
		}
		session.invalidate();
		return "redirect:/adminlogin";
	}

	@GetMapping("/admin/postjob")
	public String showPostJob(Model model, HttpSession session) {
		if (session.getAttribute("admin") == null) {
			return "redirect:/adminlogin";
		}
		JobInfoDto jdto = new JobInfoDto();
		model.addAttribute("jdto", jdto);
		return "admin/postjob";
	}

	@PostMapping("/admin/postjob")
	public String saveJob(@ModelAttribute JobInfoDto jdto, HttpSession session, RedirectAttributes attrib) {
		if (session.getAttribute("admin") == null) {
			return "redirect:/adminlogin";
		}
		JobInfo ji = new JobInfo();
		ji.setTitle(jdto.getTitle());
		ji.setDescription(jdto.getDescription());
		ji.setLocation(jdto.getLocation());
		ji.setSalary(jdto.getSalary());
		ji.setJobtype(jdto.getJobtype());
		ji.setLastdate(jdto.getLastdate());
		String posteddate = new Date().toString();
		ji.setPosteddate(posteddate);
		jrepo.save(ji);
		attrib.addFlashAttribute("msg", "Job details is posted");
		return "redirect:/admin/postjob";
	}

	@GetMapping("/admin/enquiries")
	public String viewEnquiries(HttpSession session, Model model) {
		if (session.getAttribute("admin") == null) {
			return "redirect:/adminlogin";
		}
		List<Enquiry> enq = erepo.findAll();
		model.addAttribute("enq", enq);
		return "admin/enquiries";
	}

	@GetMapping("/admin/viewjobs")
	public String viewjobs(HttpSession session, Model model) {
		if (session.getAttribute("admin") == null) {
			return "redirect:/adminlogin";
		}
		List<JobInfo> jinfo = jrepo.findAll();
		model.addAttribute("jinfo", jinfo);
		return "admin/viewjobs";
	}

	@Transactional
	@GetMapping("/admin/deletejob")
	public String deleteJob(@RequestParam(name = "id", required = false) Integer id, HttpSession session,
			RedirectAttributes attrib) {
		if (session.getAttribute("admin") == null) {
			return "redirect:/adminlogin";
		}
		if (id == null) {
			attrib.addFlashAttribute("msg", "Job ID is required.");
			return "redirect:/admin/viewjobs";
		}
		try {
			jrepo.deleteById(id);
			attrib.addFlashAttribute("msg", "Job post deleted successfully!");
		} catch (Exception e) {
			attrib.addFlashAttribute("msg", "Error deleting job post: " + e.getMessage());
		}
		return "redirect:/admin/viewjobs";
	}

	@GetMapping("/admin/changeadminpwd")
	public String changeAdminPassword(HttpSession session) {
		if (session.getAttribute("admin") == null) {
			return "redirect:/adminlogin";
		}
		return "admin/changeadminpwd";
	}

	@PostMapping("/admin/changeadminpwd")
	public String changeAdminPwd(HttpSession session, HttpServletRequest request, RedirectAttributes attrib) {
		if (session.getAttribute("admin") == null) {
			return "redirect:/adminlogin";
		}
		String oldpassword = request.getParameter("oldpassword");
		String newpassword = request.getParameter("newpassword");
		String confirmpassword = request.getParameter("confirmpassword");
		if (!newpassword.equals(confirmpassword)) {
			attrib.addFlashAttribute("msg", "New Password and Confirmpassword not match");
			return "redirect:/admin/changeadminpwd";
		} 
		try {
			AdminInfo admin = (AdminInfo) session.getAttribute("admin");
			if (!admin.getPassword().equals(oldpassword)) {
				attrib.addFlashAttribute("msg", "Old password does not match");
				return "redirect:/admin/changeadminpwd";
			}
			admin.setPassword(newpassword);
			airepo.save(admin);
			return "redirect:/admin/logout";
		} catch (Exception e) {
			attrib.addFlashAttribute("msg", "AdminId is not matched");
			return "redirect:/admin/changeadminpwd";
		}
	}
}