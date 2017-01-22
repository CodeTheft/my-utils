package com.theft.code.utils.test;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.theft.code.utils.excel.ExcelUtil;

public class ExcelTest {

	public static void main(String[] args) throws Exception {
		ExcelObject object = new ExcelObject();
		object.setId(1);
		object.setBirthDay(new Date());
		ExcelObject object2 = new ExcelObject();
		object2.setName("test");
		object2.setGender(true);
		ExcelObject object3 = new ExcelObject();
		object3.setId(2);
		object3.setName("名字");
		object3.setPhone(129399393993l);
		object3.setBirthDay(new Date());
		object3.setGender(false);
		object3.setMoeny(111111.11);
		
		List<ExcelObject> list = new ArrayList<ExcelObject>();
		list.add(object);
		list.add(object2);
		list.add(object3);
		
		FileOutputStream fileOut = new FileOutputStream("test.xls");
		HSSFWorkbook workbook = new HSSFWorkbook();
		ExcelUtil<ExcelObject> excelUtil = new ExcelUtil<ExcelObject>("yyyy-MM-dd HH:mm:ss");
		List<String> head = Arrays.asList("编号", "名称", "看看");
		excelUtil.exportExl(workbook, head, list, "test");
		workbook.write(fileOut);
		fileOut.close();
	}
	
}

class ExcelObject {

	private Integer id;
	private String name;
	private Long phone;
	private Double moeny;
	private Date birthDay;
	private Boolean gender;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getPhone() {
		return phone;
	}
	public void setPhone(Long phone) {
		this.phone = phone;
	}
	public Double getMoeny() {
		return moeny;
	}
	public void setMoeny(Double moeny) {
		this.moeny = moeny;
	}
	public Date getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
	public Boolean getGender() {
		return gender;
	}
	public void setGender(Boolean gender) {
		this.gender = gender;
	}
	
}
