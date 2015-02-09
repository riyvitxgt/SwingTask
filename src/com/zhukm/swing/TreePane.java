package com.zhukm.swing;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class TreePane extends JPanel {
	public TreePane(){
		this.setLayout(new BorderLayout());
		this.add(new Toolbar(),"North");
	}
}

class Toolbar extends JPanel{
	JToolBar toolbar;
	JButton pBtn;
	JButton dBtn;
	JButton rBtn;
	JButton cBtn;
	public Toolbar(){
		this.setSize(400, 1200);
		init();
		
		toolbar.add(pBtn);
		toolbar.add(dBtn);
		toolbar.add(rBtn);
		toolbar.add(cBtn);
		this.setLayout(new BorderLayout());
		this.add(toolbar, "North");
		this.add(new ScroPane(), "Center");
	}
	
	public void init(){
		toolbar = new JToolBar();
		pBtn = new JButton();
		dBtn = new JButton();
		rBtn = new JButton();
		cBtn = new JButton();
		
		addIcon();
	}
	
	public void addIcon(){
		ImageIcon pIcon = new ImageIcon("images\\expandall.gif");
		pBtn.setIcon(pIcon);
		
		ImageIcon dIcon = new ImageIcon("images\\collapseall.gif");
		dBtn.setIcon(dIcon);
		
		ImageIcon rIcon = new ImageIcon("images\\refresh.gif");
		rBtn.setIcon(rIcon);
		
		ImageIcon cIcon = new ImageIcon("images\\clear.gif");
		cBtn.setIcon(cIcon);
	}
}