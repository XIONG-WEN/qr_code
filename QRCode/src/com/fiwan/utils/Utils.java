package com.fiwan.utils;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * @author frogchou
 * @version 1.0
 * ��������һЩ���õķ���
 * */
public class Utils {
	
	/**
	 * ���س���ǰ����ŵ�·��
	 * @return String ����ǰ����ŵ�·��
	 * */
	public static String getCurrentPath() {
		return System.getProperty("user.dir")+File.separator+"images";
	}

	/**
	 * ����һ���ļ����������ļ���·��
	 * @param filetype �ļ����ͣ���׺��
	 * @param filename �ļ���
	 * @return String �����ļ���·��
	 * */
	public static String CreateFile(String filetype, String filename) {
		File file = null;
		try {
			File filepath = new File(getCurrentPath());
			if (!filepath.exists())
				filepath.mkdirs();
			file = new File(filepath + File.separator + filename + "."
					+ filetype);
			if (!file.exists())
				file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file.getAbsolutePath();
	}
	
	/**
	 * ����һ��Ŀ¼��������Ŀ¼��·��
	 * @param filePathName �ļ�·��
	 * @return String �����ļ���·��
	 * */
	public static String CreateFilePathAndFile(String filePathName,String filetype, String filename) {
		File file = null;
		File filePath=null;
		File programPath = new File(getCurrentPath());
		if (!programPath.exists())
			programPath.mkdirs();
		filePath = new File(programPath+ File.separator +filePathName);
		if (!filePath.exists())
			filePath.mkdirs();
		file = new File(filePath + File.separator + filename + "."
				+ filetype);
		if (!file.exists())
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		return file.getAbsolutePath();
	}

	/**
	 * ��ת���ͼƬ��Դ���õ����а���
	 * 
	 * @param image
	 *            ������image ������BufferedImage����
	 */
	// �����а�����ͼƬ������
	public static void setImageClipboard(Image image) {
		Images imgSel = new Images(image);
		// ����
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(imgSel,
				null);
	}
	
    /** 
     * �Ӽ��а���ͼƬ�� 
     */  
    public static Image getImageFromClipboard() throws Exception {  
        Clipboard sysc = Toolkit.getDefaultToolkit().getSystemClipboard();  
        Transferable cc = sysc.getContents(null);  
        if (cc == null)
            return null;
        else if (cc.isDataFlavorSupported(DataFlavor.imageFlavor))
            return (Image) cc.getTransferData(DataFlavor.imageFlavor);
        return null;
    }  
    
    /** 
     * �Ӽ��а����ı��� 
     */  
    public static String getStringFromClipboard() throws Exception {  
        Clipboard sysc = Toolkit.getDefaultToolkit().getSystemClipboard();  
        Transferable cc = sysc.getContents(null);  
        if (cc == null)
            return null;
        else if (cc.isDataFlavorSupported(DataFlavor.stringFlavor))
            return (String) cc.getTransferData(DataFlavor.stringFlavor);
        return null;
    }  
    
    /**
     * Image ת BufferedImage
     * */
    public static BufferedImage toBufferedImage(Image image) {  
        if (image instanceof BufferedImage) {  
            return (BufferedImage)image;  
         }  
        
        // This code ensures that all the pixels in the image are loaded  
         image = new ImageIcon(image).getImage();  
        
        // Determine if the image has transparent pixels; for this method's  
        // implementation, see e661 Determining If an Image Has Transparent Pixels  
        //boolean hasAlpha = hasAlpha(image);  
        
        // Create a buffered image with a format that's compatible with the screen  
         BufferedImage bimage = null;  
         GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();  
        try {  
            // Determine the type of transparency of the new buffered image  
            int transparency = Transparency.OPAQUE;  
           /* if (hasAlpha) { 
             transparency = Transparency.BITMASK; 
             }*/  
        
            // Create the buffered image  
             GraphicsDevice gs = ge.getDefaultScreenDevice();  
             GraphicsConfiguration gc = gs.getDefaultConfiguration();  
             bimage = gc.createCompatibleImage(  
             image.getWidth(null), image.getHeight(null), transparency);  
         } catch (HeadlessException e) {  
            // The system does not have a screen  
         }  
        
        if (bimage == null) {  
            // Create a buffered image using the default color model  
            int type = BufferedImage.TYPE_INT_RGB;  
            //int type = BufferedImage.TYPE_3BYTE_BGR;//by wang  
            /*if (hasAlpha) { 
             type = BufferedImage.TYPE_INT_ARGB; 
             }*/  
             bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);  
         }  
        
        // Copy image to buffered image  
         Graphics g = bimage.createGraphics();  
        
        // Paint the image onto the buffered image  
         g.drawImage(image, 0, 0, null);  
         g.dispose();  
        
        return bimage;  
    }  
	
	public static class Images implements Transferable {
		private Image image; // �õ�ͼƬ����ͼƬ��
		public Images(Image image) {
			this.image = image;
		}
		public DataFlavor[] getTransferDataFlavors() {
			return new DataFlavor[] { DataFlavor.imageFlavor };
		}
		public boolean isDataFlavorSupported(DataFlavor flavor) {
			return DataFlavor.imageFlavor.equals(flavor);
		}
		public Object getTransferData(DataFlavor flavor)
				throws UnsupportedFlavorException, IOException {
			if (!DataFlavor.imageFlavor.equals(flavor)) {
				throw new UnsupportedFlavorException(flavor);
			}
			return image;
		}
	}
	
	/**
	 * �ϲ�����ͼƬ
	 * */
	public static void modifyImageTogeter (BufferedImage imageOne ,BufferedImage imageTwo,int x,int y,File outFile ){
		  try
	      {
			  BufferedImage ImageNew=modifyImageTogeterRBufferedImage(imageOne, imageTwo, x, y, outFile);
	        ImageIO.write(ImageNew, "png", outFile);//дͼƬ
	      }
	      catch(Exception e)
	      {
	        e.printStackTrace();
	      }
	}
	
	/**
	 * �ϲ�����ͼƬ
	 * */
	public static BufferedImage modifyImageTogeterRBufferedImage (BufferedImage imageOne ,BufferedImage imageTwo,int x,int y,File outFile ){
		BufferedImage ImageNew=null;
		try
	      {
	
	        int width = imageOne.getWidth();//ͼƬ���
	        int height = imageOne.getHeight();//ͼƬ�߶�
	        //��ͼƬ�ж�ȡRGB
	        int[] ImageArrayOne = new int[width*height];
	        ImageArrayOne = imageOne.getRGB(0,0,width,height,ImageArrayOne,0,width);

	        int widthTwo = imageTwo.getWidth();//ͼƬ���
	        int heightTwo = imageTwo.getHeight();//ͼƬ�߶�
	        int[] ImageArrayTwo = new int[widthTwo*heightTwo];
	        ImageArrayTwo = imageTwo.getRGB(0,0,widthTwo,heightTwo,ImageArrayTwo,0,widthTwo);
	        
	        //������ͼƬ
	        
	        ImageNew = new BufferedImage(width,height,BufferedImage.TYPE_4BYTE_ABGR);
	        ImageNew.setRGB(0,0,width,height,ImageArrayOne,0,width);//���õ�ͼ
	        ImageNew.setRGB(x,y,widthTwo,heightTwo,ImageArrayTwo,0,widthTwo);//��ͼƬ���ڵ�ͼ��
	        
	        //����ͼƬΪ͸��
	        int alpha = 0; 
	        for (int j1 = ImageNew.getMinY(); j1 < ImageNew 
	        	      .getHeight(); j1++) { 
	        	     for (int j2 = ImageNew.getMinX(); j2 < ImageNew 
	        	       .getWidth(); j2++) { 
	        	      int rgb = ImageNew.getRGB(j2, j1); 
	        	    
	        	      int R =(rgb & 0xff0000 ) >> 16 ; 
	        	      int G= (rgb & 0xff00 ) >> 8 ; 
	        	      int B= (rgb & 0xff ); 
	        	      if(((255-R)<30) && ((255-G)<30) && ((255-B)<30)){ 
	        	      rgb = ((alpha + 1) << 24) | (rgb & 0x00ffffff); 
	        	      } 
	        	      ImageNew.setRGB(j2, j1, rgb); 
	        	     }
	        }
	
	        Graphics2D g2D = (Graphics2D) ImageNew.getGraphics(); 
	        g2D.drawImage(ImageNew, 0, 0, null ); 
	        return ImageNew;
	      }
	      catch(Exception e)
	      {
	        e.printStackTrace();
	      }
		return ImageNew;
	}
	
	public static Image getFrameIcon(){
		InputStream in=ClassLoader.getSystemResourceAsStream("resource/0.png");  
        BufferedImage image = null;
		try {
			image = ImageIO.read(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}
	
	
}
