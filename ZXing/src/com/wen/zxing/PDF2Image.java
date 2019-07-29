package com.wen.zxing;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.util.ImageIOUtil;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

public class PDF2Image {

	public static void pdfToImage(String pdfUrl, String outPath) {
		try {
			// 读入PDF
			PdfReader pdfReader = new PdfReader(pdfUrl);
			// 计算PDF页码数
			int pageCount = pdfReader.getNumberOfPages();
			// 循环每个页码
			for (int i = pageCount; i >= pdfReader.getNumberOfPages(); i--) {
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				PdfStamper pdfStamper = null;
				PDDocument pdDocument = null;

				pdfReader = new PdfReader(pdfUrl);
				pdfReader.selectPages(String.valueOf(i));
				pdfStamper = new PdfStamper(pdfReader, out);
				pdfStamper.close();
				// 利用PdfBox生成图像
				pdDocument = PDDocument.load(new ByteArrayInputStream(out.toByteArray()));
				OutputStream outputStream = new FileOutputStream(outPath);

				ImageOutputStream output = ImageIO.createImageOutputStream(outputStream);
				PDPage page = (PDPage) pdDocument.getDocumentCatalog().getAllPages().get(0);
				BufferedImage image = page.convertToImage(BufferedImage.TYPE_INT_RGB, 96);
				ImageIOUtil.writeImage(image, "bmp", outputStream, 96);
				
				if (outputStream != null) {
					outputStream.close();
				}
				if (output != null) {
					output.flush();
					output.close();
				}
				pdDocument.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
