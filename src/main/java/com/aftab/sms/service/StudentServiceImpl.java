package com.aftab.sms.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aftab.sms.binding.LoginRequesst;
import com.aftab.sms.binding.SearchRequest;
import com.aftab.sms.entities.Branch;
import com.aftab.sms.entities.Course;
import com.aftab.sms.entities.Student;
import com.aftab.sms.entities.UserAccount;
import com.aftab.sms.repo.BranchRepo;
import com.aftab.sms.repo.CourseRepo;
import com.aftab.sms.repo.StudentRepo;
import com.aftab.sms.repo.UserAccountRepo;
import com.aftab.sms.util.EmailUtils;
import com.aftab.sms.util.ExcelGenerator;
import com.aftab.sms.util.PdfGenerator;

import jakarta.servlet.http.HttpServletResponse;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepo studentRepo;
	@Autowired
	private UserAccountRepo userRepo;
	@Autowired
	private BranchRepo branchRepo;
	@Autowired
	private CourseRepo courseRepo;
	@Autowired
	private EmailUtils emailUtils;
	@Autowired
	private PdfGenerator pdfGenerator;
	@Autowired
	private ExcelGenerator excelGenerator;

	private List<Student> studentList = new ArrayList<>();

	@Override
	public Student getStudentByRegistrationNumber(Long rn) {
		return studentRepo.findByRegistrationNumber(rn);
	}

	@Override
	public List<Student> getAllStudents() {
		studentList = studentRepo.findAll();
		return studentList;
	}

	@Override
	public Student saveStudent(Student student) {
		return studentRepo.save(student);
	}

	@Override
	public Student getStudentByFirstName(String name) {
		// TODO Auto-generated method stub

		return studentRepo.findByFirstName(name);
	}

	public List<Course> getAllCourses() {
		List<Course> list = new ArrayList<>();
		list = courseRepo.findAll();
		return list;
	}

	@Override
	public Student getStudentByLastName(String name) {
		// TODO Auto-generated method stub
		return studentRepo.findByLastName(name);
	}

	@Transactional
	@Override
	public void deleteStudent(Student student) {
		// TODO Auto-generated method stub
		studentRepo.delete(student);
	}

	@Override
	public List<String> getUserAccountType() {
		// TODO Auto-generated method stub
		List<String> userNames = new ArrayList<>();
		List<UserAccount> all = userRepo.findAll();
		for (UserAccount userAccount : all) {
			userNames.add(userAccount.getRole());
		}
		return userNames;
	}

	@Transactional
	public void saveBranches(Set<Branch> branches) {
		for (Branch branch : branches) {
			branchRepo.save(branch);

		}
	}

	@Transactional
	public void saveCourses(Course course) {
		courseRepo.save(course);
	}

	@Transactional
	public void saveUserAccount(UserAccount account) {
		userRepo.save(account);
	}

	@Transactional
	public void saveBranch(Branch b) {
		branchRepo.save(b);
	}

	public List<Branch> getAllBranches() {
		return branchRepo.findAll();
	}

	@Override
	public boolean logInAttempt(LoginRequesst login) {
		// TODO Auto-generated method stub
		UserAccount user = userRepo.findByRole(login.getUserType());
		if (user.getUsername().equals(login.getUserName()) && user.getPassword().equals(login.getPassword())) {
			return true;
		}
		return false;
	}

	@Override
	public List<String> getAllCourseNames() {
		// TODO Auto-generated method stub
		List<String> coursesName = new ArrayList<>();
		List<Course> allCourses = getAllCourses();
		for (Course course : allCourses) {
			coursesName.add(course.getCourseName());
		}
		return coursesName;
	}

	@Override
	public List<String> getAllBranchNames() {
		// TODO Auto-generated method stub
		List<String> branchNameList = new ArrayList<>();
		List<Branch> allBranches = getAllBranches();
		for (Branch branch : allBranches) {
			branchNameList.add(branch.getBranchName());
		}
		return branchNameList;
	}

	@Override
	public List<Branch> getBranchesByCourseName(String courseName) {
		// TODO Auto-generated method stub
		List<Branch> byCourseName = branchRepo.findByCourseName(courseName);
		return byCourseName;
	}

	public Map<String, List<Branch>> getBranchesAndCourseTogether() {
		Map<String, List<Branch>> branchWithCourse = new HashMap<>();
		List<String> allCourseNames = getAllCourseNames();
		for (String string : allCourseNames) {
			List<Branch> branchesByCourseName = getBranchesByCourseName(string);
			branchWithCourse.put(string, branchesByCourseName);
		}
		for (Map.Entry<String, List<Branch>> entry : branchWithCourse.entrySet()) {
			System.out.println("Course: " + entry.getKey() + ", Branches: " + entry.getValue());
		}
		return branchWithCourse;
	}

	public Branch getBranchByName(String name) {
		Branch branch = branchRepo.findByBranchName(name);
		return branch;
	}

	@Override
	public Course getCourseByName(String name) {
		// TODO Auto-generated method stub
		Course course = courseRepo.findByCourseName(name);
		return course;
	}

	@Override
	public void deleteCoursesFromDB() {
		// TODO Auto-generated method stub
		courseRepo.deleteAll();

	}

	@Override
	public void deleteBrachesFromDB() {
		// TODO Auto-generated method stub
		branchRepo.deleteAll();
	}

	@Override
	public void deleteUsersFromDB() {
		// TODO Auto-generated method stub
		userRepo.deleteAll();
	}

	@Override
	public Optional<Branch> getBranchById(long branch) {
		// TODO Auto-generated method stub
		Optional<Branch> byId = branchRepo.findById(branch);
		return byId;
	}

	@Override
	public Optional<Course> getCourseById(long course) {
		// TODO Auto-generated method stub
		return courseRepo.findById(course);
	}

	@Override
	public void deleteAllStudents() {
		// TODO Auto-generated method stub
		studentRepo.deleteAll();
	}

	@Override
	public void deleteAllRecords() {
		// TODO Auto-generated method stub
		studentRepo.deleteAll();
		courseRepo.deleteAll();
		branchRepo.deleteAll();
		userRepo.deleteAll();
	}

	@Override
	public List<Student> search(SearchRequest sr) {
		// TODO Auto-generated method stub
		Student student = new Student();
		if (null != sr.getFirstName() && !"".equals(sr.getFirstName())) {
			student.setFirstName(sr.getFirstName());
		}
		if (null != sr.getLastName() && !"".equals(sr.getLastName())) {
			student.setLastName(sr.getLastName());

		}
		if (null != sr.getRegistrationNumber() && !"".equals(sr.getRegistrationNumber())) {
			student.setRegistrationNumber(Long.parseLong(sr.getRegistrationNumber()));

		}
		if (null != sr.getCourseName() && !"".equals(sr.getCourseName())) {
			student.setCourse(getCourseByName(sr.getCourseName()));

		}
		if (null != sr.getBranchName() && !"".equals(sr.getBranchName())) {
			student.setBranch(getBranchByName(sr.getBranchName()));
		}
		Example<Student> example = Example.of(student);
		List<Student> list = studentRepo.findAll(example);
		studentList = list;
		System.out.println("List: " + list);
		return list;
	}

	@Override
	public boolean exportExcel(HttpServletResponse response) throws Exception {
		File f = new File("Student_Data.xls");
		excelGenerator.generateExcel(response, studentList, f);
		String subject = "Requested Student Excel File";
		String body = "<h1>Below attached is the excel file you requsted</h1>";
		String to = "skaftab984@gmail.com";
		emailUtils.sendEmail(subject, body, to, f);
		f.delete();
		return true;
	}

	@Override
	public boolean exportPdf(HttpServletResponse response) throws Exception {
		File f = new File("Student_Data.pdf");
		System.out.println("Inside Student service class and  export pdf method");
		System.out.println(studentList);
		pdfGenerator.generatePdf(response, studentList, f);
		String subject = "YRequested Student Pdf File";
		String body = "<h1>Below attached is the pdf file you requsted</h1>";
		String to = "skaftab984@gmail.com";
		emailUtils.sendEmail(subject, body, to, f);
		f.delete();
		return true;
	}

	public void clearList() {
		studentList.clear();
	}

}
