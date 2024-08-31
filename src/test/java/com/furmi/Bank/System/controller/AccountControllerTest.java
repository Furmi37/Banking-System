package com.furmi.Bank.System.controller;

import com.furmi.Bank.System.model.Account;
import com.furmi.Bank.System.model.SavingAccount;
import com.furmi.Bank.System.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(MockitoExtension.class)
class AccountControllerTest {

    @Mock
    private AccountService accountService;

    @InjectMocks
    private AccountController accountController;
    private MockMvc mockMvc;

    Account account = new Account(null, "Monthy Python", "monthy@gmail.com", "8933333321", 1000, 1234, null);
    Account account1 = new Account(null, "Barrack Obama", "barrack@gmail.com", "79822333321", 4000, 4321,null);

    SavingAccount savingAccount = new SavingAccount(1L,0.07,5000,account);


    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
    }
    @Test
    void getAdminSettings() {
    }

    @Test
    void getHome() {
    }

    @Test
    void shouldReturnOneAccountWhenCallGetAccountByEmail() throws Exception {
        when(accountService.getAccount("monthy@gmail.com")).thenReturn(account);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/account/monthy@gmail.com"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.accountOwner").value("Monthy Python"))
                .andExpect(jsonPath("$.email").value("monthy@gmail.com"))
                .andExpect(jsonPath("$.accountNumber").value("8933333321"))
                .andExpect(jsonPath("$.balance").value(1000))
                .andExpect(jsonPath("$.pin").value(1234))
                .andReturn();
    }

    @Test
    void shouldReturnTwoAccountsWhenCallGetAll() throws Exception {

        List<Account> list = List.of(account,account1);
        when(accountService.getAccounts()).thenReturn(list);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/account/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].accountOwner").value("Monthy Python"))
                .andExpect(jsonPath("$[0].email").value("monthy@gmail.com"))
                .andExpect(jsonPath("$[0].accountNumber").value("8933333321"))
                .andExpect(jsonPath("$[0].balance").value(1000))
                .andExpect(jsonPath("$[0].pin").value(1234))

                .andExpect(jsonPath("$[1].accountOwner").value("Barrack Obama"))
                .andExpect(jsonPath("$[1].email").value("barrack@gmail.com"))
                .andExpect(jsonPath("$[1].accountNumber").value("79822333321"))
                .andExpect(jsonPath("$[1].balance").value(4000))
                .andExpect(jsonPath("$[1].pin").value(4321))
                .andReturn();
    }

    @Test
    void shouldReturnThousandValueWhenCallCheckBalance() throws Exception {

        when(accountService.getAccount("monthy@gmail.com")).thenReturn(account);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/account/balance")
                        .param("email", "monthy@gmail.com"))
                .andExpect(status().isOk());

        assertEquals(1000, account.getBalance());

    }

    @Test
    void shouldCallCreateAccountOnceWhenCreateAccount() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/account/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"accountOwner\":\"Monthy Python\",\"email\": \"monthy@gmail.com\",\"accountNumber\": \"8933333321\",\"balance\":\"1000\",\"pin\": \"1234\" }"))
                .andExpect(status().isOk());

        verify(accountService, times(1)).createAccount(eq(account));
    }

    @Test
    void shouldCallCreateAccountOnceWhenCreateSavingAccount() throws Exception{
        SavingAccount savingAccount = new SavingAccount(1L,0.07,5000,account);
        List<SavingAccount> savingsAccounts = new ArrayList<>();
        savingsAccounts.add(savingAccount);
        Account account = new Account(null, "Monthy Python", "monthy@gmail.com", "8933333321", 1000, 1234, savingsAccounts);

        when(accountService.getAccount("monthy@gmail.com")).thenReturn(account);

        when(accountService.createAccount(account)).thenReturn(account);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/account/saving")
                .param("email", "monthy@gmail.com")
                .param("savingsAccount", savingAccount.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"accountOwner\":\"Monthy Python\",\"email\": \"monthy@gmail.com\",\"accountNumber\": \"8933333321\",\"balance\":\"500\",\"pin\": \"1234\","+
                        "\"savingsAccounts\": {\"interestRate\":\"0.07\", \"payment\": \"5000\"}}"))
                .andExpect(status().isOk());

        verify(accountService, times(1)).createAccount(account);
    }

    @Test
    void shouldCallCreateAccountWhenWithdrawMoney() throws Exception {
        double amount = 500;
        when(accountService.getAccount("monthy@gmail.com")).thenReturn(account);

        account.setBalance(account.getBalance() - amount);
        when(accountService.createAccount(account)).thenReturn(account);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/account/withdraw")
                        .param("email", "monthy@gmail.com")
                        .param("amount", String.valueOf(amount))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"accountOwner\":\"Monthy Python\",\"email\": \"monthy@gmail.com\",\"accountNumber\": \"8933333321\",\"balance\":\"500\",\"pin\": \"1234\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balance").value(account.getBalance()));

        verify(accountService, times(1)).createAccount(account);
    }

    @Test
    void shouldCallCreateAccountWhenDepositMoney() throws Exception {
        double amount = 800;
        when(accountService.getAccount("monthy@gmail.com")).thenReturn(account);

        account.setBalance(account.getBalance() + amount);
        when(accountService.createAccount(account)).thenReturn(account);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/account/deposit")
                        .param("email", "monthy@gmail.com")
                        .param("amount", String.valueOf(amount))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"accountOwner\":\"Monthy Python\",\"email\": \"monthy@gmail.com\",\"accountNumber\": \"8933333321\",\"balance\":\"1800\",\"pin\": \"1234\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balance").value(account.getBalance()));

        verify(accountService, times(1)).createAccount(account);
    }

    @Test
    void shouldCallCreateAccountWhenChangePin() throws Exception {
        int pin = 4567;
        when(accountService.getAccount("monthy@gmail.com")).thenReturn(account);
        account.setPin(pin);
        when(accountService.createAccount(account)).thenReturn(account);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/account/pin")
                        .param("email", "monthy@gmail.com")
                        .param("newPin", String.valueOf(pin))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"accountOwner\":\"Monthy Python\",\"email\": \"monthy@gmail.com\",\"accountNumber\": \"8933333321\",\"balance\":\"1800\",\"pin\": \"4567\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pin").value(account.getPin()));

        verify(accountService, times(1)).createAccount(account);
    }

    @Test
    void shouldCallDeleteAccountWhenDeleteAccount() throws Exception {
        when(accountService.getAccount("monthy@gmail.com")).thenReturn(account);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/account/delete")
                .param("email", "monthy@gmail.com"));

        verify(accountService, times(1)).deleteAccount("monthy@gmail.com");
    }
}