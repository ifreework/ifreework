package com.ifreework.util;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;

import com.sun.management.OperatingSystemMXBean;

@SuppressWarnings("restriction")
public class WindowsInfoUtil {

	/**
	 * 描述：获取系统内存使用比例，百分比
	 * @return 
	 */
	public static double getMemeryScale () {
		OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

		// 总的物理内存+虚拟内存
		long totalvirtualMemory = osmxb.getTotalSwapSpaceSize();

		// 剩余的物理内存
		long freePhysicalMemorySize = osmxb.getFreePhysicalMemorySize();

		Double compare = (Double) (1 - freePhysicalMemorySize * 1.0 / totalvirtualMemory) * 100;
		return compare.intValue();
	}

	// 获取MAC地址的方法
	public static String getMACAddress() {
		InetAddress inetAddress;
		StringBuffer sb = new StringBuffer();
		try {
			inetAddress = InetAddress.getLocalHost();

			// 获得网络接口对象（即网卡），并得到mac地址，mac地址存在于一个byte数组中。
			byte[] mac = NetworkInterface.getByInetAddress(inetAddress).getHardwareAddress();
			
			for (int i = 0; i < mac.length; i++) {
				if (i != 0) {
					sb.append("-");
				}
				String s = Integer.toHexString(mac[i] & 0xFF);
				sb.append(s.length() == 1 ? 0 + s : s);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return sb.toString().toUpperCase();
	}

	
	
	public static void main(String[] args) {
		System.out.println(getMACAddress());
	}

}
