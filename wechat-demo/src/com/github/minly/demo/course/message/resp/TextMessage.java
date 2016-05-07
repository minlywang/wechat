package com.github.minly.demo.course.message.resp;

/**
 * 文本消息
 * 
 * @author Minly Wang
 * @since 2016年5月7日
 *
 */
public class TextMessage extends BaseMessage {
	// 回复的消息内容
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}