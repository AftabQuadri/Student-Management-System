//package com.aftab.sms.util;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.aftab.sms.entities.Customer;
//import com.aftab.sms.repo.InsuranceRepo;
//import com.lowagie.text.Document;
//import com.lowagie.text.Font;
//import com.lowagie.text.FontFactory;
//import com.lowagie.text.PageSize;
//import com.lowagie.text.Paragraph;
//import com.lowagie.text.pdf.CMYKColor;
//import com.lowagie.text.pdf.PdfPCell;
//import com.lowagie.text.pdf.PdfPTable;
//import com.lowagie.text.pdf.PdfWriter;
//
//import jakarta.servlet.http.HttpServletResponse;
//@Component
//public class PdfGenerator {
//	@Autowired
//	InsuranceRepo repo;
//	public boolean generatePdf(HttpServletResponse response,List<Customer>list,File f) throws Exception {
//		// TODO Auto-generated method stub
//		List<Customer> customerList;
//		Document document=new Document(PageSize.A4);
//		PdfWriter.getInstance(document, response.getOutputStream());
//		PdfWriter.getInstance(document, new FileOutputStream(f));
//
//		document.open();
//		Font fontTitle=FontFactory.getFont(FontFactory.TIMES_ROMAN);
//		fontTitle.setSize(20);
//		Paragraph paragraph=new Paragraph("List of Customers",fontTitle);
//		paragraph.setAlignment(Paragraph.ALIGN_CENTER);
//		paragraph.setSpacingAfter(20);
//		document.add(paragraph);
//		
//		PdfPTable table=new PdfPTable(11);
//		PdfPCell cell = new PdfPCell();
//		cell.setBackgroundColor(CMYKColor.GREEN);
//		Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
//		font.setColor(10, 255, 100);
//		cell.setPadding(5);
//	
//		table.addCell("S.No");
//		table.addCell("Id");
//		table.addCell("Name");
//		table.addCell("Gender");
//		table.addCell("Plan Name");
//		table.addCell("Plan Status");
//		table.addCell("Start Date"+"");
//		table.addCell("End Date"+"");
//		table.addCell("Termination Date"+"");
//		table.addCell("Denial Reason");
//		table.addCell("Termination Reason");
//		
//		if(list==null) {
//			customerList=repo.findAll();
//		}else {
//			customerList=list;
//		}
//		int row=1;
//		for (Customer element : customerList) {
//			table.addCell(row+"");
//			table.addCell(element.getId()+"");
//			table.addCell(element.getCustomerName());
//			table.addCell(element.getGender());
//			table.addCell(element.getPlanName());
//			table.addCell(element.getPlanStatus());
//			table.addCell(element.getStartDate()+"");
//			table.addCell(element.getEndDate()+"");
//			table.addCell(element.getTerminationDate()+"");
//			table.addCell(element.getDenialReason());
//			table.addCell(element.getTerminationReason());
//			row++;
//		}
//		document.add(table);
//		
//		document.close();
//		
//		return false;
//	}
//
//}
