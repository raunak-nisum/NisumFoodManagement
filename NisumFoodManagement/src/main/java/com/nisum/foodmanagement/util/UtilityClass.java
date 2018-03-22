/**
 * 
 */
package com.nisum.foodmanagement.util;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

/**
 * @author Raunak
 *
 */
public class UtilityClass {
	
	public static HSSFCellStyle getHeaderStyle(HSSFWorkbook hssfWorkbook){
		
		/*******Style Code******/
		HSSFFont hssfFont = hssfWorkbook.createFont();
		hssfFont.setFontHeightInPoints((short)10);
		hssfFont.setFontName("Arial");
		//hssfFont.setColor(IndexedColors.B.getIndex());
		hssfFont.setBold(true);
		
		HSSFCellStyle hssfCellStyle = hssfWorkbook.createCellStyle();
		hssfCellStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		hssfCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		hssfCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		hssfCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		hssfCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		hssfCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		hssfCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		hssfCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		hssfCellStyle.setWrapText(true);
		hssfCellStyle.setFont(hssfFont);
		/*******End Style Code******/
		
		return hssfCellStyle;
	}
	
public static HSSFCellStyle getStyle(HSSFWorkbook hssfWorkbook){
		
		/*******Style Code******/
		HSSFFont hssfFont = hssfWorkbook.createFont();
		hssfFont.setFontHeightInPoints((short)10);
		hssfFont.setFontName("Arial");
		//hssfFont.setColor(IndexedColors.B.getIndex());
		
		HSSFCellStyle hssfCellStyle = hssfWorkbook.createCellStyle();
		hssfCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		hssfCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		hssfCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		hssfCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		hssfCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		hssfCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		hssfCellStyle.setWrapText(true);
		hssfCellStyle.setFont(hssfFont);
		/*******End Style Code******/
		
		return hssfCellStyle;
	}

}
