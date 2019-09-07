package com.javapai.framework.utils;

import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <br>
 * 依赖apache-commons-io.jar
 * <br>
 * @author pooja
 *
 */
public final class UtilFile {
	private static final Logger log = LoggerFactory.getLogger(UtilFile.class);
	/**
	 * 
	 * @return 系统临时目录。
	 */
	public static String getTempPath() {
		if ("".equals("linux")) {
			return "";
		} else if ("".equals("win")) {
			return "";
		} else {
			return "";
		}
	}
	
	public static String getTempFolder() {
		return System.getProperty("java.io.tmpdir");
	}

	/**
	 * 写入指定内容到指定文件。<br>
	 * 
	 * @param fileName
	 *            文件名
	 * @param content
	 *            写入内容
	 * @return
	 */
	public static String addContentToFile(String fileName, String content) {
		/*method One*/
		try {
			// 打开一个随机访问文件流，按读写方式
			RandomAccessFile randomFile = new RandomAccessFile(fileName, "rw");
			// 文件长度，字节数
			long fileLength = randomFile.length();
			// 将写文件指针移到文件尾。
			randomFile.seek(fileLength);
			randomFile.writeBytes(content);
			randomFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		/*method Two*/
		try {
			//这种好像这快些
//			BufferedWriter writer = new BufferedWriter(new FileWriter("fileName", true)); or
//			BufferedWriter writer = new BufferedWriter(new FileWriter(new File("c:\\Result.txt"), true));
//			writer.write("\"101\",\"英语\",\"english\",\"100001\"\r\n");
//		    writer.close();
			
			// 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
			FileWriter writer = new FileWriter(fileName, true);
			writer.write(content);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// error processing code
			System.out.println("the content[" + content + "] write to file!");
		}
		
		return fileName;
	}
	
	public void addContentToFile(File file, String message) throws IOException {
		FileOutputStream fos = new FileOutputStream(file);
		OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");

		if (message != null && message.length() >= 0) {
//			byte abyte[] = message.getBytes();
//			dos = new DataOutputStream(new FileOutputStream(path));
//			dos.write(abyte, 0, abyte.length);
//			dos.flush();
		}

		osw.write("-----by admin!---------\n");
		osw.write(message);

		osw.close();
		fos.close();
	}
	
	public static String getFileContent(String fileName) {
		
		BufferedReader reader = null;
		StringBuilder fileContent = new StringBuilder();
		try {
			File f = new File(fileName);

			reader = new BufferedReader(new FileReader(f));
			String line = "";
			while ((line = reader.readLine()) != null) {
				fileContent.append(line);
				fileContent.append("\n"); 
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
					reader = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return fileContent.toString();
	}
	
	public static String getFileContent(InputStream is) {

		BufferedReader reader = null;
		StringBuilder fileContent = new StringBuilder();
		try {
			reader = new BufferedReader(new InputStreamReader(is));
			String line = "";
			while ((line = reader.readLine()) != null) {
				fileContent.append(line);
				fileContent.append("\n"); 
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
					reader = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return fileContent.toString();

	}

	/**
	 * 快速复制Java文件(使用NIO)
	 * 
	 * @param in
	 * @param out
	 * @throws IOException
	 */
	public static void fileCopy(File in, File out) throws IOException {
		//文件复制
//		org.apache.commons.io.FileUtils.copyInputStreamToFile(in, file);
		
		FileChannel inChannel = new FileInputStream(in).getChannel();
		FileChannel outChannel = new FileOutputStream(out).getChannel();

		try {
			// original apparently has trouble copying large files on Windows
			// magic number for Windows, 64Mb - 32Kb)
			int maxCount = (64 * 1024 * 1024) - (32 * 1024);
			long size = inChannel.size();
			long position = 0;

			while (position < size) {
				position += inChannel.transferTo(position, maxCount, outChannel);
			}

		} finally {

			if (inChannel != null) {
				inChannel.close();
			}

			if (outChannel != null) {
				outChannel.close();
			}
		}
	}
	
	public static void copyFile(String oldPath, String newPath) {
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { // 文件存在时
				InputStream inStream = new FileInputStream(oldPath); // 读入原文件
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; // 字节数文件大小
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
			}
		} catch (Exception e) {
			System.out.println("copy file error!");
			e.printStackTrace();
		}
	}

	/**
	 * 将指定文件内容写入到指定路径下.<br>
	 * 
	 * @param file
	 *            原文件
	 * @param targetFilePath
	 *            保存路径
	 * @return 错误信息，默认为空
	 */
	public static boolean copyFileToFile(File file, String targetFilePath) {
		try {
			File targetFile = new File(targetFilePath);
			return writeToFile(new FileInputStream(file), targetFile);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			return false;
		}
	}
	
	/**
	 * 将指定的数据流写入到指定文件中.<br>
	 * 
	 * @param inStream
	 *            数据流.<br>
	 * @param targetFile
	 *            目标文件.<br>
	 * @return
	 */
	public static boolean writeToFile(InputStream inStream, File targetFile) {
		FileOutputStream out = null;
		try {
			byte[] bs = IOUtils.toByteArray(inStream);
			out = new FileOutputStream(targetFile, true);
			// 拷贝数据到新文件
			IOUtils.write(bs, out);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (null != inStream) {
				try {
					inStream.close();
					inStream = null;
				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}
			}
			if (null != out) {
				try {
					out.close();
					out = null;
				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * 以字节为单位读取文件，常用于读二进制文件，如图片、声音、影像等文件。
	 * 
	 * @param fileName
	 */
	public static void readFileByBytes(String fileName) {

		File file = new File(fileName);
		InputStream in = null;

		try {
			System.out.println("以字节为单位读取文件内容，一次读一个字节：");
			// 一次读一个字节
			in = new FileInputStream(file);
			int tempbyte;
			while ((tempbyte = in.read()) != -1) {
				System.out.write(tempbyte);
			}
			in.close();

			System.out.println("以字节为单位读取文件内容，一次读多个字节：");
			// 一次读多个字节
			byte[] tempbytes = new byte[100];
			int byteread = 0;
			in = new FileInputStream(fileName);
			System.out.println("当前输入流中的字节数为:" + in.available());
			// 读入多个字节到字节数组中，byteread为一次读入的字节数
			while ((byteread = in.read(tempbytes)) != -1) {
				System.out.write(tempbytes, 0, byteread);
			}
			in.close();

		} catch (IOException e) {
			e.printStackTrace();
			return;

		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e1) {
				}
			}
		}

	}

	/**
	 * 以字符为单位读取文件，常用于读文本，数字等类型的文件
	 * 
	 * @param fileName
	 * @throws IOException
	 */
	public static void readFileByChars(String fileName) throws IOException {
		File file = new File(fileName);
		System.out.println("以字符为单位读取文件内容，一次读一个字节：");
		// 一次读一个字符
		Reader reader = new InputStreamReader(new FileInputStream(file));
		int tempchar;
		while ((tempchar = reader.read()) != -1) {
			// 对于windows下，\r\n这两个字符在一起时，表示一个换行。
			// 但如果这两个字符分开显示时，会换两次行。
			// 因此，屏蔽掉\r，或者屏蔽\n。否则，将会多出很多空行。
			if (((char) tempchar) != '\r') {
				System.out.print((char) tempchar);
			}
		}
		reader.close();

		System.out.println("以字符为单位读取文件内容，一次读多个字节：");
		// 一次读多个字符
		char[] tempchars = new char[30];
		int charread = 0;
		reader = new InputStreamReader(new FileInputStream(fileName));
		// 读入多个字符到字符数组中，charread为一次读取字符数
		while ((charread = reader.read(tempchars)) != -1) {
			// 同样屏蔽掉\r不显示
			if ((charread == tempchars.length)
					&& (tempchars[tempchars.length - 1] != '\r')) {
				System.out.print(tempchars);
			} else {
				for (int i = 0; i < charread; i++) {
					if (tempchars[i] == '\r') {
						continue;
					} else {
						System.out.print(tempchars[i]);
					}
				}
			}
		}

		if (reader != null) {
			reader.close();
		}

	}

	/**
	 * 以行为单位读取文件内容，常用于读面向行的格式化文件
	 * 
	 * @param fileName
	 * @throws IOException
	 */
	public static void readFileByLines(String fileName) throws IOException {
		int line = 1;
		String tempString = null;

		File file = new File(fileName);
		BufferedReader reader = new BufferedReader(new FileReader(file));

		// 一次读入一行，直到读入null为文件结束
		while ((tempString = reader.readLine()) != null) {
			System.out.println("View the in line " + line + ",\t content is: "
					+ tempString);
			line++;
		}
		reader.close();

		if (reader != null) {
			reader.close();
		}
	}

	/**
	 * 随机读取文件内容
	 * 
	 * @param fileName
	 */
	public static void readFileByRandomAccess(String fileName) {
		RandomAccessFile randomFile = null;
		try {
			System.out.println("随机读取一段文件内容：");
			// 打开一个随机访问文件流，按只读方式
			randomFile = new RandomAccessFile(fileName, "r");
			// 文件长度，字节数
			long fileLength = randomFile.length();
			// 读文件的起始位置
			int beginIndex = (fileLength > 4) ? 4 : 0;
			// 将读文件的开始位置移到beginIndex位置。
			randomFile.seek(beginIndex);
			byte[] bytes = new byte[10];
			int byteread = 0;
			// 一次读10个字节，如果文件内容不足10个字节，则读剩下的字节。
			// 将一次读取的字节数赋给byteread
			while ((byteread = randomFile.read(bytes)) != -1) {
				System.out.write(bytes, 0, byteread);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (randomFile != null) {
				try {
					randomFile.close();
				} catch (IOException e1) {
				}
			}
		}
	}

	/**
	 * 取得文件目录下面所有文件。 <br>
	 * 
	 * @param fileDir 文件目录.
	 * @return
	 */
	public static List<File> getFileOrDirectory(File fileDir) {
		List<File> fileList = new ArrayList<File>();
		
		if (!fileDir.isDirectory()) {
			String[] filearray = fileDir.list();
			for (String string : filearray) {
				File nFile = new File(string);
				getFileOrDirectory(nFile);
			}
		} else {
			log.info("name=" + fileDir.getName());
			log.info("path=" + fileDir.getPath());
			log.info("absolutepath=" + fileDir.getAbsolutePath());

			// add file to list.
			fileList.add(fileDir);
		}

		return null;
	}

	/**
	 * 创建ZIP和JAR文件
	 * 
	 * @param filepath
	 */
	public void createJar(String filepath) {

	}
	
	/**
	 * 检查文件夹是否不为空
	 * <p>
	 * true:不为空
	 * 
	 * @param path
	 *            文件夹路径
	 * @return true/false
	 */
	public static boolean checkDirIsNotEmpty(String path) {
		File dir = new File(path);
		if (dir.exists()) {
			File[] fileList = dir.listFiles();
			return fileList.length > 0 ? true : false;
		}
		return false;
	}

	/**
	 * 递归得到指定文件夹下及子文件内的所有文件
	 * 
	 * @param file
	 *            指定文件夹file对象
	 * @return 文件列表
	 */
	public static List<File> getFileList(List<File> resultList, File file) {
		if (file.isFile()) {
			resultList.add(file);
		} else {
			File[] fileList = file.listFiles();
			for (File f : fileList) {
				getFileList(resultList, f);
			}
		}
		return resultList;
	}

	/**
	 * 得到所有索引目录
	 * 
	 */
	public static void getIndexDirs(String indexDir, List<String> dirList) {
		File file = new File(indexDir);
		File nowfile;
		String files[] = null;
		files = file.list();
		if (null != files)
			for (int i = 0; i < files.length; i++) {
				nowfile = new File(indexDir + File.separator + files[i]);
				if (nowfile.isDirectory()) {
					dirList.add(files[i]);
				}
			}
	}

	/**
	 * 得到所有索引文件
	 * 
	 */
	public static void getIndexFiles(String indexDir, List<String> fileList) {
		File file;
		File nowfile;
		String files[] = null;
		file = new File(indexDir);
		files = file.list();
		/**
		 * 如果文件是目录，那么继续找出目录下的文件
		 * 
		 */
		if (null != files)
			for (int i = 0; i < files.length; i++) {
				nowfile = new File(indexDir + File.separator + files[i]);
				if (nowfile.isDirectory()) {
					getIndexFiles(nowfile.getAbsolutePath(), fileList);
				} else {
					fileList.add(files[i]);
				}
			}

	}

	/**
	 * 得到指定目录下系统指定类型的所有文件
	 * 
	 */
	public static void getAllFiles(File dir, List<File> fileList) {
		File files[] = null;
		files = dir.listFiles();
		/**
		 * 如果文件是目录，那么继续找出目录下的文件
		 * 
		 */
		if (null != files)
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					getAllFiles(files[i], fileList);
				} else {
					fileList.add(files[i]);
				}
			}

	}

	/**
	 * 根据文件名取得其文件类型
	 * 
	 * @param fileName
	 *            文件名
	 * @return String 文件类型
	 * @throws Exception
	 */
	public static String getFileTypeString(String fileName) {
		String fileTypeStr = "";

		int p = fileName.lastIndexOf(".");
		fileTypeStr = fileName.substring(p + 1);
		return fileTypeStr;
	}

	@SuppressWarnings("unchecked")
	public static List<String> readFile(String filePath,String charset){
		List<String> fileContentList = new ArrayList<String>();
		InputStream in = null;
		File file = null;
		try {
			file = new File(filePath);
			in = new FileInputStream(file);
			fileContentList = IOUtils.readLines(in,charset);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != in) {
				try {
					in.close();
					in = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return fileContentList;
	}

	

	/**
	 * Java中创建缩略图
	 * 
	 * @param filename
	 * @param thumbWidth
	 * @param thumbHeight
	 * @param quality
	 * @param outFilename
	 */
	public void createThumbnail(String filename, int thumbWidth,
			int thumbHeight, int quality, String outFilename)
			throws InterruptedException, FileNotFoundException, IOException {
		// load image from filename
		Image image = Toolkit.getDefaultToolkit().getImage(filename);
		MediaTracker mediaTracker = new MediaTracker(new Container());
		mediaTracker.addImage(image, 0);
		mediaTracker.waitForID(0);

		// use this to test for errors at this point.
		System.out.println(mediaTracker.isErrorAny());
		// determine thumbnail size from WIDTH and HEIGHT
		double thumbRatio = (double) thumbWidth / (double) thumbHeight;
		//
		int imageWidth = image.getWidth(null);
		int imageHeight = image.getHeight(null);
		double imageRatio = (double) imageWidth / (double) imageHeight;
		if (thumbRatio < imageRatio) {
			thumbHeight = (int) (thumbWidth / imageRatio);

		} else {
			thumbWidth = (int) (thumbHeight * imageRatio);
		}
		// draw original image to thumbnail image object and scale it to the new
		// size on-the-fly
		BufferedImage thumbImage = new BufferedImage(thumbWidth, thumbHeight,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics2D = thumbImage.createGraphics();
		graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		graphics2D.drawImage(image, 0, 0, thumbWidth, thumbHeight, null);
		// save thumbnail image to outFilename
		BufferedOutputStream out = new BufferedOutputStream(
				new FileOutputStream(outFilename));
		// JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		//
		// JPEGEncodeParam param =
		// encoder.getDefaultJPEGEncodeParam(thumbImage);
		quality = Math.max(0, Math.min(quality, 100));
		// param.setQuality((float)quality / 100.0f, false);
		//
		// encoder.setJPEGEncodeParam(param);
		// encoder.encode(thumbImage);
		out.close();
	}

	/**
	 * 内部过滤器. <br>
	 * 过滤文件指定类型
	 * 
	 * @author lx
	 * 
	 */
	public static class FileNameFilter implements FileFilter {
		public boolean accept(File file) {
			if (!file.isDirectory()) {
				String fileName = file.getName();

				if (fileName.endsWith(".jsp") || fileName.endsWith(".java")
						|| fileName.endsWith(".xml")) {

					log.info("文件类型：.jsp、.java、.xml被过滤！");

					/* 定义中文目录 */
					if (file.getName().matches(
							"[\\S\\s]*[\u4E00-\u9FFF]+[\\S\\s]*")) {
						return true;
					}
				}
			}

			return false;
		}
	}

	/**
	 * 内部类-zip文件工具类。<br>
	 */
	class UtilZip {

	}


	
	/**
	 * 删除文件或目录下所有文件。
	 * 
	 * @param file
	 *            文件或文件夹全路径
	 */
	public static boolean deleteAllFile(File file) {
		if (!file.exists()) {
			log.info("file no exists." + file.getAbsolutePath());
			return false;
		}

		if (file.isDirectory()) {
			String[] fileArray = file.list();
			for (String string : fileArray) {
				log.info("--->ready for delete files!");
				log.info("--->delete file:"+string);
			}
			
			File[] dir = file.listFiles();
			for (File f : dir) {
				deleteAllFile(f);
			}
		} else if(file.isFile()) {
			if (!file.delete()) {
				return false;
			}
			log.info("删除空的目录![" + file.getAbsolutePath() + "]");
		}
		
		return true;
	}
	
	/**
	 * 删除符合过滤规则的文件或目录。
	 * 
	 * @param file
	 *            文件或文件夹对象。
	 * @param filter
	 *            指定过滤器来过滤相应的文件名。
	 * @param delDir
	 *            是否删除目录(如果当前file是一个目录).
	 */
	public static void deleteFile(File file, FileFilter filter,boolean delDir) {
		if(!file.exists()){
			log.info(">>>>>>>无法找到指定文件!");
		}
		
		// if(file.getName().length()!=file.getName().getBytes().length){
		if (!file.getName().matches("[\\x00-\\xff]*")) {
			log.info(">>>>>" + file.getAbsolutePath() + "被过滤! ");

		} else {
			if(file.isDirectory()) {
				if(delDir) {
					file.delete();
					log.info("删除目录![" + file.getAbsolutePath() + "]");
				}
			} else {
				file.delete();
				log.info("删除文件![" + file.getAbsolutePath() + "]");
			}
		}
	
	}
	
	/**
	 * 删除符合过滤规则的文件或目录。
	 * 
	 * @param filepath
	 *            文件或文件夹全路径。
	 * @param filter
	 *            指定过滤器来过滤相应的文件名。
	 * @param delDir
	 *            是否删除目录(如果当前file是一个目录).
	 */
	public static void deleteFile(String filepath, FileFilter filter,boolean delDir) {
		File file = new File(filepath);
		deleteFile(file, filter, delDir);
	}

	/**
	 * 删除目录
	 * 
	 * @param delpath
	 */
	public static boolean delDirectory(String delpath) {
		try {
			File file = new File(delpath);
			if (!file.isDirectory()) {
				if (!file.delete()) {
					return false;
				}
			} else if (file.isDirectory()) {
				File[] filelist = file.listFiles();
				for (File delfile : filelist) {
					if (!delfile.isDirectory()) {
						if (!delfile.delete()) {
							return false;
						}
					} else if (delfile.isDirectory())
						if (!(delDirectory(delfile.getAbsolutePath()))) {
							return false;
						}
				}
				if (!file.delete()) {
					return false;
				}
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 删除文件
	 * <p>
	 * 方法说明:</><li></li>
	 * 
	 * @author DuanYong
	 * @since 2013-4-23 下午5:13:23
	 * @version 1.0
	 * @exception @param
	 *                filePath 文件路径
	 * @param deleteParentDirOnEmpty
	 *            当父目录为空时是否删除目录
	 */
	public static void deleteFile(String filePath, boolean deleteParentDirOnEmpty) {
		File file = new File(filePath);
		if (file.exists()) {
			file.delete();
		}
		if (deleteParentDirOnEmpty) {
			File[] files = file.getParentFile().listFiles();
			if (null == files || files.length == 0) {
				file.getParentFile().delete();
			}
		}
	}

}
