package com.kspt.core.moxingku.datalist.conponent.system;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.kspt.core.moxingku.datalist.conponent.ButtonAddInterfaceAbs;
import com.kspt.core.moxingku.datalist.conponent.pojo.ButtonParamModel;
import com.kspt.core.moxingku.datalist.conponent.util.Constant;
import com.kspt.core.moxingku.datalist.pojo.DataList;

@Service
public class AddButton extends ButtonAddInterfaceAbs{

	@Override
	@PostConstruct
	public void addButton() {
		// TODO Auto-generated method stub
		super.toAddButton(AddButton.class);
	}

	@Override
	public String getButtonName() {
		// TODO Auto-generated method stub
		return Constant.SYSTEM_ADD;
	}

	@Override
	public String getButtonGroup() {
		// TODO Auto-generated method stub
		return Constant.SYSTEM_GROUP;
	}

	@Override
	public String getButtonJavascript(ButtonParamModel buttonParamModel) {
		// TODO Auto-generated method stub
	DataList dataList=buttonParamModel.getDataList();
		return "openAddFormWindow('"+dataList.getId()+"')";
	}

	@Override
	public String getButtonWeb() {
		// TODO Auto-generated method stub
		return "moxingku/datalist/button/datalist_button_common";
	}

}
