package com.ifreework.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;

import com.ifreework.entity.system.Msg;

/**
 * 描述：  定义常用静态常量
 * @author：wangyh qq735789026  
 * 创建时间：2016年7月2日 下午3:17:10    
 * 修改人：wangyh    
 * 修改时间：2016年7月2日 下午3:17:10    
 * 修改备注：    
 * @version 1.0
 */
public class Const {
	
	public static final String SYSTEM_NAME = "systemName"; //系统名称
	public static final String SESSION_SECURITY_CODE = "sessionSecCode";//验证码
	public static final String SESSION_USER = "sessionUser";			//session用的用户
	public static final String SESSION_ROLE_RIGHTS = "sessionRoleRights";
	public static final String sSESSION_ROLE_RIGHTS = "sessionRoleRights";
	public static final String SESSION_menuList = "menuList";			//当前菜单
	public static final String SESSION_allmenuList = "allmenuList";		//全部菜单
	public static final String SESSION_QX = "QX";
	public static final String SESSION_userpds = "userpds";			
	public static final String SESSION_USERROL = "USERROL";				//用户对象
	public static final String SESSION_USERNAME = "USERNAME";			//用户名
	
	
	public static final String TRUE = "T";
	public static final String FALSE = "F";
	public static final String LOGIN = "/login_toLogin.do";				//登录地址
	public static final String SYSTEM_CONFIG = "WEB-INF/classes/config/system.properties";	
	public static final String SYSNAME = "admin/config/SYSNAME.txt";	//系统名称路径
	public static final String PAGE	= "admin/config/PAGE.txt";			//分页条数配置路径
	public static final String EMAIL = "admin/config/EMAIL.txt";		//邮箱服务器配置路径
	public static final String SMS1 = "admin/config/SMS1.txt";			//短信账户配置路径1
	public static final String SMS2 = "admin/config/SMS2.txt";			//短信账户配置路径2
	public static final String FWATERM = "admin/config/FWATERM.txt";	//文字水印配置路径
	public static final String IWATERM = "admin/config/IWATERM.txt";	//图片水印配置路径
	public static final String WEIXIN	= "admin/config/WEIXIN.txt";	//微信配置路径
	public static final String WEBSOCKET = "admin/config/WEBSOCKET.txt";//WEBSOCKET配置路径
	public static final String FILEPATHIMG = "uploadFiles/uploadImgs/";	//图片上传路径
	public static final String FILEPATHFILE = "uploadFiles/file/";		//文件上传路径
	public static final String FILEPATHTWODIMENSIONCODE = "uploadFiles/twoDimensionCode/"; //二维码存放路径
	public static final String NO_INTERCEPTOR_PATH = ".*/((login)|(logout)|(code)|(app)|(weixin)|(resources)|(error)|(websocket)).*";	//不对匹配该值的访问路径拦截（正则）
	public static ApplicationContext WEB_APP_CONTEXT = null; //该值会在web容器启动时由WebAppContextListener初始化
	
	/**
	 * APP Constants
	 */
	//app注册接口_请求协议参数)
	public static final String[] APP_REGISTERED_PARAM_ARRAY = new String[]{"countries","uname","passwd","title","full_name","company_name","countries_code","area_code","telephone","mobile"};
	public static final String[] APP_REGISTERED_VALUE_ARRAY = new String[]{"国籍","邮箱帐号","密码","称谓","名称","公司名称","国家编号","区号","电话","手机号"};
	
	//app根据用户名获取会员信息接口_请求协议中的参数
	public static final String[] APP_GETAPPUSER_PARAM_ARRAY = new String[]{"USERNAME"};
	public static final String[] APP_GETAPPUSER_VALUE_ARRAY = new String[]{"用户名"};
	
	public static final Map<String,Object> WEBSOCKET_USER_MAP = new HashMap<String, Object>();//缓存websocket登录用户
	public static final Map<String,List<Msg>> MSG_MAP= new HashMap<String,List<Msg>>(); //用于缓存过长的聊天记录
	
	
	
	public static final String SUCCESS = "10000"; // 成功
	public static final String PAYING = "10003"; // 用户支付中
	public static final String ERROR = "20000"; // 系统异常
	public static final String NOAUTH = "20001"; // 权限不足
	public static final String NOPARAM = "40001"; // 缺少必要参数
	public static final String PARAMERROR = "40002"; // 非法参数
	
	public static final String FAILED = "40004"; // 失败

	
	public static final String UNKNOWN = "50000"; // 系统异常
	public static final String NETERROR = "50001"; // 网络连接异常
	public static final String FILEPATH = "50003"; // 读取配置文件异常
	public static final String REQUESTERROR = "50004"; //请求参数未通过检验
	public static final String PID_NOT_FOUND = "50005"; // pid不存在
	
	public static final String TRADE_NOT_EXIST = "ACQ.TRADE_NOT_EXIST";//关闭时订单不存在
	public static final String TRADE_STATUS_ERROR = "ACQ.TRADE_STATUS_ERROR";//关闭时订单状态不存在，通常为已经支付或者订单已经关闭
	
	
	public static final String TRADE_CLOSED = "TRADE_CLOSED"; //单据已经关闭
	public static final String TRADE_SUCCESS = "TRADE_SUCCESS";//订单支付成功状态
	public static final String TRADE_FINISHED = "TRADE_FINISHED";//交易结束，不可退款
	public static final String WAIT_BUYER_PAY = "WAIT_BUYER_PAY";//等待买家付款
	
}
