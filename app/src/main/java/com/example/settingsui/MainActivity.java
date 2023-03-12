package com.example.settingsui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    TextView textView;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Shared Preferences
        sharedPreferences = getSharedPreferences("com.example.android.sharedpre", MODE_PRIVATE);

        textView = findViewById(R.id.text_view);

        Intent intent = getIntent();
        if (intent.getStringExtra("text") != null){
            textView.setText(intent.getStringExtra("text"));
        }
        else{
            String text = sharedPreferences.getString("text", "");
            textView.setText(text);
        }

        if (intent.getStringExtra("color") != null){
            textView.setBackgroundColor((Color.parseColor(intent.getStringExtra("color"))));
        }
        else {
            int bgColor = sharedPreferences.getInt("bgColor", 0);
            textView.setBackgroundColor(bgColor);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        int backgroundColor = ((ColorDrawable) textView.getBackground()).getColor();
        int textColor = textView.getCurrentTextColor();

        // Save data to Shared Preferences
        editor.putString("text", textView.getText().toString());
        editor.putInt("bgColor", backgroundColor);
        editor.apply();
    }
}