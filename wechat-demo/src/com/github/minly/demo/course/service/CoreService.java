package com.github.minly.demo.course.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.github.minly.demo.course.message.resp.Article;
import com.github.minly.demo.course.message.resp.NewsMessage;
import com.github.minly.demo.course.message.resp.TextMessage;
import com.github.minly.demo.course.util.MessageUtil;

/**
 * 核心服务类
 * 
 * @author Minly Wang
 * @since 2016年5月7日
 *
 */
public class CoreService {

	// private static Logger log = LoggerFactory.getLogger(CoreService.class);

	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return
	 */
	public static String processRequest(HttpServletRequest request) {
		String respMessage = null;
		try {
			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);

			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");
			System.out.println("");
			System.out.println("msgType:" + msgType);
			System.out.println("fromUserName:" + fromUserName);
			System.out.println("toUserName:" + toUserName);

			// 默认回复此文本消息
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setFuncFlag(0);
			// 由于href属性值必须用双引号引起，这与字符串本身的双引号冲突，所以要转义
			StringBuffer contentMsg = new StringBuffer();
			contentMsg.append("欢迎来到远东医疗社区微信公众平台").append("\n");
			contentMsg.append("您好，我是机器人小Q，请回复数字选择服务：").append("\n\n");
			contentMsg.append("1  远东宏信").append("\n");
			contentMsg.append("2 远东社区医疗").append("\n");
			contentMsg.append("3  最新资讯").append("\n");
			contentMsg.append("4  医疗生态馆").append("\n");
			contentMsg.append("5  医疗云服务").append("\n");
			contentMsg.append("6  关于我").append("\n");
			contentMsg.append("点击查看 <a href=\"http://minlywang.nat123.net/wechat-demo/\">返回我的主页</a>");

			textMessage.setContent(contentMsg.toString());
			// 将文本消息对象转换成xml字符串
			respMessage = MessageUtil.textMessageToXml(textMessage);

			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				// 接收用户发送的文本消息内容
				String content = requestMap.get("Content");
				System.out.println("content:" + content);

				// 创建图文消息
				NewsMessage newsMessage = new NewsMessage();
				newsMessage.setToUserName(fromUserName);
				newsMessage.setFromUserName(toUserName);
				newsMessage.setCreateTime(new Date().getTime());
				newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
				newsMessage.setFuncFlag(0);

				List<Article> articleList = new ArrayList<Article>();
				// 单图文消息
				if ("1".equals(content)) {
					Article article = new Article();
					article.setTitle("远东宏信");
					article.setDescription("远东宏信有限公司（简称“远东宏信”“Far Eastern Horizon”）是中国领先的金融综合服务机构，致力通过融资租赁以及其他增值服务，为客户提供度身订制的一站式金融服务解决方案。");
					article.setPicUrl("http://www.fehorizon.com/Template/fareast2/style/Images/logo.png");
					article.setUrl("http://www.fehorizon.com/");
					articleList.add(article);
					// 设置图文消息个数
					newsMessage.setArticleCount(articleList.size());
					// 设置图文消息包含的图文集合
					newsMessage.setArticles(articleList);
					// 将图文消息对象转换成xml字符串
					respMessage = MessageUtil.newsMessageToXml(newsMessage);
				} // 单图文消息---不含图片
				else if ("2".equals(content)) {
					Article article = new Article();
					article.setTitle("远东社区医疗");
					article.setDescription("远东社区医疗将为您提供整合的便利的医疗保健服务。");
					article.setPicUrl("http://minlywang.nat123.net/wechat-demo/images/medical_comunity.jpg");
					article.setUrl("http://bbs.hc3i.cn/");
					articleList.add(article);
					// 设置图文消息个数
					newsMessage.setArticleCount(articleList.size());
					// 设置图文消息包含的图文集合
					newsMessage.setArticles(articleList);
					// 将图文消息对象转换成xml字符串
					respMessage = MessageUtil.newsMessageToXml(newsMessage);
				}

				// 多图文消息
				else if ("3".equals(content)) {
					Article article1 = new Article();
					article1.setTitle("远东社区医疗\n最新资讯");
					article1.setDescription("");
					article1.setPicUrl("http://s4.51cto.com/wyfs02/M02/7F/70/wKioL1ce9OTCXc_pAANu3GSicDA132.png");
					article1.setUrl("http://www.hc3i.cn/");

					Article article2 = new Article();
					article2.setTitle("重点推荐区");
					article2.setDescription("");
					article2.setPicUrl("http://vd.hc3i.cn/adimage.php?filename=adimage.jpg&contenttype=jpeg");
					article2.setUrl("http://bbs.hc3i.cn/?gid=71");

					Article article3 = new Article();
					article3.setTitle("社区课堂");
					article3.setDescription("");
					article3.setPicUrl("http://s3.51cto.com/wyfs02/M02/6C/7D/wKioL1VK0YqgOGq-AAC1kdJzuHI170.jpg");
					article3.setUrl("http://class.hc3i.cn/");

					articleList.add(article1);
					articleList.add(article2);
					articleList.add(article3);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respMessage = MessageUtil.newsMessageToXml(newsMessage);
				}
				// 多图文消息---首条消息不含图片
				else if ("4".equals(content)) {
					Article article1 = new Article();
					article1.setTitle("医疗生态馆");
					article1.setDescription("");
					// 将图片置为空
					article1.setPicUrl("");
					article1.setUrl("http://ecosystem.hc3i.cn/");

					Article article2 = new Article();
					article2.setTitle("医疗信息化");
					article2.setDescription("");
					article2.setPicUrl("http://vd.hc3i.cn/adimage.php?filename=qiwen.jpg&contenttype=jpeg");
					article2.setUrl("http://news.hc3i.cn/col/304/");

					Article article3 = new Article();
					article3.setTitle("互联网医疗");
					article3.setDescription("");
					article3.setPicUrl("http://bcs.kuaiapk.com/rbpiczy/soft/2014/11/3/e24b44b86d1441c19ac0194aeee0330d/thumb_2b1668a025fb43ec8688e29d161a9e29_640x1136.jpeg");
					article3.setUrl("http://news.hc3i.cn/col/305/");

					Article article4 = new Article();
					article4.setTitle("移动医疗");
					article4.setDescription("");
					article4.setPicUrl("http://image.39.net/087/15/203759_n_9999_88.jpg");
					article4.setUrl("http://bbs.hc3i.cn/forum-132-1.html");

					articleList.add(article1);
					articleList.add(article2);
					articleList.add(article3);
					articleList.add(article4);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respMessage = MessageUtil.newsMessageToXml(newsMessage);
				}
				// 多图文消息---最后一条消息不含图片
				else if ("5".equals(content)) {
					Article article1 = new Article();
					article1.setTitle("医疗云服务");
					article1.setDescription("");
					article1.setPicUrl("http://img.my399.com/upload/0/2/2013/8/1375994809202.jpg");
					article1.setUrl("http://news.hc3i.cn/art/201511/34968.htm");

					Article article2 = new Article();
					article2.setTitle("“互联网＋医疗”建设与应用模式探究");
					article2.setDescription("");
					article2.setPicUrl("http://www.hc3i.cn/images/default_picname/hc3i.jpg");
					article2.setUrl("http://news.hc3i.cn/art/201511/35000.htm");

					Article article3 = new Article();
					article3.setTitle("如果觉得文章对你有所帮助，请通过博客留言或关注微信公众帐号FehorizonPOC来支持我们！");
					article3.setDescription("");
					// 将图片置为空
					article3.setPicUrl("");
					article3.setUrl("http://www.fehorizon.com/");

					articleList.add(article1);
					articleList.add(article2);
					articleList.add(article3);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respMessage = MessageUtil.newsMessageToXml(newsMessage);
				} // 单图文消息---不含图片
				else if ("6".equals(content)) {
					Article article = new Article();
					article.setTitle("关于我");
					// 图文消息中可以使用QQ表情、符号表情
					article.setDescription("minly，80后，" + emoji(0x1F6B9) + "，微信公众帐号开发经验不到1周，也希望借此机会认识更多同行！\n\n关注软件开发、设计和互联网的发展，喜欢看书。\n\n以后会推出一些实用应用功能。请关注我。。。XD。");
					// 将图片置为空
					article.setPicUrl("");
					article.setUrl("http://minlywang.nat123.net/wechat-demo/");
					articleList.add(article);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respMessage = MessageUtil.newsMessageToXml(newsMessage);
				}
			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 接收用户发送的事件请求内容
				String Event = requestMap.get("Event");
				String EventKey = requestMap.get("EventKey");
				System.out.println("EventKey:" + Event);
				System.out.println("EventKey:" + EventKey);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respMessage;
	}

	/**
	 * emoji表情转换(hex -> utf-16)
	 * 
	 * @param hexEmoji
	 * @return
	 */
	public static String emoji(int hexEmoji) {
		return String.valueOf(Character.toChars(hexEmoji));
	}
}