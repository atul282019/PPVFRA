package com.ppvfra.service;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ppvfra.entity.ApplicantRegistration;
import com.ppvfra.entity.Applicantdata;
import com.ppvfra.entity.Districts;
import com.ppvfra.entity.States;
import com.ppvfra.repository.ApplicantRegistrationRepository;

@Service
@Transactional
public class ApplicantRegistrationService {

	
	@Autowired
    ApplicantRegistrationRepository ApplicantRegistrationRepository;

	private SXSSFWorkbook workbook;
	private SXSSFSheet sheet;
	
	  public List<ApplicantRegistration> listAll(){ return
			  ApplicantRegistrationRepository.findAll(); }
	
	
	public @Valid ApplicantRegistration save(ApplicantRegistration ApplicantRegistration) { 
		return ApplicantRegistrationRepository.save(ApplicantRegistration);
		}
	
	public ApplicantRegistration get(Integer id) {
		return ApplicantRegistrationRepository.findById(id).get();
	}
	public void delete(Integer id) {
		ApplicantRegistrationRepository.deleteById(id);
	}
	
	//export code added
	
		private void writeHeaderRow() {
			Row row = sheet.createRow(0);

			CellStyle style = workbook.createCellStyle();
			Font font = workbook.createFont();
			font.setBold(true);
			style.setFont(font);

			Cell cell = row.createCell(0);
			cell.setCellValue("Sr.No.");
			cell.setCellStyle(style);

			cell = row.createCell(1);
			cell.setCellValue("Applicant Type");
			cell.setCellStyle(style);

			cell = row.createCell(2);
			cell.setCellValue("Name");
			cell.setCellStyle(style);

			cell = row.createCell(3);
			cell.setCellValue("Company/Institution Name");
			cell.setCellStyle(style);

			cell = row.createCell(4);
			cell.setCellValue("Address");
			cell.setCellStyle(style);

			cell = row.createCell(5);
			cell.setCellValue("State");
			cell.setCellStyle(style);

			cell = row.createCell(6);
			cell.setCellValue("District");
			cell.setCellStyle(style);

			cell = row.createCell(7);
			cell.setCellValue("Mobile");
			cell.setCellStyle(style);

			cell = row.createCell(8);
			cell.setCellValue("Email");
			cell.setCellStyle(style);

			cell = row.createCell(9);
			cell.setCellValue("Status");
			cell.setCellStyle(style);

			cell = row.createCell(10);
			cell.setCellValue("Remarks");
			cell.setCellStyle(style);


		}

		private void writeDataRows(List<Applicantdata> appregistration) {

			int srno = 0;
			for (int i = 0; i < appregistration.size(); i++) {
				srno = i + 1;
				Row row = sheet.createRow(srno);
				
				Cell cell = row.createCell(0);
				cell.setCellValue(srno);
				
				cell = row.createCell(1);
				cell.setCellValue(appregistration.get(i).getApplicantrole());
				
				cell = row.createCell(2);
				cell.setCellValue(appregistration.get(i).getUsername());

				cell = row.createCell(3);
				cell.setCellValue(appregistration.get(i).getName());
				
				cell = row.createCell(4);
				cell.setCellValue(appregistration.get(i).getAddress());
				
				cell = row.createCell(5);
				cell.setCellValue(appregistration.get(i).getStatename());

				cell = row.createCell(6);
				cell.setCellValue(appregistration.get(i).getDistrictname());

				cell = row.createCell(7);
				cell.setCellValue(appregistration.get(i).getMobile_number());

				cell = row.createCell(8);
				cell.setCellValue(appregistration.get(i).getEmail());

				cell = row.createCell(9);
				cell.setCellValue(appregistration.get(i).getVerificationstatus());

				cell = row.createCell(10);
				cell.setCellValue(appregistration.get(i).getVerifierremarks());

			}

		}
	
	public void export(HttpServletResponse response,List<Applicantdata> appregistration) throws IOException {
		workbook = new SXSSFWorkbook();
		sheet = workbook.createSheet("ApplicantList");
		writeHeaderRow();
		writeDataRows(appregistration);
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		
		outputStream.close();
	}
	
	//export code added
}
