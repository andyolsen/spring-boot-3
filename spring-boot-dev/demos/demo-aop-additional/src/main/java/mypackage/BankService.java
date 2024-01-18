package mypackage;

public interface BankService {
	void doDeposit(int accountID, double amount);
	void doWithdraw(int accountID, double amount);
	void levyFee(int accountID, double amount);
}
