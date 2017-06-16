package com.ifreework.service.attendance;

import java.util.ArrayList;
import java.util.Date;
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
		leaveBill.setStatus("0");
		leaveBill.setUserId(UserManager.getUser().getUserId());
		leaveBill.setCreateTime(new Date());
		leaveBillMapper.add(leaveBill);
		String processId = saveStartProcess(leaveBill.getLeaveBillId());
		leaveBill.setProcessId(processId);
		return update(leaveBill);
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

	private String saveStartProcess(String leaveBillId) { 
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("submitUser", UserManager.getUser().getUserId());// 提交人
		variables.put("secondUser", "1");// 初审人，admin
		variables.put("thirdUser1", "9991f4d7782a4ccfb8a65bd96ea7aafa");// 部门经理审批 lisi
		variables.put("thirdUser2", "e29149962e944589bb7da23ad18ddeed");// 总经理审批 zhangsan
		variables.put("leaveBillId", leaveBillId);
		return runtimeService.startProcessInstanceByKey(ACTIVITI_LEAVE_BILL_KEY,  variables).getId();
	}
	
	
	/**
	 * 
	 * 描述：个人提报请假申请
	 * @param executionId 
	 * @return
	 */
	public PageData submit(String processId){
		
		String userId = UserManager.getUser().getUserId();
		List<Task> tasks = taskService.createTaskQuery()//
				.taskAssignee(userId)//指定个人任务查询
				.processInstanceId(processId)
				.list();
		if(tasks != null && !tasks.isEmpty()){
			Task task = tasks.get(0);
			return complete(task.getId(),"1");
		}else{
			PageData pd = new PageData();
			pd.setResult(Constant.FAILED);
			pd.setMsg("当前请假任务不存在，提报失败");
			return pd;
		}
	}
	
	
	/**
	 * 描述：根据taskId,完成当前任务
	 * @param taskId 任务ID
	 * @param status  审批状态，0 未通过 1通过
	 * @return
	 */
	public PageData complete(String taskId,String status){
		PageData pd = new PageData();
		String leaveBillId = (String) taskService.getVariable(taskId, "leaveBillId");
		
		taskService.setVariable(taskId, "status", status);
		if("0".equals(status)){
			deleteTask(taskId);
		}
		LeaveBill leaveBill = getLeaveBill(leaveBillId);
		leaveBill.setStatus(status);
		update(leaveBill);
		taskService.complete(taskId);
		pd.setResult(Constant.SUCCESS);
		return pd;
	}
	
	/**
	 * 描述：删除其他分支的流程
	 * @param notDeleteTaskId 保存的流程
	 * @return
	 */
	private void deleteTask(String notDeleteTaskId){
		List<Task> list = taskService.createTaskQuery()
				.taskId(notDeleteTaskId).list();
		if(list != null && !list.isEmpty()){
			List<Task> tasks = taskService.createTaskQuery()
					.processInstanceId(list.get(0).getProcessInstanceId())
					.list();
			for (Task task : tasks) {
				String taskId = task.getId();
				if(!notDeleteTaskId.equals(taskId)){
					taskService.deleteTask(taskId);
				}
			}
		}
	}
	
	/**
	 * 描述：查询个人待办任务
	 * @return 
	 * @return
	 */
	public List<Map<String,Object>> queyTaskListByName() {
		String userId = UserManager.getUser().getUserId();
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		
		List<Task> tasks = taskService.createTaskQuery()//
					.taskAssignee(userId)//指定个人任务查询
					.processDefinitionKey(ACTIVITI_LEAVE_BILL_KEY)
					.orderByTaskCreateTime().asc()//
					.list();
		
		for (Task task : tasks) {
			Map<String,Object> map = new HashMap<String,Object>();
			String taskId = task.getId();
			String leaveBillId = (String) taskService.getVariable(taskId, "leaveBillId");
			LeaveBill leaveBill = getLeaveBill(leaveBillId);
			if("1".equals(leaveBill.getStatus())){
				map.put("taskId", taskId);
				map.put("taskName", task.getName());
				
				map.put("leaveBill", leaveBill);
				list.add(map);
			}
		}
		
		return list;
	}
	

}