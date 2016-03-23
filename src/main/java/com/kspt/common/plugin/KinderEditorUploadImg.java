package com.kspt.common.plugin;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/zq/common/kinderEditor")
public class KinderEditorUploadImg {
	@Resource
	ResourceLoader res;
	@RequestMapping(value="/upload",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String upImg(HttpServletRequest request){
	/**
	 * KindEditor JSP
	 * 
	 * 本JSP程序是演示程序，建议不要直接在实际项目中使用。
	 * 如果您确定直接使用本程序，使用之前请仔细确认相关安全设置。
	 * 
	 */

	//文件保存目录路径
	String savePath = null;
	try {
		savePath = getfilePath("/attached/");
	} catch (IOException e2) {
		// TODO Auto-generated catch block
		e2.printStackTrace();
	}

	//文件保存目录URL
	String saveUrl = request.getScheme()+"://"+request.getHeader("host")+request.getContextPath()+"/attached/";
System.out.println(saveUrl);
	//定义允许上传的文件扩展名
	HashMap<String, String> extMap = new HashMap<String, String>();
	extMap.put("image", "gif,jpg,jpeg,png,bmp");
	extMap.put("flash", "swf,flv");
	extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
	extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");

	//最大文件大小
	long maxSize = 1000000;


	if(!ServletFileUpload.isMultipartContent(request)){
		
		return  getError("请选择文件。");
	}
	//检查目录
	File uploadDir = new File(savePath);
	if(!uploadDir.isDirectory()){
		return getError("上传目录不存在。");
	}
	//检查目录写权限
	if(!uploadDir.canWrite()){
		return getError("上传目录没有写权限。");
		
	}

	String dirName = request.getParameter("dir");
	if (dirName == null) {
		dirName = "image";
	}
	if(!extMap.containsKey(dirName)){
		return getError("目录名不正确。");
	}
	//创建文件夹
	savePath += dirName + "/";
	saveUrl += dirName + "/";
	File saveDirFile = new File(savePath);
	if (!saveDirFile.exists()) {
		saveDirFile.mkdirs();
	}
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	String ymd = sdf.format(new Date());
	savePath += ymd + "/";
	saveUrl += ymd + "/";
	File dirFile = new File(savePath);
	if (!dirFile.exists()) {
		dirFile.mkdirs();
	}

	FileItemFactory factory = new DiskFileItemFactory();
	ServletFileUpload upload = new ServletFileUpload(factory);
	upload.setHeaderEncoding("UTF-8");
	List items = null;
	try {
		items = upload.parseRequest(request);
	} catch (FileUploadException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	Iterator itr = items.iterator();
	while (itr.hasNext()) {
		FileItem item = (FileItem) itr.next();
		String fileName = item.getName();
		long fileSize = item.getSize();
		if (!item.isFormField()) {
			//检查文件大小
			if(item.getSize() > maxSize){
				return getError("上传文件大小超过限制。");
			}
			//检查扩展名
			String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
			if(!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)){
				return getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。");
			}

			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
			try{
				File uploadedFile = new File(savePath, newFileName);
				item.write(uploadedFile);
			}catch(Exception e){
				return getError("上传文件失败。");
			}

			JSONObject obj = new JSONObject();
			obj.put("error", 0);
			obj.put("url", saveUrl + newFileName);
			return obj.toJSONString();
		}
	}
	return null;
	}
	private String getError(String message) {
		JSONObject obj = new JSONObject();
		obj.put("error", 1);
		obj.put("message", message);
		return obj.toJSONString();
	}
	public String getfilePath(String src) throws IOException {
		org.springframework.core.io.Resource path = res.getResource(src);
		File file = path.getFile();
		String logoRealPathDir = file.getAbsolutePath();
		String realDir = logoRealPathDir + File.separator;
		return realDir;
	}
}
