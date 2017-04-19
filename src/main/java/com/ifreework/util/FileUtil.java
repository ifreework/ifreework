package com.ifreework.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import com.ifreework.entity.system.Config;

/**
 * @描述： 文件操作工具类，包涵读取文件大小，文件上传、下载、删除等
 * 
 * @author：wangyh qq735789026
 * @创建时间：2016年7月2日 下午3:50:24
 * @修改人：wangyh
 * @修改时间：2016年7月2日 下午3:50:24 @修改备注：
 * @version 1.0
 */
public class FileUtil {
	
	private static Logger log = Logger.getLogger(FileUtil.class);
	public static String getClassPath(){
		String rootPath=FileUtil.class.getResource("/").getFile().toString();  
		return rootPath;
	}
	
	
	public static String getRootPath(){
		String rootPath=FileUtil.class.getResource("/").getFile().toString();  
		rootPath = rootPath.substring(0, rootPath.indexOf("WEB-INF"));
		return rootPath;
	}
	

	/**
	 * @Title: getFilesize
	 * @Description: TODO(获取文件大小，返回kb,数据保留三位小数，没有文件时返回0)
	 * @param 文件
	 * @return Double
	 * @throws 
	 * @author:wangyh
	 */
	public static Double getFilesize(File file) {
		return Double.valueOf(file.length()) / 1024.000;
	}

	/**
	 * @Title: toByteArray
	 * @Description: TODO(将文件读取到字节流中)
	 * @param 文件路径
	 * @return byte[]
	 * @throws IOException
	 */
	public static byte[] toByteArray(File file) throws IOException {

		if (!file.exists()) {
			throw new FileNotFoundException(file.getPath());
		}

		ByteArrayOutputStream bos = new ByteArrayOutputStream((int) file.length());
		BufferedInputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(file));
			int buf_size = 1024;
			byte[] buffer = new byte[buf_size];
			int len = 0;
			while (-1 != (len = in.read(buffer, 0, buf_size))) {
				bos.write(buffer, 0, len);
			}
			return bos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			bos.close();
		}
	}

	/**
	 * @Title: BigFiletoByteArray
	 * @Description: TODO(大文件进行处理的时候，可提高处理速度)
	 * @param 文件路径
	 * @return byte[]
	 * @throws IOException
	 */
	public static byte[] BigFiletoByteArray(File file) throws IOException {

		if (!file.exists()) {
			throw new FileNotFoundException(file.getPath());
		}

		FileChannel fc = null;
		RandomAccessFile rf = null;
		try {
			rf = new RandomAccessFile(file.getPath(), "r");
			fc = rf.getChannel();
			MappedByteBuffer byteBuffer = fc.map(MapMode.READ_ONLY, 0, fc.size()).load();
			byte[] result = new byte[(int) fc.size()];
			if (byteBuffer.remaining() > 0) {
				byteBuffer.get(result, 0, byteBuffer.remaining());
			}
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				rf.close();
				fc.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @Title: fileUpload 
	 * @Description: TODO(文件上传) 
	 * @param file : 将要上传的文件
	 * @param
	 *         filePath ：文件保存路径
	 * @param fileName ： 文件保存名称 
	 * @return String
	 *         新保存的文件名，文件最终保存路径为filePath + fileName @throws
	 */
	public static String fileUpload(MultipartFile file, String filePath) {
		String fileName = UUID.randomUUID().toString().replace("-",""); // 扩展名格式：
		try {
			if (file.getOriginalFilename().lastIndexOf(".") >= 0) {
				fileName += file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			}
			if("1".equals(Config.init().get(Config.FTP_ENABLE))){
				FTPUtil.upload(Config.init().get(Config.FILE_PATH), Integer.parseInt(Config.init().get(Config.FTP_PORT)), Config.init().get(Config.FTP_USERNAME), Config.init().get(Config.FTP_PASSWORD), fileName, filePath, file.getInputStream());
			}else{
				filePath += Config.init().get(Config.FILE_PATH);
				copyFile(file.getInputStream(), filePath, fileName);
			}
		} catch (IOException e) {
			System.out.println(e);
		}
		return filePath + "/" + fileName;
	}
	
	/**
	 * @Title: fileUpload 
	 * @Description: TODO(文件上传) 
	 * @param file : 将要上传的文件
	 * @param
	 *         filePath ：文件保存路径
	 * @param fileName ： 文件保存名称 
	 * @return String
	 *         新保存的文件名，文件最终保存路径为filePath + fileName @throws
	 */
	public static String fileUpload(String filePath, String savePath) {
		String fileName = UUID.randomUUID().toString().replace("-",""); // 扩展名格式：
		try {
			if (filePath.lastIndexOf(".") >= 0) {
				fileName += filePath.substring(filePath.lastIndexOf("."));
			}
			File file = new File(filePath);
			InputStream in = new FileInputStream(file);
			if("1".equals(Config.init().get(Config.FTP_ENABLE))){
				FTPUtil.upload(Config.init().get(Config.FILE_PATH), Integer.parseInt(Config.init().get(Config.FTP_PORT)), Config.init().get(Config.FTP_USERNAME), Config.init().get(Config.FTP_PASSWORD), fileName, savePath, in);
			}else{
				savePath += Config.init().get(Config.FILE_PATH);
				copyFile(in, savePath, fileName);
			}
			return savePath + "/" + fileName;
		} catch (IOException e) {
			e.printStackTrace();
			log.error(e);
		}
		return null;
	}

	/**
	 * @Title: copyFile @Description: TODO(文件复制) @param @return @throws
	 */
	private static void copyFile(InputStream in, String dir, String realName) throws IOException {
		File file = mkdirsmy(dir, realName);
		FileUtils.copyInputStreamToFile(in, file);
	}

	/**
	 * 判断路径是否存在，否：创建此路径
	 * 
	 * @param dir
	 *            文件路径
	 * @param realName
	 *            文件名
	 * @throws IOException
	 */
	public static File mkdirsmy(String dir, String realName) throws IOException {
		File file = new File(dir, realName);
		if (!file.exists()) {
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			file.createNewFile();
		}
		return file;
	}

	/**
	 * 判断路径是否存在，否：创建此路径
	 * 
	 * @param dir
	 *            文件路径
	 * @param realName
	 *            文件名
	 * @throws IOException
	 */
	public static File mkdirsmy(String file) throws IOException {
		File f = new File(file);
		return mkdirsmy(f);
	}

	/**
	 * 判断路径是否存在，否：创建此路径
	 * 
	 * @param dir
	 *            文件路径
	 * @param realName
	 *            文件名
	 * @throws IOException
	 */
	public static File mkdirsmy(File file) throws IOException {
		if (!file.exists()) {
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			file.createNewFile();
		}
		return file;
	}

	/**
	 * @Title: getHtmlPicture
	 * @Description: TODO(下载网络图片上传到服务器上)
	 * @param httpUrl
	 *            图片网络地址
	 * @param filePath
	 *            图片保存路径
	 * @param myFileName
	 *            图片文件名(null时用网络图片原名)
	 * @return 返回图片名称
	 */
	public static String getHtmlPicture(String httpUrl, String filePath, String myFileName) {

		URL url; // 定义URL对象url
		BufferedInputStream in; // 定义输入字节缓冲流对象in
		FileOutputStream file; // 定义文件输出流对象file
		try {
			String fileName = null == myFileName ? httpUrl.substring(httpUrl.lastIndexOf("/")).replace("/", "")
					: myFileName; // 图片文件名(null时用网络图片原名)
			url = new URL(httpUrl); // 初始化url对象
			in = new BufferedInputStream(url.openStream()); // 初始化in对象，也就是获得url字节流
			file = new FileOutputStream(mkdirsmy(filePath, fileName));
			int t;
			while ((t = in.read()) != -1) {
				file.write(t);
			}
			file.close();
			in.close();
			return fileName;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param response
	 * @param filePath
	 *            //文件完整路径(包括文件名和扩展名)
	 * @param fileName
	 *            //下载后看到的文件名
	 * @return 文件名
	 */
	public static void fileDownload(final HttpServletResponse response, String filePath, String fileName)
			throws IOException {
		if("1".equals(Config.init().get(Config.FTP_ENABLE))){ // 如果ftp服务器已经启用，则先将文件下载到本地
			filePath = downLoadFormFTP(filePath, fileName);
		}
		
		File file = new File(filePath);
		if (!StringUtil.isEmpty(filePath) &&  file.exists()) {
			byte[] data = FileUtil.toByteArray(file);
			fileName = URLEncoder.encode(fileName, "UTF-8");
			response.reset();
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			response.addHeader("Content-Length", "" + data.length);
			response.setContentType("application/octet-stream;charset=UTF-8");
			OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
			outputStream.write(data);
			outputStream.flush();
			outputStream.close();
		} else {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter prw = response.getWriter();
			prw.print("文件" + fileName + "已被删除,文件下载失败！");
			prw.flush();
			prw.close();
		}
		response.flushBuffer();
	}


	private static String downLoadFormFTP(String filePath, String fileName) {
		String localPath = getRootPath() + "temp";
		File localFile = new File(localPath);
		if(!localFile.exists()){
			localFile.mkdirs();
		}
		if(FTPUtil.download(Config.init().get(Config.FILE_PATH), Integer.parseInt(Config.init().get(Config.FTP_PORT)), Config.init().get(Config.FTP_USERNAME), Config.init().get(Config.FTP_PASSWORD), filePath, localPath)){
			filePath = localPath + "/" + fileName;
			return filePath;
		}
		return "";
	}

	
	/**
	 * @param response
	 * @param filePath
	 *            //文件完整路径(包括文件名和扩展名)
	 * @return 文件名
	 * @throws IOException 
	 */
	public static void fileDownload(final HttpServletResponse response, String filePath) throws IOException {
		File file = new File(filePath);
		fileDownload(response, filePath, file.getName());
	}

	/**
	 * @Title: zip
	 * @Description: TODO(将文件夹压缩成zip文件)
	 * @param inputFileName
	 *            你要压缩的文件夹(整个完整路径)
	 * @param zipFileName
	 *            压缩后的文件(整个完整路径)
	 * @return Boolean
	 */
	public static Boolean zip(String inputFileName, String zipFileName) throws Exception {
		zip(zipFileName, new File(inputFileName));
		return true;
	}

	private static void zip(String zipFileName, File inputFile) throws Exception {
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileName));
		zip(out, inputFile, "");
		out.flush();
		out.close();
	}

	private static void zip(ZipOutputStream out, File f, String base) throws Exception {
		if (f.isDirectory()) {
			File[] fl = f.listFiles();
			out.putNextEntry(new ZipEntry(base + "/"));
			base = base.length() == 0 ? "" : base + "/";
			for (int i = 0; i < fl.length; i++) {
				zip(out, fl[i], base + fl[i].getName());
			}
		} else {
			out.putNextEntry(new ZipEntry(base));
			FileInputStream in = new FileInputStream(f);
			int b;
			// System.out.println(base);
			while ((b = in.read()) != -1) {
				out.write(b);
			}
			in.close();
		}
	}

	public static void main(String[] temp) {
		try {
			System.out.println(getRootPath());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}