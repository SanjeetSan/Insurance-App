package insurancesapplication;

import java.time.LocalDate;

public class ClaimAssessment {
	private String assessmentId;
	private String assessorName;
	private String notes;
	private boolean recommendedToApprove;
	private LocalDate date;

	public ClaimAssessment(String assessmentId, Claim claim, String assessorName, String notes,
			boolean recommendedToApprove, LocalDate date) {
		this.assessmentId = assessmentId;
		this.assessorName = assessorName;
		this.notes = notes;
		this.recommendedToApprove = recommendedToApprove;
		this.date = date;
	}

	public ClaimAssessment(String aid, Claim c, String notes, double estimatedLoss, LocalDate date) {
		this.assessmentId = aid;
		this.assessorName = "Assessor";
		this.notes = notes + " | Estimated loss: " + estimatedLoss;
		this.recommendedToApprove = estimatedLoss < c.getPolicy().getCoverageAmount();
		this.date = date;
	}
	public boolean isRecommendedToApprove() {
		return recommendedToApprove;
	}
	public String summary() {
		return "Assessment ID: " + assessmentId + "\nAssessor: " + assessorName + "\nNotes: " + notes
				+ "\nRecommended: " + (recommendedToApprove ? "Approve" : "Reject") + "\nDate: " + date;
	}
}
