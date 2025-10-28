package insurancesapplication;

import java.time.LocalDate;

public class Claim {
	private String claimId;
	private Policy policy;
	private Customer customer;
	private LocalDate claimDate;
	private double amount;
	private String description;
	private String status;

	public Claim(String claimId, Policy policy, Customer customer, LocalDate claimDate, double amount,
			String description) {
		this.claimId = claimId;
		this.policy = policy;
		this.customer = customer;
		this.claimDate = claimDate;
		this.amount = amount;
		this.description = description;
		this.status = "FILED";
	}

	public String getClaimId() {
		return claimId;
	}
	public Policy getPolicy() {
		return policy;
	}
	public Customer getCustomer() {
		return customer;
	}
	public LocalDate getClaimDate() {
		return claimDate;
	}
	public double getAmount() {
		return amount;
	}
	public String getDescription() {
		return description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String summary() {
		return "Claim ID: " + claimId + "\nCustomer: " + customer.getName() + "\nPolicy ID: " + policy.getPolicyId()
				+ "\nClaim Date: " + claimDate + "\nAmount: " + amount + "\nDescription: " + description + "\nStatus: "
				+ status;
	}
}
