package com.edu.upc.businessbook.viewcontrollers.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.view.View;

import com.edu.upc.businessbook.R;
import com.edu.upc.businessbook.models.Local;
import com.edu.upc.businessbook.models.LocalsRepository;


import java.util.ArrayList;
import java.util.List;

public class DialogPersonalized extends DialogFragment {

    TextInputEditText editText;
    LocalsRepository localsRepository;


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_local,null);
        editText = (TextInputEditText) view.findViewById(R.id.editLocalName);
        builder.setView(view)
                .setTitle("New Local")
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //localsRepository.getLocals().add(new Local(editText.getText().toString(),"1",R.drawable.ic_business_black_24dp));
                        //locals.add(new Local(editText.getText().toString(),"1",R.drawable.ic_business_black_24dp));

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        return builder.create();
    }
}
