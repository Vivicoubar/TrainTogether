package fr.a2s.traintogether;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;

import fr.a2s.traintogether.adapter.SessionAdapter;

public class SessionPopup extends Dialog {

    private SessionModel sessionModel;

    public SessionPopup(@NonNull Context context, SessionModel sessionModel) {
        super(context);
        this.sessionModel = sessionModel;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup_session_details);
        setupComponents();
        setupCloseButton();
        setupDeleteButton();
    }

    private void setupDeleteButton() {
        findViewById(R.id.session_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SessionRepository repository = new SessionRepository();
                repository.deleteSession(sessionModel);
                dismiss();
            }
        });
    }

    private void setupCloseButton() {
        findViewById(R.id.session_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Close poppup
                dismiss();
            }
        });
    }

    private void setupComponents() {
        ((TextView)findViewById(R.id.session_title)).setText(sessionModel.title);
        ((TextView)findViewById(R.id.session_warmup_content)).setText(sessionModel.warmup);
        ((TextView)findViewById(R.id.session_sequence_content)).setText(sessionModel.session);
        ((TextView)findViewById(R.id.session_stretch_content)).setText(sessionModel.stretching);


    }
}
