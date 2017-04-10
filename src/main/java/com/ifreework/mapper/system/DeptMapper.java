package com.ifreework.mapper.system;
import java.util.List;

import com.ifreework.entity.system.Dept;
import com.ifreework.util.PageData;

public interface DeptMapper  {
	public List<Dept> queryContacts(PageData pd);
}
