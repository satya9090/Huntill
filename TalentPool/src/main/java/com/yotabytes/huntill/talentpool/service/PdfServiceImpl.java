package com.yotabytes.huntill.talentpool.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPage;
import com.itextpdf.text.pdf.PdfWriter;
import com.yotabytes.huntill.talentpool.domain.TalentCandidateInformation;
import com.yotabytes.huntill.talentpool.domain.TalentCandidateProgrammerSkills;
import com.yotabytes.huntill.talentpool.domain.TalentCandidateProjectDetails;
@Service
public class PdfServiceImpl  implements PdfService{

	  private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
	            Font.BOLD);
	    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
	            Font.NORMAL, BaseColor.RED);
	    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
	            Font.BOLD);
	    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
	            Font.BOLD);
	@Override
	public void addMetaData(Document document, TalentCandidateInformation talentCandidateInformation) {
		// TODO Auto-generated method stub
		   document.addTitle("My first PDF");
	        document.addSubject("Using iText");
	        document.addKeywords("Java, PDF, iText");
	        document.addAuthor("Lars Vogel");
	        document.addCreator("Lars Vogel");
	}

	@Override
	public void addTitlePage(Document document, TalentCandidateInformation talentCandidateInformation,PdfWriter writer)  throws DocumentException {
		// TODO Auto-generated method stub
		try {
			Font red = new Font(FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.BLUE);
		List<TalentCandidateProgrammerSkills> exe=talentCandidateInformation.getProgrammerSkills();
		System.out.println("DATA:::::"+talentCandidateInformation.getFirstName());
        Paragraph preface = new Paragraph();
        PdfContentByte canvas = writer.getDirectContent();
        Rectangle rect = new Rectangle(30 , 37, 580, 810);
        rect.setBorder(Rectangle.BOX);
        rect.setBorderWidth(2);
        canvas.rectangle(rect);
     
        // We add one empty line
        preface.add(talentCandidateInformation.getFirstName() +talentCandidateInformation.getMiddleName()+talentCandidateInformation.getLastName()+"\n"); 
       //preface.add(talentCandidateInformation.getEmailId()+"\n");
        Chunk redText = new Chunk(talentCandidateInformation.getEmailId()+"\n", red);
        preface.add(redText);
        Long p=talentCandidateInformation.getContactNumber();
        String no=Long.toString(p);
        preface.add(no+"\n"); 
        preface.add("______________________________________________________________________________"+"\n"); 
    
        preface.setAlignment(Element.ALIGN_CENTER);document.add(preface);
      
        PdfPTable table2 = new PdfPTable(1);
        PdfPCell c2 = new PdfPCell(new Paragraph("Professional Summary",catFont));
        c2.setBackgroundColor(BaseColor.GRAY);
        table2.addCell(c2);
        table2.setSpacingBefore(10f);
        table2.setSpacingAfter(10f);
        table2.setWidthPercentage(100);
        Paragraph preface1 = new Paragraph();
        
        preface1.add("Having 5+ years of professional IT experience in designing and developing Web based andEnterprise "
        		+ "applications using JAVA/J2EE technologies in Telecom, Financial, Insurance andDigital domains.");
		
        document.add(table2);
        document.add(preface1);
        
        PdfPTable table1 = new PdfPTable(1);
        
        PdfPCell c1 = new PdfPCell(new Paragraph("Technical Skils",catFont));
        c1.setBackgroundColor(BaseColor.GRAY);
        table1.addCell(c1);
        table1.setSpacingBefore(10f);
        table1.setSpacingAfter(10f);
        table1.setWidthPercentage(100);
       
        PdfPTable table = new PdfPTable(2); // 3 columns.

        PdfPCell cell1 = new PdfPCell(new Paragraph("Languages"));
        PdfPCell cell2=null;
    	for(int i=0; i<exe.size(); i++)  { 
    		TalentCandidateProgrammerSkills skill=	talentCandidateInformation.getProgrammerSkills().get(i);
    		cell2= new PdfPCell(new Paragraph(skill.getProgramingLanguage()+","));
    	}
    	PdfPCell cell4=null;
        PdfPCell cell3 = new PdfPCell(new Paragraph("Web Technologies"));
        for(int i=0; i<exe.size(); i++)  { 
    		TalentCandidateProgrammerSkills skill=	talentCandidateInformation.getProgrammerSkills().get(i);
    		cell4= new PdfPCell(new Paragraph(skill.getFrontEnd()+","));
        }
        PdfPCell cell6=null;
        PdfPCell cell5 = new PdfPCell(new Paragraph("Framework"));
        
        for(int i=0; i<exe.size(); i++)  { 
    		TalentCandidateProgrammerSkills skill=	talentCandidateInformation.getProgrammerSkills().get(i);
    		cell6= new PdfPCell(new Paragraph(skill.getFramework()+","));
        }
        PdfPCell cell12=null;
        PdfPCell cell11 = new PdfPCell(new Paragraph("Tools"));
        
        for(int i=0; i<exe.size(); i++)  { 
    		TalentCandidateProgrammerSkills skill=	talentCandidateInformation.getProgrammerSkills().get(i);
    		cell12= new PdfPCell(new Paragraph(skill.getTools()+","));
        }
        
        PdfPCell cell8=null;
        PdfPCell cell7 = new PdfPCell(new Paragraph("Databases"));
        
        for(int i=0; i<exe.size(); i++)  { 
    		TalentCandidateProgrammerSkills skill=	talentCandidateInformation.getProgrammerSkills().get(i);
    		cell8= new PdfPCell(new Paragraph(skill.getDatabase()+","));
        }
        
        PdfPCell cell9 = new PdfPCell(new Paragraph("OS & Enviroment"));
        PdfPCell cell10 = new PdfPCell(new Paragraph("Windows, UNIX, LINUX, MAC."));

        table.addCell(cell1);
        table.addCell(cell2);
        table.addCell(cell3);
        table.addCell(cell4);
        table.addCell(cell5);
        table.addCell(cell6);
        table.addCell(cell7);
        table.addCell(cell8);
        table.addCell(cell9);
        table.addCell(cell10);
        table.addCell(cell11);
        table.addCell(cell12);
        
        
         document.add(table1);
         document.add(table);
         
         
       PdfPTable table3 = new PdfPTable(1);
        
        PdfPCell c3 = new PdfPCell(new Paragraph("Professional Experience",catFont));
        c3.setBackgroundColor(BaseColor.GRAY);
        table3.addCell(c3);
        table3.setSpacingBefore(10f);
        table3.setSpacingAfter(10f);
        table3.setWidthPercentage(100);
        
        PdfPTable table4 = new PdfPTable(1);
        PdfPCell c4 = new PdfPCell(new Paragraph("Client:Test Project"+"\n"+"Jan 2019 – Till Date"+"\n"+"Location: Albany, NY"+"\n"+"Role: Full Stack Java Developer"));
        table4.addCell(c4);
        table4.setSpacingBefore(10f);
        table4.setSpacingAfter(10f);
        table4.setWidthPercentage(100);
        
        
        Paragraph preface2 = new Paragraph();
        Chunk chunk1 = new Chunk("Environment:"+"\n");
        chunk1.setUnderline(1.5f, -1);
        preface2.add(chunk1);
        preface2.add(" Agile(SCRUM), HTML5, CSS3, JavaScript, jQuery, Ajax, Bootstrap, Angular ,AWS, Micro-Services, Spring, DynamoDB, Spring Batch, Spring Boot, Spring Security, Servlet,\r\n" + 
        		"XML, GIT, JSP, Spring MVC, JDBC, Mockito, JUnit, Maven, IBM WebSphere, Restful, Log4J, JavaServer Pages,  JIRA, Spring Security, Jenkins, Marathon, Kubernetes, Grafana and STS."+"\n");
        Chunk chunk2 = new Chunk("Description:"+"\n");
        chunk2.setUnderline(1.5f, -1);
        preface2.add(chunk2);
        preface2.add(" system that serves more than 700,000 individuals each year. The Office ofMental Health (OMH) operates psychiatric centres across the State, and regulates, certifiesand oversees more than 4,500 programs, which are operated by local governments and non-profit   agencies.   These   programs   include   various   inpatient   and   outpatient   programs,emergency,"
        		+ " community support, residential and family care programs."+"\n");
        
        
        Chunk chunk3= new Chunk(" Responsibilities:"+"\n");
        chunk3.setUnderline(1.5f, -1);
        preface2.add(chunk3);
        preface2.add("Assist in the  Software   Development   Life   Cycle  (SDLC) which includes development,design, analysis, testing and Integration of various web based and client/server SMPD(Social and Messaging Product Development) applications in multi-platform environmentswith JAVA/J2EE technologies.Used   core   Java   concepts   like  Collections,   Multi-Threading,   Generics,   ExceptionHandling, Java Reflection and "
        		+ "Serialization.Used Java 1.8 features like stream and Lambda expressions.");
           
      
       
        
        
		
       
       // addEmptyLine(preface, 2);
     //   document.add(table2);
      //  document.add(preface1);
       // document.add(table1);
       // document.add(table);
        document.add(table3);
        document.add(table4);
        document.add(preface2);
        // Start a new page
        document.newPage();
        }
        catch (NullPointerException ne) {
			System.out.println(ne.getMessage());
		}
	}

	

	/*
	 * private static void addEmptyLine(Paragraph paragraph, int number) { for (int
	 * i = 0; i < number; i++) { paragraph.add(new Paragraph(" ")); } }
	 */
	/*
	 * @Override public void addContent(Document document) throws DocumentException
	 * { // TODO Auto-generated method stub Anchor anchor = new
	 * Anchor("First Chapter", catFont); anchor.setName("First Chapter");
	 * 
	 * // Second parameter is the number of the chapter Chapter catPart = new
	 * Chapter(new Paragraph(anchor), 1);
	 * 
	 * Paragraph subPara = new Paragraph("Subcategory 1", subFont); Section
	 * subCatPart = catPart.addSection(subPara); subCatPart.add(new
	 * Paragraph("Hello"));
	 * 
	 * subPara = new Paragraph("Subcategory 2", subFont); subCatPart =
	 * catPart.addSection(subPara); subCatPart.add(new Paragraph("Paragraph 1"));
	 * subCatPart.add(new Paragraph("Paragraph 2")); subCatPart.add(new
	 * Paragraph("Paragraph 3"));
	 * 
	 * // add a list createList(subCatPart); Paragraph paragraph = new Paragraph();
	 * addEmptyLine(paragraph, 5); subCatPart.add(paragraph);
	 * 
	 * // add a table createTable(subCatPart);
	 * 
	 * // now add all this to the document document.add(catPart);
	 * 
	 * // Next section anchor = new Anchor("Second Chapter", catFont);
	 * anchor.setName("Second Chapter");
	 * 
	 * // Second parameter is the number of the chapter catPart = new Chapter(new
	 * Paragraph(anchor), 1);
	 * 
	 * subPara = new Paragraph("Subcategory", subFont); subCatPart =
	 * catPart.addSection(subPara); subCatPart.add(new
	 * Paragraph("This is a very important message"));
	 * 
	 * // now add all this to the document document.add(catPart); }
	 * 
	 * private static void createTable(Section subCatPart) throws
	 * BadElementException { PdfPTable table = new PdfPTable(3);
	 * 
	 * // t.setBorderColor(BaseColor.GRAY); // t.setPadding(4); // t.setSpacing(4);
	 * // t.setBorderWidth(1);
	 * 
	 * PdfPCell c1 = new PdfPCell(new Phrase("Table Header 1"));
	 * c1.setHorizontalAlignment(Element.ALIGN_CENTER); table.addCell(c1);
	 * 
	 * c1 = new PdfPCell(new Phrase("Table Header 2"));
	 * c1.setHorizontalAlignment(Element.ALIGN_CENTER); table.addCell(c1);
	 * 
	 * c1 = new PdfPCell(new Phrase("Table Header 3"));
	 * c1.setHorizontalAlignment(Element.ALIGN_CENTER); table.addCell(c1);
	 * table.setHeaderRows(1);
	 * 
	 * table.addCell("1.0"); table.addCell("1.1"); table.addCell("1.2");
	 * table.addCell("2.1"); table.addCell("2.2"); table.addCell("2.3");
	 * 
	 * subCatPart.add(table);
	 * 
	 * } private static void createList(Section subCatPart) {
	 * 
	 * }
	 */
}
