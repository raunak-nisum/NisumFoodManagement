/**
 * 
 */
package com.nisum.foodmanagement.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nisum.foodmanagement.service.ReportService;


/**
 * @author Raunak
 *
 */
@RestController
public class ReportController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ReportService reportService;
	
    @RequestMapping(value = "/downloadSelectedDateReport", method = RequestMethod.GET, produces = "application/csv")
    public void downloadSelectedDateReport(HttpServletResponse response, @RequestParam String date, @RequestParam String food) throws IOException {
    	logger.info("In downloadReport() controller...."+date+"===="+food);
    	String fileName = food+"_"+date+".xls";
    	HSSFWorkbook hssfWorkbook =reportService.exportSelectedDateReport(food, date);
    	sendResponse(response, fileName, hssfWorkbook);
    }
	
	@RequestMapping(value = "/downloadMonthlyReport", method = RequestMethod.GET, produces = "application/csv")
    public void downloadMonthlyReport(HttpServletResponse response, @RequestParam String month, @RequestParam String year) throws IOException {
    	logger.info("In downloadReport() controller...."+month+"===="+year);
    	String fileName = month+"_"+year+".xls";
    	HSSFWorkbook hssfWorkbook =reportService.exportMonthlyReport(month, year);
    	sendResponse(response, fileName, hssfWorkbook);
    }
	
	@RequestMapping(value = "/downloadReportBetweenTwoDate", method = RequestMethod.GET, produces = "application/csv")
    public void downloadReportBetweenTwoDate(HttpServletResponse response, @RequestParam String fromDate, @RequestParam String toDate) throws IOException {
    	logger.info("In downloadReport() controller...."+fromDate+"===="+toDate);
    	String fileName = fromDate+"_"+toDate+".xls";
    	HSSFWorkbook hssfWorkbook =reportService.exportReportBetweenTwoDate(fromDate, toDate);
    	sendResponse(response, fileName, hssfWorkbook);
    }
	
	@RequestMapping(value = "/downloadReportBetweenTwoDateForSuggestion", method = RequestMethod.GET, produces = "application/csv")
    public void downloadReportBetweenTwoDateForSuggestion(HttpServletResponse response, @RequestParam String fromDate, @RequestParam String toDate) throws IOException {
    	logger.info("In downloadReportBetweenTwoDateForSuggestion() controller...."+fromDate+"===="+toDate);
    	String fileName = fromDate+"_"+toDate+".xls";
    	HSSFWorkbook hssfWorkbook =reportService.exportReportBetweenTwoDateForSuggestion(fromDate, toDate);
    	sendResponse(response, fileName, hssfWorkbook);
    }
	
	private void sendResponse(HttpServletResponse response, String fileName, HSSFWorkbook hssfWorkbook) throws IOException{
		response.setContentType("application/csv");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        //response.setHeader("Content-Length", String.valueOf(file.length()));
        //FileCopyUtils.copy(in, response.getOutputStream());
        hssfWorkbook.write(response.getOutputStream());
	}


}
