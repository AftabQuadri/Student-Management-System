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

import com.aftab.sms.entities.Student;
import com.aftab.sms.repo.StudentRepo;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ExcelGenerator {
	@Autowired
	StudentRepo repo;

	public void generateExcel(HttpServletResponse response, List<Student> list, File f) throws Exception {
		// TODO Auto-generated method stub
		for (Student student : list) {
			System.out.println(
					"----------------------Student Name---------------------------- " + student.getFirstName());
		}
		if (list.size() > 0) {
			// we can also use xssf workbook for xlsx
			Workbook workBook = new HSSFWorkbook();
			Sheet sheet = workBook.createSheet("Student_Data");
			Row headerRow = sheet.createRow(0);
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
			headerRow.createCell(10).setCellValue("Years");
			headerRow.createCell(11).setCellValue("Total semester");
			headerRow.createCell(12).setCellValue("Credits");
			headerRow.createCell(13).setCellValue("Fees");
			headerRow.createCell(14).setCellValue("Branch Id");
			headerRow.createCell(15).setCellValue("Branch Name");
			headerRow.createCell(16).setCellValue("Branch Description");
			headerRow.createCell(17).setCellValue("City");
			headerRow.createCell(18).setCellValue("Street");
			headerRow.createCell(19).setCellValue("State");
			headerRow.createCell(20).setCellValue("Postal Code");
			headerRow.createCell(21).setCellValue("Country");
			headerRow.createCell(22).setCellValue("Enrollment Date");
			headerRow.createCell(23).setCellValue("Graduation Date");

			int dataRowIndex = 1;
			for (Student student : list) {
				Row row = sheet.createRow(dataRowIndex);
				row.createCell(0).setCellValue(dataRowIndex);
				row.createCell(1).setCellValue(student.getRegistrationNumber() + "");
				row.createCell(2).setCellValue(student.getFirstName());
				row.createCell(3).setCellValue(student.getLastName());
				row.createCell(4).setCellValue(student.getGender());
				row.createCell(5).setCellValue(student.getPhoneNumber());
				row.createCell(6).setCellValue(student.getDateOfBirth() + "");
				if (student.getCourse() != null) {
					row.createCell(7).setCellValue(student.getCourse().getCourseName());
					row.createCell(8).setCellValue(student.getCourse().getCourseId());
					row.createCell(9).setCellValue(student.getCourse().getCourseDescription());
					row.createCell(10).setCellValue(student.getCourse().getSemester());
					row.createCell(11).setCellValue(student.getCourse().getYear());
					row.createCell(12).setCellValue(student.getCourse().getCredits());
					row.createCell(13).setCellValue(student.getCourse().getFees());
				} else {
					row.createCell(7).setCellValue("N/A");
					row.createCell(8).setCellValue("N/A");
					row.createCell(9).setCellValue("N/A");
					row.createCell(10).setCellValue("N/A");
					row.createCell(11).setCellValue("N/A");
					row.createCell(12).setCellValue("N/A");
					row.createCell(13).setCellValue("N/A");

				}
				if (student.getBranch() != null) {

					row.createCell(14).setCellValue(student.getBranch().getBranchId());
					row.createCell(15).setCellValue(student.getBranch().getBranchName());
					row.createCell(16).setCellValue(student.getBranch().getBranchDescription());

				} else {

					row.createCell(14).setCellValue("N/A");
					row.createCell(15).setCellValue("N/A");
					row.createCell(16).setCellValue("N/A");

				}
				row.createCell(17).setCellValue(student.getAddress().getCity());
				row.createCell(18).setCellValue(student.getAddress().getStreet());
				row.createCell(19).setCellValue(student.getAddress().getState());
				row.createCell(20).setCellValue(student.getAddress().getPostalCode());
				row.createCell(21).setCellValue(student.getAddress().getCountry());
				if (student.getEnrollmentDate() != null) {
					row.createCell(22).setCellValue(student.getEnrollmentDate() + "");
				} else {
					row.createCell(22).setCellValue("N/A");

				}
				if (student.getGraduationDate() != null) {
					row.createCell(23).setCellValue(student.getGraduationDate() + "");

				} else {
					row.createCell(23).setCellValue("N/A");

				}
				dataRowIndex++;
			}
			FileOutputStream fos = new FileOutputStream(f);
			workBook.write(fos);
			fos.close();
			ServletOutputStream servletOutputStream = response.getOutputStream();
			workBook.write(servletOutputStream);
			workBook.close();
		}
	}

}
