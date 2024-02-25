package com.zuzeyka.test;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button sendButton;
    private RecyclerView recyclerView;

    private DatabaseReference databaseReference;
    private List<String> dataList;
    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        sendButton = findViewById(R.id.sendButton);
        recyclerView = findViewById(R.id.recyclerView);

        // Инициализация базы данных Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference("messages");
        dataList = new ArrayList<>();

        // Установка адаптера для RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewAdapter(dataList);
        recyclerView.setAdapter(adapter);

        // Обработчик нажатия на кнопку отправки
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessageToFirebase();
            }
        });

        // Загрузка данных из Firebase при запуске активити
        loadDataFromFirebase();
    }

    private void sendMessageToFirebase() {
        String message = editText.getText().toString().trim();
        if (!message.isEmpty()) {
            // Создание уникального ключа для каждого сообщения
            String messageId = databaseReference.push().getKey();
            // Отправка сообщения в Firebase
            databaseReference.child(messageId).setValue(message);
            editText.setText("");
            Toast.makeText(this, "Сообщение отправлено в базу данных Firebase", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Введите текст сообщения", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadDataFromFirebase() {
        // Слушатель для загрузки данных из Firebase
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String message = snapshot.getValue(String.class);
                    dataList.add(message);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Ошибка загрузки данных из Firebase", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void updateTotalPrice() {
    }
}
