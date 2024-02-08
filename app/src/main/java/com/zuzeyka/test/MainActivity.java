package com.zuzeyka.test;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<WishlistItem> wishlistItems;
    private WishlistAdapter adapter;
    private TextView totalPriceTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);
        totalPriceTextView = findViewById(R.id.totalPriceTextView);

        wishlistItems = new ArrayList<>();
        // Add some sample wishlist items
        wishlistItems.add(new WishlistItem("Smartphone",R.drawable.f, 500));
        wishlistItems.add(new WishlistItem("Headphones", R.drawable.ff, 200));
        wishlistItems.add(new WishlistItem("Smartwatch", R.drawable.fff, 300));

        adapter = new WishlistAdapter(this, R.layout.wishlist_item, wishlistItems);
        listView.setAdapter(adapter);

        updateTotalPrice();
    }

    void updateTotalPrice() {
        double totalPrice = 0;
        for (WishlistItem item : wishlistItems) {
            if (item.isChecked()) {
                totalPrice += item.getPrice();
            }
        }
        totalPriceTextView.setText(String.format("Total: $%.2f", totalPrice));
    }
}



