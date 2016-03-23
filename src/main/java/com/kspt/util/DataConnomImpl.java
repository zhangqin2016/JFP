package com.kspt.util;

import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kspt.common.dao.daoImp.BaseDaoImp;

@Service
public class DataConnomImpl implements DataConnom {
	@Resource
	private BaseDaoImp baseDao;

	public int getSequence(String sequence_name) {

		if (updateSequence(sequence_name) < 1) {
			insertSequence(sequence_name);
		}
		return getSequenceValue(sequence_name);
	}

	public String getUUID() {
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.replaceAll("-", "");
		return uuid;
	}

	private int getSequenceValue( String sequence_name) {
		return (Integer) baseDao.queryOne("data_base_common.getSequenceValue",sequence_name);

	}

	private int insertSequence( String sequence_name) {
		return baseDao.insert("data_base_common.insertSequence", sequence_name);

	}

	private int updateSequence( String sequence_name) {
		return baseDao.update("data_base_common.updateSequence", sequence_name);

	}

}
