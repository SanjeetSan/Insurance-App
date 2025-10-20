package insurancesapplication;

public class ClaimAssessment {
    Claim claim;
    String notes;

    public ClaimAssessment(Claim claim, String notes) {
        this.claim = claim;
        this.notes = notes;
        claim.assessed = true;
    }

    @Override
    public String toString() {
        return "Claim " + claim.claimId + " assessed: " + notes;
    }
}
