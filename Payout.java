package insurancesapplication;

import java.time.LocalDate;

public class Payout {
	private String payoutId;
	private Claim claim;
	private double amount;
	private LocalDate date;
	private String status;

	public Payout(String payoutId, Claim claim, double amount, LocalDate date, String status) {
		this.payoutId = payoutId;
		this.claim = claim;
		this.amount = amount;
		this.date = date;
		this.status = status;
	}

	public String summary() {
		return "Payout ID: " + payoutId + "\nClaim: " + claim.getClaimId() + "\nAmount: " + amount + "\nDate: " + date
				+ "\nStatus: " + status;
	}
}
