class Account {
    protected String owner;
    protected double balance;
    public Account(String owner, double balance) {
        this.owner = owner;
        this.balance = balance;
    }
    public double getBalance() {
        return balance;
    }
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Қате: сома 0-ден үлкен болуы керек!");
            return;
        }
        balance += amount;
        System.out.println(owner + " шотына " + amount + " қосылды. Баланс: " + balance);
    }
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Қате: сома дұрыс емес!");
            return;
        }
        if (balance >= amount) {
            balance -= amount;
            System.out.println(owner + " шотынан " + amount + " алынды. Баланс: " + balance);
        } else {
            System.out.println("Қаражат жеткіліксіз!");
        }
    }
}
class SavingsAccount extends Account {
    private double interestRate = 0.05;

    public SavingsAccount(String owner, double balance) {
        super(owner, balance);
    }
    public void addInterest() {
        double interest = balance * interestRate;
        balance += interest;
        System.out.println(owner + ": Пайыз қосылды = " + interest + ". Баланс: " + balance);
    }
    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Қате: сома дұрыс емес!");
            return;
        }
        if (balance - amount >= 100) {
            balance -= amount;
            System.out.println(owner + " (Savings): " + amount + " алынды. Баланс: " + balance);
        } else {
            System.out.println("Қате: Минимум баланс 100 болуы керек!");
        }
    }
}
class CreditAccount extends Account {
    private double creditLimit = 500;
    public CreditAccount(String owner, double balance) {
        super(owner, balance);
    }
    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Қате: сома дұрыс емес!");
            return;
        }
        if (balance - amount >= -creditLimit) {
            balance -= amount;
            System.out.println(owner + " (Credit): " + amount + " алынды. Баланс: " + balance);
        } else {
            System.out.println("Қате: Кредит лимитінен асып кетті!");
        }
    }
}
public class BankSystem {
    public static void main(String[] args) {
        Account acc1 = new SavingsAccount("Aruzhan", 1000);
        Account acc2 = new CreditAccount("Dias", 200);
        acc1.deposit(500);
        acc1.withdraw(1300);
        System.out.println("----------------");
        acc2.withdraw(600);
        acc2.deposit(300);
        System.out.println("\n=== Полиморфизм ===");
        Account[] accounts = {acc1, acc2};
        for (Account acc : accounts) {
            acc.withdraw(50);
        }
    }
}
