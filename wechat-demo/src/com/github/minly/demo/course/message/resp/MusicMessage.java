package com.github.minly.demo.course.message.resp;

/**
 * 音乐消息
 * 
 * @author Minly Wang
 * @since 2016年5月7日
 *
 */
public class MusicMessage extends BaseMessage {
	// 音乐
	private Music Music;

	public Music getMusic() {
		return Music;
	}

	public void setMusic(Music music) {
		Music = music;
	}
}