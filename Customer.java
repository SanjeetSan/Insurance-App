package insurancesapplication;
import java.util.List;
import java.util.ArrayList;

public class Customer {
    int id;
    String name;
    List<Policy> policies = new ArrayList<>();

    public Customer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addPolicy(Policy p) {
        policies.add(p);
    }

    @Override
    public String toString() {
        return "Customer ID: " + id + ", Name: " + name;
    }
}
