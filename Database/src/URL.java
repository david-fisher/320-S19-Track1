
public class URL {
	String shortURL;
	String orgUrl;
	
	public URL(String shortURL, String orgUrl) {
		super();
		this.shortURL = shortURL;
		this.orgUrl = orgUrl;
	}
	
	public String getShortURL() {
		return shortURL;
	}
	public void setShortURL(String shortURL) {
		this.shortURL = shortURL;
	}
	public String getOrgUrl() {
		return orgUrl;
	}
	public void setOrgUrl(String orgUrl) {
		this.orgUrl = orgUrl;
	}
	
	
}
