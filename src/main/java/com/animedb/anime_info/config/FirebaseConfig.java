package com.animedb.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FileInputStream;

@Configuration
public class FirebaseConfig {

    @PostConstruct
    public void init() {
        try {
            File file = new File("src/main/resources/firebase/serviceAccountKey.json");

            if (!file.exists()) {
                System.out.println("⚠ Firebase não inicializado: arquivo serviceAccountKey.json não encontrado.");
                return;
            }

            FileInputStream serviceAccount = new FileInputStream(file);

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                System.out.println("🔥 Firebase inicializado com sucesso!");
            }

        } catch (Exception e) {
            System.out.println("⚠ Erro ao inicializar Firebase. Ignorando...");
        }
    }
}
