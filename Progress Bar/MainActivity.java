import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    TextView progressText;
    Button startButton;
    int progress = 0;
    Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        progressText = findViewById(R.id.progressStatus);
        startButton = findViewById(R.id.start);

        startButton.setOnClickListener(v -> startDownload());
    }

    private void startDownload() {
        startButton.setVisibility(View.INVISIBLE);
        progress = 0;

        new Thread(() -> {
            while (progress < 100) {
                progress++;
                handler.post(() -> {
                    progressBar.setProgress(progress);
                    progressText.setText(progress + "%");
                });

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            handler.post(this::showDialog);
        }).start();
    }

    private void showDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Download Complete")
                .setIcon(R.drawable.ic_launcher_foreground)
                .setMessage("Download Complete")
                .setPositiveButton("OK", (d, w) -> finish())
                .setNegativeButton("Cancel", (d, w) -> {
                    startButton.setVisibility(View.VISIBLE);
                    d.dismiss();
                })
                .show();
    }
}
