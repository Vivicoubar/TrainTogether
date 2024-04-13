package fr.a2s.traintogether.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fr.a2s.traintogether.MainActivity;
import fr.a2s.traintogether.R;
import fr.a2s.traintogether.SessionModel;
import fr.a2s.traintogether.SessionPopup;

public class SessionAdapter extends RecyclerView.Adapter<SessionAdapter.ViewHolder> {

    public MainActivity context;
    private int layoutId;
    private ArrayList<SessionModel> sessionModels;

    public SessionAdapter(MainActivity context, ArrayList<SessionModel> sessions, int itemVerticalSession) {
        this.layoutId = itemVerticalSession;
        this.sessionModels = sessions;
        this.context = context;
    }

    public void setSessionModels(ArrayList<SessionModel> sessionModels) {
        this.sessionModels = sessionModels;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView session;

        public ViewHolder(View view) {
            super(view);
            session = view.findViewById(R.id.title_item);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Bind your data to views here
        SessionModel currentSession = sessionModels.get(position);
        holder.session.setText(currentSession.getTitle());
        //Add interaction
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SessionPopup(context, currentSession).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        // Return the size of your dataset (invoked by the layout manager)
        return sessionModels.size();
    }

}
