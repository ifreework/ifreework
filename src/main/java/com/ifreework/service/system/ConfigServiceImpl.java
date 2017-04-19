package com.ifreework.service.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.util.StringUtil;
import com.ifreework.common.entity.PageData;
import com.ifreework.mapper.system.ConfigMapper;
import com.ifreework.util.Const;


@Service("configService")
public class ConfigServiceImpl  implements ConfigService {

	@Autowired
	private ConfigMapper configMapper;

	/**
	 * 批量跟新系统设置数据
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PageData update(PageData pd) {
		// TODO Auto-generated method stub
		PageData resultData = new PageData();
		for (Object key:pd.keySet()) {
			Map config = new HashMap();
			config.put("configKey", key);
			config.put("configValue", pd.get(key));
			configMapper.update(config);
		}
		resultData.setResult(Const.SUCCESS);
		return resultData;
	}


}