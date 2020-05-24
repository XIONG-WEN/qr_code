//package com.fiwan.qrgenerator;
//
//import java.awt.Container;
//import java.awt.Dimension;
//import java.awt.Image;
//import java.awt.Toolkit;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//import java.util.Hashtable;
//import javax.swing.ImageIcon;
//import javax.swing.JButton;
//import javax.swing.JComboBox;
//import javax.swing.JDialog;
//import javax.swing.JFileChooser;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.WindowConstants;
//
//import org.bytedeco.javacv.FrameGrabber.Exception;
//
//import com.fiwan.utils.Utils;
//import com.google.zxing.BarcodeFormat;
//import com.google.zxing.EncodeHintType;
//import com.google.zxing.MultiFormatWriter;
//import com.google.zxing.WriterException;
//import com.google.zxing.common.BitMatrix;
//import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
//
//import javax.swing.BorderFactory;
//import javax.swing.DefaultComboBoxModel;
//import javax.swing.GroupLayout;
//import javax.swing.GroupLayout.Alignment;
//import javax.swing.LayoutStyle.ComponentPlacement;
//import javax.swing.event.DocumentEvent;
//import javax.swing.event.DocumentListener;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JTextArea;
//import javax.swing.UIManager;
//import javax.swing.UnsupportedLookAndFeelException;
//
//import java.awt.Insets;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import java.awt.Font;
//import javax.swing.border.LineBorder;
//import java.awt.Color;
//
///**
// * �����������࣬�������
// * 
// * @version v.1.2
// * @author frogchou
// */
//@SuppressWarnings("serial")
//public class GeneratorForm extends JFrame {
//	private JButton openButton;
//	private JButton copyToClibboardButton;
//	private JButton savePicButton;
//	private JComboBox setColorComboBox;
//	private JComboBox setSizeComboBox;
//	private JComboBox setTypeComboBox;
//	private JComboBox setRateComboBox;
//	private JLabel showQRcodeLabel;
//	private JLabel setColorLabel;
//	private JLabel setSizeLable;
//	private JLabel setTypeLable;
//	private JLabel setRateLable;
//	private JPanel showQRcodePanel;
//	private JPanel jPanel3;
//	private JPanel settingsPanel;
//	private JScrollPane textScrollPane1;
//	private JTextArea jTextArea1;
//	private JButton scanButton;
//	private JButton getClipboardButton;
//	private JPanel panel_1;
//	private JLabel titleLable;
//	private JButton chooseButton;
//	private JButton createButton;
//	private JLabel showPathLabel;
//	
//	Settings setting = Settings.getInstance();
//	Toolkit kit = Toolkit.getDefaultToolkit(); // ���幤�߰�
//	Dimension screenSize = kit.getScreenSize(); // ��ȡ��Ļ�ĳߴ�
//	int screenWidth = screenSize.width; // ��ȡ��Ļ�Ŀ�
//	int screenHeight = screenSize.height; // ��ȡ��Ļ�ĸ�
//	String filePath="";
//
//	public GeneratorForm() {
//	
//		try {// ʹ��Windows�Ľ�����
//			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (InstantiationException e) {
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			e.printStackTrace();
//		} catch (UnsupportedLookAndFeelException e) {
//			e.printStackTrace();
//		}
//		
//		initComponents();
//		this.setResizable(false);
//		this.settextArea();
//		this.setIconImage(Utils.getFrameIcon());
//		savePicButton.setEnabled(false);
//		int windowWidth = this.getWidth(); // ��ô��ڿ�
//		int windowHeight = this.getHeight(); // ��ô��ڸ�
//		this.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);// ���ô��ھ�����ʾ
//	}
//	
//	/**
//	 * ��ʼ�����
//	 * */
//	private void initComponents() {
//
//		showQRcodePanel = new JPanel();
//		showQRcodeLabel = new JLabel();
//		jPanel3 = new JPanel();
//		copyToClibboardButton = new JButton();
//		savePicButton = new  JButton();
//		savePicButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//			}
//		});
//		openButton = new  JButton();
//		settingsPanel = new  JPanel();
//		settingsPanel.setBorder(new LineBorder(Color.GRAY, 1, true));
//		settingsPanel.setToolTipText("\u8BBE\u7F6E\u533A");
//		setColorLabel = new  JLabel();
//		setSizeLable = new  JLabel();
//		setTypeLable = new  JLabel();
//		setRateLable = new  JLabel();
//		setColorComboBox = new  JComboBox();
//		setSizeComboBox = new JComboBox();
//		setTypeComboBox = new JComboBox();
//		setRateComboBox = new JComboBox();
//		textScrollPane1 = new JScrollPane();
//		jTextArea1 = new JTextArea();
//		
//		/**
//		 * ���ı����һ�����ݸı�����¼����ı���������������
//		 * */
//		jTextArea1.getDocument().addDocumentListener(new DocumentListener() {
//			
//			public void removeUpdate(DocumentEvent e) {
//				try {
//					crateQRcode();
//				} catch (WriterException e1) {
//					e1.printStackTrace();
//				}
//			}
//			
//			public void insertUpdate(DocumentEvent e) {
//				try {
//					crateQRcode();
//				} catch (WriterException e1) {
//					e1.printStackTrace();
//				}
//			}
//			
//			public void changedUpdate(DocumentEvent e) {
//				try {
//					crateQRcode();
//				} catch (WriterException e1) {
//					e1.printStackTrace();
//				}
//			}
//		});
//		
//		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//		setTitle("\u98de\u7f51\u4e8c\u7ef4\u7801\u751f\u6210\u5668");
//		setBounds(new java.awt.Rectangle(0, 0, 0, 0));
//
//		showQRcodeLabel.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
//
//		org.jdesktop.layout.GroupLayout gl_showQRcodePanel = new org.jdesktop.layout.GroupLayout(showQRcodePanel);
//		showQRcodePanel.setLayout(gl_showQRcodePanel);
//		gl_showQRcodePanel.setHorizontalGroup(gl_showQRcodePanel.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
//				.add(showQRcodeLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE));
//		gl_showQRcodePanel.setVerticalGroup(gl_showQRcodePanel.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
//				.add(showQRcodeLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE));
//
//		copyToClibboardButton.setText("\u590D\u5236\u7801\u56FE\u5230\u526A\u5207\u677F");
//		copyToClibboardButton.setMargin(new Insets(0, 0, 0, 0));
//		copyToClibboardButton.addMouseListener(new java.awt.event.MouseAdapter() {
//			@Override
//			public void mouseClicked(java.awt.event.MouseEvent evt) {
//				jButton2MouseClicked(evt);
//			}
//		});
//
//		savePicButton.setText("\u4FDD\u5B58\u7801");
//		savePicButton.addMouseListener(new java.awt.event.MouseAdapter() {
//			@Override
//			public void mouseClicked(java.awt.event.MouseEvent evt) {
//				jButton3MouseClicked(evt);
//			}
//		});
//
//		openButton.setText("\u6253\u5f00");
//		openButton.setMargin(new Insets(0, 0, 0, 0));
//		openButton.addMouseListener(new java.awt.event.MouseAdapter() {
//			@Override
//			public void mouseClicked(java.awt.event.MouseEvent evt) {
//				jButton1MouseClicked(evt);
//			}
//		});
//		openButton.addActionListener(new java.awt.event.ActionListener() {
//			public void actionPerformed(java.awt.event.ActionEvent evt) {
//				jButton1ActionPerformed(evt);
//			}
//		});
//		
//		scanButton = new JButton();
//		scanButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				try {
//					new ScanCode(jTextArea1);
//				} catch (Exception e1) {
//					new Dialog1("�޷�������ͷ��").jd.setVisible(true);
//					e1.printStackTrace();
//				} catch (InterruptedException e1) {
//					new Dialog1("�޷�������ͷ��").jd.setVisible(true);
//					e1.printStackTrace();
//				}
//			}
//		});
//
//		scanButton.setText("\u626B\u63CF\u4E8C\u7EF4\u7801");
//		scanButton.setMargin(new Insets(0, 0, 0, 0));
//		
//		getClipboardButton = new JButton();
//		/**
//		 * ʶ����а��е�����
//		 * */
//		getClipboardButton.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				Image image = null;
//				String rstStr = null;
//				try {
//					image=Utils.getImageFromClipboard();
//				} catch (java.lang.Exception e1) {
//					new Dialog1("�޷���ȡ���а�����ݡ�").jd.setVisible(true);
//				}
//				if (image!=null)
//				{
//				rstStr=ZxingHandler.getQrcodeFromPic(Utils.toBufferedImage(image));
//				}else{
//					try {
//						rstStr=Utils.getStringFromClipboard();
//					} catch (java.lang.Exception e1) {
//						new Dialog1("�޷�ʶ����а��е����ݡ�").jd.setVisible(true);
//						e1.printStackTrace();
//					}
//				}
//				if (rstStr!=null || !"".equals(rstStr)){
//					jTextArea1.setText(rstStr);
//					try {
//						crateQRcode();
//					} catch (WriterException e1) {
//						new Dialog1("�޷�ʶ����а��е����ݡ�").jd.setVisible(true);
//					}
//				}else{
//					new Dialog1("�޷�ʶ����а��е����ݡ�").jd.setVisible(true);
//				}
//			}
//		});
//		getClipboardButton.setText("\u83B7\u53D6\u526A\u5207\u677F\u7801\u5185\u5BB9");
//		getClipboardButton.setMargin(new Insets(0, 0, 0, 0));
//
//		GroupLayout jPanel3Layout = new GroupLayout(jPanel3);
//		jPanel3Layout.setHorizontalGroup(
//			jPanel3Layout.createParallelGroup(Alignment.LEADING)
//				.addGroup(jPanel3Layout.createSequentialGroup()
//					.addComponent(savePicButton, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
//					.addPreferredGap(ComponentPlacement.RELATED)
//					.addComponent(scanButton, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
//					.addPreferredGap(ComponentPlacement.RELATED)
//					.addComponent(getClipboardButton, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
//					.addPreferredGap(ComponentPlacement.RELATED)
//					.addComponent(copyToClibboardButton, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
//					.addPreferredGap(ComponentPlacement.RELATED)
//					.addComponent(openButton, GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE))
//		);
//		jPanel3Layout.setVerticalGroup(
//			jPanel3Layout.createParallelGroup(Alignment.LEADING)
//				.addGroup(jPanel3Layout.createSequentialGroup()
//					.addGroup(jPanel3Layout.createParallelGroup(Alignment.BASELINE)
//						.addComponent(savePicButton, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
//						.addComponent(openButton, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
//						.addComponent(scanButton, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
//						.addComponent(getClipboardButton, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
//						.addComponent(copyToClibboardButton, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE))
//					.addContainerGap())
//		);
//		jPanel3.setLayout(jPanel3Layout);
//
//		setColorLabel.setText("\u7C7B\u578B");
//
//		setSizeLable.setText("\u5927\u5c0f");
//
//		setTypeLable.setText("\u683c\u5f0f");
//
//		setRateLable.setText("\u5bb9\u9519\u7387");
//
//		setColorComboBox.setModel(new DefaultComboBoxModel(new String[] { "��ɫ", "�ڰ�" ,"IDcode��֤"}));
//		setColorComboBox.addItemListener(new java.awt.event.ItemListener() {
//			public void itemStateChanged(java.awt.event.ItemEvent evt) {
//				jComboBox1ItemStateChanged(evt);
//			}
//		});
//
//		setSizeComboBox.setModel(new DefaultComboBoxModel(new String[] { "200px", "300px", "500px","600px", "800px" }));
//		setSizeComboBox.setSelectedIndex(1);
//		setSizeComboBox.addItemListener(new java.awt.event.ItemListener() {
//			public void itemStateChanged(java.awt.event.ItemEvent evt) {
//				jComboBox2ItemStateChanged(evt);
//			}
//		});
//
//		setTypeComboBox.setModel(new DefaultComboBoxModel(
//				new String[] { "png", "jpg", "bmp", "gif", "eps(ʸ��)", "pdf(ʸ��)", "svg(ʸ��)" }));
//		setTypeComboBox.addItemListener(new java.awt.event.ItemListener() {
//			public void itemStateChanged(java.awt.event.ItemEvent evt) {
//				jComboBox3ItemStateChanged(evt);
//			}
//		});
//
//		setRateComboBox.setModel(new DefaultComboBoxModel(new String[] { "30%", "25%", "15%", "7%" }));
//		setRateComboBox.setSelectedIndex(2);
//		setRateComboBox.addItemListener(new java.awt.event.ItemListener() {
//			public void itemStateChanged(java.awt.event.ItemEvent evt) {
//				jComboBox4ItemStateChanged(evt);
//			}
//		});
//
//		GroupLayout gl_settingsPanel = new GroupLayout(settingsPanel);
//		gl_settingsPanel.setHorizontalGroup(
//			gl_settingsPanel.createParallelGroup(Alignment.LEADING)
//				.addGroup(gl_settingsPanel.createSequentialGroup()
//					.addContainerGap()
//					.addGroup(gl_settingsPanel.createParallelGroup(Alignment.TRAILING)
//						.addComponent(setSizeLable)
//						.addGroup(gl_settingsPanel.createSequentialGroup()
//							.addGap(25)
//							.addComponent(setTypeLable))
//						.addComponent(setRateLable)
//						.addComponent(setColorLabel))
//					.addGap(18)
//					.addGroup(gl_settingsPanel.createParallelGroup(Alignment.TRAILING)
//						.addGroup(gl_settingsPanel.createParallelGroup(Alignment.TRAILING)
//							.addComponent(setRateComboBox, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//							.addComponent(setTypeComboBox, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//							.addComponent(setSizeComboBox, 0, 126, Short.MAX_VALUE))
//						.addComponent(setColorComboBox, 0, 126, Short.MAX_VALUE))
//					.addContainerGap())
//		);
//		gl_settingsPanel.setVerticalGroup(
//			gl_settingsPanel.createParallelGroup(Alignment.TRAILING)
//				.addGroup(gl_settingsPanel.createSequentialGroup()
//					.addContainerGap(29, Short.MAX_VALUE)
//					.addGroup(gl_settingsPanel.createParallelGroup(Alignment.BASELINE)
//						.addComponent(setColorComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
//						.addComponent(setColorLabel))
//					.addPreferredGap(ComponentPlacement.UNRELATED)
//					.addGroup(gl_settingsPanel.createParallelGroup(Alignment.BASELINE)
//						.addComponent(setSizeComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
//						.addComponent(setSizeLable))
//					.addPreferredGap(ComponentPlacement.UNRELATED)
//					.addGroup(gl_settingsPanel.createParallelGroup(Alignment.BASELINE)
//						.addComponent(setTypeLable)
//						.addComponent(setTypeComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
//					.addPreferredGap(ComponentPlacement.UNRELATED)
//					.addGroup(gl_settingsPanel.createParallelGroup(Alignment.BASELINE)
//						.addComponent(setRateComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
//						.addComponent(setRateLable))
//					.addGap(22))
//		);
//		settingsPanel.setLayout(gl_settingsPanel);
//
//		jTextArea1.setColumns(20);
//		jTextArea1.setRows(5);
//		jTextArea1.addKeyListener(new java.awt.event.KeyAdapter() {
//			@Override
//			public void keyReleased(java.awt.event.KeyEvent evt) {
//				jTextArea1KeyReleased(evt);
//			}
//		});
//		textScrollPane1.setViewportView(jTextArea1);
//		
//		JPanel panel = new JPanel();
//		
//		panel_1 = new JPanel();
//		panel_1.setBorder(new LineBorder(Color.GRAY, 1, true));
//
//		GroupLayout layout = new GroupLayout(getContentPane());
//		layout.setHorizontalGroup(
//			layout.createParallelGroup(Alignment.LEADING)
//				.addGroup(layout.createSequentialGroup()
//					.addContainerGap()
//					.addGroup(layout.createParallelGroup(Alignment.LEADING)
//						.addGroup(layout.createSequentialGroup()
//							.addComponent(showQRcodePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
//							.addPreferredGap(ComponentPlacement.RELATED)
//							.addGroup(layout.createParallelGroup(Alignment.LEADING)
//								.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
//								.addComponent(settingsPanel, GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
//								.addComponent(panel, GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)))
//						.addComponent(jPanel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//						.addComponent(textScrollPane1, GroupLayout.DEFAULT_SIZE, 519, Short.MAX_VALUE))
//					.addContainerGap())
//		);
//		layout.setVerticalGroup(
//			layout.createParallelGroup(Alignment.LEADING)
//				.addGroup(layout.createSequentialGroup()
//					.addContainerGap()
//					.addGroup(layout.createParallelGroup(Alignment.LEADING)
//						.addComponent(showQRcodePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
//						.addGroup(layout.createSequentialGroup()
//							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
//							.addPreferredGap(ComponentPlacement.RELATED)
//							.addComponent(settingsPanel, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
//							.addPreferredGap(ComponentPlacement.RELATED)
//							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)))
//					.addPreferredGap(ComponentPlacement.RELATED)
//					.addComponent(jPanel3, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
//					.addPreferredGap(ComponentPlacement.RELATED)
//					.addComponent(textScrollPane1, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
//					.addContainerGap())
//		);
//		panel_1.setLayout(null);
//		
//		titleLable = new JLabel("\u9009\u62E9\u6587\u4EF6\uFF1A");
//		titleLable.setBounds(10, 0, 185, 20);
//		panel_1.add(titleLable);
//		
//		chooseButton = new JButton();
//		chooseButton.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				JFileChooser jChooser = new JFileChooser();  
//				//����Ĭ�ϵĴ�Ŀ¼, 
//				jChooser.setCurrentDirectory(null);  
//				jChooser.setFileFilter(new FileFilter());
//				//��һ���Ի���  
//				int index = jChooser.showDialog(null, "���ļ�");  
//				if (index == JFileChooser.APPROVE_OPTION) {  
//				 filePath=jChooser.getSelectedFile().getAbsolutePath(); 
//				 showPathLabel.setText(new File(filePath).getName());
//				}  else
//				{
//					 showPathLabel.setText("û��ѡ���ļ�");
//				}
//			}
//		});
//		chooseButton.setText("\u9009\u62E9\u6587\u4EF6");
//		chooseButton.setMargin(new Insets(0, 0, 0, 0));
//		chooseButton.setBounds(10, 56, 106, 27);
//		panel_1.add(chooseButton);
//		
//		createButton = new JButton();
//		createButton.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				if(filePath==null || "".equals(filePath))
//				{
//					new Dialog1("����û��ѡ���ļ���").jd.setVisible(true);
//				}
//				else{
//					CreateQRcodePicByFile.create(filePath);
//				}
//			}
//		});
//		createButton.setText("\u751F\u6210");
//		createButton.setMargin(new Insets(0, 0, 0, 0));
//		createButton.setBounds(123, 56, 72, 27);
//		panel_1.add(createButton);
//		
//		showPathLabel = new JLabel("");
//		showPathLabel.setBounds(10, 30, 185, 16);
//		panel_1.add(showPathLabel);
//		
//		JLabel label = new JLabel("\u4E8C\u7EF4\u7801\u751F\u6210\u5668");
//		label.setFont(new Font("΢���ź�", Font.BOLD, 16));
//		panel.add(label);
//		getContentPane().setLayout(layout);
//
//		getAccessibleContext().setAccessibleName("\u4e8c\u7ef4\u7801\u751f\u6210\u5668");
//
//		pack();
//	}// </editor-fold>
//		// GEN-END:initComponents
//
//	private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {
//		String str = jTextArea1.getText();
//		if (str == null || "".equals(str)) {
//		} else {
//			try {
//				Utils.setImageClipboard(getQRimage(str, setting.getQrcodeSize()));
//			} catch (WriterException e) {
//				e.printStackTrace();
//			}
//		}
//		new Dialog1("�Ѿ���ͼƬ���Ƶ�ճ�����ˡ�").jd.setVisible(true);
//	}
//
//	private void jComboBox4ItemStateChanged(java.awt.event.ItemEvent evt) {
//		switch (setRateComboBox.getSelectedIndex()) {
//		case 0:
//			setting.setQrcodeErrorRate(ErrorCorrectionLevel.H);
//			break;
//		case 1:
//			setting.setQrcodeErrorRate(ErrorCorrectionLevel.Q);
//			break;
//		case 2:
//			setting.setQrcodeErrorRate(ErrorCorrectionLevel.M);
//			break;
//		case 3:
//			setting.setQrcodeErrorRate(ErrorCorrectionLevel.L);
//			break;
//		}
//		try {
//			this.crateQRcode();
//		} catch (WriterException e) {
//			e.printStackTrace();
//		}
//	}
//
//	private void jComboBox3ItemStateChanged(java.awt.event.ItemEvent evt) {
//		switch (setTypeComboBox.getSelectedIndex()) {
//		case 0:
//			setting.setQrcodeFiletype(setTypeComboBox.getSelectedItem().toString());
//			break;
//		case 1:
//			setting.setQrcodeFiletype(setTypeComboBox.getSelectedItem().toString());
//			break;
//		case 2:
//			setting.setQrcodeFiletype(setTypeComboBox.getSelectedItem().toString());
//			break;
//		case 3:
//			setting.setQrcodeFiletype(setTypeComboBox.getSelectedItem().toString());
//			break;
//		case 4:
//			setting.setQrcodeFiletype("eps");
//			break;
//		case 5:
//			setting.setQrcodeFiletype("pdf");
//			break;
//		case 6:
//			setting.setQrcodeFiletype("svg");
//			break;
//		}
//		try {
//			this.crateQRcode();
//		} catch (WriterException e) {
//			e.printStackTrace();
//		}
//	}
//
//	private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {
//		switch (setSizeComboBox.getSelectedIndex()) {
//		case 0:
//			setting.setQrcodeSize(200);
//			break;
//		case 1:
//			setting.setQrcodeSize(300);
//			break;
//		case 2:
//			setting.setQrcodeSize(500);
//			break;
//		case 3:
//			setting.setQrcodeSize(600);
//			break;
//		case 4:
//			setting.setQrcodeSize(800);
//			break;
//		}
//		try {
//			this.crateQRcode();
//		} catch (WriterException e) {
//			e.printStackTrace();
//		}
//	}
//
//	private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {
//		//���Ҫ������֤ͼƬ����ʽֻ����png�ĸ�ʽ��
//		if (setColorComboBox.getSelectedIndex()==2){
//			setTypeComboBox.setEnabled(false);
//			setTypeComboBox.setSelectedIndex(0);
//		}else{
//			setTypeComboBox.setEnabled(true);
//		}
//		setting.setQrcodeColor(setColorComboBox.getSelectedIndex());
//		try {
//			this.crateQRcode();
//		} catch (WriterException e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * �����ɵ�ͼƬ���浽����
//	 */
//	private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {
//		if (((JButton) evt.getSource()).isEnabled()) { // �жϰ�ť��ǰ״̬��������ʱ���¼���ִ��
//			String filename = "FiwanQR" + System.currentTimeMillis();
//			String filepath = Utils.CreateFile(setting.getQrcodeFiletype(), filename);
//			File file = new File(filepath);
//			
//			if (setting.getQrcodeColor()==2){
//				try {
//					new CreateAuthQRCode(setting.getQrcodeSize(),file).create(jTextArea1.getText(), setting.getQrcodeSize(), setting.getQrcodeErrorRate());
//				} catch (IOException e) {
//					e.printStackTrace();
//				} catch (WriterException e) {
//					e.printStackTrace();
//				}
//			}
//			
//			try {
//				if (setting.getQrcodeFiletype().equals("eps") || setting.getQrcodeFiletype().equals("pdf")
//						|| setting.getQrcodeFiletype().equals("svg")) {
//					if (setting.getQrcodeFiletype().equals("eps")) {
//						ZxingHandler.createEPSQRCode(setting, file, jTextArea1.getText());
//					}
//					if (setting.getQrcodeFiletype().equals("pdf")) {
//						ZxingHandler.createPDFQRCode(setting, file, jTextArea1.getText());
//					}
//					if (setting.getQrcodeFiletype().equals("svg")) {
//						ZxingHandler.createSVGQRCode(setting, file, jTextArea1.getText());
//					}
//				} else {
//					if (setting.getQrcodeColor() == 0) {
//						ZxingHandler.writeToFileWithColor(GetBitMatrix(jTextArea1.getText(), setting.getQrcodeSize()),
//								setting.getQrcodeFiletype(), file);
//					}
//					if (setting.getQrcodeColor() == 1) {
//						ZxingHandler.writeToFile(GetBitMatrix(jTextArea1.getText(), setting.getQrcodeSize()),
//								setting.getQrcodeFiletype(), file);
//					}
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			} catch (WriterException e) {
//				e.printStackTrace();
//			}
//			new Dialog1("�Ѿ����浽���أ������򿪰�ť�鿴��").jd.setVisible(true);
//		}
//	}
//
//	private void jTextArea1KeyReleased(java.awt.event.KeyEvent evt) {
//		try {
//			this.crateQRcode();
//		} catch (WriterException e) {
//			e.printStackTrace();
//		}
//	}
//
//	private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {
//		try {
//			Runtime.getRuntime().exec("explorer.exe " + Utils.getCurrentPath());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
//	}
//
//	public static void main(String args[]) {
//		java.awt.EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				new GeneratorForm().setVisible(true);
//			}
//		});
//	}
//
//	private void settextArea() {
//		jTextArea1.setLineWrap(true); // �����Զ����й���
//		jTextArea1.setWrapStyleWord(true); // ������в����ֹ���
//	}
//
//	/**
//	 * �����ı����е����ݺ�setting����һ����ά�룬��ʾ��ͼƬ��ʾ�����ڡ�
//	 * */
//	public void crateQRcode() throws WriterException {
//		String str = jTextArea1.getText();
//		if (str == null || "".equals(str)) {
//			showQRcodeLabel.setIcon(null);
//			savePicButton.setEnabled(false);
//		} else {
//			ImageIcon ii = new ImageIcon(getQRimage(str, setting.getQrcodeSize()));
//			ii.setImage(ii.getImage().getScaledInstance(306, 306, Image.SCALE_DEFAULT));
//			showQRcodeLabel.setIcon(ii);
//			savePicButton.setEnabled(true);
//		}
//	}
//
//	public BitMatrix GetBitMatrix(String content, int size) throws WriterException {
//		size = (size <= 0) ? 100 : size;
//		BitMatrix bitMatrix = null;
//		MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
//		Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
//		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
//		hints.put(EncodeHintType.MARGIN, 1); // ������ͼ�ױ�
//		hints.put(EncodeHintType.ERROR_CORRECTION, setting.getQrcodeErrorRate()); // �ݴ���
//		bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, size, size, hints);
//		return bitMatrix;
//	}
//
//	private BufferedImage getQRimage(String content, int size) throws WriterException {
//		size = (size <= 0) ? 100 : size;
//		BufferedImage image = null;
//		// BufferedImage image= ZxingHandler.toBufferedImage(
//		// GetBitMatrix(content,size));
//		if (setting.getQrcodeColor() == 0) {
//			image = ZxingHandler.toBufferedImageWithColor(GetBitMatrix(content, size)); // ���ɲ�ɫ
//		}
//		if (setting.getQrcodeColor() == 1) {
//			image = ZxingHandler.toBufferedImage(GetBitMatrix(content, size)); // ���ɺڰ�
//		}
//		if (setting.getQrcodeColor() == 2) {
//			image = new CreateAuthQRCode(setting.getQrcodeSize(), null).createBufferedImage(content, size, setting.getQrcodeErrorRate()); // ������֤IDcode
//		}
//		return image;
//	}
//
//	
//	class Dialog1 {
//		JDialog jd = new JDialog();
//		Dialog1(String msg) {
//			jd.setIconImage(Utils.getFrameIcon());
//			jd.setSize(300, 160);
//			jd.setTitle("��ʾ");
//			jd.setModal(true);
//			Container c2 = jd.getContentPane();
//			c2.setLayout(null);
//			jd.setLocation(screenWidth / 2 - jd.getWidth() / 2, screenHeight / 2 - jd.getHeight() / 2);
//			JLabel jl = new JLabel(msg);
//			jl.setBounds(20, -20, 250, 100);
//			JButton jbb = new JButton("ȷ��");
//			jbb.setBounds(100, 70, 60, 30);
//			c2.add(jl);
//			c2.add(jbb);
//			jbb.addActionListener(new ActionListener() {
//				public void actionPerformed(ActionEvent e) {
//					jd.dispose();
//				}
//			});
//			jd.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
//		}
//	}
//	
//	class FileFilter extends javax.swing.filechooser.FileFilter{
//		  @Override
//		public boolean accept(File f) {
//			  if (f.isDirectory())return true;
//			 if(f.getName().endsWith(".csv")||f.getName().endsWith(".txt")){
//		    return true; 
//		    }else{
//		    	 return false;
//		    }
//		  } 
//		  @Override
//		public String getDescription(){
//		    return ".csv �� .txt";
//		  }
//	}
//}