package com.fiwan.qrgenerator;

import java.awt.Font;

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * ����������� ʹ�õ�������һ�������ࡣ
 */
public class Settings {
	private Settings() {
	}

	private static final Settings single = new Settings();

	public static Settings getInstance() {
		return single;
	}

	// ���ö�ά�����ɫ Ĭ���Ǻ�ɫ
	private int qrcodeColor = 1; // 0 ��ɫ 1 �ڰ�
	// ���ö�ά��ĸ�ʽ Ĭ����png
	private String qrcodeFiletype = "png";
	// ���ö�ά��Ĵ�С Ĭ����300px
	private int qrcodeSize = 35;
	// ÿ���С
	private float blockSize = 1.34f;
	// ���ö�ά����ݴ��� Ĭ����M
	private ErrorCorrectionLevel qrcodeErrorRate = ErrorCorrectionLevel.L;
	// ��������
	private Font font = new Font("΢���ź�", Font.PLAIN, 12);

	public int getQrcodeColor() {
		return qrcodeColor;
	}

	public void setQrcodeColor(int qrcodeColor) {
		this.qrcodeColor = qrcodeColor;
	}

	public String getQrcodeFiletype() {
		return qrcodeFiletype;
	}

	public void setQrcodeFiletype(String qrcodeFiletype) {
		this.qrcodeFiletype = qrcodeFiletype;
	}

	public int getQrcodeSize() {
		return qrcodeSize;
	}

	public void setQrcodeSize(int qrcodeSize) {
		this.qrcodeSize = qrcodeSize;
	}

	public float getBlockSize() {
		return blockSize;
	}

	public void setBlockSize(float blockSize) {
		this.blockSize = blockSize;
	}

	public ErrorCorrectionLevel getQrcodeErrorRate() {
		return qrcodeErrorRate;
	}

	public void setQrcodeErrorRate(ErrorCorrectionLevel qrcodeErrorRate) {
		this.qrcodeErrorRate = qrcodeErrorRate;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

}
