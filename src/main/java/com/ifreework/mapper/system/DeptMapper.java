package com.ifreework.mapper.system;
import java.util.List;

import com.ifreework.common.entity.PageData;
import com.ifreework.entity.system.Dept;

public interface DeptMapper  {
	public List<Dept> queryContacts(PageData pd);
}
