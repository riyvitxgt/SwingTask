package com.zhukm.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;

public class ContentPane extends JPanel {
	public ContentPane() {
		this.setLayout(new BorderLayout());

		// 工具条
		JToolBar toolbar = new JToolBar();

		JButton talkBtn = new JButton("聊天工具");
		JButton xmlBtn = new JButton("XMLBase");
		JButton antBtn = new JButton("Ant");
		JButton treeBtn = new JButton();
		JButton fileSearBtn = new JButton("文件查询");
		JButton uploadBtn = new JButton("文件上传");
		JButton countBtn = new JButton("专项统计");

		toolbar.add(talkBtn);
		toolbar.add(new JLabel("  |  "));
		toolbar.add(xmlBtn);
		toolbar.add(new JLabel("  |  "));
		toolbar.add(antBtn);
		toolbar.add(new JLabel("  |  "));
		toolbar.add(treeBtn);
		toolbar.add(new JLabel("  |  "));
		toolbar.add(fileSearBtn);
		toolbar.add(new JLabel("  |  "));
		toolbar.add(uploadBtn);
		toolbar.add(new JLabel("  |  "));
		toolbar.add(countBtn);

		talkBtn.setBorder(BorderFactory.createEmptyBorder());
		xmlBtn.setBorder(BorderFactory.createEmptyBorder());
		antBtn.setBorder(BorderFactory.createEmptyBorder());
		treeBtn.setBorder(BorderFactory.createEmptyBorder());
		fileSearBtn.setBorder(BorderFactory.createEmptyBorder());
		uploadBtn.setBorder(BorderFactory.createEmptyBorder());
		countBtn.setBorder(BorderFactory.createEmptyBorder());

		ImageIcon talkIcon = new ImageIcon("images\\TB_talk.jpg");
		talkIcon.setImage(talkIcon.getImage().getScaledInstance(20, 20,
				Image.SCALE_DEFAULT));
		talkBtn.setIcon(talkIcon);
		
		ImageIcon antIcon = new ImageIcon("images\\ant_view.gif");
		antBtn.setIcon(antIcon);
		
		ImageIcon searIcon = new ImageIcon("images\\find.png");
		fileSearBtn.setIcon(searIcon);
		
		ImageIcon treeIcon = new ImageIcon("images\\browser.gif");
		treeBtn.setIcon(treeIcon);

		// Tab
		JTabbedPane tp = new JTabbedPane();

		JPanel pPane = new JPanel();
		JPanel countPane = new JPanel();

		pPane.setSize(400, 500);
		countPane.setSize(400, 1500);
		
		tp.addTab("tab1", new PortalPane());
		tp.addTab("tab2", countPane);

		tp.setEnabledAt(0, true);
		tp.setTitleAt(0, "Partol");
		tp.setEnabledAt(1, true);
		tp.setTitleAt(1, "专项统计");

		this.add(toolbar, "North");
		this.add(tp, "Center");
	}
}

