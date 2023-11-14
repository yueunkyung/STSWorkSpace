package com.shinhan.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shinhan.dto.EmpVO;
import com.shinhan.model.DeptService;
import com.shinhan.model.EmpService;
import com.shinhan.model.JobDAO;

@Controller
@RequestMapping("/emp")
public class EmpController {

	Logger logger = LoggerFactory.getLogger(EmpController.class);

	@Autowired
	EmpService eService;

	@Autowired
	DeptService dService;

	@Autowired
	JobDAO jdao;

	@GetMapping("/empList.do")
	public String emplistDisplay(Model model) {
		// int a = 10/0; //에러테스트

		/*
		 * Map<String, ?> redirectData = RequestContextUtils.getInputFlashMap(request);
		 * if(redirectData!=null) { for(String key:redirectData.keySet()) {
		 * model.addAttribute(key, redirectData.get(key)); System.out.println("?????" +
		 * model.addAttribute(key, redirectData.get(key))); } }
		 */

		model.addAttribute("dlist", dService.selectAll());
		model.addAttribute("jlist", jdao.selectAll());
		// model.addAttribute("emplist", eService.selectAll(deptid, jobid, salary,
		// hiredate));
		return "emp/empList";
	}

	// Ajax로 옴
	@GetMapping("/empListAjax.do")
	public String emplistDisplay2(@RequestParam(value = "deptid[]", required = false) ArrayList<Integer> deptid,
			@RequestParam(value = "jobid", required = false) String jobid,
			@RequestParam(value = "salary", required = false) Integer salary,
			@RequestParam(value = "hiredate", required = false) String hiredate, Model model,
			HttpServletRequest request, HttpSession session) {

		logger.info("deptid: {}", deptid);
		logger.info("jobid: {}", jobid);
		logger.info("salary: {}", salary);
		logger.info("hiredate: {}", hiredate);

		session.setAttribute("select_deptid", deptid);
		session.setAttribute("select_jobid", jobid);
		session.setAttribute("select_salary", salary);
		session.setAttribute("select_hiredate", hiredate);

//		if(deptid == null) deptid = 0;
//		if(jobid == null) jobid = "%";
//		if(salary == null) salary = 0; 
//		if(hiredate == null) hiredate = "1900-01-01";

		model.addAttribute("emplist", eService.selectAll(deptid, jobid, salary, hiredate));

		return "emp/empList_ajax";
	}

	@GetMapping("/empDetail.do")
	public String empDetailDisplay(int empid, Model model) {
		model.addAttribute("emp", eService.selectById(empid));
		model.addAttribute("dlist", dService.selectAll());
		model.addAttribute("jlist", jdao.selectAll());
//		model.addAttribute("mlist", eService.selectAll(0, "%", 0, "1990-01-01"));
		model.addAttribute("mlist", eService.selectManagerAll());
		return "emp/empDetail";
	}

	@PostMapping("/empDetail.do")
	public String empUpdate(EmpVO emp, RedirectAttributes attr) {
		logger.info("수정할 직원 정보" + emp.toString());
		int result = eService.empUpdate(emp);
		attr.addFlashAttribute("message", result > 0 ? "수정 성공" : "수정 실패");
		attr.addFlashAttribute("message2", result > 0 ? "수정 성공" : "수정 실패");

		return "redirect:/emp/empList.do";
	}
	/*
	 * Model : controller가 jsp에 forward시 request에 저장하기 위해 사용 -> Model은 Map의 형태를 하고
	 * 있다.(key:value) RedirectAttributes : redirect시에 값을 전달하기 위함
	 */

	@GetMapping("empInsert.do")
	public void empInsertDisplay(Model model) {
		model.addAttribute("dlist", dService.selectAll());
		model.addAttribute("jlist", jdao.selectAll());
		model.addAttribute("mlist", eService.selectManagerAll());
	}

	@PostMapping("/empInsert.do")
	public String empInsertPost(EmpVO emp, RedirectAttributes attr, HttpSession session) {

		MultipartFile mf = emp.getUploadFile();
		String originFileName = mf.getOriginalFilename();
		long time = System.currentTimeMillis();
		String saveFileName = String.format("%d_%s", time, originFileName);
		ServletContext application = session.getServletContext();
		String path = application.getRealPath("./resources/uploads");
		
		logger.info("파일이름:{}", mf.getOriginalFilename());
		
		//DB저장을 위해 VO에 setting
		emp.setPic(saveFileName);
		
		try {
			mf.transferTo(new File(path, saveFileName));	//Upload
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		logger.info("입력할 직원 정보" + emp.toString());
		int result = eService.empInsert(emp);
		attr.addFlashAttribute("message", result > 0 ? "입력 성공" : "입력 실패");

		return "redirect:/emp/empList.do";
	}

	// method가 생략시 모두 가능
	@RequestMapping(value = "/empDelete.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String empDeleteGet(int empid, RedirectAttributes attr) {
		logger.info("삭제할 직원정보 : " + empid);
		int result = eService.empDelete(empid);
		attr.addFlashAttribute("message", result > 0 ? "삭제성공" : "삭제실패");
		return "redirect:/emp/empList.do";
	}

	/*
	 * @GetMapping("/empDelete.do") public String empDeleteGet(int empid,
	 * RedirectAttributes attr) { deleteProcess(empid, attr); return
	 * "redirect:/emp/empList.do"; }
	 * 
	 * @PostMapping("/empDelete.do") public String empDeletePost(int empid,
	 * RedirectAttributes attr) { deleteProcess(empid, attr); return
	 * "redirect:/emp/empList.do"; } private void deleteProcess(int empid,
	 * RedirectAttributes attr) { logger.info("삭제할 직원 정보"+ empid); int result =
	 * eService.empDelete(empid); attr.addFlashAttribute("message",
	 * result>0?"삭제 성공":"삭제 실패"); }
	 */
}
