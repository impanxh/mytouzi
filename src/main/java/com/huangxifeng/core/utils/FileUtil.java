/*
 * Copyright @2010 Net365. All rights reserved.
 */
package com.huangxifeng.core.utils;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * 
 * </pre>
 * 
 * @author HuangFeng(2010-9-26)
 */
public class FileUtil {

	/** 获得系统默认编码 */
	public final static String SYS_ENCODING = System.getProperty("file.encoding");

	/**
	 * 文件拷贝(源文件及目标文件一定要存在)
	 * 
	 * @param srcFile
	 *            源文件
	 * @param destFile
	 *            目标文件
	 * @throws IOException
	 *             拷贝出错时抛出，比如：源文件拒绝访问，目标文件或目录不存在
	 * @author lijian 2008-12-02
	 */
	public static void copyFile(String srcFile, String descFile) throws IOException {
		File src = new File(srcFile);
		File desc = new File(descFile);
		copyFile(src, desc);
	}

	/**
	 * 文件拷贝(源文件及目标文件一定要存在)
	 * 
	 * @param srcFile
	 *            源文件
	 * @param destFile
	 *            目标文件
	 * @throws IOException
	 *             拷贝出错时抛出，比如：源文件拒绝访问，目标文件或目录不存在
	 * @author lijian 2008-12-02
	 */
	public static void copyFile(File srcFile, File destFile) throws IOException {
		FileUtils.copyFile(srcFile, destFile);
	}

	/**
	 * 文件目录拷贝(源目录及目标目录一定要存在)
	 * 
	 * @param srcDir
	 *            源目录
	 * @param destDir
	 *            目标目录
	 * @throws IOException
	 *             拷贝出错时抛出，比如：源目录拒绝访问，目录或目录不存在
	 * @author lijian 2008-12-02
	 */
	public static void copyDir(String srcDir, String destDir) throws IOException {
		File src = new File(srcDir);
		File desc = new File(destDir);
		copyDir(src, desc);
	}

	/**
	 * 文件目录拷贝(源目录及目标目录一定要存在)
	 * 
	 * @param srcDir
	 *            源目录
	 * @param destDir
	 *            目标目录
	 * @throws IOException
	 *             拷贝出错时抛出，比如：源目录拒绝访问，目录或目录不存在
	 * @author lijian 2008-12-02
	 */
	public static void copyDir(File srcDir, File destDir) throws IOException {
		FileUtils.copyDirectory(srcDir, destDir);
	}

	/**
	 * 重命名文件(剪切文件).
	 * 
	 * @param srcFile
	 *            源文件
	 * @param descFile
	 *            目的文件夹
	 * @return 重命名后的文件名
	 * @author shuchao Apr 8, 2009
	 * @modifier
	 */
	public static String reName(String srcFile, String descDir) {
		descDir = descDir + FileUtil.getFileSeparator();
		descDir = replaceFileSeparator(descDir);
		File in = new File(srcFile);
		if (!new File(descDir).exists()) {
			new File(descDir).mkdirs();
		}
		String fileName = getFileName(srcFile);
		String namWithoutExtension = getNameWithoutExtension(fileName);
		String extension = getExtension(fileName);

		String savedFileName = descDir + fileName;
		File out = new File(savedFileName);

		int counter = 1;
		while (out.exists()) {
			savedFileName = descDir + namWithoutExtension + "_" + counter + "." + extension;
			out = new File(savedFileName);
			counter++;
		}
		in.renameTo(out);

		return out.getName();
	}

	/**
	 * 重命名文件(剪切文件).
	 * 
	 * @param srcFile
	 *            源文件
	 * @param descFile
	 *            目的文件夹
	 * @return 重命名后的文件名
	 * @author shuchao Apr 8, 2009
	 * @modifier
	 */
	public static String reName(String srcFile, String descDir, String namWithoutExtension) {

		descDir = descDir + FileUtil.getFileSeparator();
		descDir = replaceFileSeparator(descDir);
		File in = new File(srcFile);
		if (!new File(descDir).exists()) {
			new File(descDir).mkdirs();
		}
		String fileName = getFileName(srcFile);
		String extension = getExtension(fileName);

		String savedFileName = descDir + namWithoutExtension + "." + extension;
		File out = new File(savedFileName);

		int counter = 1;
		while (out.exists()) {
			savedFileName = descDir + namWithoutExtension + "_" + counter + "." + extension;
			out = new File(savedFileName);
			counter++;
		}
		in.renameTo(out);

		return out.getName();
	}

	/**
	 * <pre>
	 * 重命名文件
	 * 注：目标文件路径和重命名文件路径都必需存在，不存在 则报错
	 * </pre>
	 * 
	 * @author HuangFeng(Mar 19, 2010)
	 * @param srcFile
	 *            目标文件
	 * @param renameFile
	 *            重命名文件
	 * @return 重命名文件名
	 */
	public static boolean rename(String srcFile, String renameFile) throws IOException {
		File in = new File(replaceFileSeparator(renameFile));
		File out = new File(renameFile);
		return in.renameTo(out);
	}

	/**
	 * 拷贝文件，避免重复.
	 * 
	 * @param srcFile
	 *            源文件
	 * @param descDir
	 *            目的文件目录
	 * @return 新的文件名（xxx.yyy）
	 * @throws IOException
	 * @author shuchao Mar 26, 2009
	 * @modifier
	 */
	public static String copyFileNoRepreat(String srcFile, String descDir) throws IOException {
		descDir = descDir + FileUtil.getFileSeparator();
		descDir = replaceFileSeparator(descDir);
		if (!new File(descDir).exists()) {
			new File(descDir).mkdirs();
		}
		File in = new File(srcFile);
		String fileName = getFileName(srcFile);
		String namWithoutExtension = getNameWithoutExtension(fileName);
		String extension = getExtension(fileName);

		String savedFileName = descDir + fileName;
		File out = new File(savedFileName);

		int counter = 1;
		while (out.exists()) {
			savedFileName = descDir + namWithoutExtension + "_" + counter + "." + extension;
			out = new File(savedFileName);
			counter++;
		}

		FileInputStream fis = new FileInputStream(in);
		FileOutputStream fos = new FileOutputStream(out);
		byte[] buf = new byte[1024];
		int i = 0;
		while ((i = fis.read(buf)) != -1) {
			fos.write(buf, 0, i);
		}
		fis.close();
		fos.close();
		return out.getName();
	}

	/**
	 * 获取文件名（不带扩展名）.
	 * 
	 * @param fileName
	 *            文件名
	 * @return
	 * @author shuchao Mar 26, 2009
	 * @modifier
	 */
	public static String getNameWithoutExtension(String fileName) {
		return fileName.substring(0, fileName.lastIndexOf("."));
	}

	/**
	 * 获取文件扩展名.
	 * 
	 * @param fileName
	 *            文件名
	 * @return
	 * @author shuchao Mar 26, 2009
	 * @modifier
	 */
	public static String getExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".") + 1);
	}

	/**
	 * 获取文件名(带扩展名).
	 * 
	 * @param fileName
	 *            文件名
	 * @return
	 * @author shuchao Mar 26, 2009
	 * @modifier
	 */
	public static String getFileName(String fileName) {
		String temp = fileName.substring(fileName.lastIndexOf("/") + 1);
		int len = temp.length();
		if (len == fileName.length()) {
			temp = fileName.substring(fileName.lastIndexOf("\\") + 1);
			len = temp.length();
		}
		if (len == fileName.length()) {
			temp = fileName.substring(fileName.lastIndexOf("\\\\") + 1);
		}
		return temp;
	}

	/**
	 * 斜杠替换当前系统文件分隔符，比如：<code>( '\' -> '/' )</code>
	 * 
	 * @author HuangFeng(Mar 25, 2010)
	 * @param str
	 *            文件名或字符串
	 * @return 系统分隔符文件名或字符串
	 */
	public static String replaceFileSeparator(String str) {
		String _sys_ = getFileSeparator();
		return str.replace("\\", _sys_).replace("/", _sys_).replace("//", _sys_).replace("\\\\", _sys_)
				.replace("\\/", _sys_).replace("/\\", _sys_);
	}

	/**
	 * 获取系统文件分隔符
	 */
	public static String getFileSeparator() {
		return System.getProperty("file.separator");
	}

	/**
	 * 获得当前的时间序列
	 * 
	 * @param i
	 * @return
	 */
	public synchronized static String getTimeSequence(int i) {
		return String.valueOf(new java.util.Date().getTime() + Long.valueOf(i));
	}

	/**
	 * 用于图片删除和更新时删除,把生成的图片也删除掉
	 * 
	 * @param fileName
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public static boolean deleteFile(String fileName, String filePath) throws Exception {
		try {
			File file = new File(filePath + fileName.replace("!", System.getProperty("file.separator")) + ".jpg");
			File file1 = new File(
					filePath + fileName.replace("!", System.getProperty("file.separator")) + "-50-50" + ".jpg");
			File file2 = new File(
					filePath + fileName.replace("!", System.getProperty("file.separator")) + "-90-90" + ".jpg");
			File file3 = new File(
					filePath + fileName.replace("!", System.getProperty("file.separator")) + "-150-150" + ".jpg");
			File file4 = new File(
					filePath + fileName.replace("!", System.getProperty("file.separator")) + "-300-300" + ".jpg");

			File file5 = new File(
					filePath + fileName.replace("!", System.getProperty("file.separator")) + "-big" + ".jpg");

			if (file != null && file.exists() && !file.isDirectory()) {
				file.delete();
			}
			if (file1 != null && file1.exists() && !file1.isDirectory()) {
				file1.delete();
			}
			if (file2 != null && file2.exists() && !file2.isDirectory()) {
				file2.delete();
			}
			if (file3 != null && file3.exists() && !file3.isDirectory()) {
				file3.delete();
			}
			if (file4 != null && file4.exists() && !file4.isDirectory()) {
				file4.delete();
			}
			if (file5 != null && file5.exists() && !file5.isDirectory()) {
				file5.delete();
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public static boolean deleteLogo(String fileName, String filePath) throws Exception {

		try {
			File file = new File(filePath, fileName);
			if (file != null && file.exists() && !file.isDirectory()) {
				file.delete();
			} else {
				throw new Exception("删除文件[" + filePath + fileName + "]不存在");
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 删除预览图片
	 * 
	 * @param fileName
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public static boolean deletePre(String fileName, String filePath) throws Exception {
		try {

			String fullImageName = fileName.replace("!", System.getProperty("file.separator"));
			String mainImageName = fullImageName;
			// System.out.println("删除图片："+fullImageName);
			if (null != mainImageName && fullImageName.lastIndexOf(".") != -1) {
				mainImageName = fullImageName.substring(0, fullImageName.lastIndexOf("."));
			}
			File imageName = new File(filePath + fullImageName);
			if (imageName != null && imageName.exists() && !imageName.isDirectory()) {
				imageName.delete();
			}

			File imageName250 = new File(filePath + mainImageName + "-300-300" + ".jpg");
			if (imageName250 != null && imageName250.exists() && !imageName250.isDirectory()) {
				imageName250.delete();
			}

			File imageName150 = new File(filePath + mainImageName + "-150-150" + ".jpg");
			if (imageName150 != null && imageName150.exists() && !imageName150.isDirectory()) {
				imageName150.delete();
			}

			File imageName90 = new File(filePath + mainImageName + "-90-90" + ".jpg");
			if (imageName90 != null && imageName90.exists() && !imageName90.isDirectory()) {
				imageName90.delete();
			}

			File imageName50 = new File(filePath + mainImageName + "-50-50" + ".jpg");
			if (imageName50 != null && imageName50.exists() && !imageName50.isDirectory()) {
				imageName50.delete();
			}

			File imageNameBig = new File(filePath + mainImageName + "-big" + ".jpg");
			if (imageNameBig != null && imageNameBig.exists() && !imageNameBig.isDirectory()) {
				imageNameBig.delete();
			}

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 将内容写入文件中
	 * 
	 * @param filepath
	 *            文件所在物理路径
	 * @param content
	 *            写入内容
	 */
	public static void writeFile(String filepath, String content) {
		writeFile(filepath, content, null);
	}

	public static void writeFile(String filepath, String content, String encoding) {

		FileOutputStream fos = null;
		Writer out = null;
		try {

			makeFile(filepath);

			fos = new FileOutputStream(replaceFileSeparator(filepath));

			if (null == encoding) {
				out = new OutputStreamWriter(fos);
			} else {
				out = new OutputStreamWriter(fos, encoding);
			}

			out.write(content);
			out.flush();
			out.close();
			fos.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			fos = null;
			out = null;
		}
	}

	/**
	 * 文件内容读取
	 * 
	 * @param path
	 *            文件路径
	 * @return String
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public static String readLine(String path, String encoding) {
		StringBuilder buf = new StringBuilder();
		try {

			InputStreamReader read = null;
			if (encoding == null || encoding.trim().length() == 0) {
				read = new InputStreamReader(new FileInputStream(new File(path)));
			} else {
				read = new InputStreamReader(new FileInputStream(new File(path)), encoding);
			}

			BufferedReader bufread = new BufferedReader(read);
			String str = "";

			while ((str = bufread.readLine()) != null) {
				buf.append(str).append("\n");
			}

			read.close();
			bufread.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return buf.toString();
	}

	/**
	 * 文件内容读取
	 * 
	 * @param path
	 *            文件路径
	 * @return String
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public static String read(String path, String encoding) {
		return readLine(path, encoding).replace("\n", "");
	}

	public static String readString(String path, String encoding) {
		StringBuilder buf = new StringBuilder();
		try {

			InputStreamReader read = null;
			if (encoding == null || encoding.trim().length() == 0) {
				read = new InputStreamReader(new FileInputStream(new File(path)));
			} else {
				read = new InputStreamReader(new FileInputStream(new File(path)), encoding);
			}

			BufferedReader bufread = new BufferedReader(read);
			String str = "";

			while ((str = bufread.readLine()) != null) {
				buf.append(str);
			}

			read.close();
			bufread.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return buf.toString();
	}

	/**
	 * 文件内容读取
	 * 
	 * @param path
	 *            文件路径
	 * @return String
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public static String read(String path) {

		return read(path, null);
	}

	/**
	 * 读取指定目录下的 所有文件
	 * 
	 * @param path
	 * @return
	 */
	public static File[] readFiles(String path) {
		File file = new File(path);
		return file.listFiles();
	}

	/**
	 * Method：文件是否存在<br>
	 * Author：HF-JWinder(2008-8-13 下午06:11:22)
	 * 
	 * @param filePath
	 *            文件路径
	 * @return boolean
	 */
	public static boolean isExist(String filePath) {
		File file = new File(replaceFileSeparator(filePath));
		if (file.exists()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 删除指定路径下的文件或文件夹<br>
	 * － 有时希望删除文件，比如：图片，文档等．使用方法：<code>removeFile('test.jpg',true);</code>
	 * 删除一个文件时<code>flag=true</code> <br>
	 * － 有时希望删除某个目录下的所有文件，使用方法:<code>removeFile('C:\\temp\\',false);</code> <br>
	 * － 注意：如果在删除所有文件的同时也希望删除此目录的话，那么
	 * <code>removeFile('C:\\temp\\',true);-flag=true</code>;
	 * 
	 * @param fileUrl
	 *            文件或是目录
	 * @param flag
	 *            是否删除文件夹
	 * @throws Exception
	 *             文件不存在． 或是 文件拒绝访问．
	 */
	public static void removeFile(String fileUrl, boolean flag) throws IOException {

		File file = new File(fileUrl);

		// 文件或目录是否存在．
		if (!file.exists()) {
			throw new FileNotFoundException("指定目录不存在:" + fileUrl);
		}

		// 保存中间结果
		boolean rslt = true;

		// 删除文件时,是否删除文件目录
		// flag = true ;表示删除文件以及文件夹
		// flag = false ;只删除文件夹里的文件
		if (flag) {

			// 先尝试直接删除，此文件或是目录
			// 若是文件则直接删除，不能删除文件为拒绝访问．
			// 若文件夹且非空。枚举、递归删除里面内容
			if (!(rslt = file.delete())) {

				File[] subs = file.listFiles();
				if (null == subs) {
					// 有些系统文件无法删除， 也不是文件夹，所以获得文件列表是空的．
					throw new IOException("文件拒绝访问:" + file.getName());
				}

				for (int i = 0; i <= subs.length - 1; i++) {
					if (subs[i].isDirectory()) {
						// 递归删除子文件夹内容
						removeFile(subs[i].toString(), flag);
					}
					// 删除子文件夹本身
					rslt = subs[i].delete();
				}

				// 最后删除文件夹目录
				rslt = file.delete();

			}

		} else {

			// 删除文件夹下的所有文件
			File[] subs = file.listFiles();

			if (null == subs) {
				// 有些系统文件无法删除， 也不是文件夹，所以获得文件列表是空的．
				throw new IOException("文件拒绝访问:" + file.getName());
			}

			for (int i = 0; i <= subs.length - 1; i++) {
				if (subs[i].isDirectory()) {
					// 递归删除子文件夹内容
					removeFile(subs[i].toString(), flag);
				}
				// 删除子文件夹本身
				rslt = subs[i].delete();
			}

		}

		if (!rslt) {
			throw new IOException("无法删除:" + file.getName());
		}
	}

	/**
	 * 创建文件
	 * 
	 * @param filepath
	 *            文件所在目录路径,比如:c:/test/test.txt
	 * @return 操作
	 */
	public static boolean makeFile(String filepath) throws IOException {
		try {
			File file = new File(replaceFileSeparator(filepath));
			mkdirs(file.getParent());
			return file.createNewFile();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 创建目录
	 * 
	 * @author HuangFeng(Mar 1, 2010)
	 * @param filepath
	 *            目录
	 * @return boolean
	 */
	public static boolean mkdirs(String filepath) {
		if (!isExist(filepath)) {
			File file = new File(filepath);
			return file.mkdirs();
		} else {
			return true;
		}
	}

	/**
	 * 读取文件，转成二进制流
	 */
	public static byte[] readbyte(String filepath) {
		try {
			File file = new File(filepath);
			FileInputStream input = new FileInputStream(file);
			if (file.length() > 50 * 1000 * 1000) {
				// log.error(String.format("文件太大：%s %s"
				// ,filepath,file.length()));
				throw new IOException(String.format("文件太大：%s %s", filepath, file.length()));
			}
			int length = (int) file.length();
			byte[] result = new byte[length];
			input.read(result, 0, length);
			input.close();
			return result;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new byte[0];
	}

	/**
	 * 写入文件，使用二进制流
	 */
	public static boolean writebyte(String filepath, byte[] bytes) {
		try {
			File file = new File(filepath);
			FileOutputStream fstream = new FileOutputStream(file);
			BufferedOutputStream stream = new BufferedOutputStream(fstream);
			stream.write(bytes);
			stream.flush();
			stream.close();
			fstream.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static List<String> readToStringList(String path, String encoding) {
		try {

			InputStreamReader read = null;
			if (encoding == null || encoding.trim().length() == 0) {
				read = new InputStreamReader(new FileInputStream(new File(path)));
			} else {
				read = new InputStreamReader(new FileInputStream(new File(path)), encoding);
			}

			List<String> list = new ArrayList<String>();
			BufferedReader bufread = new BufferedReader(read);
			String str = "";

			while ((str = bufread.readLine()) != null) {
				list.add(str);
			}

			read.close();
			bufread.close();
			return list;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static void main(String[] args) throws IOException {
		System.out.println(FileUtil.read("/home/hf/tmp/11.txt"));
	}
}
