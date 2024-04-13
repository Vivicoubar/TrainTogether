package fr.a2s.traintogether;

import java.util.ArrayList;

public interface UpdateDataCallback {
    void onUpdate(ArrayList<SessionModel> sessionModels);
}