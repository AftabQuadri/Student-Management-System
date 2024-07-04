package com.aftab.sms.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aftab.sms.entities.Enrollment;
import com.aftab.sms.entities.Student;
import com.aftab.sms.repo.StudentRepo;
import com.lowagie.text.Cell;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class ExcelGenerator {
	@Autowired
	StudentRepo repo;
	public boolean generateExcel(HttpServletResponse response,List<Student> list,File f) throws Exception {
		// TODO Auto-generated method stub
		List<Student>studentList;
		//we can also use xssf workbook for xlsx
		Workbook workBook=new HSSFWorkbook();
		Sheet sheet = workBook.createSheet("Customer_data");
		Row headerRow=sheet.createRow(0);
		headerRow.createCell(0).setCellValue("S.No");
		headerRow.createCell(1).setCellValue("Registration Number");
		headerRow.createCell(2).setCellValue("First Name");
		headerRow.createCell(3).setCellValue("Last Name");
		headerRow.createCell(4).setCellValue("Gender");
		headerRow.createCell(5).setCellValue("Phone");
		headerRow.createCell(6).setCellValue("DOB");
		headerRow.createCell(7).setCellValue("Course Name");
		headerRow.createCell(8).setCellValue("Course Id");
		headerRow.createCell(9).setCellValue("Course Description");
		headerRow.createCell(10).setCellValue("Total semester");
		headerRow.createCell(10).setCellValue("Years");
		headerRow.createCell(10).setCellValue("Credits");
		headerRow.createCell(10).setCellValue("Fees");
		headerRow.createCell(10).setCellValue("Branch Id");
		headerRow.createCell(10).setCellValue("Branch Name");
		headerRow.createCell(10).setCellValue("Branch Description");
		headerRow.createCell(10).setCellValue("City");
		headerRow.createCell(10).setCellValue("Street");
		headerRow.createCell(10).setCellValue("State");
		headerRow.createCell(10).setCellValue("Postal Code");
		headerRow.createCell(10).setCellValue("Country");
		headerRow.createCell(10).setCellValue("Enrollment Date");
		headerRow.createCell(10).setCellValue("Graduation Date");
		headerRow.createCell(10).setCellValue("Enrollments");
		headerRow.createCell(10).setCellValue("Grades");
		headerRow.createCell(10).setCellValue("Library");
		
		
		
		if(list!=null) {
			studentList=list;
		}
		else {
			studentList=repo.findAll();
		}
			
		int dataRowIndex=1;
		for (Student student : studentList) {
			Row row=sheet.createRow(dataRowIndex);
			row.createCell(0).setCellValue(dataRowIndex);
			row.createCell(1).setCellValue(student.getRegistrationNumber());
			row.createCell(2).setCellValue(student.getGender());
			row.createCell(3).setCellValue(student.getPhoneNumber());
			row.createCell(4).setCellValue(student.getDateOfBirth()+"");
			row.createCell(5).setCellValue(student.getCourse().getCourseName());
			row.createCell(6).setCellValue(student.getCourse().getCourseId());
			row.createCell(7).setCellValue(student.getCourse().getCourseDescription());
			row.createCell(8).setCellValue(student.getCourse().getSemester());
			row.createCell(9).setCellValue(student.getCourse().getYear());
			row.createCell(10).setCellValue(student.getCourse().getCredits());
			row.createCell(10).setCellValue(student.getCourse().getFees());
			row.createCell(10).setCellValue(student.getBranch().getBranchId());
			row.createCell(10).setCellValue(student.getBranch().getBranchName());
			row.createCell(10).setCellValue(student.getBranch().getBranchDescription());
			row.createCell(10).setCellValue(student.getAddress().getCity());
			row.createCell(10).setCellValue(student.getAddress().getStreet());
			row.createCell(10).setCellValue(student.getAddress().getState());
			row.createCell(10).setCellValue(student.getAddress().getState());
			row.createCell(10).setCellValue(student.getAddress().getPostalCode());
			row.createCell(10).setCellValue(student.getAddress().getPostalCode());
			row.createCell(10).setCellValue(student.getAddress().getCountry());
			if(student.getEnrollmentDate()!=null) {
				row.createCell(10).setCellValue(student.getEnrollmentDate()+"");
			}
			else {
				row.createCell(10).setCellValue("N/A");

			}
			if(student.getGraduationDate()!=null) {
				row.createCell(10).setCellValue(student.getGraduationDate()+"");

			}
			else {
				row.createCell(10).setCellValue("N/A");

			}
					if() {
				row.createCell(10).setCellValue();
					
			}
			else {
				row.createCell(10).setCellValue();
				
			}
			if() {
				row.createCell(10).setCellValue();
					
			}
			else {
				row.createCell(10).setCellValue();
				
			}
			dataRowIndex++;
		}
		FileOutputStream fos=new FileOutputStream(f);
		workBook.write(fos);
		fos.close();
		ServletOutputStream servletOutputStream=response.getOutputStream();
		workBook.write(servletOutputStream);
		workBook.close();
		return false;
	}

}
