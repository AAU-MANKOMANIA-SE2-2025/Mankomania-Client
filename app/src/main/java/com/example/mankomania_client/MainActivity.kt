package com.example.mankomania_client

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.mankomania_client.websocket.ServerCommunicationClient
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

/**
 * # MainActivity
 *
 * Haupt-Einstiegspunkt der Android-App.
 * 
 * @author 
 * @since 
 * @description Diese Activity wird beim App-Start geladen und initialisiert die UI.
 */
class MainActivity : ComponentActivity() {


    fun testHttpConnection() {
        // Erstelle einen OkHttpClient
        val client = OkHttpClient()

        // Stelle sicher, dass du 10.0.2.2 verwendest, um den Host-Rechner im Emulator anzusprechen.
        val request = Request.Builder()
            .url("http://10.0.2.2:8080/")  // Passe den Port und Pfad ggf. an
            .build()

        // Führe die Anfrage in einem Hintergrund-Thread aus
        Thread {
            try {
                val response: Response = client.newCall(request).execute()
                if (response.isSuccessful) {
                    Log.d("HTTPTest", "Erfolgreich verbunden! Antwort: ${response.body?.string()}")
                } else {
                    Log.e("HTTPTest", "Fehlerhafte Antwort: ${response.code}")
                }
            } catch (e: Exception) {
                Log.e("HTTPTest", "Verbindungsfehler: ${e.message}")
            }
        }.start()
    }


    /**
     * Standard-Android-Lifecyclemethode.
     * Hier könnte zukünftig die UI gesetzt werden.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Start the server communication when the activity is created.
        ServerCommunicationClient.start()
        testHttpConnection()
    }

    override fun onDestroy() {
        // Stop the server communication when the activity is destroyed.
        ServerCommunicationClient.stop()
        super.onDestroy()
    }
}