package com.ifreework.service.attendance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifreework.common.constant.Constant;
import com.ifreework.common.entity.PageData;
import com.ifreework.common.manager.UserManager;
import com.ifreework.entity.attendance.LeaveBill;
import com.ifreework.mapper.attendance.LeaveBillMapper;

@Service("leaveBillService")
public class LeaveBillServiceImpl implements LeaveBillService {

	private static final String ACTIVITI_LEAVE_BILL_KEY = "leave_bill";
	@Autowired
	private LeaveBillMapper leaveBillMapper;
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private TaskService taskService;
	
	public LeaveBill getLeaveBill(String leaveBillId) {
		return leaveBillMapper.getLeaveBill(leaveBillId);
	}

	@Override
	public PageData queryPageList(PageData pd) {
		pd.put("userId", UserManager.getUser().getUserId());
		List<LeaveBill> list = leaveBillMapper.queryPageList(pd);
		pd.setPagination(list);
		return pd;
	}

	@Override
	public PageData add(LeaveBill leaveBill) {
		PageData pd = new PageData();
		leaveBill.setStatus("0");
		leaveBill.setUserId(UserManager.getUser().getUserId());
		leaveBillMapper.add(leaveBill);
		pd.setResult(Constant.SUCCESS);
		return pd;
	}

	@Override
	public PageData update(LeaveBill leaveBill) {
		PageData pd = new PageData();
		leaveBillMapper.update(leaveBill);
		pd.setResult(Constant.SUCCESS);
		return pd;
	}

	@Override
	public PageData delete(String leaveBillId) {
		PageData pd = new PageData();
		leaveBillMapper.delete(leaveBillId);
		pd.setResult(Constant.SUCCESS);
		return pd;
	}

	public PageData saveStartProcess(String leaveBillId) { 
		PageData pd = new PageData();
		LeaveBill leaveBill = getLeaveBill(leaveBillId);

		// 2：更新请假单的请假状态从0变成1（初始录入-->审核中）
		leaveBill.setStatus("1");

		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("inputUser", UserManager.getUser());// 表示惟一用户
		variables.put("leaveBillId", leaveBillId);

		// 6：使用流程定义的key，启动流程实例，同时设置流程变量，同时向正在执行的执行对象表中的字段BUSINESS_KEY添加业务数据，同时让流程关联业务
		runtimeService.startProcessInstanceByKey(ACTIVITI_LEAVE_BILL_KEY, leaveBillId, variables);
		
		update(leaveBill);
		pd.setResult(Constant.SUCCESS);
		return pd;
	}
	
	/**
	 * 描述：查询个人待办任务
	 * @return 
	 * @return
	 */
	public List<Task> queyTaskListByName() {
		String userName = UserManager.getUsername();
		List<Task> list = taskService.createTaskQuery()//
					.taskAssignee(userName)//指定个人任务查询
					.orderByTaskCreateTime().asc()//
					.list();
		return list;
	}
	

}