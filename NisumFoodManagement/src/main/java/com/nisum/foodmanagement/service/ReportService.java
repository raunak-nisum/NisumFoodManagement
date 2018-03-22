/**
 * 
 */
package com.nisum.foodmanagement.service;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.nisum.foodmanagement.repository.FoodDetailsRepository;
import com.nisum.foodmanagement.repository.SuggestionRepository;
import com.nisum.foodmanagement.util.DateUtil;
import com.nisum.foodmanagement.repository.ReportRepository;
import com.nisum.foodmanagement.util.UtilityClass;

/**
 * @author Raunak
 *
 */
@Service
public class ReportService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ReportRepository reportRepository;
	
	@Autowired
	FoodDetailsRepository foodDetailsReporitory;
	
	@Autowired
	SuggestionRepository suggestionRepository;

/*	public List<Object[]> getMonthlyReport(String month, String year){
		logger.info("In service layer....getMonthlyReport()....");
		List<Object[]> list = reportRepository.getMonthlyReport(month, year);
		return list;
	}
	
	public List<Object[]> getMonthlyReportWithTotalAmount(String month, String year){
		logger.info("In service layer....getMonthlyReportWithTotalAmount()....");
		List<Object[]> list = reportRepository.getMonthlyReportWithTotalAmount(month, year);
		return list;
	}
	
	public String exportReport(String excelData){
		logger.info("In service layer....exportReport()....");
		String status = "SUCCESS";
		System.out.println(excelData);
		String [] strArray = excelData.split("&");
		excelData = strArray[0];
		System.out.println(strArray[1]);
		String fileName = strArray[1].substring(0, strArray[1].length()-1).replaceAll("\\%2C", "_");
		fileName = fileName + ".xls";
		System.out.println(fileName);
		excelData = excelData.replaceAll("\\+", " ");
		excelData = excelData.substring(0, excelData.length()-1);
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
		HSSFSheet sheet = hssfWorkbook.createSheet("Monthly Food Report");
		
		HSSFCellStyle style= UtilityClass.getStyle(hssfWorkbook);
		
		sheet.createFreezePane(0, 1);
		
		try{
			createHeader(hssfWorkbook, sheet);
			
			String[] rowData = excelData.split("%7C");//Split by "|"
			for (int rowCount = 1,rowIndex=0; rowIndex < rowData.length; rowCount++,rowIndex++) {
				//System.out.println(rowData[rowIndex]);
				HSSFRow row = sheet.createRow(rowCount);
				String[] columnData = rowData[rowIndex].split("%2C");//Split by ","
					for (int columnCount = 1,index = 0; index < columnData.length; columnCount++,index++) {
						HSSFCell cell = row.createCell(columnCount)	;
						if(index == 5)
							cell.setCellValue("Rs. "+columnData[index]);
						else
							cell.setCellValue(columnData[index]);
						cell.setCellStyle(style);
						sheet.autoSizeColumn(columnCount);
					}
			}
			FileOutputStream out = 
					new FileOutputStream(new File(filePath+fileName));
			hssfWorkbook.write(out);
			out.close();
			status = fileName;
			System.out.println("Excel written successfully..");
		}catch(Exception e){
			status = "FAILED";
			e.printStackTrace();
		}
		return status;
	}*/
	
	public void createHeader(HSSFWorkbook hssfWorkbook, HSSFSheet sheet){
		HSSFCellStyle headerStyle= UtilityClass.getHeaderStyle(hssfWorkbook);
		HSSFRow headerRow = sheet.createRow(0);
		headerRow.setHeight((short)800);
		headerRow.createCell(1).setCellValue("Employee Id");
		headerRow.createCell(2).setCellValue("Name");
		headerRow.createCell(3).setCellValue("Breakfast Count");
		headerRow.createCell(4).setCellValue("Lunch Count");
		headerRow.createCell(5).setCellValue("Dinner Count");
		headerRow.createCell(6).setCellValue("Total Amount");
		for (int i = 1; i <= 6; i++) {
			headerRow.getCell(i).setCellStyle(headerStyle);
			sheet.autoSizeColumn(i);
			
		}
	}
	
	public HSSFWorkbook exportSelectedDateReport(String food, String date){
		logger.info("In service layer....exportSelectedDateReport()....");
		
		Date sqlDate = DateUtil.getSQLDate(date);    
    	System.out.println("SQL date...."+sqlDate);
    	List<Object[]> foodDetailList = null;
    	if("breakFast".equalsIgnoreCase(food))
    		foodDetailList = foodDetailsReporitory.findAllByCreatedDateAndBreakFast(sqlDate, "Y");
    	else if("lunch".equalsIgnoreCase(food))
    		foodDetailList = foodDetailsReporitory.findAllByCreatedDateAndLunch(sqlDate, "Y");
        else if("dinner".equalsIgnoreCase(food))
        	foodDetailList = foodDetailsReporitory.findAllByCreatedDateAndDinner(sqlDate, "Y");
    	
    	
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
		HSSFSheet sheet = hssfWorkbook.createSheet(food+" Details");
		
		HSSFCellStyle style= UtilityClass.getStyle(hssfWorkbook);
		
		sheet.createFreezePane(0, 1);
		try{
			HSSFCellStyle headerStyle= UtilityClass.getHeaderStyle(hssfWorkbook);
			HSSFRow headerRow = sheet.createRow(0);
			headerRow.setHeight((short)800);
			headerRow.createCell(1).setCellValue("Employee Id");
			headerRow.createCell(2).setCellValue("Name");
			headerRow.createCell(3).setCellValue("Total");
			for (int i = 1; i <= 3; i++) {
				headerRow.getCell(i).setCellStyle(headerStyle);
				sheet.autoSizeColumn(i);
				
			}
			
			for (int rowCount = 1,i = 0; i < foodDetailList.size(); i++,rowCount++) {
				Object[] object = foodDetailList.get(i);
				HSSFRow row = sheet.createRow(rowCount);
				for (int columnCount = 1,index = 0; index < object.length; columnCount++,index++) {
					HSSFCell cell = row.createCell(columnCount)	;
					cell.setCellValue(String.valueOf(object[index]));
					cell.setCellStyle(style);
					sheet.autoSizeColumn(columnCount);
				}
				if(i == 0){
					HSSFCell cell = row.createCell(3)	;
					cell.setCellValue(foodDetailList.size());
					cell.setCellStyle(style);
					sheet.autoSizeColumn(3);
				}
			}
			System.out.println("Excel written successfully..");
		}catch(Exception e){
			e.printStackTrace();
		}
		return hssfWorkbook;
	}
	
	public HSSFWorkbook exportMonthlyReport(String month, String year){
		logger.info("In service layer....exportMonthlyReport()....");
		
		List<Object[]> monthlyList = reportRepository.getMonthlyReportWithTotalAmount(month, year);
		
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
		HSSFSheet sheet = hssfWorkbook.createSheet("Monthly Report");
		
		HSSFCellStyle style= UtilityClass.getStyle(hssfWorkbook);
		
		sheet.createFreezePane(0, 1);
		
		try{
			createHeader(hssfWorkbook, sheet);
			
			//String[] rowData = excelData.split("%7C");//Split by "|"
			for (int rowCount = 1,rowIndex=0; rowIndex < monthlyList.size(); rowCount++,rowIndex++) {
				//System.out.println(rowData[rowIndex]);
				HSSFRow row = sheet.createRow(rowCount);
				//String[] columnData = rowData[rowIndex].split("%2C");//Split by ","
				Object[] rowData = monthlyList.get(rowIndex);
					for (int columnCount = 1,index = 0; index < rowData.length; columnCount++,index++) {
						HSSFCell cell = row.createCell(columnCount)	;
						if(index == 5)
							cell.setCellValue("Rs. "+rowData[index]);
						else
							cell.setCellValue(String.valueOf(rowData[index]));
						cell.setCellStyle(style);
						sheet.autoSizeColumn(columnCount);
					}
			}
			System.out.println("Excel Created Successfully");
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return hssfWorkbook;
	}
	
	public HSSFWorkbook exportReportBetweenTwoDate(String fromDate, String toDate){
		logger.info("In service layer....exportReportBetweenTwoDate()....");
		
		List<Object[]> monthlyList = reportRepository.getMonthlyReportBetweenTwoDate(fromDate, toDate);
		
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
		HSSFSheet sheet = hssfWorkbook.createSheet("Monthly Report");
		
		HSSFCellStyle style= UtilityClass.getStyle(hssfWorkbook);
		
		sheet.createFreezePane(0, 1);
		
		try{
			createHeader(hssfWorkbook, sheet);
			
			for (int rowCount = 1,rowIndex=0; rowIndex < monthlyList.size(); rowCount++,rowIndex++) {
				HSSFRow row = sheet.createRow(rowCount);
				Object[] rowData = monthlyList.get(rowIndex);
					for (int columnCount = 1,index = 0; index < rowData.length; columnCount++,index++) {
						HSSFCell cell = row.createCell(columnCount)	;
						if(index == 5)
							cell.setCellValue("Rs. "+rowData[index]);
						else
							cell.setCellValue(String.valueOf(rowData[index]));
						cell.setCellStyle(style);
						sheet.autoSizeColumn(columnCount);
					}
			}
			System.out.println("Excel Created Successfully");
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return hssfWorkbook;
	}
	
	public HSSFWorkbook exportReportBetweenTwoDateForSuggestion(String fromDate, String toDate){
		logger.info("In service layer....exportReportBetweenTwoDateForSuggestion()....");
		
		List<Object[]> suggestionList = suggestionRepository.findAllSuggestionBetweenTwoDate(fromDate, toDate);
		
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
		HSSFSheet sheet = hssfWorkbook.createSheet("Suggestion Report");
		
		HSSFCellStyle style= UtilityClass.getStyle(hssfWorkbook);
		
		sheet.createFreezePane(0, 1);
		
		try{
			HSSFCellStyle headerStyle= UtilityClass.getHeaderStyle(hssfWorkbook);
			HSSFRow headerRow = sheet.createRow(0);
			headerRow.setHeight((short)800);
			headerRow.createCell(1).setCellValue("Employee Id");
			headerRow.createCell(2).setCellValue("Name");
			headerRow.createCell(3).setCellValue("Title");
			headerRow.createCell(4).setCellValue("Comment");
			for (int i = 1; i <= 4; i++) {
				headerRow.getCell(i).setCellStyle(headerStyle);
				sheet.autoSizeColumn(i);
				
			}
			
			for (int rowCount = 1,i = 0; i < suggestionList.size(); i++,rowCount++) {
				Object[] object = suggestionList.get(i);
				HSSFRow row = sheet.createRow(rowCount);
				for (int columnCount = 1,index = 0; index < object.length; columnCount++,index++) {
					HSSFCell cell = row.createCell(columnCount)	;
					cell.setCellValue(String.valueOf(object[index]));
					cell.setCellStyle(style);
					sheet.autoSizeColumn(columnCount);
				}
			}
			System.out.println("Excel Created Successfully");
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return hssfWorkbook;
	}

}
