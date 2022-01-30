package com.sudhanshutiwari.gitreposearchapp.adapterClass;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sudhanshutiwari.gitreposearchapp.R;
import com.sudhanshutiwari.gitreposearchapp.activity.RepoIssues;
import com.sudhanshutiwari.gitreposearchapp.modelclass.UserGitHubRepo;

import java.util.List;

public class gitRepoAdapter extends RecyclerView.Adapter<gitRepoAdapter.ReposViewHolder> {

    private List<UserGitHubRepo> userRepos;
    private int layoutRow;
    private Context context;



    public gitRepoAdapter(List<UserGitHubRepo> userRepos, int layoutRow, Context context) {
        this.userRepos = userRepos;
        this.layoutRow = layoutRow;
        this.context = context;
    }

    public List<UserGitHubRepo> getUserRepos() {
        return userRepos;
    }

    public void setUserRepos(List<UserGitHubRepo> userRepos) {
        this.userRepos = userRepos;
    }

    public int getLayoutRow() {
        return layoutRow;
    }

    public void setLayoutRow(int layoutRow) {
        this.layoutRow = layoutRow;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ReposViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repositemview, parent,false);
        
        return new ReposViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReposViewHolder holder, int position) {

        holder.reposName.setText("Name : "+userRepos.get(position).getRepoName());
        holder.reposDescription.setText("Description : "+userRepos.get(position).getRepoDescription());
        holder.reposLanguage.setText("Language : " +userRepos.get(position).getRepoLanguage());
        holder.reposOpenIssues.setText("Issues : "+userRepos.get(position).getRepoIssues());
        holder.starGazers.setText("⭐: "+userRepos.get(position).getStarGazers());

        String userRepoNames , userRepoDescription , userRepoLang , reposIssues;
        userRepoNames = userRepos.get(position).getRepoName();
        userRepoDescription = userRepos.get(position).getRepoDescription();
        userRepoLang = userRepos.get(position).getRepoLanguage();
        reposIssues = userRepos.get(position).getRepoIssues();


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RepoIssues.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("repoName", userRepoNames);
                intent.putExtra("userRepoDescription", userRepoDescription);
                intent.putExtra("userRepoLanguage", userRepoLang);
                intent.putExtra("repoIssues",reposIssues);
                context.startActivity(intent);
                
            }
        });

    }

    @Override
    public int getItemCount() {
        return userRepos.size();
    }

    public class ReposViewHolder extends RecyclerView.ViewHolder {
        TextView reposName, reposDescription, reposLanguage , reposOpenIssues , starGazers;


        public ReposViewHolder(@NonNull View itemView) {
            super(itemView);


            reposName = itemView.findViewById(R.id.repoName);
            reposDescription = itemView.findViewById(R.id.repoDescription);
            reposLanguage = itemView.findViewById(R.id.repoLang);
            reposOpenIssues  = itemView.findViewById(R.id.repoIssues);
            starGazers = itemView.findViewById(R.id.starGazers);
        }
    }
}
