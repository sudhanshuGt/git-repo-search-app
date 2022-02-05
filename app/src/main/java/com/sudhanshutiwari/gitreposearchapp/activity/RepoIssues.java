package com.sudhanshutiwari.gitreposearchapp.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sudhanshutiwari.gitreposearchapp.R;
import com.sudhanshutiwari.gitreposearchapp.adapterClass.gitRepoIssuesAdapter;
import com.sudhanshutiwari.gitreposearchapp.gitapi.GitApiClient;
import com.sudhanshutiwari.gitreposearchapp.gitapi.gitRepoIssues;
import com.sudhanshutiwari.gitreposearchapp.modelclass.repoIssuesM;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepoIssues extends AppCompatActivity {

    Bundle extras;
    TextView reposNAmes , reposDescrip , reposlanguages , reposIssues;

    RecyclerView issuesRecycler;
    RecyclerView.Adapter issueAdapter;
    ArrayList<repoIssuesM> issuesData = new ArrayList<>();
    String name , description, language , GitUserName , issues;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_issues);

        reposNAmes = findViewById(R.id.reposNI);
        reposDescrip = findViewById(R.id.reposDI);
        reposlanguages  = findViewById(R.id.reposLI);
        reposIssues  = findViewById(R.id.repoII);

        extras = getIntent().getExtras();
        name = extras.getString("repoName");
        reposNAmes.setText(name);
        description = extras.getString("userRepoDescription");
        reposDescrip.setText(description);
        language = extras.getString("userRepoLanguage");
        reposlanguages.setText("Language : "+language);
        issues = extras.getString("repoIssues");
        reposIssues.setText("Issues : "+issues);



        GitUserName = userGitHistory.getUserNameValue();



        issuesRecycler = findViewById(R.id.reposIssuesRecycler);
        issuesRecycler.setLayoutManager(new LinearLayoutManager(this));
        issueAdapter = new gitRepoIssuesAdapter(issuesData, R.layout.issuesitemview, getApplicationContext());
        issuesRecycler.setAdapter(issueAdapter);

        getRepoIssuesList();

    }

    private void getRepoIssuesList() {

        gitRepoIssues gitApI = GitApiClient.getClient().create(gitRepoIssues.class);
        Call<List<repoIssuesM>> call = gitApI.getRepoIssues(GitUserName,name );
        call.enqueue(new Callback<List<repoIssuesM>>() {
            @Override
            public void onResponse(Call<List<repoIssuesM>> call, Response<List<repoIssuesM>> response) {
                issuesData.clear();
                issuesData.addAll(response.body());
                issueAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<repoIssuesM>> call, Throwable t) {
                Log.d("response" , t.toString());
            }
        });
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