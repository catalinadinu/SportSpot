package com.example.sportspot;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sportspot.util.Const;
import com.example.sportspot.util.Feedback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static com.example.sportspot.FeedbackActivity.ADD_FEEDBACK_KEY;

public class ProfileActivity extends AppCompatActivity {
    private TextView comment;
    private TextView score;
    private Button add_feedback;
    private Button contact;
    private Button disconnect;
    private Button chooseImage;
    private Button chooseAvatar;
    private Intent intent;
    private ImageView profileImage;
    private Feedback feedback;
    private ArrayList<Feedback> feedbackList = new ArrayList<>();

    private Uri imageUri;

    private String avatarUrl = "https://i.pinimg.com/originals/78/54/84/7854843699c1893928012a442386a129.jpg";

    public static final int REQUEST_CODE_ADD_FEEDBACK = 1;
    public static final int REQUEST_CODE_CHOOSE_IMAGE = 2;

    private SharedPreferences sp;

    private DatabaseReference mDatabase;
    private FirebaseStorage storage;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initComponents();

        chooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQUEST_CODE_CHOOSE_IMAGE);
            }
        });

        chooseAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Picasso.get().load(avatarUrl).into(profileImage);
            }
        });

        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(ProfileActivity.this, ContactActivity.class);
                startActivity(intent);
            }
        });

        add_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(ProfileActivity.this, FeedbackActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ADD_FEEDBACK);
            }
        });

        disconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp = getSharedPreferences(Const.SP_FILE_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.remove(Const.SP_EMAIL_KEY);
                editor.remove(Const.SP_PASSWORD_KEY);
                editor.apply();

                FirebaseAuth.getInstance().signOut();

                intent = new Intent(ProfileActivity.this, AuthActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initComponents(){
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        profileImage = findViewById(R.id.profile_image);
        chooseImage = findViewById(R.id.profile_upload_image_button);
        chooseAvatar = findViewById(R.id.profile_upload_avatar_button);
        comment = findViewById(R.id.profile_comment);
        score = findViewById(R.id.profile_score);
        add_feedback = findViewById(R.id.profile_add_feedback);
        contact = findViewById(R.id.profile_contact);
        disconnect = findViewById(R.id.profile_disconnect);


        getImageFromFirebaseStorage();

        mDatabase = FirebaseDatabase.getInstance().getReference("feedback").push();

        getFeedbackFromFirebase();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_ADD_FEEDBACK && resultCode == RESULT_OK && data != null){
            feedback = data.getParcelableExtra(ADD_FEEDBACK_KEY);
            if(feedback != null){
                Toast.makeText(getApplicationContext(), R.string.feedback_succes, Toast.LENGTH_LONG).show();
                String comentariu = feedback.getComentariu();
                String nota = String.valueOf(feedback.getNota());
                comment.setText(comentariu);
                score.setText(nota);

                String user = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                feedback.setUser(user);

                mDatabase.setValue(feedback);

            }
            else {
                Toast.makeText(getApplicationContext(), R.string.feedback_fail, Toast.LENGTH_LONG).show();
            }

        }

        if(requestCode == REQUEST_CODE_CHOOSE_IMAGE && resultCode == RESULT_OK
                && data != null && data.getData() != null){
            imageUri = data.getData();
            profileImage.setImageURI(imageUri);
            try{
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                profileImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
            StorageReference ref = storageReference.child(FirebaseAuth.getInstance().getCurrentUser().getEmail());
            ref.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(getApplicationContext(), R.string.photo_upload_succes, Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(), R.string.photo_upload_fail, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void getFeedbackFromFirebase(){
        FirebaseDatabase.getInstance().getReference().child("feedback").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot child : dataSnapshot.getChildren()){

                    String user = child.getValue(Feedback.class).getUser();
                    String comment = child.getValue(Feedback.class).getComentariu();
                    int score = child.getValue(Feedback.class).getNota();
                    Feedback f = new Feedback();
                    f.setComentariu(comment);
                    f.setNota(score);
                    f.setUser(user);

                    if (user.equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())) {
                        feedbackList.add(f);
                    }
                }
                if(feedbackList != null && !feedbackList.isEmpty()){
                    int position = feedbackList.size() - 1;
                    feedback = feedbackList.get(position);
                    String c = "Comentariu: " + feedback.getComentariu();
                    String s = "Nota: " + String.valueOf(feedback.getNota());
                    comment.setText(c);
                    score.setText(s);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void getImageFromFirebaseStorage(){
        StorageReference ref = storageReference.child(FirebaseAuth.getInstance().getCurrentUser().getEmail());
        try{
            final File file = File.createTempFile("image", "jpg");
            ref.getFile(file).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                    profileImage.setImageBitmap(bitmap);
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Bitmap getBitmapfromURL(String avatarUrl)
    {
        try
        {
            URL url = new URL(avatarUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(input);
            return bitmap;

        }
        catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }
}
