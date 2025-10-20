package insurancesapplication;

public class Payout {
    Claim claim;
    double amount;
    boolean processed;

    public Payout(Claim claim, double amount) {
        this.claim = claim;
        this.amount = amount;
        this.processed = claim.approved;
    }

    @Override
    public String toString() {
        if (processed) {
            return "Payout of " + amount + " processed for Claim " + claim.claimId;
        } else {
            return "Payout not possible, claim not approved.";
        }
    }
}
