package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    EditText ed;
    Button btn;
    Spinner sp;
    DatabaseReference databaseArtists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseArtists= FirebaseDatabase.getInstance().getReference("artists");

        ed= (EditText)findViewById(R.id.et1);
        btn=(Button)findViewById(R.id.btn);
        sp=(Spinner)findViewById(R.id.sp);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addartist();
            }
        });
    }
    private void addartist() {
        String name=ed.getText().toString().trim();
        String genre=sp.getSelectedItem().toString();

        if(!TextUtils.isEmpty(name)) {
            String id=databaseArtists.push().getKey();
            Artist artist=new Artist(id,name,genre);

            databaseArtists.child(id).setValue(artist);
            Toast.makeText(this,"Artist Added",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this,"Please enter a name",Toast.LENGTH_LONG).show();

        }
    }
}