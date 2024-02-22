package com.ppvfra.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;

import com.ppvfra.entity.Districts;
import com.ppvfra.entity.States;

@Service
public class DashboardService {

	private SXSSFWorkbook workbook;
	private SXSSFSheet sheet;
	
	//export code added
	
			private void writeHeaderRow() {
				Row row = sheet.createRow(0);

				CellStyle style = workbook.createCellStyle();
				Font font = workbook.createFont();
				font.setBold(true);
				style.setFont(font);

				Cell cell = row.createCell(0);
				cell.setCellValue("S.No");
				cell.setCellStyle(style);

				cell = row.createCell(1);
				cell.setCellValue("Application ID");
				cell.setCellStyle(style);

				cell = row.createCell(2);
				cell.setCellValue("Application Date");
				cell.setCellStyle(style);

				cell = row.createCell(3);
				cell.setCellValue("FormType");
				cell.setCellStyle(style);

				cell = row.createCell(4);
				cell.setCellValue("Name");
				cell.setCellStyle(style);

				cell = row.createCell(5);
				cell.setCellValue("Crop");
				cell.setCellStyle(style);

				cell = row.createCell(6);
				cell.setCellValue("Crop Group");
				cell.setCellStyle(style);

				cell = row.createCell(7);
				cell.setCellValue("Denomination");
				cell.setCellStyle(style);

				cell = row.createCell(8);
				cell.setCellValue("Application status");
				cell.setCellStyle(style);

			}

			private void writeDataRows(List<Object[]> admin_viewapplication, List<States> stateselections,
					List<Districts> districtselection) {

				int srno = 0;
				for (int i = 0; i < admin_viewapplication.size(); i++) {
					srno = i + 1;
					Row row = sheet.createRow(srno);
					
					Cell cell = row.createCell(0);
					cell.setCellValue(srno);
					
					for(int j=0;j<admin_viewapplication.get(i).length;j++) {
					
					cell = row.createCell(1);
					cell.setCellValue(admin_viewapplication.get(i)[6].toString());
					
					cell = row.createCell(2);
					cell.setCellValue(Optional.ofNullable(admin_viewapplication.get(i)[1].toString()).orElse(""));

					cell = row.createCell(3);
					cell.setCellValue(Optional.ofNullable(admin_viewapplication.get(i)[11].toString()).orElse(""));
					
					cell = row.createCell(4);
					cell.setCellValue(Optional.ofNullable(admin_viewapplication.get(i)[2]).orElse(" ").toString());
					
										
					cell = row.createCell(5);
					cell.setCellValue(Optional.ofNullable(admin_viewapplication.get(i)[4]).orElse("").toString());
					
					cell = row.createCell(6);
					cell.setCellValue(Optional.ofNullable(admin_viewapplication.get(i)[12]).orElse("").toString());

					cell = row.createCell(7);
					cell.setCellValue(Optional.ofNullable(admin_viewapplication.get(i)[3]).orElse("").toString());

					cell = row.createCell(8);
					cell.setCellValue(Optional.ofNullable(admin_viewapplication.get(i)[7]).orElse("").toString());
					

					}
				}

			}
		
		public void export(HttpServletResponse response,List<Object[]> admin_viewapplication, List<States> stateselections,
				List<Districts> districtselection) throws IOException {
			workbook = new SXSSFWorkbook();
			sheet = workbook.createSheet("ApplicationStatusList");
			writeHeaderRow();
			writeDataRows(admin_viewapplication,stateselections,districtselection);
			ServletOutputStream outputStream = response.getOutputStream();
			workbook.write(outputStream);
			workbook.close();
			
			outputStream.close();
		}
		
		//export code added
	
}
