package insurancesapplication;

import java.time.LocalDate;

public class PremiumPayment {
	private String paymentId;
	private Policy policy;
	private double amount;
	private LocalDate date;

	public PremiumPayment(String paymentId, Policy policy, double amount, LocalDate date, boolean recorded) {
		this.paymentId = paymentId;
		this.policy = policy;
		this.amount = amount;
		this.date = date;
	}

	public String receiptString() {
		return "Payment ID: " + paymentId + "\nPolicy: " + policy.getPolicyId() + "\nAmount: " + amount + "\nDate: "
				+ date;
	}
}
