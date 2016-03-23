package com.kspt.common;

import java.util.Vector;

public class UtilString {
	// static int count=0;

	/***/
	public String strSuper;

	/***/
	protected String lowcaseSuper;

	/**
	 * Constructor a nullString of UtilString
	 * 
	 * 
	 */
	public UtilString() {
		strSuper = new String();
		lowcaseSuper = strSuper;
	}

	public UtilString(String strInit) {
		strInit = strInit == null ? "" : strInit;
		strSuper = new String(strInit);
		lowcaseSuper = strInit.toLowerCase();
	}

	/**
	 * 将字符串按strSign字串作为分割符分割字符串，分割到一个Vector
	 * 
	 * @param
	 * @return return a Vector;
	 * 
	 */
	public Vector split(String strSign) {
		int begin = 0;
		int end = 0;
		Vector vecResult = new Vector();

		if (strSign == "") {
			int i;

			for (i = 0; i < strSuper.length(); i++) {
				vecResult.add(strSuper.substring(i, i + 1));
			}

			return vecResult;
		}

		end = strSuper.indexOf(strSign);

		if (end == -1) {
			vecResult.add(strSuper);

			return vecResult;
		} else {
			while (end >= 0) {
				vecResult.add(strSuper.substring(begin, end));
				begin = end + strSign.length();
				end = strSuper.indexOf(strSign, begin);
			}

			vecResult.add(strSuper.substring(begin, strSuper.length()));

			return vecResult;
		}
	}

	/**
	 * 从0位置寻找字符串strBegin在strSuper中出现的位置 不区分大小写
	 * 
	 * @param strBegin
	 *            一个将要查找的字符串
	 * @return 如果找到返回字符串首位置，否则返回-1;
	 * 
	 */
	public int matchOf(String strBegin) {
		return matchOf(strBegin, false);
	}

	/**
	 * 从0位置寻找字符串strBegin在strSuper中出现的位置
	 * 
	 * @param strBegin
	 *            将要查找的字符串
	 * @param ignoreCase
	 *            是否区分大小写，为true时表示区分
	 * @return 如果找到返回字符串首位置，否则返回-1;
	 * 
	 */
	public int matchOf(String strBegin, boolean ignoreCase) {
		int pos = -1;

		if (ignoreCase) {
			pos = lowcaseSuper.indexOf(strBegin.toLowerCase());
		} else {
			pos = strSuper.indexOf(strBegin);
		}

		if (pos < 0) {
			return pos;
		} else {
			return (pos + strBegin.length());
		}
	}

	/**
	 * 从末尾寻找字符串strEnd在strSuper中出现的位置 不区分大小写
	 * 
	 * @param strEnd
	 *            ?strBegin? 一个将要查找的字符串
	 * @return 如果找到返回字符串首位置，否则返回-1;
	 * 
	 */
	public int lastMatchOf(String strEnd) {
		return lastMatchOf(strEnd, false);
	}

	/**
	 * 从0位置寻找字符串strEnd在strSuper中出现的位置
	 * 
	 * @param strEnd
	 *            将要查找的字符串
	 * @param ignoreCase
	 *            是否区分大小写，为true时表示区分
	 * @return 如果找到返回字符串首位置，否则返回-1;
	 * 
	 */
	public int lastMatchOf(String strEnd, boolean ignoreCase) {
		if (ignoreCase) {
			return lowcaseSuper.lastIndexOf(strEnd.toLowerCase());
		} else {
			return strSuper.lastIndexOf(strEnd);
		}
	}

	/**
	 * 截取标记为strBegin与strEnd之间的字符串 不区分大小写
	 * 
	 * @param strBegin
	 *            一个头标志
	 * @param strEnd
	 *            尾标志
	 * @return 如果找到返回被截取的字符串值，否则返回空字符串;
	 * 
	 */
	public String matchValue(String strBegin, String strEnd) {
		return matchValue(strBegin, strEnd, false);
	}

	/**
	 * 截取标记为strBegin与strEnd之间的字符串
	 * 
	 * @param strBegin
	 *            一个头标志
	 * @param strEnd
	 *            尾标志
	 * @param ignoreCase
	 *            是否区分大小写，为true时表示区分
	 * @return 如果找到返回被截取的字符串值，否则返回空字符串;
	 * 
	 */
	public String matchValue(String strBegin, String strEnd, boolean ignoreCase) {
		String strResult = new String();
		int posBegin = this.matchOf(strBegin, ignoreCase);
		int posEnd = this.lastMatchOf(strEnd, ignoreCase);

		if ((posEnd > posBegin) && (posBegin != -1)) {
			strResult = strSuper.substring(posBegin, posEnd);
		}

		return strResult;
	}

	/**
	 * 将strOld替换为strNew
	 * 
	 * @param strOld
	 *            被替换字符串
	 * @param strNew
	 *            替换值
	 * 
	 */
	public String replace(String strOld, String strNew) {
		return replace(strOld, strNew, false);
	}

	/**
	 * 将strOld替换为strNew
	 * 
	 * @param strOld
	 *            被替换字符串
	 * @param strNew
	 *            替换值
	 * @param ignoreCase
	 *            是否区分大小写，为true时表示区分
	 * 
	 */
	public String replace(String strOld, String strNew, boolean ignoreCase) {
		if (strOld == null || strOld.equals(""))
			return strSuper;// by jackliu,2010,11(strOld传递空串造成死循环)
		// String strResult = new String();
		String strResult = new String();
		String strCore;

		String strReplace;

		if (ignoreCase) {
			strCore = lowcaseSuper;
			strReplace = strOld.toLowerCase();
		} else {
			strCore = strSuper;
			strReplace = strOld;
		}

		int posBegin = 0;
		int posEnd = strCore.indexOf(strReplace);

		// nothing should be replaced
		if (posEnd < 0) {
			return strSuper;
		}

		// StringBuffer sbResult=new StringBuffer();
		// while (posEnd >= 0) {
		// sbResult.append(strSuper.substring(posBegin, posEnd));
		// sbResult.append(strNew);
		// posBegin = posEnd + strReplace.length();
		// posEnd = strCore.indexOf(strReplace, posBegin);
		// System.out.println(count++);
		// }
		// sbResult.append(strSuper.substring(posBegin));
		// //strResult += strSuper.substring(posBegin);
		// something should be replaced
		while (posEnd >= 0) {
			strResult += strSuper.substring(posBegin, posEnd);
			strResult += strNew;
			posBegin = posEnd + strReplace.length();
			posEnd = strCore.indexOf(strReplace, posBegin);
		}

		strResult += strSuper.substring(posBegin);

		return strResult.toString();
	}

	/**
	 * 重指定开始的位置strBegin将strOld替换为strNew，strBegin是位置的标志字符串 不区分大小写
	 * 
	 * @param strBegin
	 *            开始执行替换动作的标志字符串
	 * @param strEnd
	 *            ?strOld? 替换值
	 * @param strNew
	 *            被替换字符串
	 * 
	 */
	public String replace(String strBegin, String strEnd, String strNew) {
		return replace(strBegin, strEnd, strNew, false);
	}

	/**
	 * 重指定开始的位置strBegin将strOld替换为strNew，strBegin是位置的标志字符串
	 * 
	 * @param strBegin
	 *            开始执行替换动作的标志字符串
	 * @param strEnd
	 *            ?strOld? 替换值
	 * @param strNew
	 *            被替换字符串
	 * @param ignoreCase
	 *            是否区分大小写，为true时表示区分
	 * 
	 */
	public String replace(String strBegin, String strEnd, String strNew, boolean ignoreCase) {
		int posBegin = this.matchOf(strBegin, ignoreCase);
		int posEnd = this.lastMatchOf(strEnd, ignoreCase);
		String strResult = new String();

		if ((posEnd >= posBegin) && (posBegin != -1)) {
			strResult = strSuper.substring(0, posBegin);
			strResult += strNew;
			strResult += strSuper.substring(posEnd);

			return strResult;
		} else {
			return strSuper;
		}
	}

	/**
	 * @param strBegin
	 * 
	 */
	public boolean startsWith(String strBegin) {
		return startsWith(strBegin, false);
	}

	/**
	 * @param strBegin
	 * @param ignoreCase
	 * 
	 */
	public boolean startsWith(String strBegin, boolean ignoreCase) {
		if (ignoreCase) {
			return lowcaseSuper.startsWith(strBegin.toLowerCase());
		} else {
			return strSuper.startsWith(strBegin);
		}
	}

	/**
	 * @param strEnd
	 * 
	 */
	public boolean endsWith(String strEnd) {
		return endsWith(strEnd, false);
	}

	/**
	 * @param strEnd
	 * @param ignoreCase
	 * 
	 */
	public boolean endsWith(String strEnd, boolean ignoreCase) {
		if (ignoreCase) {
			return lowcaseSuper.endsWith(strEnd.toLowerCase());
		} else {
			return strSuper.endsWith(strEnd);
		}
	}

	/**
	 * 将多余字符变成..
	 * 
	 * @param str
	 * @param length
	 * @return
	 * 
	 */
	public static String cutString(String str, int length) {
		if (str == null) {
			return "";
		}

		if ((str.length() > length) && (length > 2)) {
			return str.substring(0, length) + ".";
		} else {
			return str;
		}
	}

	/**
	 * 获取当前线程的用户信息
	 * 
	 * @param threadName
	 * @return
	 * 
	 */
	public static String[] getCurThreadUserInfo(String threadName) {
		String[] array = new String[2];
		if (threadName != null && threadName.trim().length() > 0) {
			String[] threadArray = threadName.split("--");
			array[0] = threadArray[0];
			array[1] = threadArray.length > 2 ? threadArray[2] : "";
		} else {
			array[0] = "";
			array[1] = "";
		}
		return array;
	}
}
