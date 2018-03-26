package com.newnews.newnews;


import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;

import static android.app.Activity.RESULT_OK;

public class CreateFragment extends Fragment implements View.OnClickListener {

    EditText title_create, imgDes_create, content_create;
    ImageView bodyImg_create;

    private StorageReference storageRef;
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

        title_create = rootView.findViewById(R.id.title_create);
        imgDes_create = rootView.findViewById(R.id.bodyImgDescription_create);
        content_create = rootView.findViewById(R.id.content_create);
        bodyImg_create = rootView.findViewById(R.id.bodyImg_create);
        bodyImg_create.setOnClickListener(this);

        storageRef = FirebaseStorage.getInstance().getReference();
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
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            fileUri = data.getData();
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

    private String getFileExt(Uri uri) {
        ContentResolver contentResolver = getActivity().getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private void setImageView(int i) {
        bodyImg_create.setImageDrawable(getResources().getDrawable(i, getActivity().getApplicationContext().getTheme()));
    }
}






























