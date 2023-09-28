package prolog;

public class PrologResultObject {
	private Boolean ruleFired = false;
	private String ruleMessage = "false";

	public Boolean getRuleFired() {
		return ruleFired;
	}

	public void setRuleFired(Boolean ruleFired) {
		this.ruleFired = ruleFired;
	}

	public String getRuleMessage() {
		return ruleMessage;
	}

	public void setRuleMessage(String ruleMessage) {
		this.ruleMessage = ruleMessage;
	}

}
