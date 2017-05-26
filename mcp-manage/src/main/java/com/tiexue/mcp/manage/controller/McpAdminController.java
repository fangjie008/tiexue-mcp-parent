package com.tiexue.mcp.manage.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONObject;
import com.tiexue.mcp.base.config.CustomizedPropertyConfigurer;
import com.tiexue.mcp.core.entity.McpAdmin;
import com.tiexue.mcp.core.service.IMcpAdminService;

@Controller
@RequestMapping("admin")
public class McpAdminController {

	/**
	 * logger
	 */
	private static Logger logger = Logger.getLogger(McpAdminController.class);

	@Resource
	private IMcpAdminService mcpAdminSer;

	/**
	 * 后台跳转地址
	 */
	@Value("back.adminPath")
	private String adminPath;

	private String getAdminPath() {
		return (String) CustomizedPropertyConfigurer.getContextProperty(adminPath);
	}

	/**
	 * 登录页面 和配置文件相结合, 会自动调用subject.login
	 */
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(HttpServletRequest req, Model model) {
		return "mcpAdmin/login";
	}

	/**
	 * 实际登录操作
	 **/
	@RequestMapping(value = "doLogin", method = RequestMethod.POST)
	public String doLogin(HttpServletRequest request, Model model) {
		Subject subject = SecurityUtils.getSubject();
		String userName = request.getParameter("username");
		String password = request.getParameter("password");

		System.out.println(userName);
		System.out.println(password);

		UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
		token.setRememberMe(true);

		String error = null;
		try {
			subject.login(token); // 执行登录操作
			if (subject.isAuthenticated()) {
				return "redirect:/mcphome/homepage";
			} else {
				return "redirect:/login.jsp";
			}
		} catch (Exception e) {
			error = "错误：" + e.getMessage();
			return "redirect:/login.jsp";
		}
		// return "redirect:/mcphome/homepage"; // 转到主页面
	}

	/**
	 * 查询用户列表
	 **/
	// @RequiresRoles("admin")
	@RequestMapping("list")
	public String getList(Model model) {
		model.addAttribute("userList", mcpAdminSer.getList());
		return "mcpAdmin/list";
	}

	/**
	 * 添加用户
	 **/
	// @RequiresRoles("admin")
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String showAddUser() {
		return "mcpAdmin/add";
	}

	/**
	 * 创建用户逻辑
	 **/
	// @RequiresRoles("admin")
	@RequestMapping(value = "doAddUser", method = RequestMethod.POST)
	public String doAddUser(String username, String password, String intro, String roles,
			RedirectAttributes redirectAttributes) {
		McpAdmin user = new McpAdmin();
		user.setName(username);
		user.setIntro(intro);
		user.setPassword(password);
		user.setAuth(roles);

		mcpAdminSer.addUser(user);

		redirectAttributes.addAttribute("msg", "新增成功");
		return "redirect:/admin/list";
	}

	/**
	 * 更新用户
	 **/
	@RequiresRoles("admin")
	@RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("mcpAdmin", mcpAdminSer.getById(id));
		return "mcpAdmin/edit";
	}

	/**
	 * 修改用户
	 **/
	@RequiresRoles("admin")
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String doUpdate(McpAdmin user, RedirectAttributes redirectAttributes) {
		JSONObject jObject = new JSONObject();
		try {
			McpAdmin mcpAdmin = mcpAdminSer.getById(user.getId());
			mcpAdmin.setAuth(user.getAuth());
			mcpAdmin.setIntro(user.getIntro());
			mcpAdminSer.update(mcpAdmin);
			jObject.put("ok",true);
			jObject.put("msg", "修改成功");
			// redirectAttributes.addAttribute("msg", "修改成功");
			// return "redirect:/admin/list";
		} catch (Exception e) {
			// TODO: handle exception
			jObject.put("ok",false);
			jObject.put("msg","保存失败");
		}
		return jObject.toString();
	}

	/**
	 * 删除用户
	 **/
	@RequiresRoles("admin")
	@RequestMapping(value = "/{id}/del", method = RequestMethod.GET)
	public String showDelUser(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("user", mcpAdminSer.getById(id));
		model.addAttribute("op", "删除");

		return "mcpAdmin/edit";
	}

	/**
	 * 删除操作
	 **/
	@RequiresRoles("admin")
	@RequestMapping(value = "/{id}/del", method = RequestMethod.POST)
	@ResponseBody
	public String doDelUser(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		//mcpAdminSer.del(id);
		//redirectAttributes.addFlashAttribute("msg", "删除成功");
		//return "redirect:/admin/list";
		JSONObject jObject=new JSONObject();
		try {
			mcpAdminSer.del(id);
			jObject.put("ok",true);
			jObject.put("msg", "删除成功");
		} catch (Exception e) {
			logger.error("error:"+e.getMessage());
			jObject.put("ok",false);
			jObject.put("msg","删除失败");
		}
		return jObject.toJSONString();
	}

	/**
	 * 修改密码
	 **/
	@RequiresRoles("admin")
	@RequestMapping(value = "/{id}/changepassword", method = RequestMethod.GET)
	public String showChangePassword(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("user", mcpAdminSer.getById(id));
		model.addAttribute("op", "修改密码");
		return "mcpAdmin/changePassword";
	}

	/**
	 * 修改密码-操作
	 **/
	@RequiresRoles("admin")
	@RequestMapping(value = "/{id}/changepassword", method = RequestMethod.POST)
	public String doChangePassword(@PathVariable("id") Integer id, String newPassword,
			RedirectAttributes redirectAttributes) {
		mcpAdminSer.changePassword(id, newPassword);
		redirectAttributes.addFlashAttribute("msg", "密码修改成功");
		return "redirect:/admin/list";
	}


	/**
	 * 查询单个用户
	 **/
	@RequestMapping("get")
	public String getUser(HttpServletRequest req, Integer userId) {
		// todo:查询用户
		// 赋值给request
		return "mcpAdmin/get";
	}

	/**
	 * 修改用户密码
	 **/
	@RequestMapping("changepassword")
	public String changePwd(HttpServletRequest req) {
		// todo:查询当前登录用户
		// todo:执行修改密码操作
		return "mcpAdmin/changePassword";
	}

	/**
	 * 登出
	 * 
	 * @param userName
	 * @param model
	 * @return
	 */
	public String logout(Model model) {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "mcpAdmin/login";
	}

	/**************** 权限管理示例 *******************/
	/**
	 * 通过代码方式判断权限
	 **/
	@RequestMapping("/hello1")
	public String hello1() {
		SecurityUtils.getSubject().checkRole("admin");
		return "success";
	}

	/**
	 * 通过注解方式判断权限
	 **/
	@RequiresRoles("admin") // 用户拥有某种角色
	@RequiresPermissions("sdf") // 角色拥有某种权限
	@RequiresAuthentication // 通过登录也进行了身份认证
	@RequiresUser // 已经进行身份认证或者通过记住我登录的
	@RequiresGuest // 游客身份
	@RequestMapping("/hello2")
	public String hello2() {
		return "success";
	}

}
