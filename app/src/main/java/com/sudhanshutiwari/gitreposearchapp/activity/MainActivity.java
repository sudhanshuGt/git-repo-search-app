package com.sudhanshutiwari.gitreposearchapp.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.sudhanshutiwari.gitreposearchapp.R;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private TextView textView , ErrorTextView;
    private TextInputLayout userNameInput;
    public static Button searchButton;
    public static String userId , GitHUbUserName;
    public static int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initializing view
        textView = findViewById(R.id.txtView);
        ErrorTextView = findViewById(R.id.ErrorTextView);
        userNameInput = findViewById(R.id.userNameInput);
        searchButton = findViewById(R.id.userSearchButton);
        
        // handling button click
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GitHUbUserName = userNameInput.getEditText().getText().toString();
                if(GitHUbUserName.isEmpty())
                {
                    ErrorTextView.setText("field is empty");
                    ErrorTextView.setVisibility(View.VISIBLE);
                }
                else {
                    DownloadTask task = new DownloadTask();
                    task.execute("https://api.github.com/users/"+GitHUbUserName);
                    if(flag == 1){
                          Intent intent = new Intent(MainActivity.this, userGitHistory.class);
                          intent.putExtra("GitUserName", GitHUbUserName);
                          startActivity(intent);
                      }
                      else{
                          ErrorTextView.setText("invalid");
                          ErrorTextView.setVisibility(View.VISIBLE);
                      }
                }
            }
        });
    }

    // downloading json data of user from api url to match data with user input
    public static class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;
            try {
                url = new URL(urls[0]);

                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();

                while (data != -1) {
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }
                return result;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONObject jsonObject = new JSONObject(s);
                userId = jsonObject.getString("login").toString();
                if (GitHUbUserName.equals(userId)) {
                    flag = 1;
                    Log.d("value", "true");
                } else {
                    flag = 0;
                    Log.d("value", "false");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}