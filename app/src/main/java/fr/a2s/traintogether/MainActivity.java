package fr.a2s.traintogether;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.SurfaceControl;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import fr.a2s.traintogether.fragments.AccountFragment;
import fr.a2s.traintogether.fragments.AddSessionFragment;
import fr.a2s.traintogether.fragments.HomeFragment;

public class MainActivity extends AppCompatActivity {

    FirebaseUser user;
    Button disconnectButton;
    TextView username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MainActivity context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user==null) {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            //import navigation view
            BottomNavigationView navigationView = findViewById(R.id.navigation_view);
            navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.home_page: {
                            loadFragment(new HomeFragment(context), R.string.account_intro);
                            return true;
                        }
                        case R.id.add_page: {
                            loadFragment(new AddSessionFragment(context), R.string.create_intro);
                            return true;
                        }
                        case R.id.profile_page: {
                            loadFragment(new AccountFragment(context), R.string.account_intro);
                        }
                        default:
                            return false;
                    }
                }
            });
            username = findViewById(R.id.user_name);
            username.setText(user.getDisplayName());
            disconnectButton = findViewById(R.id.app_disconnect);
            disconnectButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FirebaseAuth.getInstance().signOut();
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            });

            loadFragment(new HomeFragment(context), R.string.account_intro);
        }
    }

    public void loadFragment(Fragment fragment, int pageTitle){
        SessionRepository sessionRepository = new SessionRepository();
        sessionRepository.updateData(new UpdateDataCallback() {
            @Override
            public void onUpdate(ArrayList<SessionModel> sessionModels) {
                //Inject the fragment into the Fragment
                ((TextView)findViewById(R.id.page_title)).setText(pageTitle);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }
}