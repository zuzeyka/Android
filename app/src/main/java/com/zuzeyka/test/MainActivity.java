package com.zuzeyka.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private Spinner senderSpinner;
    private Spinner receiverSpinner;
    private EditText amountEditText;
    private Button transferButton;
    private Button cancelButton;
    private ListView accountsListView;

    private List<Account> accounts;
    private ArrayAdapter<Account> accountsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        senderSpinner = findViewById(R.id.senderSpinner);
        receiverSpinner = findViewById(R.id.receiverSpinner);
        amountEditText = findViewById(R.id.amountEditText);
        transferButton = findViewById(R.id.transferButton);
        cancelButton = findViewById(R.id.cancelButton);
        accountsListView = findViewById(R.id.accountsListView);

        accounts = new ArrayList<>();
        accounts.add(new Account("A B", "1232339"));
        accounts.add(new Account("B C", "2313123123"));
        accounts.add(new Account("C D", "123123323"));
        accounts.add(new Account("D F", "123123123123"));
        accounts.add(new Account("B X", "5352352352"));
        accounts.add(new Account("A Z", "5125235"));

        ArrayAdapter<Account> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, accounts);
        accountsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, accounts);
        senderSpinner.setAdapter(spinnerAdapter);
        receiverSpinner.setAdapter(spinnerAdapter);
        accountsListView.setAdapter(accountsAdapter);

        transferButton.setOnClickListener(view -> {
            Account sender = (Account) senderSpinner.getSelectedItem();
            Account receiver = (Account) receiverSpinner.getSelectedItem();
            double amount = Double.parseDouble(amountEditText.getText().toString());

            new Thread(() -> {
                try {
                    Thread.sleep(10000);
                    double senderBalance = sender.getBalance();
                    double receiverBalance = receiver.getBalance();
                    sender.setBalance(senderBalance - amount);
                    receiver.setBalance(receiverBalance + amount);
                    runOnUiThread(() -> {
                        accountsAdapter.notifyDataSetChanged();
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        });

        cancelButton.setOnClickListener(view -> {
            cancelButton.setEnabled(false);
            new Handler().postDelayed(() -> cancelButton.setEnabled(true), 10000);
        });
    }
    public void updateTotalPrice() {
    }
}

