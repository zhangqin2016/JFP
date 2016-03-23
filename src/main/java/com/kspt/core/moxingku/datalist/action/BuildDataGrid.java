package com.kspt.core.moxingku.datalist.action;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.kspt.common.UtilFile;
import com.kspt.common.ZQResourceLoaderPath;
import com.kspt.common.ZqConstant;
import com.kspt.core.moxingku.datalist.conponent.ButtonAddInterface;
import com.kspt.core.moxingku.datalist.conponent.cache.ButtonConponentCache;
import com.kspt.core.moxingku.datalist.conponent.pojo.ButtonParamModel;
import com.kspt.core.moxingku.datalist.pojo.DataListButton;
import com.kspt.core.moxingku.datalist.pojo.DataListColumn;
import com.kspt.core.moxingku.datalist.pojo.DataListQuery;
import com.kspt.core.moxingku.datalist.service.DataListService;
import com.kspt.core.moxingku.datalist.util.DataListComponent;
import com.kspt.core.moxingku.datalist.util.DataListConstant;

@Controller
@RequestMapping("/zq/datalist/build")
public class BuildDataGrid {
	@Resource 
	private DataListComponent dataListComponent;
	@Resource
	private ZQResourceLoaderPath loaderPath;
	@Resource
	private  DataListService dataListService;
	@RequestMapping(method = { RequestMethod.POST })
	public @ResponseBody
	String buildPage(String type, String id,HttpServletRequest request) {
		try {
			String path = loaderPath.getDataGrid() + File.separator
					+ dataListComponent.getModelName(id) + File.separator + id + ".html";
			File f = new File(path);
			if (!f.getParentFile().exists()) {
				f.getParentFile().mkdirs();
			}
			f.createNewFile();
			UtilFile.write(path, buildPageByType(type, id,request));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ZqConstant.BUILD_SUCCESS;
	}

	private String buildPageByType(String type, String id,HttpServletRequest request) {
		if (type.equals(DataListConstant.DATAGRID_TYPE_EXT)) {
			return bulidExtPage(id);
		} 
		else if (type.equals(DataListConstant.DATAGRID_TYPE_BOOTGRID)) {
			return bulidBootgridPage(id,request);
		}
		return null;
	}

	private String bulidExtPage(String id) {
		return null;
	}
	private String bulidBootgridPage(String id,HttpServletRequest request) {
		String ctx=request.getContextPath();
		StringBuffer htmlTable = new StringBuffer();
		htmlTable
		.append("<!DOCTYPE html>\r\n");
		htmlTable
		.append("<html> \r\n");
		htmlTable
		.append("<head> \r\n");
		htmlTable
		.append("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'> \r\n");
		htmlTable
		.append("<meta http-equiv='X-UA-Compatible' content='IE=edge'> \r\n");
		htmlTable.append("<link rel='stylesheet' href='"+ctx+"/chajian/jquery-bootgrid-master/demo/css/bootstrap.css' />   \r\n"); 
		htmlTable.append(" <link rel='stylesheet' href='"+ctx+"/chajian/jquery-bootgrid-master/dist/jquery.bootgrid.css' />\r\n"); 
		htmlTable.append(" <link rel='stylesheet' href='"+ctx+"/chajian/craftpip-jquery-confirm-00f7956/css/jquery-confirm.css' />\r\n"); 
		htmlTable.append(" <script type='text/javascript' src='"+ctx+"/chajian/jquery-bootgrid-master/demo/js/moderniz.2.8.1.js'></script>");
		htmlTable.append("<title></title>\r\n");
		htmlTable.append("</head> \r\n");
		htmlTable.append("<body> \r\n");
		htmlTable.append(" <nav   class=\"navbar navbar-default navbar-static-top\" >\r\n");
		htmlTable.append("  <div class=\"container\"> \r\n");
		htmlTable.append(" <div class=\"navbar-header\"> \r\n");
		htmlTable.append("   <a class=\"navbar-brand\" href=\"#\">"+dataListService.get(id).getDisplayTitle()+"</a> \r\n");
		htmlTable.append("   </div> \r\n");
		htmlTable.append(" </div>\r\n");
		htmlTable.append("  </nav>\r\n");
		htmlTable.append("<div class='container-fluid'  >\r\n");
		htmlTable.append(getQuerys(id).getString("html"));
		htmlTable.append("<div class='row'> \r\n");
		htmlTable.append(" <div class='col-md-12' style=\"border-top: 1px solid #ddd\">  \r\n");
		htmlTable.append(" <table class='table table-condensed table-hover table-striped' id='datalist_"+id+"' style=\"border-top: 2px solid #ddd\"> \r\n");
		htmlTable.append(" <thead> \r\n");
		htmlTable.append("<tr> \r\n");
		htmlTable.append(getTableHead(id));
		htmlTable.append("</tr> \r\n");
		htmlTable.append("</thead> \r\n");
		htmlTable.append(" </table> \r\n");
		htmlTable.append("</div>\r\n");
		htmlTable.append("</div>\r\n");
		htmlTable.append("</div>\r\n");
		htmlTable.append("<script type='text/javascript' src='"+ctx+"/chajian/jquery-bootgrid-master/lib/jquery-1.11.1.min.js'></script> \r\n");
		htmlTable.append("<script type='text/javascript' src='"+ctx+"/chajian/jquery-bootgrid-master/demo/js/bootstrap.js'></script>\r\n");
		htmlTable.append("<script type='text/javascript' src='"+ctx+"/chajian/jquery-bootgrid-master/dist/jquery.bootgrid.js'></script>  \r\n");
		htmlTable.append(" <script type='text/javascript' src='"+ctx+"/zq_js/run/run.js'></script>\r\n");
		htmlTable.append(" <script type='text/javascript' src='"+ctx+"/chajian/craftpip-jquery-confirm-00f7956/js/jquery-confirm.js'></script>\r\n");
		htmlTable.append(" <script type=\"text/javascript\">\r\n");
		htmlTable.append(" function enterToQuery(event){ \r\n");
		htmlTable.append("   var e = event || window.event || arguments.callee.caller.arguments[0];\r\n");
		htmlTable.append(" 	if(e.keyCode == 13){ \r\n");
		htmlTable.append("   $('#querySearch').click(); \r\n");
		htmlTable.append(" } \r\n");
		htmlTable.append(" } \r\n");
		htmlTable.append("function openAddFormWindow(){ \r\n");
		htmlTable.append(" window.open(\""+ctx+"/run/form/page.zq?datalistId="+id+"\",\"_blank\"); \r\n");
		htmlTable.append(" } \r\n");
		htmlTable.append(" $(function($){   \r\n"); 
		htmlTable.append(" $('#reset').click(function(){  \r\n");
		htmlTable.append(" $('#queryDiv').find('input,select').val(''); \r\n");
		htmlTable.append("  }); \r\n");
		htmlTable.append(" $('#querySearch').click(function(){  \r\n");
		htmlTable.append(" $(\"#datalist_"+id+"\").bootgrid('reload');  \r\n");
		htmlTable.append("  }); \r\n");

		htmlTable.append(" function getQueryString(){  \r\n");  
		htmlTable.append(getQuerys(id).getString("query")==null?"var queryString;":getQuerys(id).getString("query"));
		htmlTable.append(" return queryString; \r\n");  
		htmlTable.append("  } \r\n");  


		htmlTable.append("$(\"#datalist_"+id+"\").bootgrid({ \r\n");  
		htmlTable.append("ajax: true,   \r\n");  
		htmlTable.append("selection: true,  \r\n");  
		htmlTable.append("multiSelect: true, \r\n");  
		htmlTable.append("rowSelect: true,\r\n");  
		htmlTable.append("keepSelection: true, \r\n"); 
		htmlTable.append("post: function ()  \r\n");  
		htmlTable.append("{  \r\n");  
		htmlTable.append("   return {  \r\n");  
		htmlTable.append("   datalist_id: \""+id+"\" ,\r\n");  
		htmlTable.append("   querys:getQueryString()\r\n");  
		htmlTable.append("   };  \r\n");  
		htmlTable.append("}, \r\n");  
		htmlTable.append("url: \"/run/form/bootgrid/json.zq\"   \r\n"); 
		htmlTable.append("}).on(\"loaded.rs.jquery.bootgrid\", function (e){;\r\n");  
		htmlTable.append("$(\"#datalist_"+id+"-header\").find(\"#datalist_"+id+"-header_button\").html(\""+putButton(id, request)+"\") ;  \r\n"); 
		htmlTable.append(" });   \r\n");  
		htmlTable.append(" });   \r\n");  
		htmlTable.append(" </script>\r\n");
		htmlTable.append("</body>\r\n");
		htmlTable.append("</html>\r\n");
		return htmlTable.toString();
	}


	private String getTableHead(String id) {
	
		List<DataListColumn> columns=dataListComponent.getDataListColumnsByDataListId(id);
		if(columns==null||columns.size()==0){
			return "";
		}
		StringBuffer htmlColumn = new StringBuffer();
		for (DataListColumn dataListColumn : columns) {
			String ischecke="";
			if(dataListColumn.getTableField().equals(dataListService.get(id).getTable_id())){
				ischecke="data-identifier='true' data-type='numeric'";
			}
			String isIs_hidden="";
			if(dataListColumn.isIs_hidden()){
				isIs_hidden="data-visible='false'";
			}
			htmlColumn.append("<th data-column-id='"+dataListColumn.getTableField()+"' "+ischecke+"  "+isIs_hidden+" >");
			htmlColumn.append(dataListColumn.getTitle()+"");
			htmlColumn.append("</th> \r\n");
		}
		return htmlColumn.toString();
	}
	private String putButton(String id,HttpServletRequest request){
		List<DataListButton> buttons=dataListComponent.getDataListButtonsByDataListId(id);
		if(buttons==null||buttons.size()==0){
			return "";
		}
		/**/
		StringBuffer htmlButton = new StringBuffer();
		for (DataListButton dataListButton : buttons) {
			String script=getButtonScript(dataListButton.getButtonId(), id,  request);
			htmlButton.append(" <button type='button' onclick=\\\""+script+"\\\" class='btn btn-default'>"+dataListButton.getTitle()+"</button> ");
		}
		return htmlButton.toString();

	}
	@Resource
	private ButtonConponentCache buttonConponentCache;
	private  String getButtonScript(String buttonId,String dataListId,HttpServletRequest request){
		ButtonParamModel buttonParamModel=new ButtonParamModel();
		buttonParamModel.setHttpServletRequest(request);
		buttonParamModel.setDataList(dataListService.get(dataListId));
		ButtonAddInterface buttonAddInterface=dataListService.getButtonComponent(buttonId);
		return buttonAddInterface.getButtonJavascript(buttonParamModel);

	}
	private JSONObject getQuerys(String id){
		JSONObject query=new JSONObject();
		List<DataListQuery> querys=dataListComponent.getDataListQuerysByDataListId(id);
		query.put("html", "");
		if(querys==null){
			return query;
		}
		Integer size=querys.size();
		if(size==0){
			return query;
		}

		StringBuffer htmlQuery = new StringBuffer();
		DecimalFormat df = new DecimalFormat("0");
		String width=df.format(new Double(1.0/size)*90)+"%";
		if(size>3){
			width="24.5%";
		}
		/*<label for="exampleInputName2">Name</label> <input type="text" class="form-control" id="exampleInputName2" placeholder="">*/
		htmlQuery.append("<div id='queryDiv'> \r\n");
		htmlQuery.append("<div class='row' > \r\n");
		htmlQuery.append(" <div class='col-md-12'>  \r\n");
		htmlQuery.append("<form name='queryForm' class='form-inline'> \r\n");
		StringBuffer queryScript  =new StringBuffer("var objQuery=new Object(); \r\n");
		for (DataListQuery dataListQuery : querys) {
			String title=(dataListQuery.getTitle().length()>5)?(dataListQuery.getTitle().substring(0, 4)+"..."):dataListQuery.getTitle();
			htmlQuery.append("<div class='form-group' style='width:"+width+";margin-top:10px;'> <label title='"+dataListQuery.getTitle()+"' style='width:100px;' for='"+dataListQuery.getQueryField()+"'>"+title+"：</label> <input onkeyup='enterToQuery(event);' type='text' class='form-control' id='"+dataListQuery.getQueryField()+"' name='"+dataListQuery.getQueryField()+"' placeholder=''> </div> \r\n");
			queryScript.append("objQuery.query_"+dataListQuery.getId()+"=$('#"+dataListQuery.getQueryField()+"').val(); \r\n");
		}
		queryScript.append("var queryString=JSON.stringify(objQuery); \r\n");
		htmlQuery.append("</form>\r\n");
		htmlQuery.append("</div>\r\n");
		htmlQuery.append("</div>\r\n");
		htmlQuery.append("<div class='row' style='margin:10px;'> \r\n");
		htmlQuery.append(" <div class='col-md-5'>  \r\n");
		htmlQuery.append("</div>\r\n");
		htmlQuery.append(" <div class='col-md-1'>  \r\n");
		htmlQuery.append(" <button type='button' class='btn btn-default' id='querySearch'><span class=\"glyphicon glyphicon-search\" aria-hidden=\"true\"></span> 查询</button> \r\n");
		htmlQuery.append("</div>\r\n");
		htmlQuery.append(" <div class='col-md-1'>  \r\n");
		htmlQuery.append(" <button type='button' class='btn btn-default' id='reset'><span class=\"glyphicon glyphicon-repeat\" aria-hidden=\"true\"></span> 重置</button> \r\n");
		htmlQuery.append("</div>\r\n");
		htmlQuery.append(" <div class='col-md-5'>  \r\n");
		htmlQuery.append("</div>\r\n");
		htmlQuery.append("</div>\r\n");
		htmlQuery.append("</div>\r\n");
		query.put("html", htmlQuery.toString());
		query.put("query", queryScript);
		return query;

	}

}
