package com.tiexue.mcp.core.dto;

/**
 * 充值统计类
 * @author zhangxiaowei
 *
 */
public class WxPayStatsDto {

	/**
	 * 推广标识
	 */
	private String sign;
	/**
	 * 小说Id
	 */
	private int novelId;
	
	/**
	 * 小说名称
	 */
	private String title;
	
	/**
	 * 推广充值总金额
	 */
	private int totalPayMoney;
	/**
	 * 今日支付金额
	 */
	private int todayPayMoney;
	
	/**
	 * 匿名登录数
	 */
	private int anonymityLogin;
	/**
	 * 支付人数
	 */
	private int payCount;
	/**
	 * 关注公众号人数
	 */
	private int followCount;
	/**
	 * 总消费数
	 */
	private int totalConsumeMoney;
	/**
	 * 今日消费金额
	 */
	private int todayConsumeMoney;
	
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public int getNovelId() {
		return novelId;
	}
	public void setNovelId(int novelId) {
		this.novelId = novelId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getTotalPayMoney() {
		return totalPayMoney;
	}
	public void setTotalPayMoney(int totalPayMoney) {
		this.totalPayMoney = totalPayMoney;
	}
	public int getTodayPayMoney() {
		return todayPayMoney;
	}
	public void setTodayPayMoney(int todayPayMoney) {
		this.todayPayMoney = todayPayMoney;
	}
	public int getAnonymityLogin() {
		return anonymityLogin;
	}
	public void setAnonymityLogin(int anonymityLogin) {
		this.anonymityLogin = anonymityLogin;
	}
	public int getPayCount() {
		return payCount;
	}
	public void setPayCount(int payCount) {
		this.payCount = payCount;
	}
	public int getFollowCount() {
		return followCount;
	}
	public void setFollowCount(int followCount) {
		this.followCount = followCount;
	}
	public int getTotalConsumeMoney() {
		return totalConsumeMoney;
	}
	public void setTotalConsumeMoney(int totalConsumeMoney) {
		this.totalConsumeMoney = totalConsumeMoney;
	}
	public int getTodayConsumeMoney() {
		return todayConsumeMoney;
	}
	public void setTodayConsumeMoney(int todayConsumeMoney) {
		this.todayConsumeMoney = todayConsumeMoney;
	}
	
	
}
