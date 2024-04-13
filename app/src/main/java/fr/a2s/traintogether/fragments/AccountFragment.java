package fr.a2s.traintogether.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import fr.a2s.traintogether.MainActivity;
import fr.a2s.traintogether.R;

public class AccountFragment extends Fragment {

    public MainActivity context;

    public AccountFragment(MainActivity context) {
        this.context = context;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container,false);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null) {
            TextView displayName = view.findViewById(R.id.account_user);
            displayName.setText(user.getDisplayName());
            TextView email = view.findViewById(R.id.account_email);
            email.setText(user.getEmail());
        }

        return view;
    }
}
