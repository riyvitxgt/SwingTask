package com.zhukm.swing;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class TreePane extends JPanel {
	private Toolbar toolbar;
	public TreePane(){}
	public TreePane(InfoPane infoPane){
		toolbar = new Toolbar(infoPane);
		this.setLayout(new BorderLayout());
		this.add(toolbar,"North");
	}
	public Toolbar getToolbar() {
		return toolbar;
	}
	public void setToolbar(Toolbar toolbar) {
		this.toolbar = toolbar;
	}
}

class Toolbar extends JPanel{
	JToolBar toolbar;
	JButton pBtn;
	JButton dBtn;
	JButton rBtn;
	JButton cBtn;

	public Toolbar(InfoPane infoPane){
		init();
		
		toolbar.add(pBtn);
		toolbar.add(dBtn);
		toolbar.add(rBtn);
		toolbar.add(cBtn);
		this.setLayout(new BorderLayout());
		this.add(toolbar, "North");
		this.add(new ScroPane(infoPane), "Center");
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
	
	public JToolBar getToolbar() {
		return toolbar;
	}

	public void setToolbar(JToolBar toolbar) {
		this.toolbar = toolbar;
	}

	public JButton getpBtn() {
		return pBtn;
	}

	public void setpBtn(JButton pBtn) {
		this.pBtn = pBtn;
	}

	public JButton getdBtn() {
		return dBtn;
	}

	public void setdBtn(JButton dBtn) {
		this.dBtn = dBtn;
	}

	public JButton getrBtn() {
		return rBtn;
	}

	public void setrBtn(JButton rBtn) {
		this.rBtn = rBtn;
	}

	public JButton getcBtn() {
		return cBtn;
	}

	public void setcBtn(JButton cBtn) {
		this.cBtn = cBtn;
	}
}