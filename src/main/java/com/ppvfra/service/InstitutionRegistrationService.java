package com.ppvfra.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

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
import com.ppvfra.entity.InstitutionRegistration;
import com.ppvfra.entity.InstitutionTypes;
import com.ppvfra.entity.States;
import com.ppvfra.repository.InstitutionRegistrationRepository;

@Service
@Transactional
public class InstitutionRegistrationService {
	
	@Autowired
	InstitutionRegistrationRepository institutionregrep;
	
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;

	public List<InstitutionRegistration> listall(){
		return institutionregrep.findAll();
	}
	public InstitutionRegistration save (InstitutionRegistration institutionregistration){
		return institutionregrep.save(institutionregistration);
	}
	
	
	public InstitutionRegistration get(Integer id) {
		return institutionregrep.findById(id).get();
	}
	public void delete(Integer id) {
		institutionregrep.deleteById(id);
	}
	
	//export code added
	
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
		cell.setCellValue("Institute Type");
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

	private void writeDataRows(List<InstitutionRegistration> institutereg,List<InstitutionTypes> instype, List<States> stateselections,
			List<Districts> districtselection) {

		int srno = 0;
		for (int i = 0; i < institutereg.size(); i++) {
			srno = i + 1;
			Row row = sheet.createRow(srno);
			String instituteType = "";
			String StateName = "";
			String districtName = "";

			Cell cell = row.createCell(0);
			cell.setCellValue(srno);
			sheet.autoSizeColumn(0);
			
			cell = row.createCell(1);
			cell.setCellValue(institutereg.get(i).getInstituteName());
			sheet.autoSizeColumn(1);

			if (institutereg.get(i).getType() != null) {
				for (InstitutionTypes itype : instype) {
					if (institutereg.get(i).getType() == itype.getId()) {
						instituteType = itype.getTypename();
					}
				}
			}
			
			cell = row.createCell(2);
			cell.setCellValue(instituteType);
			sheet.autoSizeColumn(2);

			cell = row.createCell(3);
			cell.setCellValue(institutereg.get(i).getAuthor_Address());
			sheet.autoSizeColumn(3);

			if (institutereg.get(i).getAuthor_statecode() != null) {
				for (States statedrop : stateselections) {
					if (institutereg.get(i).getAuthor_statecode() == statedrop.getStatecode()) {
						StateName = statedrop.getStatename_inenglish();
					}
				}
			}

			cell = row.createCell(4);
			cell.setCellValue(StateName);
			sheet.autoSizeColumn(4);

			if (institutereg.get(i).getAuthor_districtcode() != null) {
				for (Districts distsel : districtselection) {
					if (institutereg.get(i).getAuthor_districtcode() == distsel.getDistrictcode()) {
						districtName = distsel.getDistrictname_inenglish();
					}
				}
			}

			cell = row.createCell(5);
			cell.setCellValue(districtName);
			sheet.autoSizeColumn(5);

			cell = row.createCell(6);
			cell.setCellValue(Optional.ofNullable(institutereg.get(i).getAuthor_Name()).orElse("") + " "
					+ Optional.ofNullable(institutereg.get(i).getAuthor_Designation()).orElse(""));
			sheet.autoSizeColumn(6);

			cell = row.createCell(7);
			cell.setCellValue(Optional.ofNullable(institutereg.get(i).getAuthor_Mobile()).orElse(""));
			sheet.autoSizeColumn(7);

			cell = row.createCell(8);
			cell.setCellValue(Optional.ofNullable(institutereg.get(i).getAuthor_Fax()).orElse(""));
			sheet.autoSizeColumn(8);

			cell = row.createCell(9);
			cell.setCellValue(Optional.ofNullable(institutereg.get(i).getAuthor_Email()).orElse(""));
			sheet.autoSizeColumn(9);

			cell = row.createCell(10);
			cell.setCellValue(Optional.ofNullable(institutereg.get(i).getVerificationstatus()).orElse(""));
			sheet.autoSizeColumn(10);

			cell = row.createCell(11);
			cell.setCellValue(Optional.ofNullable(institutereg.get(i).getVerificationremarks()).orElse(""));
			sheet.autoSizeColumn(11);

		}

	}

	public void export(HttpServletResponse response,List<InstitutionRegistration> institutereg,List<InstitutionTypes> instype, List<States> stateselections,
			List<Districts> districtselection) throws IOException {
		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet("InstituteList");
		writeHeaderRow();
		writeDataRows(institutereg,instype,stateselections,districtselection);
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}
	
	//export code added
}
