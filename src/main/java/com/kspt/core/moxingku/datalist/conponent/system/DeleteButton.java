package com.kspt.core.moxingku.datalist.conponent.system;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.kspt.core.moxingku.datalist.conponent.ButtonAddInterfaceAbs;
import com.kspt.core.moxingku.datalist.conponent.pojo.ButtonParamModel;
import com.kspt.core.moxingku.datalist.conponent.util.Constant;
import com.kspt.core.moxingku.datalist.pojo.DataList;

@Service
public class DeleteButton extends ButtonAddInterfaceAbs{

	@Override
	@PostConstruct
	public void addButton() {
		// TODO Auto-generated method stub
		super.toAddButton(DeleteButton.class);
	}
	
	@Override
	public String getButtonName() {
		// TODO Auto-generated method stub
		return Constant.SYSTEM_DELETE;
	}

	@Override
	public String getButtonGroup() {
		// TODO Auto-generated method stub
		return Constant.SYSTEM_GROUP;
	}

	@Override
	public String getButtonJavascript(ButtonParamModel buttonParamModel) {
		// TODO Auto-generated method stub
		DataList data_list=buttonParamModel.getDataList();
		StringBuffer javascript=new StringBuffer();
		javascript.append("deleteData('"+data_list.getId()+"');");
		return javascript.toString();
	}

	@Override
	public String getButtonWeb() {
		// TODO Auto-generated method stub
		return "moxingku/datalist/button/datalist_button_common";
	}

}
