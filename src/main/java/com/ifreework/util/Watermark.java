package com.ifreework.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
/**
 * 
 * 描述：   图片水印处理工具类
 * @author：wangyh qq735789026  
 * @创建时间：2016年7月4日 下午3:50:44    
 * @修改人：wangyh    
 * @修改时间：2016年7月4日 下午3:50:44    
 * @version 1.0
 */
public class Watermark {

	private static String strFWATERM, strIWATERM;

	static {
		strFWATERM = readTxtFile(Const.FWATERM); // 读取文字水印配置
		strIWATERM = readTxtFile(Const.IWATERM); // 读取图片水印配置
	}

	/**
	 * 刷新
	 */
	public static void fushValue() {
		strFWATERM = readTxtFile(Const.FWATERM); // 读取文字水印配置
		strIWATERM = readTxtFile(Const.IWATERM); // 读取图片水印配置
	}

	/**
	 * @param imagePath
	 *            图片全路径
	 */
	public static void setWatemark(String imagePath) {
		// 文字水印
		if (null != strFWATERM && !"".equals(strFWATERM)) {
			String strFW[] = strFWATERM.split(",fh,");
			if (strFW.length == 5) {
				if ("yes".equals(strFW[0])) {
					pressText(strFW[1].toString(), imagePath, "", 1, Color.RED, Integer.parseInt(strFW[2]),
							Integer.parseInt(strFW[3]), Integer.parseInt(strFW[4])); // 文字
				}
			}
		}
		// 图片水印
		if (null != strIWATERM && !"".equals(strIWATERM)) {
			String strIW[] = strIWATERM.split(",fh,");
			if (strIW.length == 4) {
				if ("yes".equals(strIW[0])) {
					// pressImage(PathUtil.getClasspath() + Const.FILEPATHIMG +
					// strIW[1], imagePath,
					// Integer.parseInt(strIW[2]), Integer.parseInt(strIW[3]));
				}
			}
		}
	}

	/**
	 * 把图片印刷到图片上
	 * 
	 * @param pressImg
	 *            -- 水印文件
	 * @param targetImg
	 *            -- 目标文件
	 * @param x
	 *            --x坐标
	 * @param y
	 *            --y坐标
	 */
	public final static void pressImage(String pressImg, String targetImg, int x, int y) {
		try {
			// 目标文件
			File _file = new File(targetImg);
			Image src = ImageIO.read(_file);
			int wideth = src.getWidth(null);
			int height = src.getHeight(null);
			BufferedImage image = new BufferedImage(wideth, height, BufferedImage.TYPE_INT_RGB);
			Graphics g = image.createGraphics();
			g.drawImage(src, 0, 0, wideth, height, null);

			// 水印文件
			File _filebiao = new File(pressImg);
			Image src_biao = ImageIO.read(_filebiao);
			int wideth_biao = src_biao.getWidth(null);
			int height_biao = src_biao.getHeight(null);
			// g.drawImage(src_biao, (wideth - wideth_biao) / 2,(height -
			// height_biao) / 2, wideth_biao, height_biao, null);
			g.drawImage(src_biao, x, y, wideth_biao, height_biao, null);
			// 水印文件结束
			g.dispose();
			FileOutputStream out = new FileOutputStream(targetImg);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			encoder.encode(image);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 打印文字水印图片
	 * 
	 * @param pressText
	 *            --文字
	 * @param targetImg
	 *            -- 目标图片
	 * @param fontName
	 *            -- 字体名
	 * @param fontStyle
	 *            -- 字体样式
	 * @param color
	 *            -- 字体颜色
	 * @param fontSize
	 *            -- 字体大小
	 * @param x
	 *            -- 偏移量
	 * @param y
	 */

	public static void pressText(String pressText, String targetImg, String fontName, int fontStyle, Color color,
			int fontSize, int x, int y) {
		try {
			File _file = new File(targetImg);
			Image src = ImageIO.read(_file);
			int wideth = src.getWidth(null);
			int height = src.getHeight(null);
			BufferedImage image = new BufferedImage(wideth, height, BufferedImage.TYPE_INT_RGB);
			Graphics g = image.createGraphics();
			g.drawImage(src, 0, 0, wideth, height, null);
			g.setColor(color);
			g.setFont(new Font(fontName, fontStyle, fontSize));
			g.drawString(pressText, x, y);
			g.dispose();
			FileOutputStream out = new FileOutputStream(targetImg);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			encoder.encode(image);
			out.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * 读取txt里的单行内容
	 * 
	 * @param filePath
	 *            文件路径
	 */
	public static String readTxtFile(String fileP) {
		try {

			String filePath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource("")) + "../../"; // 项目路径
			filePath = filePath.replaceAll("file:/", "");
			filePath = filePath.replaceAll("%20", " ");
			filePath = filePath.trim() + fileP.trim();
			if (filePath.indexOf(":") != 1) {
				filePath = File.separator + filePath;
			}
			String encoding = "utf-8";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding); // 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					return lineTxt;
				}
				read.close();
			} else {
				System.out.println("找不到指定的文件,查看此路径是否正确:" + filePath);
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
		}
		return "";
	}

}
