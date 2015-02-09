package com.zhukm.swing;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainFrame extends JFrame{
	private JFrame mainFrame;
	public MainFrame(){
		mainFrame = new JFrame("FireBall - [专项统计]");
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		
		ImageIcon frameIcon = new ImageIcon("images\\frameIcon.PNG");
		mainFrame.setIconImage(frameIcon.getImage());
		
		mainFrame.setSize((int)screenSize.getWidth(), (int)screenSize.getHeight());
		mainFrame.setVisible(true);
		
		JMenuBar menubar = new JMenuBar();
		mainFrame.setJMenuBar(menubar);
		
		JMenu fMenu = new JMenu("文件(F)");
		JMenu tMenu = new JMenu("工具(T)");
		JMenu mMenu = new JMenu("资源中心(M)");
		JMenu vMenu = new JMenu("视图(V)");
		JMenu lMenu = new JMenu("流程菜单");
		JMenu hMenu = new JMenu("帮助(H)");
		
		menubar.add(fMenu);
		menubar.add(tMenu);
		menubar.add(mMenu);
		menubar.add(vMenu);
		menubar.add(lMenu);
		menubar.add(hMenu);
		
		fMenu.setMnemonic('F');
		tMenu.setMnemonic('T');
		mMenu.setMnemonic('M');
		vMenu.setMnemonic('V');
		hMenu.setMnemonic('H');
		
		JMenuItem newItem = new JMenuItem("New", 'N');
		JMenuItem openItem = new JMenuItem("Open");
		JMenuItem saveItem = new JMenuItem("Save", 'S');
		JMenuItem saveAsItem = new JMenuItem("Save As...");
		JMenuItem printItem = new JMenuItem("Print", 'P');
		
		
		fMenu.add(newItem);
		fMenu.add(openItem);
		fMenu.addSeparator();
		fMenu.add(saveItem);
		fMenu.add(saveAsItem);
		fMenu.addSeparator();
		fMenu.add(printItem);
		
		ImageIcon saveIcon = new ImageIcon("images\\save_edit.png");
		saveIcon.setImage(saveIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		saveItem.setIcon(saveIcon);
		
		ContentPane contentPane = new ContentPane();
		mainFrame.setContentPane(contentPane);
	}
}






















































