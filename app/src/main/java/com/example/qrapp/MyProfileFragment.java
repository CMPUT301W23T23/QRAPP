package com.example.qrapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.auth.User;

/**
 */
public class MyProfileFragment extends Fragment {

    DocumentReference userRef;

    TextView usernameText;
    TextView emailText;
    TextView phoneText;
    TextView locationText;

    //TODO Stats section
    public MyProfileFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_profile, container, false);

        //get the text views
        usernameText = view.findViewById(R.id.myp_username_text);
        emailText = view.findViewById(R.id.myp_email_text);
        phoneText = view.findViewById(R.id.myp_phone_text);
        locationText = view.findViewById(R.id.myp_location_text);


        FirebaseFirestore db = FirebaseFirestore.getInstance();

        //Todo this is very fragile, add fallbacks besides just crashing
        String deviceID = "DefaultDummyDevice"; //TODO this should be the device ID once we set up registration
        //query the database for the user with the device ID
        db.collection("Users").whereEqualTo("DeviceID", deviceID).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                //get the first document in the query
                DocumentSnapshot document = task.getResult().getDocuments().get(0);
                //set the text views to the user's info
                usernameText.setText(document.getString("Username"));
                emailText.setText(document.getString("Email"));
                phoneText.setText(document.getString("Phone"));
            }
        });




        return view;




    }
}