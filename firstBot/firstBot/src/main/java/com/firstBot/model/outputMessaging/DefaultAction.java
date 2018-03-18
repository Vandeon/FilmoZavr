package com.firstBot.model.outputMessaging;

public class DefaultAction {

	private String type;
	
	private String url;
	
	private String messenger_extensions;
	
	private String webview_height_ratio;
	
	private String fallback_url;
	
	public DefaultAction() {}

	public DefaultAction(String type, String url, String messenger_extensions, String webview_height_ratio,
			String fallback_url) {
		super();
		this.type = type;
		this.url = url;
		this.messenger_extensions = messenger_extensions;
		this.webview_height_ratio = webview_height_ratio;
		this.fallback_url = fallback_url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMessenger_extensions() {
		return messenger_extensions;
	}

	public void setMessenger_extensions(String messenger_extensions) {
		this.messenger_extensions = messenger_extensions;
	}

	public String getWebview_height_ratio() {
		return webview_height_ratio;
	}

	public void setWebview_height_ratio(String webview_height_ratio) {
		this.webview_height_ratio = webview_height_ratio;
	}

	public String getFallback_url() {
		return fallback_url;
	}

	public void setFallback_url(String fallback_url) {
		this.fallback_url = fallback_url;
	}

	@Override
	public String toString() {
		return "DefaultAction [type=" + type + ", url=" + url + ", messenger_extensions=" + messenger_extensions
				+ ", webview_height_ratio=" + webview_height_ratio + ", fallback_url=" + fallback_url + "]";
	}

}
