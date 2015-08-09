package book.ch02.account;

import junit.framework.TestCase;
import book.ch01.account.Account;

/**
 * Created by Sangjun on 2015. 8. 2..
 */
public class AccountTest extends TestCase {
    Account account;

    public void setUp() throws Exception {
        account = new Account(10000);
    }

    public void testGetBalance() throws Exception {
        assertEquals(10000, account.getBalance());
    }

    public void testWithdraw() throws Exception {
        account.deposit(1000);
        assertEquals(11000, account.getBalance());
    }

    public void testDeposit() throws Exception {
        account.withdraw(1000);
        assertEquals(9000, account.getBalance());
    }
}