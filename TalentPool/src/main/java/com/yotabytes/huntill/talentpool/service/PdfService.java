package com.yotabytes.huntill.talentpool.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.yotabytes.huntill.talentpool.domain.TalentCandidateInformation;

public interface PdfService {

	void addMetaData(Document document, TalentCandidateInformation talentCandidateInformation);

	void addTitlePage(Document document, TalentCandidateInformation talentCandidateInformation, PdfWriter writer) throws DocumentException;

	

}
