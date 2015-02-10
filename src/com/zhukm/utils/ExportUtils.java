package com.zhukm.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.swing.JTable;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExportUtils {
	/**
	 * 将JTable里面的数据导出到excel表格中
	 * @param table JTable表格
	 * @param fileName excel表格文件名
	 */
	public static void ExportAsExcel(JTable table, String fileName) {
		HSSFWorkbook wb = new HSSFWorkbook();
		File f = null;
		try {
			f = new File(fileName);
			if (!f.exists()) {
				f.createNewFile();
				FileOutputStream fileOut = new FileOutputStream(f);

				HSSFSheet sheet = wb.createSheet();
				List<List<String>> rst = JdbcUtils.getStringRst("mybatis",
						"topics");
				int r = table.getRowCount();
				int c = table.getColumnCount();
				for (int i = 0; i < r; i++) {
					HSSFRow row = sheet.createRow(i);
					for (int j = 0; j < c; j++) {
						HSSFCell cell = row.createCell(j);
						cell.setCellValue((String)table.getValueAt(i, j));
					}

				}

				wb.write(fileOut);
				fileOut.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
