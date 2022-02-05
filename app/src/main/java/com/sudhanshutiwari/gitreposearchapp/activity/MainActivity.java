package com.sudhanshutiwari.gitreposearchapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.sudhanshutiwari.gitreposearchapp.R;
import com.sudhanshutiwari.gitreposearchapp.gitapi.GitApiClient;
import com.sudhanshutiwari.gitreposearchapp.gitapi.UserEndPoint;
import com.sudhanshutiwari.gitreposearchapp.modelclass.gitUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

                final UserEndPoint gitApi = GitApiClient.getClient().create(UserEndPoint.class);
                Call<gitUser> call = gitApi.getTheUser(GitHUbUserName);
                call.enqueue(new Callback<gitUser>() {
                    @Override
                    public void onResponse(Call<gitUser> call, Response<gitUser> response) {

                        if(GitHUbUserName.isEmpty())
                        {
                          ErrorTextView.setText("field can't be  empty !");
                          ErrorTextView.setVisibility(View.VISIBLE);
                        }
                        else {
                            if(!response.isSuccessful()){
                                 ErrorTextView.setText("Invalid UserName !");
                                 ErrorTextView.setVisibility(View.VISIBLE);
                                 
                            }
                            else if (response.isSuccessful()){
                                ErrorTextView.setVisibility(View.INVISIBLE);
                                Intent intent = new Intent(MainActivity.this, userGitHistory.class);
                                intent.putExtra("GitUserName", GitHUbUserName);
                                startActivity(intent);
                            }
                        }

                    }
                    @Override
                    public void onFailure(Call<gitUser> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Failed to load data", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }

}