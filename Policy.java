package insurancesapplication;

import java.time.*;
import java.util.*;

public class Policy {
	private String policyId;
	private Customer customer;
	private String type;
	private LocalDate startDate;
	private LocalDate endDate;
	private double coverageAmount;
	private boolean active;
	private List<PremiumPayment> payments = new ArrayList<>();
	private List<Claim> claims = new ArrayList<>();

	public Policy(String policyId, Customer customer, String type, LocalDate startDate, LocalDate endDate,
			double coverageAmount) {
		this.policyId = policyId;
		this.customer = customer;
		this.type = type;
		this.startDate = startDate;
		this.endDate = endDate;
		this.coverageAmount = coverageAmount;
		this.active = true;
	}

	public Policy(String pid, Customer c, String type, double coverageAmount, boolean active) {
		this.policyId = pid;
		this.customer = c;
		this.type = type;
		this.coverageAmount = coverageAmount;
		this.active = active;
		this.startDate = LocalDate.now();
		this.endDate = startDate.plusYears(1);
	}

	public String getPolicyId() {
		return policyId;
	}
	public Customer getCustomer() {
		return customer;
	}
	public String getType() {
		return type;
	}
	public double getCoverageAmount() {
		return coverageAmount;
	}
	public boolean isActive() {
		LocalDate today = LocalDate.now();
		return active && (today.isBefore(endDate) || today.isEqual(endDate));
	}
	public void deactivate() {
		active = false;
	}
	public void addPayment(PremiumPayment p) {
		payments.add(p);
	}
	public void addClaim(Claim c) {
		claims.add(c);
	}
	public boolean isPremiumUpToDate() {
		return !payments.isEmpty();
	}
	@Override
	public String toString() {
		return "Policy ID: " + policyId + ", Type: " + type + ", Active: " + isActive();
	}
	public String shortString() {
		return policyId + " (" + type + ")";
	}
}
