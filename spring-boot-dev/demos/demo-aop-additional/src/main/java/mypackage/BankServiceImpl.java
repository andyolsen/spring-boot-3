package mypackage;

import org.springframework.stereotype.Component;

@Component
public class BankServiceImpl implements BankService {

	public void doDeposit(int accountID, double amount) {
		System.out.printf("Depositing %.2f into account %d\n", amount, accountID);
	}

	public void doWithdraw(int accountID, double amount) {
		System.out.printf("Withdrawing %.2f from account %d\n", amount, accountID);
	}

	public void levyFee(int accountID, double amount) {
		System.out.printf("Levying fee of %.2f on account %d\n", amount, accountID);
	}
}
