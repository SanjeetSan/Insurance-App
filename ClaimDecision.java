package insurancesapplication;

public class ClaimDecision {
    Claim claim;
    String decisionNote;
    boolean approved;

    public ClaimDecision(Claim claim, String decisionNote, boolean approved) {
        this.claim = claim;
        this.decisionNote = decisionNote;
        this.approved = approved;
        claim.approved = approved;
    }

    @Override
    public String toString() {
        return "Claim " + claim.claimId + " Decision: " + 
               (approved ? "Approved" : "Rejected") + " (" + decisionNote + ")";
    }
}
