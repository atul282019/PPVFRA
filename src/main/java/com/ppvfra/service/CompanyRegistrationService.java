package com.ppvfra.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ppvfra.entity.CompanyRegistration;
import com.ppvfra.entity.Districts;
import com.ppvfra.entity.States;
import com.ppvfra.repository.CompanyRegistrationRepository;

@Service
@Transactional
public class CompanyRegistrationService {

	@Autowired
	CompanyRegistrationRepository companyrep;

	private XSSFWorkbook workbook;
	private XSSFSheet sheet;

	public List<CompanyRegistration> listall() {
		return companyrep.findAll();
	}

	public void save(CompanyRegistration companyregistration) {
		try {
			companyrep.save(companyregistration);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public CompanyRegistration get(Long id) {
		return companyrep.findById(id).get();
	}

	public void delete(Long id) {
		companyrep.deleteById(id);
	}

	public CompanyRegistration saveAndFlush(CompanyRegistration companyregistration) {
		return companyrep.saveAndFlush(companyregistration);
	}

	private void writeHeaderRow() {
		Row row = sheet.createRow(0);

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(12);
		style.setFont(font);

		Cell cell = row.createCell(0);
		cell.setCellValue("Sr.No.");
		cell.setCellStyle(style);

		cell = row.createCell(1);
		cell.setCellValue("Name");
		cell.setCellStyle(style);

		cell = row.createCell(2);
		cell.setCellValue("CIN Number");
		cell.setCellStyle(style);

		cell = row.createCell(3);
		cell.setCellValue("Address");
		cell.setCellStyle(style);

		cell = row.createCell(4);
		cell.setCellValue("State");
		cell.setCellStyle(style);

		cell = row.createCell(5);
		cell.setCellValue("District");
		cell.setCellStyle(style);

		cell = row.createCell(6);
		cell.setCellValue("Name&Designation");
		cell.setCellStyle(style);

		cell = row.createCell(7);
		cell.setCellValue("Mobile");
		cell.setCellStyle(style);

		cell = row.createCell(8);
		cell.setCellValue("Fax");
		cell.setCellStyle(style);

		cell = row.createCell(9);
		cell.setCellValue("Email");
		cell.setCellStyle(style);

		cell = row.createCell(10);
		cell.setCellValue("Status");
		cell.setCellStyle(style);

		cell = row.createCell(11);
		cell.setCellValue("Remarks");
		cell.setCellStyle(style);
		
	}

	private void writeDataRows(List<CompanyRegistration> compreg, List<States> stateselections,
			List<Districts> districtselection) {

		int srno = 0;
		for (int i = 0; i < compreg.size(); i++) {
			srno = i + 1;
			Row row = sheet.createRow(srno);
			String registeredOfficeState = "";
			String registeredOfficeDistrict = "";

			Cell cell = row.createCell(0);
			cell.setCellValue(srno);
			sheet.autoSizeColumn(0);

			cell = row.createCell(1);
			cell.setCellValue(Optional.ofNullable(compreg.get(i).getCompanyname()).orElse(""));
			sheet.autoSizeColumn(1);

			cell = row.createCell(2);
			cell.setCellValue(Optional.ofNullable(compreg.get(i).getCin_number()).orElse(""));
			sheet.autoSizeColumn(2);

			cell = row.createCell(3);
			cell.setCellValue(Optional.ofNullable(compreg.get(i).getAuthor_Address()).orElse(""));
			sheet.autoSizeColumn(3);

			if (compreg.get(i).getRegisteredoffice_state() != null) {
				for (States statedrop : stateselections) {
					if (compreg.get(i).getRegisteredoffice_state() == statedrop.getStatecode()) {
						registeredOfficeState = statedrop.getStatename_inenglish();
					}
				}
			}

			cell = row.createCell(4);
			cell.setCellValue(registeredOfficeState);
			sheet.autoSizeColumn(4);

			if (compreg.get(i).getRegisteredoffice_districts() != null) {
				for (Districts distsel : districtselection) {
					if (compreg.get(i).getRegisteredoffice_districts() == distsel.getDistrictcode()) {
						registeredOfficeDistrict = distsel.getDistrictname_inenglish();
					}
				}
			}

			cell = row.createCell(5);
			cell.setCellValue(registeredOfficeDistrict);
			sheet.autoSizeColumn(5);

			cell = row.createCell(6);
			cell.setCellValue(Optional.ofNullable(compreg.get(i).getAuthor_Name()).orElse("") + " "
					+ Optional.ofNullable(compreg.get(i).getAuthor_Designation()).orElse(""));
			sheet.autoSizeColumn(6);

			cell = row.createCell(7);
			cell.setCellValue(Optional.ofNullable(compreg.get(i).getAuthor_Mobile()).orElse(""));
			sheet.autoSizeColumn(7);

			cell = row.createCell(8);
			cell.setCellValue(Optional.ofNullable(compreg.get(i).getAuthor_Fax()).orElse(""));
			sheet.autoSizeColumn(8);

			cell = row.createCell(9);
			cell.setCellValue(Optional.ofNullable(compreg.get(i).getAuthor_Email()).orElse(""));
			sheet.autoSizeColumn(9);

			cell = row.createCell(10);
			cell.setCellValue(Optional.ofNullable(compreg.get(i).getVerificationstatus()).orElse(""));
			sheet.autoSizeColumn(10);

			cell = row.createCell(11);
			cell.setCellValue(Optional.ofNullable(compreg.get(i).getVerificationremarks()).orElse(""));
			sheet.autoSizeColumn(11);

		}

	}

	public void export(HttpServletResponse response,List<CompanyRegistration> compreg, List<States> stateselections,
			List<Districts> districtselection) throws IOException {
		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet("CompanyList");
		writeHeaderRow();
		writeDataRows(compreg,stateselections,districtselection);
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}
}
