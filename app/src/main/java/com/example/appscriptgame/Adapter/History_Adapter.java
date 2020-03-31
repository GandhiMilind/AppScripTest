package com.example.appscriptgame.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appscriptgame.ModelClass.GameData;
import com.example.appscriptgame.MainActivity;
import com.example.appscriptgame.R;
import java.util.ArrayList;

public class History_Adapter extends RecyclerView.Adapter<History_Adapter.ViewHolder> {

    private Context context;
    private ArrayList<GameData> gameshistory;

    public History_Adapter(Context context, ArrayList<GameData> gameshistory) {
        super();
        this.context = context;
        this.gameshistory = gameshistory;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.history_item,parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.index.setText("Games: " + gameshistory.get(position).getIndex());
        holder.time.setText(gameshistory.get(position).getTime());
        holder.name.setText("Name: " + gameshistory.get(position).getName());
        holder.qus1.setText("1.) "+MainActivity.questions[0]);
        holder.ans1.setText(" Answer: "+ gameshistory.get(position).getAns1());
        holder.qus2.setText("2.) "+MainActivity.questions[1]);
        holder.ans2.setText(" Answer: "+gameshistory.get(position).getAns2());

    }

    @Override
    public int getItemCount() {
        return gameshistory.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView index,time,name,qus1,ans1,qus2,ans2;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            index = itemView.findViewById(R.id.index);
            time = itemView.findViewById(R.id.time);
            name = itemView.findViewById(R.id.name);
            qus1 = itemView.findViewById(R.id.qus1);
            ans1 = itemView.findViewById(R.id.ans1);
            qus2 = itemView.findViewById(R.id.qus2);
            ans2 = itemView.findViewById(R.id.ans2);

        }
    }
}
