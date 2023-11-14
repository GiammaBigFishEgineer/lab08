package it.unibo.bank.impl;

import it.unibo.bank.api.AccountHolder;
import it.unibo.bank.api.BankAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class TestStrictBankAccount {

    private final static int INITIAL_AMOUNT = 100;
    public static final double TRANSACTION_FEE = 0.1;
    public static final double MANAGEMENT_FEE = 5;

    // Create a new AccountHolder and a StrictBankAccount for it each time tests are executed.
    private AccountHolder mRossi;
    private BankAccount bankAccount;

    /**
     * Prepare the tests.
     */
    @BeforeEach
    public void setUp() {
        this.mRossi = new AccountHolder("Mario", "Rossi", 1);
        this.bankAccount = new StrictBankAccount(mRossi, 0.0);
    }

    /**
     * Test the initial state of the StrictBankAccount.
     */
    @Test
    public void testInitialization() {
        assertEquals(0.0, bankAccount.getBalance());
        assertEquals(0, bankAccount.getTransactionsCount());
        assertEquals(mRossi, bankAccount.getAccountHolder());
    }

    /**
     * Perform a deposit of 100€, compute the management fees, and check that the balance is correctly reduced.
     */
    @Test
    public void testManagementFees() {
        final double feeAmount = MANAGEMENT_FEE + bankAccount.getTransactionsCount() * TRANSACTION_FEE + 0.1;
        bankAccount.deposit(mRossi.getUserID(), INITIAL_AMOUNT);
        bankAccount.chargeManagementFees(mRossi.getUserID());
        assertEquals(INITIAL_AMOUNT-feeAmount, bankAccount.getBalance());
    }

    /**
     * Test that withdrawing a negative amount causes a failure.
     * assertThrows(IllegalArgumentException.class, () -> {
            bankAccount.withdraw(mRossi.getUserID(), -INITIAL_AMOUNT);
        })
     */
    @Test
    public void testNegativeWithdraw() {    
        final var initialBalance = bankAccount.getBalance();
        try {
            bankAccount.withdraw(mRossi.getUserID(), -INITIAL_AMOUNT);
            fail("withdrwawing negative number");
        } catch(IllegalArgumentException e){
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isBlank());
            assertTrue(e.getMessage().length() > 10);
            assertEquals(initialBalance, bankAccount.getBalance());
        }
    }

    /**
     * Test that withdrawing more money than it is in the account is not allowed.
     */
    @Test
    public void testWithdrawingTooMuch() {
        final var initialBalance = bankAccount.getBalance();
        try {
            bankAccount.withdraw(mRossi.getUserID(), INITIAL_AMOUNT*20);
            fail("withdrwawing too much money");
        } catch(IllegalArgumentException e){
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isBlank());
            assertTrue(e.getMessage().length() > 10);
            assertEquals(initialBalance, bankAccount.getBalance());
        }
    }
}
