package insurancesapplication;

public class Claim {
    int claimId;
    Policy policy;
    String description;
    boolean assessed;
    boolean approved;

    public Claim(int claimId, Policy policy, String description) {
        this.claimId = claimId;
        this.policy = policy;
        this.description = description;
        this.assessed = false;
        this.approved = false;
    }

    @Override
    public String toString() {
        return "Claim ID: " + claimId + ", Policy: " + policy.policyId + ", Desc: " + description +
               ", Assessed: " + assessed + ", Approved: " + approved;
    }
}
