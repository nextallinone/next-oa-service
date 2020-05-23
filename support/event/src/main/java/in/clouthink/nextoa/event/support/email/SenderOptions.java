package in.clouthink.nextoa.event.support.email;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 */
@ConfigurationProperties(prefix = "in.clouthink.nextoa.email.sender")
public class SenderOptions {

	private String from;

	private String fromAlias;

	private String shortUrl;

	private String longUrl;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getFromAlias() {
		return fromAlias;
	}

	public void setFromAlias(String fromAlias) {
		this.fromAlias = fromAlias;
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	public String getLongUrl() {
		return longUrl;
	}

	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}
}
