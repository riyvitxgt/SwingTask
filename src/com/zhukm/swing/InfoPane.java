package com.zhukm.swing;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;

public class InfoPane extends JPanel {
	Box hbox; 
	Box hbox2;
	Box vbox;
	
	JButton export; 
	JButton reset;
	JButton refresh;
	JButton view; 
	JTable table;
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
		
		table = new JTable(9,10);
	
		
		hbox.add(export);
		hbox.add(Box.createHorizontalStrut(20));
		hbox.add(reset);
		hbox.add(Box.createHorizontalStrut(20));
		hbox.add(refresh);
		hbox.add(Box.createHorizontalStrut(20));
		hbox.add(view);
		
		hbox.setBackground(Color.gray);
		hbox2.add(table);
		
		vbox.add(hbox);
		vbox.add(Box.createVerticalGlue());
		vbox.add(hbox2);
		
		addIcon();
		setBorder();
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
}
