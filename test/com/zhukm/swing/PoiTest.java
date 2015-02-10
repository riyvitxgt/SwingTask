package com.zhukm.swing;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;

import com.zhukm.utils.JdbcUtils;


public class PoiTest {
	@Test
	public void writeTest(){
		
		HSSFWorkbook wb = new HSSFWorkbook();
		File f = null;
		try {
			f = new File("D:\\work.xls");
			if(!f.exists()){
				f.createNewFile();
			}
			FileOutputStream fileOut = 
					new FileOutputStream(f);
			
			HSSFSheet sheet = wb.createSheet();
			List<List<String>> rst = JdbcUtils.getStringRst("mybatis", "topics");
			int r = rst.get(0).size();
			for(int i = 0; i < rst.size(); i++){
				HSSFRow row = sheet.createRow(i);
				for(int j = 0; j < r; j++){
					HSSFCell cell = row.createCell(j);
					cell.setCellValue(rst.get(i).get(j));
				}
				
			}
			/*for(int i = 0; i < 5; i++){
				HSSFRow row = sheet.createRow(i);
				for(int j = 0; j < 20; j++){
					HSSFCell cell = row.createCell(j);
					cell.setCellValue("第  " + i + "行    第" + j + "列");
				}
			}*/
			wb.write(fileOut);
			fileOut.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDate(){
		System.out.println(new Date().getTime());
	}
}
