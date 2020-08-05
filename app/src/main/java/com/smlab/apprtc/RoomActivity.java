package com.smlab.apprtc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.appspot.apprtc.AppRTCClient;
import org.appspot.apprtc.PeerConnectionClient;
import org.webrtc.IceCandidate;
import org.webrtc.SessionDescription;
import org.webrtc.StatsReport;

import java.security.SecureRandom;

public class RoomActivity extends Activity {

    private Button button;
    private static final String TAG = "RoomActivity";
    private static final String APPRTC_URL = "https://appr.tc";
    private static final String UPPER_ALPHA_DIGITS = "ACEFGHJKLMNPQRUVWXY123456789";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_room);

//        connectVideoCall(randomRoomID);
        createRoom();
        joinRoom();
    }

    private void createRoom() {
        // Generate a random room ID with 7 uppercase letters and digits
        String randomRoomID = randomString(7, UPPER_ALPHA_DIGITS);
        // Show the random room ID so that another client can join from https://appr.tc
        TextView roomIdTextView = findViewById(R.id.roomID);
        roomIdTextView.setText(getString(R.string.room_id_caption) + randomRoomID);
        Log.d(TAG, getString(R.string.room_id_caption) + randomRoomID);
    }

    // Create a random string
    private String randomString(int length, String characterSet) {
        StringBuilder sb = new StringBuilder(); //consider using StringBuffer if needed
        for (int i = 0; i < length; i++) {
            int randomInt = new SecureRandom().nextInt(characterSet.length());
            sb.append(characterSet.substring(randomInt, randomInt + 1));
        }
        return sb.toString();
    }

    public void joinRoom() {
        button = findViewById(R.id.joinBtn);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent myIntent = new Intent(RoomActivity.this, TalkActivity.class);
                startActivity(myIntent);
            }

        });

    }
}
