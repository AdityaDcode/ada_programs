package com.example.intent2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText = findViewById(R.id.sendmessage);
        Button button = findViewById(R.id.button);

        button.setOnClickListener(v -> {
            String message = editText.getText().toString();
            Intent intent = new Intent(MainActivity.this, Screen2.class);
            intent.putExtra("message", message);
            startActivity(intent);
        });
    }
}

