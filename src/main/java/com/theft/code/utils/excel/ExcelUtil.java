package com.theft.code.utils.excel;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.theft.code.utils.date.DateFormatUtil;

public class ExcelUtil<T> {

	private String pattern = "yyyy-MM-dd";
	
	public ExcelUtil() {}
	
	public ExcelUtil(String dateFormatPattern) {
		this.pattern = dateFormatPattern;
	}
	
	/**
	 * 导出集合数据到excel表格
	 * @param workbook 工作簿
	 * @param heads 表格第一行列标题
	 * @param data 数据集合
	 * @param sheetTitle 表格sheet名
	 * @throws Exception
	 */
	public void exportExl(HSSFWorkbook workbook, List<String> heads, Collection<T> data, String sheetTitle) throws Exception {
		HSSFSheet sheet = workbook.createSheet(sheetTitle);
		
		int index = 0;
		HSSFRow headRow = sheet.createRow(0);
		if (heads != null && !heads.isEmpty()) {
			for (int i = 0; i < heads.size(); i++) {
				HSSFCell cell = headRow.createCell(i);
				cell.setCellValue(heads.get(i));
			}
			index = 1;
		}
		
		Iterator<T> iterator = data.iterator();
		while (iterator.hasNext()) {
			HSSFRow row = sheet.createRow(index);
			T t = (T) iterator.next();
			createObjectCell(row, t);
			index ++;
		}
		
	}

	private void createObjectCell(HSSFRow row, T t) throws Exception {
		// 获取javabean申明属性
		Field[] fields = t.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			// 获取属性名
			String propName = fields[i].getName();
			// 获取get方法名
			String getMethodName = "get" + Character.toUpperCase(propName.charAt(0)) + propName.substring(1);
			
			Method method = t.getClass().getMethod(getMethodName, new Class[] {});
			// 通过invoke调用定义类的方法
			Object result = method.invoke(t, new Object[0]);
			if (result == null) {
				row.createCell(i).setCellValue("");
				continue;
			}
			
			if (result instanceof Integer) {
				row.createCell(i).setCellValue((Integer) result);
			} else if (result instanceof Long) {
				row.createCell(i).setCellValue((Long) result);
			} else if (result instanceof Float) {
				row.createCell(i).setCellValue((Float) result);
			} else if (result instanceof Double) {
				row.createCell(i).setCellValue((Double) result);
			} else if (result instanceof Boolean) {
				row.createCell(i).setCellValue((Boolean) result);
			} else if (result instanceof Date) {
				row.createCell(i).setCellValue(DateFormatUtil.formatDate2String((Date) result, pattern));
			} else {
				row.createCell(i).setCellValue(result.toString());
			}
		}
	}
	
}
