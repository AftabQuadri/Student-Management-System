package com.aftab.sms.runner;

import java.util.Arrays;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.aftab.sms.entities.Branch;
import com.aftab.sms.entities.Course;
import com.aftab.sms.entities.UserAccount;
import com.aftab.sms.service.StudentService;

@Component
@Transactional
public class DataLoader implements ApplicationRunner {
	@Autowired
	StudentService service;

	@Override
	@Transactional
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("------------------------------");
		service.deleteAllRecords();
		Course bTech = createBTechCourse();
		Course mTech = createMTechCourse();
		Course mba = createMBACourse();

		System.out.println("B.Tech Course Name: " + bTech.getCourseName());

		Set<Branch> bTechBranches = initializeBtechBranches(bTech);
		Set<Branch> mTechBranches = initializeMtechBranches(mTech);
		Set<Branch> mbaBranches = initializeMBABranches(mba);

		service.saveBranches(mbaBranches);
		service.saveBranches(bTechBranches);
		service.saveBranches(mTechBranches);

		mbaBranches.clear();
		bTechBranches.clear();
		mTechBranches.clear();

		List<Branch> allBranches = service.getAllBranches();
		System.out.println("All branches: " + allBranches);

		for (Branch branch : allBranches) {
			if (branch.getCourseName().equals("B.Tech")) {
				bTechBranches.add(branch);
			} else if (branch.getCourseName().equals("M.Tech")) {
				mTechBranches.add(branch);
			} else if (branch.getCourseName().equals("MBA")) {
				mbaBranches.add(branch);
			}
		}

		bTech.setBranches(bTechBranches);
		mTech.setBranches(mTechBranches);
		mba.setBranches(mbaBranches);

		service.saveCourses(bTech);
		service.saveCourses(mTech);
		service.saveCourses(mba);

		System.out.println(bTechBranches);

		createUser();
	}

	private Course createBTechCourse() {
		Course bTech = new Course();
		bTech.setCourseName("B.Tech");
		bTech.setCourseDescription("Bachelor of Technology");
		bTech.setYear(4);
		bTech.setCredits(100);
		bTech.setSemester("8");
		bTech.setFees("8,00,000");
		return bTech;
	}

	private Course createMTechCourse() {
		Course mTech = new Course();
		mTech.setCourseName("M.Tech");
		mTech.setCourseDescription("Master of Technology");
		mTech.setYear(2);
		mTech.setCredits(60);
		mTech.setSemester("4");
		mTech.setFees("3,00,000");
		return mTech;
	}

	private Course createMBACourse() {
		System.out.println("Inside MBA course...");

		Course mba = new Course();
		mba.setCourseName("MBA");
		mba.setCourseDescription("Master of Business Administration");
		mba.setYear(2);
		mba.setCredits(80);
		mba.setSemester("4");
		mba.setFees("4,00,000");
		System.out.println(mba);
		return mba;
	}

	private Set<Branch> initializeBtechBranches(Course c) {
		List<Branch> branches = Arrays.asList(createBranch("CSE", "Computer Science and Engineering", c),
				createBranch("ECE", "Electronics and Communication Engineering", c),
				createBranch("ME", "Mechanical Engineering", c), createBranch("CE", "Civil Engineering", c),
				createBranch("EE", "Electrical Engineering", c));

		return new HashSet<>(branches);
	}

	private Set<Branch> initializeMtechBranches(Course c) {
		List<Branch> branches = Arrays.asList(createBranch("CSE", "Computer Science and Engineering", c),
				createBranch("ECE", "Electronics and Communication Engineering", c),
				createBranch("ME", "Mechanical Engineering", c), createBranch("CE", "Civil Engineering", c),
				createBranch("EE", "Electrical Engineering", c));

		return new HashSet<>(branches);
	}

	private Set<Branch> initializeMBABranches(Course course) {
		List<Branch> branches = Arrays.asList(createBranch("Marketing", "Specialization in Marketing", course),
				createBranch("Finance", "Specialization in Finance", course),
				createBranch("Human Resources", "Specialization in Human Resources", course),
				createBranch("Operations", "Specialization in Operations Management", course),
				createBranch("International Business", "Specialization in International Business", course));

		return new HashSet<>(branches);
	}

	private Branch createBranch(String name, String description, Course course) {
		Branch branch = new Branch();
		branch.setBranchName(name);
		branch.setBranchDescription(description);
		branch.setCourseName(course.getCourseName());
		return branch;
	}

	@Transactional
	private void createUser() {
		UserAccount adminAccount = new UserAccount();
		adminAccount.setUsername("Aftab");
		adminAccount.setPassword("admin");
		adminAccount.setRole("Admin");
		service.saveUserAccount(adminAccount);

		UserAccount studentAccount = new UserAccount();
		studentAccount.setUsername("Aftab");
		studentAccount.setPassword("student");
		studentAccount.setRole("Student");
		service.saveUserAccount(studentAccount);

		UserAccount facultyAccount = new UserAccount();
		facultyAccount.setUsername("Aftab");
		facultyAccount.setPassword("faculty");
		facultyAccount.setRole("Faculty");
		service.saveUserAccount(facultyAccount);
	}

	public void saveBranches(List<Set<Branch>> br) {
		for (Set<Branch> branch : br) {
			service.saveBranches(branch);
		}
	}

}
