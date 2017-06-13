package com.ifreework.service.attendance;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifreework.common.constant.Constant;
import com.ifreework.common.entity.PageData;
import com.ifreework.common.manager.UserManager;
import com.ifreework.entity.attendance.LeaveBill;
import com.ifreework.mapper.attendance.LeaveBillMapper;

@Service("leaveBillService")
public class LeaveBillServiceImpl  implements LeaveBillService {

	@Autowired
	private LeaveBillMapper leaveBillMapper;

	public LeaveBill getLeaveBill(String leaveBillId){
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

}