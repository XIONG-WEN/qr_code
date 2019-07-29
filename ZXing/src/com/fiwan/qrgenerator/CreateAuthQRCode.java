package com.fiwan.qrgenerator;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.fiwan.utils.Utils;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * 生成IDcode认证二维码类
 * */
public class CreateAuthQRCode {
	public static int authSize;
	static File file;
	int x;
	int y;
	int size;
	CreateAuthQRCode(int authSize,File file){
		CreateAuthQRCode.authSize=authSize;
		this.file=file;
	}

	/**
	 * 生成IDcode认证二维码
	 * @param content 生成内容
	 * @param size 码大小
	 * @param errorCorrectionLevel 容错率
	 * 
	 * */
	public  void create(String content,int size,ErrorCorrectionLevel errorCorrectionLevel) throws IOException, WriterException{
		pub(size);
		InputStream in=ClassLoader.getSystemResourceAsStream("resource/"+authSize+".png");  
        BufferedImage imageOne = ImageIO.read(in);
        BitMatrix matrix=ZxingHandler.GetBitMatrix(content, this.size, errorCorrectionLevel,0);
        BufferedImage imageTwo= ZxingHandler.toBufferedImageWithAuthColor(matrix);
        Utils.modifyImageTogeter(imageOne, imageTwo, x, y,file); 
	}
	
	/**
	 * 生成IDcode认证二维码
	 * @param content 生成内容
	 * @param size 码大小
	 * @param errorCorrectionLevel 容错率
	 * @return BufferedImage 返回生成我IDcode认证码
	 * */
	public  BufferedImage createBufferedImage(String content,int size,ErrorCorrectionLevel errorCorrectionLevel) {
		pub(size);
		System.out.println(authSize);
		InputStream in=ClassLoader.getSystemResourceAsStream("resource/"+authSize+".png");  
		BitMatrix matrix=null;
        BufferedImage imageOne=null;;
		try {
			imageOne = ImageIO.read(in);
			 matrix =ZxingHandler.GetBitMatrix(content, this.size, errorCorrectionLevel,0);
		} catch (IOException e) {
		e.printStackTrace();
		} catch (WriterException e) {
			e.printStackTrace();
		}
        BufferedImage imageTwo= ZxingHandler.toBufferedImageWithAuthColor(matrix);
       return Utils.modifyImageTogeterRBufferedImage(imageOne, imageTwo, x, y,file);
	}
	
	private void pub (int size){
		switch (size) {
		case 200:
			this.size=104;
			x=47;
			y=48;
			break;
		case 300:
			this.size=150;
			x=76;
			y=77;
			break;
		case 500:
			this.size=245;
			x=126;
			y=127;
			break;
		case 600:
			this.size=294;
			x=153;
			y=154;
			break;
		case 800:
			this.size=398;
			x=200;
			y=202;
			break;
		}
	}
}
