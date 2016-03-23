package com.kspt.core.moxingku.form.action;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kspt.common.JsonUtil;
import com.kspt.common.UtilFile;
import com.kspt.common.ZQResourceLoaderPath;
import com.kspt.common.dao.daoImp.BaseDaoImp;
import com.kspt.core.moxingku.form.engine.dao.impl.FormEngineDaoImpl;
import com.kspt.core.moxingku.form.engine.dao.impl.FormUiDaoImpl;
import com.kspt.core.moxingku.form.pojo.FormEntityRelationshipModel;
import com.kspt.core.moxingku.form.pojo.FormModel;
import com.kspt.core.moxingku.form.pojo.FormUIModel;
import com.kspt.core.moxingku.pojo.ModelLibrary;
import com.kspt.util.DataConnomImpl;

@RequestMapping("/moxingku/form/linkTable/ui")
@Controller
public class FormUIAction {
	@Resource
	private BaseDaoImp baseDao;
	@Resource
	private DataConnomImpl connomImpl;
	@Resource
	private FormEngineDaoImpl formEngineDaoImpl;

	@RequestMapping(method = { RequestMethod.POST, RequestMethod.GET })
	public String getrenyuan_companygrid(String form_er_id, String isMain,
			ModelMap map) {
		map.put("form_er_id", form_er_id);
		map.put("isMain", isMain);
		return "moxingku/formmoxing/form_linkTable_ui_grid";
	}

	@RequestMapping(value = "/gridJson", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody
	String getCompanyGridJson(String form_er_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<FormUIModel> listModel = (List<FormUIModel>) baseDao.query(
				"zq_form_ui.getFormUIbyFormErId", form_er_id);
		map.put("root", listModel);
		map.put("totalProperty", listModel.size());
		return JsonUtil.toJSONString(map);
	}

	@Transactional
	@RequestMapping(value = "/save", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody
	String save(String datas) {
		List<FormUIModel> list=JsonUtil.parseJavaBean(datas, FormUIModel.class);
		for (FormUIModel formUIModel : list) {
			baseDao.update("zq_form_ui.update", formUIModel);
		}
		return "操作完成!";
	}

	@RequestMapping(value = "/editFormHtml", method = { RequestMethod.POST,
			RequestMethod.GET })
	public String editFormHtml(String form_id, ModelMap map) {
		map.put("form_id", form_id);
		try {
			FormModel formModel=	(FormModel) baseDao.queryOne("zq_form.getFormById", form_id);
			ModelLibrary modelLibrary=	(ModelLibrary) baseDao.queryOne("zq_model_library.getmoldelibrarybyid", formModel.getModel_lib_id());

			map.put("htmlmoban", UtilFile.readAll(zQResourceLoaderPath.getTemplateCustomPath() +modelLibrary.getModel_name()+File.separator+ form_id+".html"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "moxingku/formmoxing/form_linkTable_editFormHtml";
	}

	@RequestMapping(value = "/getFormUiPage", method = { RequestMethod.POST,
			RequestMethod.GET })
	public String getFormUiPage(String ui_type, ModelMap modelMap, String id) {
		FormUiDaoImpl formUiDaoImpl = formEngineDaoImpl
				.getFormUIComponent(ui_type);
		FormUIModel formUIModel=(FormUIModel) baseDao.queryOne("form_ui.get", id);
		formUiDaoImpl.setFormUIModel(formUIModel);
		String page = formUiDaoImpl.getUiEditPage(modelMap);
		modelMap.put("id", id);
		modelMap.put("ui_type", ui_type.replace("UI_", ""));
		return page;
	}

	@Resource
	private ZQResourceLoaderPath zQResourceLoaderPath;

	@RequestMapping(value = "/test", method = { RequestMethod.POST,
			RequestMethod.GET })
	public String test(String name, ModelMap map2,HttpServletRequest request) {
		/* GroupTemplate groupTemplate = beetlGroupUtilConfiguration
				.getGroupTemplate();
		Template t;
		try {
			t = groupTemplate.getTemplate(zQResourceLoaderPath
					.getWebTemplateSystemPath() + "1.html");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("user", name);
			t.binding(map);
			t.render();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		map2.put("user", name); 
		//request.setAttribute("user", name);
		return "custom/1";

	}

	@RequestMapping(value = "/saveForm", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody
	String saveForm(String form_id, ModelMap map,String htmlmoban) {
		try {
			FormModel formModel=	(FormModel) baseDao.queryOne("zq_form.getFormById", form_id);
			ModelLibrary modelLibrary=	(ModelLibrary) baseDao.queryOne("zq_model_library.getmoldelibrarybyid", formModel.getModel_lib_id());

			UtilFile.write(zQResourceLoaderPath.getTemplateCustomPath()+ modelLibrary.getModel_name()+File.separator+ form_id+".html", htmlmoban);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "保存成功!";
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/createForm", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody
	String createForm(String form_id,int column, ModelMap map, HttpServletRequest request) {


		Map<String, Object> record = new HashMap<String, Object>();
		record.put("er_type", 0);
		record.put("form_id", form_id);
		FormModel formModel=	(FormModel) baseDao.queryOne("zq_form.getFormById", form_id);
		ModelLibrary modelLibrary=	(ModelLibrary) baseDao.queryOne("zq_model_library.getmoldelibrarybyid", formModel.getModel_lib_id());
		FormEntityRelationshipModel entityRelationshipModel = (FormEntityRelationshipModel) baseDao
				.queryOne("zq_form_er_er.getFormEr", record);
		List<FormUIModel> formUIModels = (List<FormUIModel>) baseDao.query(
				"zq_form_ui.getFormUIbyFormErId",
				entityRelationshipModel.getId());
		StringBuffer html = new StringBuffer();
		html.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\"/>");
		html.append("\r\n");
		html.append("<html>");
		html.append("\r\n");
		html.append("<head>");
		html.append("\r\n");
		html.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>");
		html.append("\r\n");
		html.append("<link href=\""+request.getContextPath()+"/chajian/bootstrap-3.3.5/dist/css/bootstrap.min.css\" rel=\"stylesheet\" type=\"text/css\" /> \r\n");
		html.append("<link href=\""+request.getContextPath()+"/chajian/uploadify/uploadify.css\" rel=\"stylesheet\" type=\"text/css\"  /> \r\n");
		html.append("<script type=\"text/javascript\" src=\""+request.getContextPath()+"/zq_js/jquery/jquery-1.9.1.js\"></script>");

		html.append("\r\n");
		html.append("<title></title>");
		html.append("\r\n");
		html.append("</head>");
		html.append("\r\n");
		html.append("<body>");
		html.append(" <nav   class=\"navbar navbar-default navbar-fixed-top site-navbar\" >\r\n");
		html.append("  <div class=\"container\"> \r\n");
		html.append(" <div class=\"navbar-header\"> \r\n");
		html.append("   <a class=\"navbar-brand\" href=\"#\">表单信息</a> \r\n");
		html.append("   </div> \r\n");
		html.append("      </div>\r\n");
		html.append("   </div>\r\n");
		html.append(" </div>\r\n");
		html.append("  </nav>\r\n");
		html.append("\r\n");
		html.append("<form name=\"zq_form\"  id=\"zq_form\" class=\"form-horizontal\" style=\"margin-top: 60px;\"> \r\n");
		html.append("\r\n");
		for (int i = 0; i < formUIModels.size(); i++) {
			FormUIModel formUIModel = formUIModels.get(i);
			String padding="";
			if(i==formUIModels.size()-1){
				padding="style=\"padding-bottom: 60px;\"";
			}
			String fenshu="col-sm-12";
			switch (column) {
			case 4:
				fenshu="col-sm-6";
				break;
			case 6:
				fenshu="col-sm-3";
				break;		
			default:
				break;
			}
			html.append("<div class=\"form-group "+fenshu+"\" "+padding+">");
			html.append("\r\n");
			html.append(" <label for=\""+formUIModel.getUi_name()+"\" class=\"col-sm-4 control-label\">"+formUIModel.getUi_title()+"</label>");
			html.append("\r\n");
			html.append(" <div class=\"col-sm-8\">");
			html.append("${"+formUIModel.getUi_name()+"}");
			html.append("</div>");
			html.append("</div>");
			html.append("\r\n");
		}
		html.append("\r\n");
		html.append("</form>");
		html.append("\r\n");
		html.append("<script type=\"text/javascript\" src=\""+request.getContextPath()+"/chajian/bootstrap-3.3.5/dist/js/bootstrap.min.js\"></script>\r\n");
		html.append("<script type=\"text/javascript\" src=\""+request.getContextPath()+"/chajian/uploadify/jquery.uploadify.js\"></script>\r\n");
		html.append("<script type=\"text/javascript\" src=\""+request.getContextPath()+"/chajian/bootstrap-3.3.5/bootstrap3-validation.js\"></script>\r\n");
		html.append("<script type=\"text/javascript\" src=\""+request.getContextPath()+"/chajian/My97DatePicker/WdatePicker.js\"></script>\r\n");
		html.append("<script type=\"text/javascript\" src=\""+request.getContextPath()+"/chajian/headroom.js-master/src/Headroom.js\"></script>\r\n");
		html.append(getScriptValue(formUIModels,form_id));
		html.append(" <nav   class=\"navbar navbar-default navbar-fixed-bottom site-navbar\" >\r\n");
		html.append("  <div class=\"container\" style=\"padding-top:10px;padding-bottom:10px;\">\r\n");
		html.append("     <div class=\"collapse navbar-collapse\" id=\"navbar\">\r\n");
		html.append("          <div class=\"nav navbar-nav navbar-right\">\r\n");
		html.append("    <span><a href=\"#\" class=\"btn btn-success\"  onclick='saveZqForm()'>保存 </a>");
		html.append("           </span>\r\n");
		html.append("    <span><a href=\"#\"   class=\"btn btn-info\" onclick='window.close();'>关闭</a>");
		html.append("           </span>\r\n");
		html.append("      </div>\r\n");
		html.append("   </div>\r\n");
		html.append(" </div>\r\n");
		html.append("  </nav>\r\n");
		html.append("</body>");
		html.append("\r\n");
		try {
			File f=new File(zQResourceLoaderPath.getTemplateCustomPath() + modelLibrary.getModel_name()+File.separator+form_id+".html");
			if (!f.getParentFile().exists()){
				f.getParentFile().mkdirs();
			}
			UtilFile.write(zQResourceLoaderPath.getTemplateCustomPath() +  modelLibrary.getModel_name()+File.separator+form_id+".html", html.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "生成成功!";
	}
	private String getScriptValue(List<FormUIModel>formUIModels,String form_id){
		StringBuffer buffer=new StringBuffer();
		buffer.append("<script type=\"text/javascript\"> \r\n");
		buffer.append("	$(function($) { \r\n");
		buffer.append("	$(\"#zq_form\").validation();\r\n");
		buffer.append("});                                           \r\n");  
		buffer.append("function saveZqForm(){  \r\n");       
		buffer.append("if ($(\"#zq_form\").valid() == false) { \r\n"); 

		buffer.append("	return false;                        \r\n"); 
		buffer.append("}                                     \r\n"); 
		buffer.append("var formObject=new Object();");
		if(formUIModels!=null&&formUIModels.size()>0){
			FormUIModel formUIModel=new FormUIModel();
			for (int i = 0; i < formUIModels.size(); i++) {
				formUIModel=formUIModels.get(i);
				FormUiDaoImpl formUiDaoImpl = formEngineDaoImpl
						.getFormUIComponent("UI_"+formUIModel.getUi_type());
				formUiDaoImpl.setFormUIModel(formUIModel);
				buffer.append(formUiDaoImpl.getJavaScriptValue()+" \r\n");
			}
		}
		buffer.append("var formObjectJson = JSON.stringify(formObject);                 \r\n");      
		buffer.append("$.post(\"/run/form/save.zq\", {  \r\n");      
		buffer.append("	formObjectJson :formObjectJson,                                 \r\n");      
		buffer.append("	form_id :'"+form_id+"'                                 \r\n");    
		buffer.append("}, function(data) {                           \r\n");      
		buffer.append("	alert(data);                                 \r\n");      
		buffer.append("});                                           \r\n");      
		buffer.append("}                                         \r\n");     
		buffer.append("</script>  \r\n");    
		return buffer.toString();

	}
}