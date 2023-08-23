package com.example.demo.service;
import com.example.demo.entity.LoanStore;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;

import java.util.*;
import java.util.logging.Logger;

public class LoanServiceTest {


        @Test
        public void testCreateLoanValidDates() {
            Logger loggerMock = Mockito.mock(Logger.class);
            LoanService loanService = new LoanService(loggerMock);

            // Test valid payment and due dates
            assertDoesNotThrow(() -> {
                loanService.createLoan("L1", "C1", "LEN1", 10000, 10000,
                        new Date(), 0.01, new Date(System.currentTimeMillis() + 100000),
                        0.01);
            });
        }

        @Test
        public void testCreateLoanInvalidDates() {
            Logger loggerMock = Mockito.mock(Logger.class);
            LoanService loanService = new LoanService(loggerMock);

            // Test invalid payment and due dates
            assertThrows(IllegalArgumentException.class, () -> {
                loanService.createLoan("L1", "C1", "LEN1", 10000, 10000,
                        new Date(System.currentTimeMillis() + 100000), 0.01,
                        new Date(), 0.01);
            });
        }

        @Test
        public void testAggregateByLender() {
            // Create and add loan instances
            List<LoanStore> loans = createLoanList();

            Logger loggerMock = Mockito.mock(Logger.class);
            LoanService loanService = new LoanService(loggerMock);

            Map<String, Double> aggregationResult = loanService.aggregateByLender(loans);

            // Assert the aggregation result
            assertNotNull(aggregationResult);
            assertEquals(2, aggregationResult.size()); // Assuming you have loans from two lenders

        }

        @Test
        public void testAggregateByInterest() {
            // Create and add loan instances
            List<LoanStore> loans = createLoanList();

            Logger loggerMock = Mockito.mock(Logger.class);
            LoanService loanService = new LoanService(loggerMock);

            Map<String, Double> aggregationResult = loanService.aggregateByInterest(loans);

            // Assert the aggregation result
            assertNotNull(aggregationResult);

        }

        @Test
        public void testAggregateByCustomerId() {
            // Create and add loan instances
            List<LoanStore> loans = createLoanList();

            Logger loggerMock = Mockito.mock(Logger.class);
            LoanService loanService = new LoanService(loggerMock);

            Map<String, Double> aggregationResult = loanService.aggregateByCustomerId(loans);

            // Assert the aggregation result
            assertNotNull(aggregationResult);

        }

        @Test
        public void testCheckAndLogDueDateCrossing() {
            // Create and add loan instances
            List<LoanStore> loans = createLoanList();

            Logger loggerMock = Mockito.mock(Logger.class);
            LoanService loanService = new LoanService(loggerMock);

            // Mock the logger call
            loanService.checkAndLogDueDateCrossing(loans);

        }

    private List<LoanStore> createLoanList() {
        List<LoanStore> loans = new ArrayList<>();

        // Create loan instances and add them to the list
        loans.add(new LoanStore.LoanStoreBuilder("L1", "C1", "LEN1")
                .amount(10000)
                .remainingAmount(10000)
                .paymentDate(new Date())
                .interestPerDay(0.01)
                .dueDate(new Date(System.currentTimeMillis() + 100000))
                .penaltyPerDay(0.01)
                .canceled(false)
                .build());

        loans.add(new LoanStore.LoanStoreBuilder("L2", "C2", "LEN1")
                .amount(20000)
                .remainingAmount(5000)
                .paymentDate(new Date())
                .interestPerDay(0.01)
                .dueDate(new Date(System.currentTimeMillis() + 200000))
                .penaltyPerDay(0.01)
                .canceled(false)
                .build());

        loans.add(new LoanStore.LoanStoreBuilder("L3", "C1", "LEN2")
                .amount(50000)
                .remainingAmount(30000)
                .paymentDate(new Date())
                .interestPerDay(0.02)
                .dueDate(new Date(System.currentTimeMillis() + 150000))
                .penaltyPerDay(0.02)
                .canceled(false)
                .build());

        loans.add(new LoanStore.LoanStoreBuilder("L4", "C3", "LEN2")
                .amount(30000)
                .remainingAmount(30000)
                .paymentDate(new Date())
                .interestPerDay(0.02)
                .dueDate(new Date(System.currentTimeMillis() - 100000))
                .penaltyPerDay(0.02)
                .canceled(false)
                .build());



        return loans;
    }

}


