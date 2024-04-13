package fr.a2s.traintogether.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fr.a2s.traintogether.MainActivity;
import fr.a2s.traintogether.R;
import fr.a2s.traintogether.SessionModel;
import fr.a2s.traintogether.SessionRepository;
import fr.a2s.traintogether.UpdateDataCallback;
import fr.a2s.traintogether.adapter.SessionAdapter;
import fr.a2s.traintogether.adapter.SessionItemDecoration;

public class HomeFragment extends Fragment {
    private RecyclerView verticalRecyclerView;
    private SessionAdapter sessionAdapter;
    private MainActivity context;

    public HomeFragment(MainActivity context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        HomeFragment fragment = this;
        verticalRecyclerView = view.findViewById(R.id.vertical_recycler_view);
        sessionAdapter = new SessionAdapter(context, new ArrayList<>(), R.layout.item_vertical_session);
        verticalRecyclerView.setAdapter(sessionAdapter);
        verticalRecyclerView.addItemDecoration(new SessionItemDecoration());
        updateAll();
        Spinner spinner = view.findViewById(R.id.sport_search_bar);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String sport = spinner.getSelectedItem().toString();
                switch (sport.toUpperCase()) {
                    case "":
                    case "ALL":
                        updateAll();
                        break;
                    default:
                        filterWithSport(sport.toUpperCase());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        return view;
    }

    private void updateAll() {
        // Update data from the repository
        SessionRepository.getInstance().updateData(new UpdateDataCallback() {
            @Override
            public void onUpdate(ArrayList<SessionModel> sessionModels) {
                // Update the adapter with the new data
                sessionAdapter.setSessionModels(sessionModels);
            }
        });
    }

    private void filterWithSport(String sport) {
        // Update data from the repository
        SessionRepository.getInstance().updateData(new UpdateDataCallback() {
            @Override
            public void onUpdate(ArrayList<SessionModel> sessionModels) {
                // Update the adapter with the new data
                ArrayList<SessionModel> newSessions = new ArrayList<>();
                for (SessionModel model: sessionModels ) {
                    if (model.getSport().toUpperCase().equals(sport)) {
                        newSessions.add(model);
                    }
                }
                sessionAdapter.setSessionModels(newSessions);
            }
        });
    }
}
