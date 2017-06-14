package com.ifreework.service.attendance;


import java.util.List;

import org.activiti.engine.task.Task;

import com.ifreework.common.entity.PageData;
import com.ifreework.entity.attendance.LeaveBill;

public interface LeaveBillService {
	public PageData queryPageList(PageData pd);
	public LeaveBill getLeaveBill(String leaveBillId);
	public PageData add(LeaveBill leaveBill);
	public PageData update(LeaveBill leaveBill);
	public PageData delete(String leaveBillId);
	public PageData saveStartProcess(String string);
	public List<Task> queyTaskListByName();
}
