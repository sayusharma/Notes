package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashSet;

public class Main2Activity extends AppCompatActivity {
    int itemId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView textView = findViewById(R.id.addNewNote);
        EditText editText = (EditText) findViewById(R.id.editText);
        Intent intent = getIntent();
        itemId = intent.getIntExtra("itemId",-1);
        int view = intent.getIntExtra("View",-1);
        Log.i("View",Integer.toString(view));
        if(view==1){
            textView.setVisibility(View.VISIBLE);
        }else{
            textView.setVisibility(View.INVISIBLE);
        }
        if(itemId!=-1){
            Log.i("Editetxt",""+String.valueOf(editText));
            editText.setText(MainActivity.arrayList.get(itemId));
        }else{
            MainActivity.arrayList.add("");
            itemId = MainActivity.arrayList.size()-1;
        }
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                MainActivity.arrayList.set(itemId,String.valueOf(charSequence));
                MainActivity.arrayAdapter.notifyDataSetChanged();
                SharedPreferences sharedPreferences = getSharedPreferences("com.example.notes",CONTEXT_RESTRICTED);
                HashSet<String> set = new HashSet<>(MainActivity.arrayList);
                sharedPreferences.edit().putStringSet("notes",set).apply();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
