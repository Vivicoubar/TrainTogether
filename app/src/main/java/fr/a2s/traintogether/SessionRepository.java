package fr.a2s.traintogether;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import javax.security.auth.callback.Callback;

public class SessionRepository {

    public static SessionRepository instance;
    // Connect to database reference "sessions"
    public  DatabaseReference databaseRef;
    //Create an array with the sessions
    public  ArrayList<SessionModel> sessionModels;

    public SessionRepository() {
        databaseRef = FirebaseDatabase.getInstance().getReference("sessions");
        sessionModels = new ArrayList<>();
    };

    //In order to have only one instance of repository (ie) one dataset
    public static synchronized SessionRepository getInstance() {
        if (instance==null) {
            instance = new SessionRepository();
        }
        return instance;
    }

    public void deleteSession(SessionModel sessionModel) {
        databaseRef.child(sessionModel.id).removeValue();
    }

    public void insertData(SessionModel sessionModel) {
        databaseRef.child(sessionModel.id).setValue(sessionModel);
    }

    public void filterData(FilterDataCallback callback) {

    }

    //Update sessions data
    public void updateData(UpdateDataCallback callback) {
        //Absorb data from databaseRef to put in the sessionModels
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Erase previous data
                sessionModels.clear();
                //Get the data
                for(DataSnapshot ds : snapshot.getChildren()) {
                    //Build session
                    SessionModel model = ds.getValue(SessionModel.class);
                    //Verify session isnt null
                    if(model!= null) {
                        model.title = model.sport.toUpperCase() + ": " + model.title;
                        sessionModels.add(model);
                    }
                }
                //Invoke the callback after data processing
                if (callback != null) {
                    callback.onUpdate(sessionModels);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
