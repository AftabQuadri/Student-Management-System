package com.aftab.sms.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.stereotype.Component;

import com.aftab.sms.entities.Student;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;

@Component
public class PdfGenerator {
	public void generatePdf(HttpServletResponse response, List<Student> list, File f) throws Exception {
		// TODO Auto-generated method stub

		List<Student> studentList = list;
		if (studentList == null) {
			return;
		}
		if (studentList.size() > 0) {

			Document document = new Document(PageSize.A4);
			PdfWriter.getInstance(document, response.getOutputStream());
			PdfWriter.getInstance(document, new FileOutputStream(f));

			document.open();
			Font fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
			fontTitle.setSize(20);
			Paragraph paragraph = new Paragraph("List of Students", fontTitle);
			paragraph.setAlignment(Paragraph.ALIGN_CENTER);
			paragraph.setSpacingAfter(20);
			document.add(paragraph);

			PdfPTable table = new PdfPTable(11);
			PdfPCell cell = new PdfPCell();
			cell.setBackgroundColor(CMYKColor.GREEN);
			Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
			font.setColor(10, 255, 100);
			cell.setPadding(5);

			table.addCell("S.No");
			table.addCell("Registration No.");
			table.addCell("Full Name");
			table.addCell("Gender");
			table.addCell("DOB");
			table.addCell("Course");
			table.addCell("Branch");
			table.addCell("Fees");
			table.addCell("Years");
			table.addCell("Semester");

			table.addCell("Enrollment Date");

			int row = 1;
			for (Student student : studentList) {
				table.addCell(row + "");
				table.addCell(student.getRegistrationNumber() + "");
				table.addCell(student.getFirstName() + " " + student.getMiddleName() + " " + student.getLastName());
				table.addCell(student.getGender());
				table.addCell(student.getDateOfBirth() + "");
				table.addCell(student.getCourse().getCourseName());
				table.addCell(student.getCourse().getFees());
				table.addCell(student.getCourse().getYear() + "");
				table.addCell(student.getCourse().getSemester() + "");

				row++;
			}
			document.add(table);

			document.close();

		}

	}
}