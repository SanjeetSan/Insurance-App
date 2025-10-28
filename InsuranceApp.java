package insurancesapplication;

import java.time.LocalDate;
import java.util.*;

public class InsuranceApp {
    static Scanner sc = new Scanner(System.in);
    static List<Customer> customers = new ArrayList<>();
    static List<Policy> policies = new ArrayList<>();
    static List<Claim> claims = new ArrayList<>();
    static List<ClaimAssessment> assessments = new ArrayList<>();
    static List<ClaimDecision> decisions = new ArrayList<>();
    static List<Payout> payouts = new ArrayList<>();

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n--- Insurance Management System ---");
            System.out.println("1. Add Customer");
            System.out.println("2. Create Policy");
            System.out.println("3. Record Premium");
            System.out.println("4. File Claim");
            System.out.println("5. Assess Claim");
            System.out.println("6. Decide Claim");
            System.out.println("7. Process Payout");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> addCustomer();
                case 2 -> createPolicy();
                case 3 -> recordPremium();
                case 4 -> fileClaim();
                case 5 -> assessClaim();
                case 6 -> decideClaim();
                case 7 -> processPayout();
                case 8 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 8);
    }

    private static void addCustomer() {
        System.out.print("Enter Customer ID: ");
        String id = sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Email: ");
        String email = sc.nextLine();
        customers.add(new Customer(id, name, email));
        System.out.println("Customer added successfully!");
    }

    private static void createPolicy() {
        System.out.print("Enter Policy ID: ");
        String pid = sc.nextLine();
        System.out.print("Enter Customer ID: ");
        String cid = sc.nextLine();
        Customer c = findCustomer(cid);
        if (c == null) {
            System.out.println("Customer not found.");
            return;
        }
        System.out.print("Enter Policy Type: ");
        String type = sc.nextLine();
        System.out.print("Enter Coverage Amount: ");
        double amt = Double.parseDouble(sc.nextLine());

        Policy p = new Policy(pid, c, type, amt, true);
        policies.add(p);
        c.addPolicy(p);
        System.out.println("Policy created successfully!");
    }

    private static void recordPremium() {
        System.out.print("Enter Policy ID: ");
        String pid = sc.nextLine();
        Policy p = findPolicy(pid);
        if (p == null) {
            System.out.println("Policy not found.");
            return;
        }
        System.out.print("Enter Payment ID: ");
        String payId = sc.nextLine();
        System.out.print("Enter Amount: ");
        double amt = Double.parseDouble(sc.nextLine());

        PremiumPayment pay = new PremiumPayment(payId, p, amt, LocalDate.now(), true);
        p.addPayment(pay);
        System.out.println("Premium recorded successfully!");
        System.out.println(pay.receiptString());
    }

    private static void fileClaim() {
        System.out.print("Enter Claim ID: ");
        String cid = sc.nextLine();
        System.out.print("Enter Policy ID: ");
        String pid = sc.nextLine();
        Policy p = findPolicy(pid);
        if (p == null || !p.isActive()) {
            System.out.println("Invalid or inactive policy.");
            return;
        }
        System.out.print("Enter Claim Description: ");
        String desc = sc.nextLine();
        System.out.print("Enter Claim Amount: ");
        double amt = Double.parseDouble(sc.nextLine());

        Claim c = new Claim(cid, p, p.getCustomer(), LocalDate.now(), amt, desc);
        claims.add(c);
        System.out.println("Claim filed successfully!");
        System.out.println(c.summary());
    }

    private static void assessClaim() {
        System.out.print("Enter Claim ID: ");
        String cid = sc.nextLine();
        Claim c = findClaim(cid);
        if (c == null) {
            System.out.println("Claim not found.");
            return;
        }

        System.out.print("Enter Assessment ID: ");
        String aid = sc.nextLine();
        System.out.print("Enter Notes: ");
        String notes = sc.nextLine();
        System.out.print("Enter Estimated Loss: ");
        double loss = Double.parseDouble(sc.nextLine());

        ClaimAssessment ca = new ClaimAssessment(aid, c, notes, loss, LocalDate.now());
        assessments.add(ca);
        System.out.println("Claim assessed successfully!");
        System.out.println(ca.summary());
    }

    private static void decideClaim() {
        System.out.print("Enter Claim ID: ");
        String cid = sc.nextLine();
        Claim c = findClaim(cid);
        if (c == null) {
            System.out.println("Claim not found.");
            return;
        }

        System.out.print("Decision ID: ");
        String did = sc.nextLine();
        System.out.print("Enter decision (APPROVED/REJECTED): ");
        String decision = sc.nextLine().trim().toUpperCase();
        System.out.print("Enter Note: ");
        String note = sc.nextLine();
        System.out.print("Enter Approved Amount (0 if rejected): ");
        double amt = Double.parseDouble(sc.nextLine());

        ClaimDecision cd = new ClaimDecision(did, c, decision, note, LocalDate.now(), amt);
        decisions.add(cd);

        c.setStatus(decision.equals("APPROVED") ? "Approved" : "Rejected");
        System.out.println("Claim decision saved.");
        System.out.println(cd.summary());
    }

    private static void processPayout() {
        System.out.print("Enter Claim ID: ");
        String cid = sc.nextLine();
        Claim c = findClaim(cid);
        if (c == null) {
            System.out.println("Claim not found.");
            return;
        }

        ClaimDecision cd = decisions.stream()
                .filter(d -> d.getDecision().equalsIgnoreCase("APPROVED") && d.getApprovedAmount() > 0)
                .findFirst()
                .orElse(null);

        if (cd == null) {
            System.out.println("No approved decision found for this claim.");
            return;
        }

        System.out.print("Enter Payout ID: ");
        String payoutId = sc.nextLine();

        Payout payout = new Payout(payoutId, c, cd.getApprovedAmount(), LocalDate.now(), "Processed");
        payouts.add(payout);

        System.out.println("Payout processed successfully!");
        System.out.println("Payout Details:\n" + payout.summary());
    }

    private static Customer findCustomer(String id) {
        for (Customer c : customers) {
            if (c.getCustomerId().equals(id)) return c;
        }
        return null;
    }

    private static Policy findPolicy(String id) {
        for (Policy p : policies) {
            if (p.getPolicyId().equals(id)) return p;
        }
        return null;
    }

    private static Claim findClaim(String id) {
        for (Claim c : claims) {
            if (c.getClaimId().equals(id)) return c;
        }
        return null;
    }
}
