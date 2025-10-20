package insurancesapplication;

public class PremiumPayment {
    Policy policy;
    double amount;
    boolean paid;

    public PremiumPayment(Policy policy, double amount) {
        this.policy = policy;
        this.amount = amount;
        this.paid = true;
        policy.premiumsPaid = true;
    }

    @Override
    public String toString() {
        return "Premium for Policy " + policy.policyId + " paid: " + paid;
    }
}
