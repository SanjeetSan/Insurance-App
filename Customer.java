package insurancesapplication;

import java.util.*;

public class Customer {
    private String customerId;
    private String name;
    private String email;
    private List<Policy> policies = new ArrayList<>();

    public Customer(String customerId, String name, String email) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
    }

    public String getCustomerId() {
        return customerId;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public void addPolicy(Policy p) {
        policies.add(p);
    }
    public List<Policy> getPolicies() {
        return policies;
    }
    @Override
    public String toString() {
        return "Customer ID: " + customerId + ", Name: " + name + ", Email: " + email;
    }
}
