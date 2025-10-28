package insurancesapplication;

import java.time.LocalDate;

public class ClaimDecision {
	private String decision;
	private String note;
	private LocalDate date;
	private double approvedAmount;

	public ClaimDecision(String decisionId, Claim claim, String decision, String note, LocalDate date,
			double approvedAmount) {
		this.decision = decision;
		this.note = note;
		this.date = date;
		this.approvedAmount = approvedAmount;
	}

	public String getDecision() {
		return decision;
	}
	public double getApprovedAmount() {
		return approvedAmount;
	}
	public String summary() {
		return "Decision: " + decision + "\nNote: " + note + "\nDate: " + date + "\nApproved Amount: " + approvedAmount;
	}
}
