package com.example.demo_proj;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    RecyclerView demo_rv;
    RecyclerView.Adapter adapter;
    int constValue;
    Button submit;
    LinearLayout data;
    EditText constValueEd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
    }

    private void initialize() {
        try {
            constValue = 15;         //const value to define the rows
            submit = findViewById(R.id.submit_button);
            data = findViewById(R.id.data);
            constValueEd = findViewById(R.id.constValue);
            demo_rv = findViewById(R.id.demo_rv);
            demo_rv.setHasFixedSize(true);
            demo_rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            adapter = new boxItemAdapter(constValue, this);
            demo_rv.setAdapter(adapter);
        }catch (Exception e){
            e.printStackTrace();
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                if(constValueEd.getText().toString().isEmpty()){
//                    Toast.makeText(getApplicationContext(), "please enter a value to show the checkbox rows", Toast.LENGTH_LONG).show();
//                    return;
//                }
                if(data != null)
                    data.removeAllViews();

                for(int i=0; i<constValue; i++){
                    HorizontalScrollView horizontalScrollView = new HorizontalScrollView(getApplicationContext());
                    LinearLayout linearLayout = new LinearLayout(getApplicationContext());
                    linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    linearLayout.setOrientation(LinearLayout.HORIZONTAL);

                    TextView textRow =  new TextView(getApplicationContext());
                    textRow.setText("Row"+(i+1)+ "-> ");
                    linearLayout.addView(textRow);

                    for(int j=0; j<i+1; j++){
                        TextView textView = new TextView(getApplicationContext());

                        if(boxItemAdapter.instance.map.containsKey(""+(i+1)+""+(j+1))){

                            Object value = boxItemAdapter.instance.map.get(""+(i+1)+""+(j+1));
                            textView.setText("C"+(j+1) + ":" + value + " ");
                            linearLayout.addView(textView);
                        }else{
                            textView.setText("C"+(j+1) + ":" + "false" + " ");
                            linearLayout.addView(textView);
                        }

                    }
                    horizontalScrollView.addView(linearLayout);
                    data.addView(horizontalScrollView);
                }

//                if(data != null)
//                data.removeAllViews();
//
//                for (Map.Entry<String,String> entry : boxItemAdapter.instance.map.entrySet()) {
//                    String key = entry.getKey();
//                    String value = entry.getValue();
//
//                    // do stuff
//                    TextView textView = new TextView(getApplicationContext());
//                    textView.setText(key + " : " + value);
//                    data.addView(textView);
//                }

//                System.out.println(boxItemAdapter.instance.map);

//                final int childCount = data.getChildCount();
//                for (int i = 0; i < childCount; i++) {
//                    LinearLayout ll = (LinearLayout) data.getChildAt(i);
//                    final int childCount1 = ll.getChildCount();
//                    for(int j=0; j<childCount1; j++) {
//                        TextView tv = (TextView) ll.getChildAt(i);
//                        System.out.print("Row"+(i+1)+ ":" + tv.getText().toString()+ " ");
//                    }
//                    System.out.println();
//                }
            }
        });
    }
}