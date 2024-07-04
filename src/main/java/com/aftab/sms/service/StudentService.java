package com.aftab.sms.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aftab.sms.binding.LoginRequesst;
import com.aftab.sms.binding.SearchRequest;
import com.aftab.sms.entities.Branch;
import com.aftab.sms.entities.Course;
import com.aftab.sms.entities.Student;
import com.aftab.sms.entities.UserAccount;
@Service
public interface StudentService {
    Student getStudentByRegistrationNumber(Long id);

    List<Student> getAllStudents();
    @Transactional
    Student saveStudent(Student student);
    
    Student getStudentByFirstName(String name);

    Student getStudentByLastName(String name);
    @Transactional
    void deleteStudent(Student student);
    
    List<String> getUserAccountType();
    public void saveBranches(Set<Branch> branches);
	public void saveCourses(Course courses);
	public void saveUserAccount(UserAccount account);

	public void saveBranch(Branch b);
	public List<Course> getAllCourses();
	public List<Branch> getAllBranches();
	
	public boolean logInAttempt(LoginRequesst login);
	public List<String> getAllCourseNames();
	public List<String> getAllBranchNames();
	
	public List<Branch> getBranchesByCourseName(String courseName);
	public Map<String, List<Branch> > getBranchesAndCourseTogether();
	public Branch getBranchByName(String name);

	public Course getCourseByName(String course);
	
	public void deleteCoursesFromDB();
	public void deleteBrachesFromDB();
	public void deleteUsersFromDB();

	public Optional<Branch> getBranchById(long id);
	public Optional<Course> getCourseById(long id);
	public void deleteAllStudents();
	public void deleteAllRecords();

	List<Student> search(SearchRequest sr);
	public void clearList();

}
