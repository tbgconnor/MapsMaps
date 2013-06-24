package com.Square9.AndroidMapsV2Test;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Dialog Fragment to save a file to disk
 * @author Koen Gilissen
 * @version 1.0
 */
public class SaveToFileDialogFragment extends DialogFragment
{
    private String fileName;
    private String fileExtension;
    private OnDialogDoneListener mListener;
    private TextView textViewFileExtension;
    private EditText editTextFileName;
    private Button buttonSave;
    private Button buttonCancel;

    public static SaveToFileDialogFragment newInstance()
    {
        SaveToFileDialogFragment dlg = new SaveToFileDialogFragment();
        return dlg;
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        try
        {
            mListener = (OnDialogDoneListener) activity;
        }
        catch(Exception e)
        {
            //Host activity should always implement the interface
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null)
        {
            fileName = savedInstanceState.getString("fileName");
            fileExtension = savedInstanceState.getString("fileExtension");
        }
        else
        {
            fileName = "";
            fileExtension = "";
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        getDialog().setTitle(R.string.dlgfrag_savetofile_title);
        // create view by inflating layout
        View v = inflater.inflate(R.layout.dialogfragment_save_to_file, container);
        //assign view elements of this dialog fragment
        editTextFileName = (EditText) v.findViewById(R.id.editText_saveToFile_FileName);
        textViewFileExtension = (TextView) v.findViewById(R.id.textView_saveToFile_fileExtension);
        buttonSave = (Button) v.findViewById(R.id.button_saveToFile_save);
        buttonCancel = (Button) v.findViewById(R.id.button_saveToFile_cancel);

        buttonSave.setOnClickListener(onButtonClick);
        buttonCancel.setOnClickListener(onButtonClick);

        return v;
    }

    @Override
    public void onStart()
    {
        super.onStart();
    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        fileName = editTextFileName.getText().toString();
        outState.putString("fileName", fileName);
        outState.putString("fileExtension", fileExtension);
    }

    private View.OnClickListener onButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            if(v.getId() == R.id.button_saveToFile_save)
            {
                mListener.onDialogDone(SaveToFileDialogFragment.this.getTag(), false, editTextFileName.getText().toString());
                SaveToFileDialogFragment.this.dismiss();
            }
            else
            {
                SaveToFileDialogFragment.this.dismiss();
            }

        }
    };

}