package fr.a2s.traintogether.fragments;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;

import java.util.UUID;

import fr.a2s.traintogether.MainActivity;
import fr.a2s.traintogether.R;
import fr.a2s.traintogether.SessionModel;
import fr.a2s.traintogether.SessionRepository;

public class AddSessionFragment extends Fragment {

    public MainActivity context;

    public AddSessionFragment(MainActivity context) {
        this.context = context;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_session, container, false);

        //Get the confirm button
        Button confirmButton = view.findViewById(R.id.create_validate_button);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View buttonView) {
                System.out.println(view);
                sendForm(view);

            }

            public void sendForm(View view) {
                SessionRepository repository = new SessionRepository();
                String id = UUID.randomUUID().toString();
                String sessionTitle = ((EditText)view.findViewById(R.id.create_summary)).getText().toString();
                String sessionWarmup = ((EditText)view.findViewById(R.id.create_warmup)).getText().toString();
                String sessionSequence = ((EditText)view.findViewById(R.id.create_sequence)).getText().toString();
                String sessionStretch = ((EditText)view.findViewById(R.id.create_stretch)).getText().toString();
                String sessionSport = ((Spinner)view.findViewById(R.id.create_sport)).getSelectedItem().toString();
                //Create object
                SessionModel sessionModel = new SessionModel(id, sessionTitle,sessionWarmup,sessionSequence,sessionStretch,sessionSport);
                repository.insertData(sessionModel);
            }
        });
        return view;
    }


}
