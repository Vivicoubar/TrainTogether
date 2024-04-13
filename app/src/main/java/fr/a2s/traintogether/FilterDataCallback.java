package fr.a2s.traintogether;

import java.util.ArrayList;

public interface FilterDataCallback {
    void onUpdate(ArrayList<SessionModel> sessionModels);
}