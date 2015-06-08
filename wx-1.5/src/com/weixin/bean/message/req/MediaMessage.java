package com.weixin.bean.message.req;

public class MediaMessage extends BaseMessage {

	 private long MediaId;
	 
	 private long ThumbMediaId;

	public long getMediaId() {
		return MediaId;
	}

	public void setMediaId(long mediaId) {
		MediaId = mediaId;
	}

	public long getThumbMediaId() {
		return ThumbMediaId;
	}

	public void setThumbMediaId(long thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}
	 
	 
}
