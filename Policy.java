package insurancesapplication;

public class Policy {
    int policyId;
    Customer customer;
    boolean active;
    boolean premiumsPaid;

    public Policy(int policyId, Customer customer) {
        this.policyId = policyId;
        this.customer = customer;
        this.active = true;
        this.premiumsPaid = false;
    }

    @Override
    public String toString() {
        return "Policy ID: " + policyId + ", Active: " + active + ", PremiumsPaid: " + premiumsPaid;
    }
}
