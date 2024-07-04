package com.aftab.sms.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aftab.sms.binding.AddStudent;
import com.aftab.sms.binding.LoginRequesst;
import com.aftab.sms.binding.SearchRequest;
import com.aftab.sms.entities.Branch;
import com.aftab.sms.entities.Course;
import com.aftab.sms.entities.Student;
import com.aftab.sms.repo.CourseRepo;
import com.aftab.sms.service.StudentService;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class MainController {
	@Autowired
	private StudentService studentService;
	@Autowired
	private CourseRepo courseRepo;
	List<Student> allStudents = new ArrayList<>();

	@GetMapping("/home")
	public String loadIndex(SearchRequest sr, Model model) {
		System.out.println("-------------------Inside home----------------");
		model.addAttribute("search", sr);
		studentService.clearList();
		init(model);
		return "index";
	}

	@GetMapping("/search")
	public String search(@ModelAttribute("search") SearchRequest search, Model m) {
		System.out.println("inside search:" + search);
		List<Student> students = studentService.search(search);
		m.addAttribute("students", students);
		System.out.println("Received Data : " + search);
		init(m);
		return "index";

	}

	@PostMapping("/login")
	public String search(@ModelAttribute("login") LoginRequesst login, Model m) {
		System.out.println("inside login: " + login);
		System.out.println("-------------------------Log In---------------------");
		System.out.println(login);
		String userType = login.getUserType();
		String userName = login.getUserName();
		String userPass = login.getPassword();
		String message = "";
		if (userType != null && !userType.isEmpty() && userName != null && !userName.isEmpty() && userPass != null
				&& !userPass.isEmpty()) {
			boolean logInAttempt = studentService.logInAttempt(login);
			if (logInAttempt) {
				message = "Login successful";
			} else {
				message = "Invalid Login Attempt try again";
			}

		}
		m.addAttribute("message", message);
		init(m);
		return "index";
	}

	@GetMapping("/excel")
	public void exportExcel(HttpServletResponse response, Model m) throws Exception {
		response.setContentType("application/octet-stream");
		response.addHeader("Content-Disposition", "attachment;filename=Student_data.xls");
		studentService.exportExcel(response);

	}

	@GetMapping("/pdf")
	public void exportPdf(HttpServletResponse response, Model m) throws Exception {
		response.setContentType("application/pdf");
		response.addHeader("Content-Disposition", "attachment;filename=Student_data.pdf");
		studentService.exportPdf(response);

	}

	private void init(Model model) {
		model.addAttribute("accountType", studentService.getUserAccountType());
		model.addAttribute("courses", studentService.getAllCourseNames());
		model.addAttribute("branches", studentService.getAllBranchNames());
		model.addAttribute("courseObj", studentService.getAllCourses());
		model.addAttribute("branchWithCourse", studentService.getBranchesAndCourseTogether());
		System.out.println("********************Within Init************************");
	}

	@GetMapping("/loadAll")
	public String loadAllStudents(Model m) {
		System.out.println("Inside load method");
		allStudents.clear();
		allStudents = studentService.getAllStudents();
		if (allStudents.size() > 0) {
			m.addAttribute("students", allStudents);
		}

		init(m);
		return "index";
	}

	@PostMapping("/getCourses")
	@ResponseBody
	public ResponseEntity<List<Course>> getCourseInJsonFormat() {
		return new ResponseEntity<>(studentService.getAllCourses(), HttpStatus.OK);
	}

	@GetMapping("/branches/{courseId}")
	@ResponseBody
	public ResponseEntity<Set<Branch>> getBranchesByCourse(@PathVariable Long courseId) {
		Optional<Course> courseOptional = courseRepo.findById(courseId);

		if (courseOptional.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		Set<Branch> branches = courseOptional.get().getBranches();
		return ResponseEntity.ok(branches);
	}

	@PostMapping("/handleStudent")
	public String handleStudentForm(@ModelAttribute("addStudent") AddStudent addStudent, BindingResult br, Model m) {
		if (br.hasErrors()) {
			System.out.println("Error inside Handle method");
		}
		if (addStudent.getStudentImage().isEmpty()) {
			System.out.println("Image file is blank");
		}
		try {
			Student student = new Student();
			student.setFirstName(addStudent.getFirstName());
			student.setLastName(addStudent.getLastName());
			student.setMiddleName(addStudent.getMiddleName());
			student.setGender(addStudent.getGender());
			student.setPhoneNumber(addStudent.getPhoneNumber());
			student.setStudentImage(addStudent.getStudentImage().getBytes());
			student.setDateOfBirth(addStudent.getDateOfBirth());
			student.setAddress(addStudent.getAddress());
			System.out.println("getBranch: " + addStudent.getBranch());
			System.out.println("getCourse: " + addStudent.getCourse());
			Optional<Branch> branch = studentService.getBranchById(Long.parseLong(addStudent.getBranch()));
			Optional<Course> course = studentService.getCourseById(Long.parseLong(addStudent.getCourse()));
			if (branch.isPresent() && course.isPresent()) {
				Branch b = branch.get();
				Course c = course.get();
				student.setCourse(c);
				student.setBranch(b);
			}
			studentService.saveStudent(student);
		} catch (Exception e) {
			e.printStackTrace(); // Log or handle exception as needed
		}
		System.out.println("______Inside Handle Method_______________________");
		allStudents = studentService.getAllStudents();
		m.addAttribute("students", allStudents);
		init(m);
		return "index";
	}

}
//	
