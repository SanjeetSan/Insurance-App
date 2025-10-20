package insurancesapplication;
import java.util.*;

public class InsuranceApp {
    static Scanner sc = new Scanner(System.in);
    static Map<Integer, Customer> customers = new HashMap<>();
    static Map<Integer, Policy> policies = new HashMap<>();
    static Map<Integer, Claim> claims = new HashMap<>();

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
            choice = sc.nextInt();

            switch (choice) {
                case 1: addCustomer(); break;
                case 2: createPolicy(); break;
                case 3: recordPremium(); break;
                case 4: fileClaim(); break;
                case 5: assessClaim(); break;
                case 6: decideClaim(); break;
                case 7: processPayout(); break;
                case 8: System.out.println("Exiting..."); break;
                default: System.out.println("Invalid choice");
            }
        } while (choice != 8);
    }

    static void addCustomer() {
        System.out.print("Enter Customer ID: ");
        int id = sc.nextInt();
        System.out.print("Enter Name: ");
        String name = sc.next();
        Customer c = new Customer(id, name);
        customers.put(id, c);
        System.out.println("Customer Added: " + c);
    }

    static void createPolicy() {
        System.out.print("Enter Policy ID: ");
        int pid = sc.nextInt();
        System.out.print("Enter Customer ID: ");
        int cid = sc.nextInt();
        Customer c = customers.get(cid);
        if (c != null) {
            Policy p = new Policy(pid, c);
            c.addPolicy(p);
            policies.put(pid, p);
            System.out.println("Policy Created: " + p);
        } else {
            System.out.println("Customer not found!");
        }
    }

    static void recordPremium() {
        System.out.print("Enter Policy ID: ");
        int pid = sc.nextInt();
        Policy p = policies.get(pid);
        if (p != null) {
            System.out.print("Enter Amount: ");
            double amt = sc.nextDouble();
            PremiumPayment pp = new PremiumPayment(p, amt);
            System.out.println(pp);
        } else {
            System.out.println("Policy not found!");
        }
    }

    static void fileClaim() {
        System.out.print("Enter Claim ID: ");
        int cid = sc.nextInt();
        System.out.print("Enter Policy ID: ");
        int pid = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Description: ");
        String desc = sc.nextLine();
        Policy p = policies.get(pid);
        if (p != null && p.active && p.premiumsPaid) {
            Claim cl = new Claim(cid, p, desc);
            claims.put(cid, cl);
            System.out.println("Claim Filed: " + cl);
        } else {
            System.out.println("Claim cannot be filed! (Policy inactive or premium not paid)");
        }
    }

    static void assessClaim() {
        System.out.print("Enter Claim ID: ");
        int cid = sc.nextInt();
        sc.nextLine();
        Claim cl = claims.get(cid);
        if (cl != null) {
            System.out.print("Enter Assessment Notes: ");
            String notes = sc.nextLine();
            ClaimAssessment ca = new ClaimAssessment(cl, notes);
            System.out.println(ca);
        } else {
            System.out.println("Claim not found!");
        }
    }

    static void decideClaim() {
        System.out.print("Enter Claim ID: ");
        int cid = sc.nextInt();
        sc.nextLine();
        Claim cl = claims.get(cid);
        if (cl != null && cl.assessed) {
            System.out.print("Approve claim? (yes/no): ");
            String ans = sc.nextLine();
            boolean approved = ans.equalsIgnoreCase("yes");
            System.out.print("Enter Decision Note: ");
            String note = sc.nextLine();
            ClaimDecision cd = new ClaimDecision(cl, note, approved);
            System.out.println(cd);
        } else {
            System.out.println("Claim not assessed yet!");
        }
    }

    static void processPayout() {
        System.out.print("Enter Claim ID: ");
        int cid = sc.nextInt();
        Claim cl = claims.get(cid);
        if (cl != null) {
            Payout p = new Payout(cl, 10000); 
            System.out.println(p);
        } else {
            System.out.println("Claim not found!");
        }
    }
}
