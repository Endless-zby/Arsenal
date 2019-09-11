//package club.zby.newplan.controller;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.http.HttpSession;
//
//import club.zby.newplan.Entity.Constants;
//import club.zby.newplan.Entity.QQUserInfo;
//import club.zby.newplan.Untlis.HttpClientUtils;
//import club.zby.newplan.Untlis.URLEncodeUtil;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.alibaba.fastjson.JSON;
//
///**
// * qq登录
// *
// * @author wangsong
// * @date 2019年6月18日 下午8:04:15
// */
//@Controller
//@RequestMapping("qq")
//public class QQController {
//
//	/**
//	 * QQ ：读取Appid相关配置信息静态类
//	 */
//	@Autowired
//	private Constants constants;
//
//
//	/**
//	 * 登录页
//	 */
//	@GetMapping("qqlogin")
//	public String login() {
//		return "login";
//	}
//
//	/**
//	 * 获取链接
//	 * @return
//	 * @throws Exception
//	 */
//	public String getCode() throws Exception {
//		// 拼接url
//		StringBuilder url = new StringBuilder();
//		url.append("https://graph.qq.com/oauth2.0/authorize?");
//		url.append("response_type=code");
//		url.append("&client_id=" + constants.getQqAppId());
//		// 回调地址 ,回调地址要进行Encode转码
//		String redirect_uri = constants.getQqRedirectUrl();
//		// 转码
//		url.append("&redirect_uri=" + URLEncodeUtil.getURLEncoderString(redirect_uri));
//		url.append("&state=ok");
//		// HttpClientUtils.get(url.toString(), "UTF-8");
//		System.out.println(url.toString());
//		return url.toString();
//	}
//
//
//
//	/**
//	 * 开始登录
//	 *
//	 * @param code
//	 * 实际业务：token过期调用刷新    token重新获取token信息
//	 * 数据库字段: accessToken，expiresIn，refreshToken，openId
//	 * qq回调
//	 * @return
//	 * @throws Exception
//	 */
//	@GetMapping("/QQLogin")
//	@ResponseBody
//	public QQUserInfo QQLogin(String code, Model model) throws Exception {
//		if (code != null) {
//			System.out.println(code);
//		}
//	    //获取tocket
//		Map<String, Object> qqProperties = getToken(code);
//		//获取openId(每个用户的openId都是唯一不变的)
//	    String openId = getOpenId(qqProperties);
//	    qqProperties.put("openId",openId);
//
//	    //tocket过期刷新token
//	    //Map<String, Object> refreshToken = refreshToken(qqProperties);
//
//		//获取数据
//		QQUserInfo	userInfo =  getUserInfo(qqProperties);
//		return userInfo;
//	}
//
//
//}
