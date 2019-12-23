package com.wen.zxing;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

import com.fiwan.qrgenerator.Settings;
import com.fiwan.qrgenerator.ZxingHandler;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * 二维码生成和读的工具类
 * 
 * @author admin
 *
 */
public class QrCodeUtil {

	/**
	 * 生成包含字符串信息的二维码图片
	 * 
	 * @param outputStream
	 *            文件输出流路径
	 * @param content
	 *            二维码携带信息
	 * @param qrCodeSize
	 *            二维码图片大小
	 * @param imageFormat
	 *            二维码的格式
	 */
	public static boolean createQrCode(String filePath, String content, String title, int qrCodeSize, String imageFormat) {
		try {
			// 设置二维码纠错级别ＭＡＰ
			Hashtable hintMap = new Hashtable();
			hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L); // 矫错级别
			hintMap.put(EncodeHintType.MARGIN, 0);
			QRCodeWriter qrCodeWriter = new QRCodeWriter();
			// 创建比特矩阵(位矩阵)的QR码编码的字符串
			BitMatrix byteMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, qrCodeSize, qrCodeSize, hintMap);
			// 使BufferedImage勾画QRCode (matrixWidth 是行二维码像素点)
			int matrixWidth = byteMatrix.getWidth();
			int matrixHeight = byteMatrix.getHeight();
			BufferedImage image = new BufferedImage(matrixWidth, matrixHeight, BufferedImage.TYPE_INT_RGB);
			for (int x = 0; x < matrixWidth; x++) {
				for (int y = 0; y < matrixHeight; y++) {
					image.setRGB(x, y, byteMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
				}
			}

			Font font = new Font("微软雅黑粗体", Font.BOLD, 20);
			FontMetrics fm = new JLabel().getFontMetrics(font);
			int fontHeight = fm.getHeight();
			int fontWidth = fm.stringWidth(title);

			BufferedImage out = new BufferedImage(matrixWidth, matrixHeight + fontHeight, BufferedImage.TYPE_INT_RGB);
			for (int x = 0; x < matrixWidth; x++) {
				for (int y = 0; y < fontHeight; y++) {
					out.setRGB(x, y, 0xFFFFFFFF);
				}
			}

			Graphics graphics = out.createGraphics();

			graphics.drawImage(image, 0, fontHeight, matrixWidth, matrixHeight, null);

			graphics.setFont(font);
			graphics.setColor(Color.BLACK);
			graphics.drawString(title, (matrixWidth - fontWidth) / 2, fontHeight);

			graphics.dispose();

			OutputStream outputStream = new FileOutputStream(new File(filePath));
			return ImageIO.write(out, imageFormat, outputStream);
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 生成包含字符串信息的二维码图片
	 * 
	 * @param outputStream
	 *            文件输出流路径
	 * @param content
	 *            二维码携带信息
	 * @param qrCodeSize
	 *            二维码图片大小
	 */
	// public static boolean createQrCodePdf(String filePath, String content,
	// String title, int qrCodeSize) {
	// try {
	// // 设置二维码纠错级别ＭＡＰ
	// Hashtable hintMap = new Hashtable();
	// hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L); //
	// 矫错级别
	// hintMap.put(EncodeHintType.MARGIN, 0);
	// QRCodeWriter qrCodeWriter = new QRCodeWriter();
	// // 创建比特矩阵(位矩阵)的QR码编码的字符串
	// BitMatrix byteMatrix = qrCodeWriter.encode(content,
	// BarcodeFormat.QR_CODE, qrCodeSize, qrCodeSize, hintMap);
	// // 使BufferedImage勾画QRCode (matrixWidth 是行二维码像素点)
	// int matrixWidth = byteMatrix.getWidth();
	// int matrixHeight = byteMatrix.getHeight();
	// BufferedImage image = new BufferedImage(matrixWidth, matrixHeight,
	// BufferedImage.TYPE_INT_RGB);
	// for (int x = 0; x < matrixWidth; x++) {
	// for (int y = 0; y < matrixHeight; y++) {
	// image.setRGB(x, y, byteMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
	// }
	// }
	//
	// PageSize size = PageSize.A4;
	//
	// Font font = new Font("微软雅黑粗体", Font.BOLD, 16);
	// FontMetrics fm = new JLabel().getFontMetrics(font);
	// int fontHeight = fm.getHeight();
	// int fontWidth = fm.stringWidth(title);
	//
	// Graphics2D graphics = new VectorGraphics2D();
	//
	// int imageLeft = ((int) size.getWidth() - matrixWidth) / 2;
	// graphics.drawImage(image, imageLeft, fontHeight, matrixWidth,
	// matrixHeight, null);
	//
	// int fontLeft = ((int) size.getWidth() - fontWidth) / 2 - 5;
	// graphics.setFont(font);
	// graphics.setColor(Color.BLACK);
	// graphics.drawString(title, fontLeft, fontHeight);
	//
	// CommandSequence commands = ((VectorGraphics2D) graphics).getCommands();
	// PDFProcessor pdfProcessor = new PDFProcessor(true);
	// Document doc = pdfProcessor.getDocument(commands, size);
	// doc.writeTo(new FileOutputStream(new File(filePath)));
	// return true;
	// } catch (Exception e) {
	// return false;
	// }
	// }

	/**
	 * 生成包含字符串信息的二维码图片
	 * 
	 * @param outputStream
	 *            文件输出流路径
	 * @param content
	 *            二维码携带信息
	 * @param qrCodeSize
	 *            二维码图片大小
	 */
	// public static boolean createQrCodeSvg(String filePath, String content,
	// String title, int qrCodeSize) {
	// try {
	// // 设置二维码纠错级别ＭＡＰ
	// Hashtable hintMap = new Hashtable();
	// hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L); //
	// 矫错级别
	// hintMap.put(EncodeHintType.MARGIN, 0);
	// QRCodeWriter qrCodeWriter = new QRCodeWriter();
	// // 创建比特矩阵(位矩阵)的QR码编码的字符串
	// BitMatrix byteMatrix = qrCodeWriter.encode(content,
	// BarcodeFormat.QR_CODE, qrCodeSize, qrCodeSize, hintMap);
	// // 使BufferedImage勾画QRCode (matrixWidth 是行二维码像素点)
	// int matrixWidth = byteMatrix.getWidth();
	// int matrixHeight = byteMatrix.getHeight();
	// BufferedImage image = new BufferedImage(matrixWidth, matrixHeight,
	// BufferedImage.TYPE_INT_RGB);
	// for (int x = 0; x < matrixWidth; x++) {
	// for (int y = 0; y < matrixHeight; y++) {
	// image.setRGB(x, y, byteMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
	// }
	// }
	//
	// PageSize size = PageSize.A4;
	//
	// Font font = new Font("微软雅黑粗体", Font.BOLD, 16);
	// FontMetrics fm = new JLabel().getFontMetrics(font);
	// int fontHeight = fm.getHeight();
	// int fontWidth = fm.stringWidth(title);
	//
	// Graphics2D graphics = new VectorGraphics2D();
	//
	// int imageLeft = ((int) size.getWidth() - matrixWidth) / 2;
	// graphics.drawImage(image, imageLeft, fontHeight, matrixWidth,
	// matrixHeight, null);
	//
	// int fontLeft = ((int) size.getWidth() - fontWidth) / 2 - 5;
	// graphics.setFont(font);
	// graphics.setColor(Color.BLACK);
	// graphics.drawString(title, fontLeft, fontHeight);
	//
	// CommandSequence commands = ((VectorGraphics2D) graphics).getCommands();
	// SVGProcessor svgProcessor = new SVGProcessor();
	// Document doc = svgProcessor.getDocument(commands, size);
	// doc.writeTo(new FileOutputStream(new File(filePath)));
	// return true;
	// } catch (Exception e) {
	// return false;
	// }
	// }

	/**
	 * 读二维码并输出携带的信息
	 */
	public static String readQrCode(InputStream inputStream) {
		try {
			// 从输入流中获取字符串信息
			BufferedImage image = ImageIO.read(inputStream);
			// 将图像转换为二进制位图源
			LuminanceSource source = new BufferedImageLuminanceSource(image);
			BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
			QRCodeReader reader = new QRCodeReader();
			return reader.decode(bitmap).getText();
		} catch (Exception e) {
			return "";
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
			}
		}
	}

	/**
	 * 查看系统支持的字体
	 */
	public static void printFont() {
		GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment(); //返回本地 GraphicsEnvironment 。
	    String [] forName = e.getAvailableFontFamilyNames(); //返回包含在此所有字体系列名称的数组， GraphicsEnvironment本地化为默认的语言环境，如返回 Locale.getDefault() 。 
	    for (int i = 0; i < forName.length; i++) {
	        System.out.println(forName[i]);
	    }
	}
	
	/**
	 * 测试代码
	 * 
	 * @throws IOException
	 * 
	 * @throws WriterException
	 */
	public static void main(String[] args) {
		// bikeCode(); //bike二维码生成器
//		PAHWCode();
		YJCode();
	}

	private static void bikeCode() {
		try {
			// 创建
			createBikeCode();
			// 检查
			checkBikeCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void createBikeCode() throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(new File("D:\\code\\bikeCode\\bikeCode.txt")));
		String url;
		while ((url = br.readLine()) != null) {
			String id = url.substring(url.lastIndexOf("/") + 1);
			String path = "D:\\code\\bikeCode\\qrcode\\" + id + ".pdf";
			ZxingHandler.createPDFQRCode(Settings.getInstance(), new File(path), url, id);
		}
		br.close();
	}

	private static void checkBikeCode() throws Exception {
		File file = new File("D:\\code\\bikeCode\\qrcode");
		File[] listFiles = file.listFiles();
		int success = 0;
		int fail = 0;
		for (File item : listFiles) {
			String fileName = item.getName();
			String localId = fileName.replace(".pdf", "");

			File out = new File("D:\\code\\bikeCode\\check", localId + ".bmp");
			if (out.exists()) {
				out.delete();
			}
			out.createNewFile();
			PDF2Image.pdfToImage(item.getAbsolutePath(), out.getAbsolutePath());

			String readQrCode = readQrCode(new FileInputStream(out));
			String readId = readQrCode.substring(readQrCode.lastIndexOf("/") + 1);
			if (localId.equals(readId)) {
				success++;
				out.delete();
			} else {
				fail++;
				System.out.println("id不一致:(" + localId + "), (" + readId + ")");
			}
		}
		System.out.println("检查完毕");
		System.out.println("总数量:" + listFiles.length + ", 成功:" + success + ", 失败:" + fail);
	}
	
	private static void PAHWCode() {
		try {
			// 创建
			createPAHWCode();
			// 检查
			checkPAHWCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void createPAHWCode() throws Exception {
		for(int i = 1; i <= 200; i++) {
			String url;
			if (i < 10) {
				url = "BU6ZMJ0000" + i;
			} else if (i < 100) {
				url = "BU6ZMJ000" + i;
			} else {
				url = "BU6ZMJ00" + i;
			}
			String path = "D:\\code\\PAHWCode\\qrcode\\" + url + ".pdf";
			ZxingHandler.createPDFQRCodeForPAHW(new File(path), url, url);
		}
		
		/*BufferedReader br = new BufferedReader(new FileReader(new File("D:\\code\\PAHWCode\\code.txt")));
		String url;
		while ((url = br.readLine()) != null) {
			String path = "D:\\code\\PAHWCode\\qrcode\\" + url + ".pdf";
			ZxingHandler.createPDFQRCodeForPAHW(new File(path), url, url);
		}
		br.close();*/
		
//		String url = "CIS021018";
//		String url = "BMD011005";
//		String path = "D:\\code\\PAHWCode\\qrcode\\" + url + ".pdf";
//		ZxingHandler.createPDFQRCodeForPAHW(new File(path), url, url);
	}
	
	private static void checkPAHWCode() throws Exception {
		File file = new File("D:\\code\\PAHWCode\\qrcode");
		File[] listFiles = file.listFiles();
		int success = 0;
		int fail = 0;
		for (File item : listFiles) {
			String fileName = item.getName();
			String localId = fileName.replace(".pdf", "");

			File out = new File("D:\\code\\PAHWCode\\check", localId + ".bmp");
			if (out.exists()) {
				out.delete();
			}
			out.createNewFile();
			PDF2Image.pdfToImage(item.getAbsolutePath(), out.getAbsolutePath());

			String readQrCode = readQrCode(new FileInputStream(out));
			String readId = readQrCode.substring(readQrCode.lastIndexOf("/") + 1);
			if (localId.equals(readId)) {
				success++;
				out.delete();
			} else {
				fail++;
				System.out.println("id不一致:(" + localId + "), (" + readId + ")");
			}
		}
		System.out.println("检查完毕");
		System.out.println("总数量:" + listFiles.length + ", 成功:" + success + ", 失败:" + fail);
	}
	
	private static void YJCode() {
		try {
			String baseDir = "D:\\code\\YJCode";
			new File(baseDir, "qrcode").mkdirs();
			new File(baseDir, "check").mkdirs();
			// 创建
			createYJCode(baseDir);
			// 检查
			checkCode(baseDir);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void createYJCode(String baseDir) throws Exception {
		for(int i = 1; i <= 400; i++) {
			String url;
			if (i < 10) {
				url = "YJ00000" + i;
			} else if (i < 100) {
				url = "YJ0000" + i;
			} else {
				url = "YJ000" + i;
			}
			ZxingHandler.createPDFQRCodeForPAHW(new File(baseDir, "qrcode\\" + url + ".pdf"), url, url);
		}
	}
	
	private static void checkCode(String baseDir) throws Exception {
		File file = new File(baseDir, "qrcode");
		File[] listFiles = file.listFiles();
		int success = 0;
		int fail = 0;
		for (File item : listFiles) {
			String fileName = item.getName();
			String localId = fileName.replace(".pdf", "");

			File out = new File(baseDir, "check\\" + localId + ".bmp");
			if (out.exists()) {
				out.delete();
			}
			out.createNewFile();
			PDF2Image.pdfToImage(item.getAbsolutePath(), out.getAbsolutePath());

			String readQrCode = readQrCode(new FileInputStream(out));
			String readId = readQrCode.substring(readQrCode.lastIndexOf("/") + 1);
			if (localId.equals(readId)) {
				success++;
				out.delete();
			} else {
				fail++;
				System.out.println("id不一致:(" + localId + "), (" + readId + ")");
			}
		}
		System.out.println("检查完毕");
		System.out.println("总数量:" + listFiles.length + ", 成功:" + success + ", 失败:" + fail);
	}
	
}
