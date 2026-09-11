import java.util.List;

public class Courses {
	
	private List<WebAutomation> webAutomation;
	public List<WebAutomation> getWebAutomation() {
		return webAutomation;
	}
	public void setWebAutomation(List<WebAutomation> webAutomation) {
		this.webAutomation = webAutomation;
	}
	public List<Api> getApi() {
		return Api;
	}
	public void setApi(List<Api> api) {
		Api = api;
	}
	public List<Mobile> getMobile() {
		return Mobile;
	}
	public void setMobile(List<Mobile> mobile) {
		Mobile = mobile;
	}
	private List<Api> Api;
	private List<Mobile> Mobile;
}
