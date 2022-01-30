package com.sudhanshutiwari.gitreposearchapp.adapterClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sudhanshutiwari.gitreposearchapp.R;
import com.sudhanshutiwari.gitreposearchapp.modelclass.repoIssuesM;

import java.util.List;

public class gitRepoIssuesAdapter extends RecyclerView.Adapter<gitRepoIssuesAdapter.gitIssuesViewHolder> {

    private List<repoIssuesM> repoIssuesItem;
    private Context context;

    public gitRepoIssuesAdapter(List<repoIssuesM> repoIssuesItem, int issuesitemview, Context context) {
        this.repoIssuesItem = repoIssuesItem;
        this.context = context;
    }

    public List<repoIssuesM> getRepoIssuesItem() {
        return repoIssuesItem;
    }

    public void setRepoIssuesItem(List<repoIssuesM> repoIssuesItem) {
        this.repoIssuesItem = repoIssuesItem;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public gitRepoIssuesAdapter.gitIssuesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.issuesitemview, parent,false);

        return new gitIssuesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull gitRepoIssuesAdapter.gitIssuesViewHolder holder, int position) {

        holder.reposT.setText("Title :"+repoIssuesItem.get(position).getRepoIssuesTitle());
        holder.reposB.setText("Body : "+repoIssuesItem.get(position).getRepoIssuesBy());
        holder.reposN.setText("Number #"+repoIssuesItem.get(position).getRepoIssuesNumber());

    }

    @Override
    public int getItemCount() {
        return repoIssuesItem.size();
    }

    public class gitIssuesViewHolder extends RecyclerView.ViewHolder {

        TextView reposT , reposB , reposN;

        public gitIssuesViewHolder(@NonNull View itemView) {
            super(itemView);

            reposT = itemView.findViewById(R.id.issuesTitle);
            reposB = itemView.findViewById(R.id.issuesBy);
            reposN = itemView.findViewById(R.id.issuesNumber);

        }
    }
}
