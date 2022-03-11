package kr.or.ddit.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.CellReference;

public class ExcelCellRef {
	//cell에 해당하는 컬럼명을 가져옴
	public static String getName(Cell cell, int cellIndex) {
		int cellNum = 0;
		if (cell != null) {
			cellNum = cell.getColumnIndex();
		}
		else {
			cellNum = cellIndex;
		}
		
		return CellReference.convertNumToColString(cellNum);
	}
	
	@SuppressWarnings("deprecation")
	public static String getvalue(Cell cell) {
		String value="";
		if (cell==null) {
			value="";
		}else {
			if (cell.getCellType()== CellType.FORMULA) {
				value=cell.getCellFormula();
			}else if (cell.getCellType()== CellType.NUMERIC) {
				value=cell.getNumericCellValue()+"";
			}else if (cell.getCellType()== CellType.STRING) {
				value=cell.getStringCellValue()+"";
			}else if (cell.getCellType()== CellType.BOOLEAN) {
				value=cell.getBooleanCellValue()+"";
			}else if (cell.getCellType()== CellType.ERROR) {
				value=cell.getErrorCellValue()+"";
			}else if (cell.getCellType()== CellType.BLANK) {
				value="";
			}else {
				value=cell.getStringCellValue();
			}
		}
		
		return value;
	}
}
