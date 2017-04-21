package com.tiexue.mcp.core.dto;

public class Pager {

	private int pageNo;          //当前页码  
	private int pageSize;        //每页行数  
	private int totalRecord;      //总记录数  
	private int totalPage;        //总页数 
	private int prePage;		//	上一页
	private int nextPage;		//下一页
	private int lastPageNo;        //最后一页
	private String firstPageUrl; //第一页地址
	private String prePageUrl;	 //上一页地址
	private String nextPageUrl;	 //下一页地址
	private String lastPageUrl;  //最后一页地址
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getPrePage() {
		return prePage;
	}
	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	public String getFirstPageUrl() {
		return firstPageUrl;
	}
	public void setFirstPageUrl(String firstPageUrl) {
		this.firstPageUrl = firstPageUrl;
	}
	public String getPrePageUrl() {
		return prePageUrl;
	}
	public void setPrePageUrl(String prePageUrl) {
		this.prePageUrl = prePageUrl;
	}
	public String getNextPageUrl() {
		return nextPageUrl;
	}
	public void setNextPageUrl(String nextPageUrl) {
		this.nextPageUrl = nextPageUrl;
	}
	public String getLastPageUrl() {
		return lastPageUrl;
	}
	public void setLastPageUrl(String lastPageUrl) {
		this.lastPageUrl = lastPageUrl;
	}
	public int getLastPageNo() {
		return lastPageNo;
	}
	public void setLastPageNo(int lastPageNo) {
		this.lastPageNo = lastPageNo;
	}

	
	 //获取分页信息
	 public Pager getPager(int pageNo,int pageSize,int totalRecord){
		Pager pager=new Pager();
		pager.setPageNo(pageNo);
		pager.setPageSize(pageSize);
		pager.setTotalRecord(totalRecord);
		//总页数
		int totalPage=0;
		totalPage=totalRecord/pageSize;
		int remNum=totalRecord%pageSize;
		if(remNum>0)
			totalPage+=1;
		pager.setTotalPage(totalPage);
		//最后页码
		int lastPageNo=totalRecord%pageSize>0?(totalRecord-totalRecord%pageSize):(totalRecord-pageSize);
		pager.setLastPageNo(lastPageNo);
		int prePage=-1;
		if(pageNo>0)
			prePage=pageNo-pageSize;
		pager.setPrePage(prePage);
		int nextPage=0;
		if(pageNo<lastPageNo)
			nextPage=pageNo+pageSize;
		pager.setNextPage(nextPage);
		return pager;
	}

}
