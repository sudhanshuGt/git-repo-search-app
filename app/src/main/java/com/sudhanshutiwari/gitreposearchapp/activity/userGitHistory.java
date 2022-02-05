package com.sudhanshutiwari.gitreposearchapp.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sudhanshutiwari.gitreposearchapp.R;
import com.sudhanshutiwari.gitreposearchapp.adapterClass.gitRepoAdapter;
import com.sudhanshutiwari.gitreposearchapp.gitapi.GitApiClient;
import com.sudhanshutiwari.gitreposearchapp.gitapi.GitHubRepoEndPoint;
import com.sudhanshutiwari.gitreposearchapp.gitapi.UserEndPoint;
import com.sudhanshutiwari.gitreposearchapp.modelclass.UserGitHubRepo;
import com.sudhanshutiwari.gitreposearchapp.modelclass.gitUser;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class userGitHistory extends AppCompatActivity {
    Bundle extras;
    String userNameString;
    public  static ImageView userProfile;
    public static   TextView userName , userFollowers , userRepo , userFollowing , repoOfUser;
    Bitmap myBitImage;
    RecyclerView repoRecyclerView;
    RecyclerView.Adapter repoAdapter;
    ArrayList<UserGitHubRepo> repoData = new ArrayList<>();
    private static String userNameValue;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_git_history);
        // getting intent value
        extras = getIntent().getExtras();
        userNameString = extras.getString("GitUserName");
        userNameValue = userNameString;
        
        // initializing layout view
        userProfile = findViewById(R.id.userProfile);
        userName = findViewById(R.id.userName);
        userFollowers = findViewById(R.id.userFollowers);
        userRepo = findViewById(R.id.userRepo);
        userFollowing = findViewById(R.id.userFollowing);
        repoOfUser = findViewById(R.id.repoOfUser);

        // initializing recyclerview and adapter
        repoRecyclerView = findViewById(R.id.repoListRecycler);
        repoRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        repoAdapter = new gitRepoAdapter(repoData, R.layout.repositemview, getApplicationContext());
        repoRecyclerView.setAdapter(repoAdapter);

        // calling function for fetching user git profile data
        getUserDataFromGit();

        //calling function for user git repositories
        loadUserRepositories();
    }

    private void loadUserRepositories() {
        repoOfUser.setText("~ "+userNameString + " repositories" +" ~");
        GitHubRepoEndPoint GitApi = GitApiClient.getClient().create(GitHubRepoEndPoint.class);
        Call<List<UserGitHubRepo>> call = GitApi.getRepo(userNameString);
        call.enqueue(new Callback<List<UserGitHubRepo>>() {
            @Override
            public void onResponse(Call<List<UserGitHubRepo>> call, Response<List<UserGitHubRepo>> response) {
                repoData.clear();
                if(response.body() != null)
                {
                    repoData.addAll(response.body());
                }
                repoAdapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<List<UserGitHubRepo>> call, Throwable t) {
                Log.d("respons", t.toString());
                finish();
            }
        });
    }

    public class ImageDownloader extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(inputStream);
                return myBitmap;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private void getUserDataFromGit() {
        final UserEndPoint gitApi = GitApiClient.getClient().create(UserEndPoint.class);
        Call<gitUser>  call = gitApi.getTheUser(userNameString);
        call.enqueue(new Callback<gitUser>() {
            @Override
            public void onResponse(Call<gitUser> call, Response<gitUser> response) {
                 ImageDownloader task = new ImageDownloader();
                 try {
                     myBitImage  = task.execute(response.body().getAvator()).get();
                 } catch (InterruptedException e) {
                     e.printStackTrace();                 
                 } catch (ExecutionException e) {
                     e.printStackTrace();
                 }
                  userProfile.setImageBitmap(myBitImage);
                 if(response.body().getName()== null){
                     userName.setText("No username");
                 } else {
                     userName.setText(response.body().getName());
                 }
                 userFollowers.setText("followers :" + response.body().getFollowers());
                 userFollowing.setText("following :"+response.body().getFollowing());
                 userRepo.setText("repo :  "+response.body().getUserRepo());
            }
            @Override
            public void onFailure(Call<gitUser> call, Throwable t) {
                Toast.makeText(userGitHistory.this, "Failed to load data", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public static String getUserNameValue() {
        return userNameValue;
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}