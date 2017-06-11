package com.ifreework.service.attendance;


import com.ifreework.common.entity.PageData;
import com.ifreework.entity.attendance.LeaveBill;

public interface LeaveBillService {
	public PageData queryPageList(PageData pd);
	public PageData add(LeaveBill leaveBill);
	public PageData update(LeaveBill leaveBill);
	public PageData delete(String leaveBillId);
}
