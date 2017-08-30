package com.tiexue.mcp.manage.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.tiexue.mcp.core.entity.WxBook;
import com.tiexue.mcp.core.entity.WxPlatformSign;
import com.tiexue.mcp.core.service.IWxBookService;
import com.tiexue.mcp.core.service.IWxPlatformSignService;
import com.tiexue.mcp.manage.dto.Paging;
import com.tiexue.mcp.manage.dto.ResultMsg;


@Controller
@RequestMapping("wxplatform")
public class WxPlatformSignController {
	private static Logger logger=Logger.getLogger(WxPlatformSignController.class);
	@Resource
	IWxPlatformSignService iWxPlatformSignSer;
	@Resource
	IWxBookService iWxBookService;
	private final int psize=20;
	@RequestMapping("list")
	public String getList(HttpServletRequest request, HttpServletResponse response) {
		try {
			int pindex=1;
			String pindexStr=request.getParameter("pindex");
			if(pindexStr!=null&&!pindexStr.isEmpty()){
				pindex=Integer.parseInt(pindexStr);
			}
			int pStart=(pindex-1)*psize;
			int pcount=iWxPlatformSignSer.getCount(" 1=1");
			//如果最后一页只有一条数据，则在删除时取上一页数据
			if(pStart>0&&pStart>=pcount){
				pStart=pStart-psize;
			}
			List<WxPlatformSign> platformSigns= iWxPlatformSignSer.getListByPage("1=1",pStart,psize);
			if(platformSigns!=null&&platformSigns.size()>0){
				for (WxPlatformSign wxPlatformSign : platformSigns) {
					WxBook wxBook= iWxBookService.selectByPrimaryKey(wxPlatformSign.getNovelid());
					if(wxBook!=null)
					 wxPlatformSign.setNovelname(wxBook.getName());
				}
			}
			Paging paging=new Paging();
			paging.setPcount(pcount);
			paging.setPsize(psize);
			paging.calcPtotalpages();
			if(pindex>paging.getPtotalpages()){
				pindex=paging.getPtotalpages();
			}
			paging.setPindex(pindex);
			request.setAttribute("platformSigns",platformSigns);
			request.setAttribute("paging",paging);
		} catch (Exception e) {
			logger.error("category/list error:"+e.getMessage());
		}
		return "wxplatform/list";
	}

	@RequestMapping("detail")
	public String getDetail(HttpServletRequest request, HttpServletResponse response, Integer id) {
		try {
			if (id != null && id > 0) {
				WxPlatformSign platformSign = iWxPlatformSignSer.selectByPrimaryKey(id);

				request.setAttribute("detail", platformSign);
			}
		} catch (Exception e) {
			logger.debug("getDetail exception: " + e);
		}
		return "wxplatform/save";
	}

	@RequestMapping("save")
	@ResponseBody
	public String save(HttpServletRequest request, HttpServletResponse response, Integer id) {
		WxPlatformSign platformSign = new WxPlatformSign();
		ResultMsg resultMsg = new ResultMsg();
		resultMsg.setOk(false);
		try {
			String sign = request.getParameter("sign");
			String platformname = request.getParameter("platformname");
			String novelidStr = request.getParameter("novelid");
			String moneyallStr = request.getParameter("moneyall");
			String remark = request.getParameter("remark");
			Integer novelId=0;
			long moneyall=0;
			// 更新
			if (id != null && id > 0) {
				platformSign = iWxPlatformSignSer.selectByPrimaryKey(id);
			} else {
				platformSign.setCreatetime(new Date());
			}
			if(novelidStr!=null&&!novelidStr.isEmpty()){
				novelId=Integer.parseInt(novelidStr);
			}
			if(moneyallStr!=null&&!moneyallStr.isEmpty()){
				moneyall=(long)Double.parseDouble(moneyallStr);
			}
			platformSign.setSign(sign);
			platformSign.setPlatformname(platformname);
			platformSign.setNovelid(novelId);
			platformSign.setMoneyall(moneyall);
			platformSign.setRemark(remark);
			// 更新
			if (id != null && id > 0) {
				int res = iWxPlatformSignSer.updateByPrimaryKey(platformSign);
				if (res > 0) {
					resultMsg.setOk(true);
					resultMsg.setMsg("更新成功");
				}
			} else {
				// 状态为正常
				int res = iWxPlatformSignSer.insert(platformSign);
				if (res > 0) {
					resultMsg.setOk(true);
					resultMsg.setMsg("新增成功");
				}
			}

		} catch (Exception e) {
			logger.debug(" save execption:"+e);
			resultMsg.setOk(false);
			resultMsg.setMsg("操作异常");
		}
		return JSON.toJSONString(resultMsg);
	}
	
	@RequestMapping("deleteModel")
	@ResponseBody
	public String deleteModel(HttpServletRequest request,Integer id){
		ResultMsg resultMsg = new ResultMsg();
		resultMsg.setOk(false);
		try {
			if(id!=null&&id>0){
				int res=iWxPlatformSignSer.deleteByPrimaryKey(id);
				resultMsg.setOk(true);
				resultMsg.setMsg("删除成功");
			}else{
				resultMsg.setMsg("参数异常");
			}
			
		} catch (Exception e) {
			logger.debug(" deleteModel error:"+e);
			resultMsg.setMsg("删除异常");
		}
		return JSON.toJSONString(resultMsg);
	}

	
}
