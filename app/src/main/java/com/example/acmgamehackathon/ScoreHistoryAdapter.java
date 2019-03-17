package com.example.acmgamehackathon;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ScoreHistoryAdapter extends RecyclerView.Adapter<ScoreHistoryAdapter.ScoreHistoryViewHolder> {
    Context mContext;
    ArrayList<Game> mGames;
    private static final String TAG = "ScoreHistoryAdapter";

    public ScoreHistoryAdapter(Context context, ArrayList<Game> games) {
        mContext = context;
        mGames = games;
    }

    @NonNull
    @Override
    public ScoreHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.cardview_score_history, parent, false);
        ScoreHistoryViewHolder viewHolder = new ScoreHistoryViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreHistoryViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mGames.size();
    }

    class ScoreHistoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView teamA;
        TextView teamB;

        public ScoreHistoryViewHolder(View itemView) {
            super(itemView);
            teamA = (TextView) itemView.findViewById(R.id.teamA);
            teamB = (TextView) itemView.findViewById(R.id.teamB);
        }

        void bind(final int position) {
            teamA.setText(mGames.get(position).getTeamA());
            teamB.setText(mGames.get(position).getTeamB());
            String winner = mGames.get(position).getWinner();
            Log.d(TAG, winner);

            if (winner.equals("TeamA")) {
                teamA.setTextColor(Color.GREEN);
                teamB.setTextColor(Color.RED);
            }
            else {
                teamB.setTextColor(Color.GREEN);
                teamA.setTextColor(Color.RED);
            }
        }

        @Override
        public void onClick(View view) {

        }
    }
}
