package com.kspt.common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class UtilFile {
	
	public static String readAll(String fileName) throws IOException {
		return readAll(fileName, "UTF-8");
	}

	public static String readAll(String fileName, String encodeing)
			throws IOException {
		FileInputStream in = null;
		try {
			in = new FileInputStream(fileName);
			String res;
			int len = in.available();
			if (len > 0) {
				byte[] buf = new byte[len];
				in.read(buf, 0, len);
				res = (new String(buf, encodeing));
			} else {
				res = null;
			}
			return res;
		} finally {
			try {
				in.close();
				in = null;
			} catch (Exception e) {
			}
		}
	}

	public static boolean isUTF8File(String srcFileName) throws IOException {
		String fileContent = readAll(srcFileName);
		if (fileContent == null) {
			return true;
		}
		if (fileContent == null
				|| Charset.forName("UTF-8").newEncoder().canEncode(fileContent)) {
			return true;
		} else {
			return false;
		}
	}

	public static String getEncoding(String str) {
		if (str == null) {
			return "";
		}
		String encode = "GB2312";
		try {
			if (Charset.forName("GB2312").newEncoder().canEncode(str)) {
				String s = encode;
				return s;
			}
		} catch (Exception exception) {
		}
		encode = "ISO-8859-1";
		try {
			if (Charset.forName("ISO-8859-1").newEncoder().canEncode(str)) {
				String s1 = encode;
				return s1;
			}
		} catch (Exception exception1) {
		}
		encode = "UTF-8";
		try {
			if (Charset.forName("UTF-8").newEncoder().canEncode(str)) {
				String s2 = encode;
				return s2;
			}
		} catch (Exception exception2) {
		}
		encode = "GBK";
		try {
			if (Charset.forName("GBK").newEncoder().canEncode(str)) {
				String s3 = encode;
				return s3;
			}
		} catch (Exception exception3) {
		}
		return encode;
	}

	public static void write(String srcFileName, String fileContent,
			String encodeing) throws IOException {
		BufferedWriter bf = null;
		try {
			bf = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(srcFileName), encodeing));
			String[] ss = fileContent.split("__eol__");
			for (int i = 0; i < ss.length; i++) {
				bf.write(ss[i]);
				if (i != ss.length - 1) {
					bf.newLine();
				}
			}

			bf.flush();
		} finally {
			if (bf != null) {
				try {
					bf.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public static boolean write(String srcFileName,InputStream InputStream) throws IOException {
		OutputStream out = null;
		try {
			out = new FileOutputStream(srcFileName);
			 byte b[]=new byte[1024];  
			  int read=InputStream.read(b);  
            while(read!=-1)  
            {  
            	out.write(b,0,read);  
                read=InputStream.read(b);  
            }  
        
		}catch(Exception e){
			e.printStackTrace();
			return false;
		} finally {
			if (out != null) {
				try {
				    InputStream.close();  
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return true;
	}

	
	public static void write(String srcFileName, String fileContent)
			throws IOException {
		write(srcFileName, fileContent, "UTF8");
	}

	/**
	 * 递归得到指定路径下的指定格式的文件如：xxx.xml那么type就是xml
	 */

	public static List<File> getAllFile(File file, String type) {
		List<File> list = new ArrayList<File>();
		if (file.exists()) {
			if (file.isFile()) {
				return null;
			} else {
				recursionFiles(file, list, type);
			}
		}

		return list;

	}

	public static List<File> getAllFile(File file) {
		List<File> list = new ArrayList<File>();
		if (file.exists()) {
			if (file.isFile()) {
				return null;
			} else {
				recursionFiles(file, list, "");
			}
		}
		return list;

	}

	private static List<File> recursionFiles(File file, List<File> lf,
			String type) {
		File[] files = file.listFiles();
		for (File file2 : files) {
			if (file2.isFile()) {
				if (type.equals("")) {
					lf.add(file2);
				} else {
					if (file2.getName().endsWith("." + type)) {
						lf.add(file2);
					}
				}
			} else {
				recursionFiles(file2, lf, type);
			}
		}
		return lf;

	}

	public static void deleteFile(File file) {
		if (file.exists()) {
			if (file.isFile()) {
				file.delete();
			} else if (file.isDirectory()) {
				File file2[] = file.listFiles();
				for (File file3 : file2) {
					deleteFile(file3);
				}

			}
		}
		file.delete();
	}
}
