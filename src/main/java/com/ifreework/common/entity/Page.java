package com.ifreework.common.entity;


/**
 * 分页类
 * @author Wangyh QQ735789026
 * 创建时间：2014年6月28日
 */
public class Page {
	
	private int size = 50; //每页显示记录数
	private int page = 1;	//当前页
	
	private String result; 
	private String msg="";
	private Object rows=null;
	private Integer total = null;
	
	/**    
	 * size    
	 *    
	 * @return  the size    
	*/
	
	public int getSize() {
		return size;
	}
	/**    
	 * @param size the size to set    
	 */
	public void setSize(int size) {
		this.size = size;
	}
	
	/**    
	 * page    
	 *    
	 * @return  the page    
	*/
	
	public int getPage() {
		return page;
	}
	/**    
	 * @param page the page to set    
	 */
	public void setPage(int page) {
		this.page = page;
	}
	
	/**    
	 * result    
	 *    
	 * @return  the result    
	*/
	
	public String getResult() {
		return result;
	}
	/**    
	 * @param result the result to set    
	 */
	public void setResult(String result) {
		this.result = result;
	}
	
	/**    
	 * msg    
	 *    
	 * @return  the msg    
	*/
	
	public String getMsg() {
		return msg;
	}
	/**    
	 * @param msg the msg to set    
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	/**    
	 * rows    
	 *    
	 * @return  the rows    
	*/
	
	public Object getRows() {
		return rows;
	}
	/**    
	 * @param rows the rows to set    
	 */
	public void setRows(Object rows) {
		this.rows = rows;
	}
	
	/**    
	 * total    
	 *    
	 * @return  the total    
	*/
	
	public Integer getTotal() {
		return total;
	}
	/**    
	 * @param total the total to set    
	 */
	public void setTotal(Integer total) {
		this.total = total;
	}
	
	
}
