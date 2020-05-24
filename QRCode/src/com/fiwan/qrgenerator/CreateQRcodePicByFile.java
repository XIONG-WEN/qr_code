package com.fiwan.qrgenerator;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.WindowConstants;
import com.fiwan.utils.Utils;

/**
 * ���ļ�����һ���ά�벢���浽����
 * */
public class CreateQRcodePicByFile {
	static Settings setting=Settings.getInstance();  
	public static void create(String byFilePath) {
	 ArrayList<String> list=new ArrayList<String>();
		File byFile=new File(byFilePath);
		InputStreamReader read;
		 String folder=new File(byFilePath).getName().split("\\.")[0];
		try {
			read = new InputStreamReader(new FileInputStream(byFile));
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while((lineTxt = bufferedReader.readLine()) != null){
                	if(lineTxt.trim()!=null || "".equals(lineTxt.trim())){
                		list.add(lineTxt.trim());
                	}
                }
                read.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		new ProgressDialog(list,folder).jd.setVisible(true);
	}
}

/**
 * ���ɶ�ά����ȣ���Ҫ�߼��ڴ˴��� 
 * */
class ProgressDialog {
		JDialog jd = new JDialog();
		Toolkit kit = Toolkit.getDefaultToolkit(); // ���幤�߰�
		Dimension screenSize = kit.getScreenSize(); // ��ȡ��Ļ�ĳߴ�
		int screenWidth = screenSize.width; // ��ȡ��Ļ�Ŀ�
		int screenHeight = screenSize.height; // ��ȡ��Ļ�ĸ�
		ProgressDialog( ArrayList<String> list, final String folder ) {
			jd.setIconImage(Utils.getFrameIcon());
			jd.setSize(300, 160);
			jd.setTitle("��ʾ");
			jd.setModal(true);
			jd.setLocation(screenWidth / 2 - jd.getWidth() / 2, screenHeight / 2 - jd.getHeight() / 2);
			jd.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			JLabel label = new JLabel("��������ͼƬ");  
	        JProgressBar progressBar = new JProgressBar(); 
	        progressBar.setPreferredSize(new Dimension(260,30));
	        JButton button = new JButton("��");  
	        button.setEnabled(false);  
	        Container container = jd.getContentPane();  
	        container.setLayout(new GridLayout(3, 1));  
	        JPanel panel1= new JPanel(new FlowLayout(FlowLayout.LEFT));  
	        JPanel panel2= new JPanel(new FlowLayout(FlowLayout.CENTER));  
	        JPanel panel3= new JPanel(new FlowLayout(FlowLayout.RIGHT));  
	        panel1.add(label);  
	        panel2.add(progressBar);  
	        panel3.add(button);  
	        container.add(panel1);  
	        container.add(panel2);  
	        container.add(panel3);  
	        progressBar.setStringPainted(true);
	        final CreatePicProgress cpp= new CreatePicProgress(progressBar, button,list,folder);  
	        cpp.start();
	        button.addActionListener(new ActionListener() {  
				public void actionPerformed(ActionEvent e) {  
	        		try {
	        			Runtime.getRuntime().exec("explorer.exe " + Utils.getCurrentPath()+File.separator+folder);
	        		} catch (IOException ioe) {
	        			ioe.printStackTrace();
	        		}
	                jd.dispose();  
	            }  
	        });  
	        jd.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					cpp.exit=true;
					try {
						cpp.join();
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					} 
					jd.dispose();  
				}
			});
		}
	}	  