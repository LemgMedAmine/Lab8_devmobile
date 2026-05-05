package com.example.lab8_devmobile;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Travail réalisé par : LEMGHILI MOHAMMED AMINE
 * Description : Cette activité gère le chargement d'images et des calculs lourds 
 * en utilisant des threads et AsyncTask pour assurer une interface réactive.
 */
public class MainActivity extends AppCompatActivity {

    // Développé par LEMGHILI MOHAMMED AMINE - Références UI
    private TextView txtStatus;
    private ProgressBar progressBar;
    private ImageView img;

    // Handler pour la communication avec le thread principal
    private Handler mainHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialisation des composants par LEMGHILI MOHAMMED AMINE
        txtStatus = findViewById(R.id.txtStatus);
        progressBar = findViewById(R.id.progressBar);
        img = findViewById(R.id.img);

        Button btnLoadThread = findViewById(R.id.btnLoadThread);
        Button btnCalcAsync = findViewById(R.id.btnCalcAsync);
        Button btnToast = findViewById(R.id.btnToast);

        mainHandler = new Handler(Looper.getMainLooper());

        // Test de réactivité de l'UI
        btnToast.setOnClickListener(v ->
                Toast.makeText(getApplicationContext(), "Interface réactive - LEMGHILI MOHAMMED AMINE", Toast.LENGTH_SHORT).show()
        );

        // Chargement via Thread
        btnLoadThread.setOnClickListener(v -> loadImageWithThread());

        // Chargement via AsyncTask
        btnCalcAsync.setOnClickListener(v -> new HeavyCalcTask().execute());
    }

    /**
     * Méthode implémentée par LEMGHILI MOHAMMED AMINE pour charger une image en arrière-plan
     */
    private void loadImageWithThread() {
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setProgress(0);
        txtStatus.setText("Statut : Chargement via Thread...");

        new Thread(() -> {
            try {
                // Simulation d'une latence réseau
                Thread.sleep(1000); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Décodage de la ressource en dehors du thread principal
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test);

            // Mise à jour de l'UI via le mainHandler (par LEMGHILI MOHAMMED AMINE)
            mainHandler.post(() -> {
                img.setImageBitmap(bitmap);
                progressBar.setVisibility(View.INVISIBLE);
                txtStatus.setText("Statut : Image chargée (Thread)");
            });

        }).start();
    }

    /**
     * Classe interne AsyncTask personnalisée par LEMGHILI MOHAMMED AMINE
     */
    private class HeavyCalcTask extends AsyncTask<Void, Integer, Long> {

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setProgress(0);
            txtStatus.setText("Statut : Calcul en cours (AsyncTask)...");
        }

        @Override
        protected Long doInBackground(Void... voids) {
            long result = 0;
            for (int i = 1; i <= 100; i++) {
                // Algorithme de calcul lourd
                for (int k = 0; k < 200000; k++) {
                    result += (i * k) % 7;
                }
                // Mise à jour de la progression par LEMGHILI MOHAMMED AMINE
                publishProgress(i);
            }
            return result;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Long result) {
            progressBar.setVisibility(View.INVISIBLE);
            txtStatus.setText("Statut : Calcul terminé. Résultat = " + result);
        }
    }
}
