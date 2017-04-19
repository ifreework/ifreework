
/**    
 * 文件名：PageData.java    
 *    
 * 版本信息：    
 * 日期：2016年7月4日    
 * Copyright  Corporation 2016     
 * 版权所有    
 *    
 */
package com.ifreework.common.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.ifreework.util.ReflectUtil;
import com.ifreework.util.StringUtil;

/**
 * 描述：
 * 
 * @author：wangyh qq735789026
 * @创建时间：2016年7月4日 下午2:48:57
 * @修改人：wangyh
 * @修改时间：2016年7月4日 下午2:48:57
 * @version 1.0
 */
@SuppressWarnings("rawtypes")
public class PageData extends HashMap implements Map {
	private static final Logger log = Logger.getLogger(PageData.class);
	private static final long serialVersionUID = 1L;
	private static final String[] COLUMN_FIELDS = { "data", "name" };

	/**
	 * 描述：获取分页事，每页显示多少条数据 @Title: getLength @param @return @throws
	 */
	public Integer getLength() {
		return (Integer) this.get("length");
	}

	/**
	 * 
	 * 描述：设置分页时，每页的显示的数据条数 @Title: setLength @param @return @throws
	 */
	public void setLength(Integer length) {
		this.put("length", length);
	}

	/**
	 * 获取页码 描述： @Title: getStart @param @return @throws
	 */
	public Integer getStart() {
		return (Integer) this.get("start");
	}

	/**
	 * 描述：设置页码，从1开始 @Title: setStart @param start 当前页数 @return @throws
	 */
	public void setStart(Integer start) {
		this.put("start", start == 0 ? 1 : start);
	}

	/**
	 * 描述：获取查询总数，分页中使用 @Title: getRecordsFiltered @param @return @throws
	 */
	public long getRecordsFiltered() {
		Long recordsFiltered = (Long) this.get("recordsFiltered");
		if (recordsFiltered == null) {
			recordsFiltered = 0L;
		}
		return recordsFiltered;
	}

	/**
	 * 
	 * 描述：设置查询总数 @Title: setRecordsFiltered @param @return @throws
	 */
	public void setRecordsFiltered(long i) {
		this.put("recordsFiltered", i);
	}

	/**
	 * 描述：设置分页信息 @Title: setPagination @param @return @throws
	 */
	public void setPagination(List list) {
		if (list instanceof Page) {
			Page p = (Page) list;
			long i = p.getTotal();
			this.setData(list);
			this.setRecordsFiltered(i);
		} else {
			log.error(list.getClass() + " is not a Page Object!");
		}
	}

	/**
	 * 
	 * 描述：获取返回结果 @Title: getResult @param @return @throws
	 */
	public String getResult() {
		return this.getString("result");
	}

	/**
	 * 设置返回结果 描述： @Title: setResult @param @return @throws
	 */
	public void setResult(String result) {
		this.put("result", result);
	}

	/**
	 * 
	 * 描述：获取描述信息 @Title: getMsg @param @return @throws
	 */
	public String getMsg() {
		return this.getString("msg");
	}

	/**
	 * 描述：设置描述信息 @Title: setMsg @param @return @throws
	 */
	public void setMsg(String msg) {
		this.put("msg", msg);
	}

	/**
	 * 
	 * 描述：获取返回数据 @Title: getData @param @return @throws
	 */
	public Object getData() {
		return this.getString("data");
	}

	/**
	 * 设置返回数据 描述： @Title: setData @param @return @throws
	 */
	public void setData(Object data) {
		this.put("data", data);
	}

	Map map = null;
	HttpServletRequest request;

	@SuppressWarnings("unchecked")
	public PageData(HttpServletRequest request) {
		this.request = request;
		Map properties = request.getParameterMap();
		Map returnMap = new HashMap();
		Iterator entries = properties.entrySet().iterator();
		Map.Entry entry;
		String name = "";
		String value = "";
		while (entries.hasNext()) {
			entry = (Map.Entry) entries.next();
			name = (String) entry.getKey();
			Object valueObj = entry.getValue();
			if (null == valueObj) {
				value = "";
			} else if (valueObj instanceof String[]) {
				String[] values = (String[]) valueObj;
				for (int i = 0; i < values.length; i++) {
					value = values[i] + ",";
				}
				value = value.substring(0, value.length() - 1);
			} else {
				value = valueObj.toString();
			}
			returnMap.put(name, value);
		}
		setColumns(returnMap);
		setOrder(returnMap);
		map = returnMap;
		log.info("The page param is " + JSON.toJSONString(map));
	}

	/**
	 * 
	 * 描述：初始化入参中的字段内容 @Title: setColumns @param @return @throws
	 */
	@SuppressWarnings("unchecked")
	private void setColumns(Map map) {
		List<Column> columns = new ArrayList<Column>();
		map.put("columns", columns);
		try {
			for (int i = 0; i > -1; i++) {
				Column column = columns.size() > i ? columns.get(i) : new Column();
				columns.add(i, column);
				for (String columnField : COLUMN_FIELDS) {
					String key = "columns[" + i + "][" + columnField + "]";
					Object columnValue = map.remove(key);
					if (columnValue == null) {
						columns.remove(i);
						return;
					} else {
						ReflectUtil.setValueByFieldName(column, columnField, columnValue);
					}
				}
			}

		} catch (SecurityException | NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("Column set value error!" + e.getMessage());
		}
	}

	/**
	 * 
	 * 描述：设置字段的排序规则 @Title: setOrder @param @return @throws
	 */
	@SuppressWarnings("unchecked")
	private void setOrder(Map map) {
		List<Column> columns = (List<Column>) map.get("columns");
		if (columns != null && !columns.isEmpty()) {
			for (int i = 0; i > -1; i++) {
				String orderKey = "order[" + i + "][column]";
				String orderStr = (String) map.remove(orderKey);
				if (!StringUtil.isEmpty(orderStr)) {
					Integer index = Integer.parseInt(orderStr);
					Column column = columns.get(index);
					if (column != null) {
						column.setOrderable(true);
						orderKey = "order[" + i + "][dir]";
						orderStr = (String) map.remove(orderKey);
						if (StringUtil.isEmpty(orderStr)) {
							orderStr = "asc";
						}
						column.setOrderDir(orderStr);
					} else {
						break;
					}
				} else {
					break;
				}
			}
		}
	}

	public PageData() {
		map = new HashMap();
	}

	@Override
	public Object get(Object key) {
		Object obj = null;
		if (map.get(key) instanceof Object[]) {
			Object[] arr = (Object[]) map.get(key);
			obj = request == null ? arr : (request.getParameter((String) key) == null ? arr : arr[0]);
		} else {
			obj = map.get(key);
		}
		return obj;
	}

	public String getString(Object key) {
		return (String) get(key);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object put(Object key, Object value) {
		return map.put(key, value);
	}

	@Override
	public Object remove(Object key) {
		return map.remove(key);
	}

	public void clear() {
		map.clear();
	}

	public boolean containsKey(Object key) {
		// TODO Auto-generated method stub
		return map.containsKey(key);
	}

	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return map.containsValue(value);
	}

	public Set entrySet() {
		// TODO Auto-generated method stub
		return map.entrySet();
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return map.isEmpty();
	}

	public Set keySet() {
		// TODO Auto-generated method stub
		return map.keySet();
	}

	@SuppressWarnings("unchecked")
	public void putAll(Map t) {
		// TODO Auto-generated method stub
		map.putAll(t);
	}

	public int size() {
		// TODO Auto-generated method stub
		return map.size();
	}

	public Collection values() {
		// TODO Auto-generated method stub
		return map.values();
	}
}
