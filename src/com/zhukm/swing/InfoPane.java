package com.zhukm.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.zhukm.utils.ExportUtils;

public class InfoPane extends JPanel {
	private Box hbox; 
	private Box hbox2;
	private Box vbox;
	
	private JButton export; 
	private JButton reset;
	private JButton refresh;
	private JButton view; 
	private JTable table;
	private JScrollPane scrollPane;
	public InfoPane() {
		init();
		this.add(vbox);
		this.setBackground(Color.WHITE);
	}
	
	public void init(){
		export = new JButton("导出");
		reset = new JButton("重置");
		refresh = new JButton("刷新数据");
		view = new JButton("查看屏蔽数据");
		
		hbox = Box.createHorizontalBox();
		hbox2 = Box.createHorizontalBox();
		vbox = Box.createVerticalBox();
		
		scrollPane = new JScrollPane();
		
		
		
		table = new JTable();
	
		
		hbox.add(export);
		hbox.add(Box.createHorizontalStrut(20));
		hbox.add(reset);
		hbox.add(Box.createHorizontalStrut(20));
		hbox.add(refresh);
		hbox.add(Box.createHorizontalStrut(20));
		hbox.add(view);
		
		hbox.setBackground(Color.gray);
		//hbox2.add(table);
		scrollPane.setPreferredSize(new Dimension(1100,500));
		
		vbox.add(hbox);
		vbox.add(Box.createVerticalGlue());
		vbox.add(hbox2);
		
		addIcon();
		setBorder();
		
		export.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JTable table = getTable();
				if(table.getRowCount() == 0){
					System.out.println("还未加载数据库中的数据");
				}else{
					ExportUtils.ExportAsExcel(table, "D:\\" + new Date().getTime() + ".xls");
					/*System.out.println(table);
					System.out.println(table.getValueAt(0, 0));
					System.out.println(table.getColumnCount() + "列   " + table.getRowCount() + "行");;*/
				}
			}
			
		});
	}
	
	public void addIcon(){
		ImageIcon saveIcon = new ImageIcon("images\\save_edit.png");
		export.setIcon(saveIcon);
		
		ImageIcon resetIcon = new ImageIcon("images\\reset.gif"); 
		reset.setIcon(resetIcon);
		
		ImageIcon refreshtIcon = new ImageIcon("images\\refresh.gif");
		refresh.setIcon(refreshtIcon);
		
		ImageIcon viewIcon = new ImageIcon("images\\watchlist_view.png");
		view.setIcon(viewIcon);
	}
	
	public void setBorder(){
		export.setBorder(BorderFactory.createEmptyBorder());
		reset.setBorder(BorderFactory.createEmptyBorder());
		refresh.setBorder(BorderFactory.createEmptyBorder());
		view.setBorder(BorderFactory.createEmptyBorder());
	}

	public Box getHbox() {
		return hbox;
	}

	public void setHbox(Box hbox) {
		this.hbox = hbox;
	}

	public Box getHbox2() {
		return hbox2;
	}

	public void setHbox2(Box hbox2) {
		this.hbox2 = hbox2;
	}

	public Box getVbox() {
		return vbox;
	}

	public void setVbox(Box vbox) {
		this.vbox = vbox;
	}

	public JButton getExport() {
		return export;
	}

	public void setExport(JButton export) {
		this.export = export;
	}

	public JButton getReset() {
		return reset;
	}

	public void setReset(JButton reset) {
		this.reset = reset;
	}

	public JButton getRefresh() {
		return refresh;
	}

	public void setRefresh(JButton refresh) {
		this.refresh = refresh;
	}

	public JButton getView() {
		return view;
	}

	public void setView(JButton view) {
		this.view = view;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		//scrollPane.removeAll();
		scrollPane.setViewportView(table);
		hbox2.add(scrollPane);
		this.table = table;
	}
}
