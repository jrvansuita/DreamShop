package com.vanhackathon.dreamshop.fire;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.vanhackathon.dreamshop.listener.OnResult;
import com.vanhackathon.dreamshop.utils.Utils;

import java.util.UUID;

/**
 * Created by jrvansuita on 23/10/16.
 */

public class ImageStore {

    private Context context;
    private OnResult<String> onResult;

    public ImageStore(Context context){
        this.context = context;
    }

    public static ImageStore with(Context context){
        return new ImageStore(context);
    }



    public void store( Uri uri){
        FirebaseStorage storage = FirebaseStorage.getInstance();

        StorageReference storageRef = storage.getReferenceFromUrl("gs://vanhackathon-1d4be.appspot.com/");

        StorageReference imgRef = storageRef.child(UUID.randomUUID().toString().substring(0,10));


        byte[] data = Utils.getBytes(uri, context);
        if (data != null) {
            UploadTask uploadTask = imgRef.putBytes(data);
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    if (onResult != null)
                        onResult.onResult("");
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    if (onResult != null)
                        onResult.onResult(taskSnapshot.getDownloadUrl().toString());
                }
            });
        }
    }


    public ImageStore setOnResult(OnResult<String> onResult) {
        this.onResult = onResult;
        return this;
    }
}
