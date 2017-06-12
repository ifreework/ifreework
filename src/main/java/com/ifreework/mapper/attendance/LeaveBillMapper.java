package com.ifreework.mapper.attendance;
import java.util.List;

import com.ifreework.common.entity.PageData;
import com.ifreework.entity.attendance.LeaveBill;

public interface LeaveBillMapper  {
	public List<LeaveBill> queryPageList(PageData pd);
	public void add(LeaveBill leaveBill);
	public void update(LeaveBill leaveBill);
	public void delete(String leaveBillId);
}
