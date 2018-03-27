package com.newnews.newnews;


import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;

public class CreateFragment extends Fragment implements View.OnClickListener {

    EditText title_create, imgDes_create, content_create, author_create;
    EditText time_create;
    ImageView bodyImg_create;
    //FloatingActionButton upload_fat;

    private DatabaseReference databaseRef;
    private Uri fileUri;

    public static final int REQUEST_CODE = 1234;

    public CreateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_create, container, false);
        setHasOptionsMenu(true);


        title_create = rootView.findViewById(R.id.title_create);
        imgDes_create = rootView.findViewById(R.id.bodyImgDescription_create);
        content_create = rootView.findViewById(R.id.content_create);
        bodyImg_create = rootView.findViewById(R.id.bodyImg_create);
        //upload_fat = rootView.findViewById(R.id.upload_fat);
        bodyImg_create.setOnClickListener(this);
        time_create = rootView.findViewById(R.id.time_create);
        author_create = rootView.findViewById(R.id.author_create);
        //upload_fat.setOnClickListener(this);
        time_create.setOnClickListener(this);
        databaseRef = FirebaseDatabase.getInstance().getReference().child("articles");

        return rootView;
    }

    @Override
    public void onClick(View v) {
        if (v == bodyImg_create) {
            Intent intent = new Intent();
            intent.setType("*/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Upload Image"), REQUEST_CODE);
        }
        /*
        if (v == upload_fat) {
            if (fileUri != null) {
                final ProgressDialog progressDialog = new ProgressDialog(getActivity());
                progressDialog.setTitle("Publising Articles");
                progressDialog.show();

                StorageReference ref = FirebaseStorage.getInstance().getReference().child("detailImages").child(fileUri + "." + getFileExt(fileUri));
                ref.putFile(fileUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        //taskSnapshot.getDownloadUrl().toString()
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(), "File uploaded", Toast.LENGTH_SHORT).show();
                        String uid = databaseRef.push().getKey();
                        String title = title_create.getText().toString();
                        String imgDescription = imgDes_create.getText().toString();
                        String content = content_create.getText().toString();
                        String author = author_create.getText().toString();
                        String time = time_create.getText().toString();
                        String imgUrl = taskSnapshot.getDownloadUrl().toString();
                        Article article = new Article(title, author, imgUrl, imgUrl, content, time, uid, imgDescription);
                        databaseRef.child(uid).setValue(article);

                        Intent intent = new Intent(getContext(), MainActivity.class);
                        getActivity().finish();
                        startActivity(intent);

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        double progress = (100 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                        progressDialog.setMessage("Uploaded" + (int) progress + "%");
                    }
                });
            } else {
                Toast.makeText(getActivity(), "Please select File", Toast.LENGTH_SHORT).show();
            }

        }*/

        if (v == time_create) {

            final Calendar myCalendar = Calendar.getInstance();
            DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    myCalendar.set(Calendar.YEAR, year);
                    myCalendar.set(Calendar.MONTH, month);
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                    String myFormat = "MM/dd/yy";
                    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                    time_create.setText(sdf.format(myCalendar.getTime()));
                }
            };
            new DatePickerDialog(getActivity(), dateSetListener, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            fileUri = data.getData();
            Log.d("hehe", fileUri.toString());
            Log.d("hehe", getFileExt(fileUri));
            try {
                switch (getFileExt(fileUri)) {
                    case "jpeg":
                    case "png":
                    case "exif":
                    case "jpeg 2000":
                    case "tiff":
                    case "gif":
                    case "bmp":
                    case "ppm":
                    case "pgm":
                    case "pbm":
                    case "pnm":
                        Bitmap bm = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), fileUri);
                        bodyImg_create.setImageBitmap(bm);
                        break;
                    case "docx":
                    case "pdf":
                    case "txt":
                    case "ppt":
                    default:
                        setImageView(R.drawable.ic_action_create);
                        break;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.fragment_create_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_publish:
                if (fileUri != null) {
                    final ProgressDialog progressDialog = new ProgressDialog(getActivity());
                    progressDialog.setTitle("Publising Articles");
                    progressDialog.show();

                    StorageReference ref = FirebaseStorage.getInstance().getReference().child("detailImages").child(fileUri + "." + getFileExt(fileUri));
                    ref.putFile(fileUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            //taskSnapshot.getDownloadUrl().toString()
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(), "File uploaded", Toast.LENGTH_SHORT).show();
                            String uid = databaseRef.push().getKey();
                            String title = title_create.getText().toString();
                            String imgDescription = imgDes_create.getText().toString();
                            String content = content_create.getText().toString();
                            String author = author_create.getText().toString();
                            String time = time_create.getText().toString();
                            String imgUrl = taskSnapshot.getDownloadUrl().toString();
                            Article article = new Article(title, author, imgUrl, imgUrl, content, time, uid, imgDescription);
                            databaseRef.child(uid).setValue(article);

                            Intent intent = new Intent(getContext(), MainActivity.class);
                            getActivity().finish();
                            startActivity(intent);

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                            progressDialog.setMessage("Uploaded" + (int) progress + "%");
                        }
                    });
                } else {
                    Toast.makeText(getActivity(), "Please select File", Toast.LENGTH_SHORT).show();
                }
                return true;
                default:
                    return super.onOptionsItemSelected(item);


        }

    }

    private String getFileExt(Uri uri) {
        ContentResolver contentResolver = getActivity().getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private void setImageView(int i) {
        bodyImg_create.setImageDrawable(getResources().getDrawable(i, getActivity().getApplicationContext().getTheme()));
    }
}






























