package kr.or.ddit.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import kr.or.ddit.dto.ExcelReadOption;

public class ExcelRead {
	public static Workbook getWorkbook(String filePath) throws IOException {
		FileInputStream fis = null;
		fis = new FileInputStream(filePath);
		Workbook wb = null;
		if (filePath.toUpperCase().endsWith(".XLS")) {
			wb = new HSSFWorkbook(fis);
		}else if (filePath.toUpperCase().endsWith(".XLSX")) {
			wb = new XSSFWorkbook(fis);
		}
		return wb;
	}
	
	public static List<Map<String, String>> read(ExcelReadOption excelReadOption) throws IOException {
		//액셀파일 자체, 읽어들인다.
		Workbook wb = getWorkbook(excelReadOption.getFilePath());
		//엑셀파일에서 첫번째 시트가져옴
		Sheet sheet = wb.getSheetAt(0);
		
		//sheet에서 유효한 데이터의 개수
		int numOfRows = sheet.getPhysicalNumberOfRows();
		int numOfCells = 0;
		
		Row row = null;
		Cell cell = null;
		String cellName = "";
		Map<String, String> map = null;
		
		List<Map<String, String>> result = new ArrayList<Map<String,String>>();
		
		for(int rowIndex=excelReadOption.getStartRow()-1; rowIndex<numOfRows; rowIndex++) {
			row = sheet.getRow(rowIndex);
			if (row!=null) {
				numOfCells = row.getPhysicalNumberOfCells();
				map = new HashedMap<String, String>();
				for (int cellIndex = 0; cellIndex < numOfCells; cellIndex++) {
					cell = row.getCell(cellIndex);
					cellName = ExcelCellRef.getName(cell, cellIndex);
					if (!excelReadOption.getOutputColumns().contains(cellName)) {
						continue;
					}
					map.put(cellName, ExcelCellRef.getvalue(cell));
				}
				result.add(map);
			}
		}
		return result;
	}
}
