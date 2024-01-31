package com.example.zikrcounter;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private int count = 1;
    TextView text,cardZikrTxt,cardMinMaxCounterTxt, animation;
    AlertDialog.Builder alert;
    RecyclerView recyclerView;
    ZikrAdapter adapter;
    ArrayList<Zikr> list;
    private int index = 0;
    private int counts = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alert = new AlertDialog.Builder(this);
        recyclerView = findViewById(R.id.recycler_view);
        list = new ArrayList<>();
        text = findViewById(R.id.counter);
        cardZikrTxt = findViewById(R.id.card_zikr);
        cardMinMaxCounterTxt = findViewById(R.id.card_min_Max);
        animation = findViewById(R.id.animation);

        cardZikrTxt.setText("");
        cardMinMaxCounterTxt.setText("Select Check Boxes and Click + to Start");
        text.setText("Start");


        //Adding Zikrs to recycler List here onwards

        list.add(new Zikr("Astaghfirullah", 3));
        list.add(new Zikr("Alhamdulillah", 3));
        list.add(new Zikr("Allah hu Akbar", 5));


        adapter = new ZikrAdapter(this,list);
        LinearLayoutManager llm = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);
    }

    public void increment(View view) {
        int totalSelected = adapter.getListOfSelectedZikr().size();
        counts++;
        if (counts == list.get(index).getNoOfTimesZikr()) {
            index++;
            counts = 0;
            count = 0;
        }
             if (index < totalSelected) {
                 String txt =  adapter.getListOfSelectedZikr().get(index).getZikr();
                cardZikrTxt.setText(txt);
                cardMinMaxCounterTxt.setText("0 / " + adapter.getListOfSelectedZikr().get(index).getNoOfTimesZikr());
                text.setText("" + count);
                count++;
                showAnimation(txt);
        }else{
                cardZikrTxt.setText("");
                cardMinMaxCounterTxt.setText("");
                text.setText("Completed");
        }
    }

    private void showAnimation(String txt){
        animation.setVisibility(View.VISIBLE);
        animation.setText(txt);
        animation.animate().translationY(-100).alpha(0).setDuration(0600);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                animation.setVisibility(View.GONE);
                animation.animate().translationY(100).alpha(1).setDuration(0001);
            }
        },0600);

    }

    public void addZikr(View view) {
        LayoutInflater inflater = this.getLayoutInflater();
        View inflaterView = inflater.inflate(R.layout.add_zikr_layout,null);
        alert.setView(inflaterView);
        TextInputLayout zikr = inflaterView.findViewById(R.id.Zikr);
        TextInputLayout noOfTimes = inflaterView.findViewById(R.id.no_of_times);
        Button add = inflaterView.findViewById(R.id.add_zikr_btn);
        add.setOnClickListener(view1 -> {
            String zikrText = zikr.getEditText().getText().toString();
            int zikrTimes = Integer.parseInt(noOfTimes.getEditText().getText().toString());
            adapter.addZikr(new Zikr(zikrText,zikrTimes));
        });
        alert.show();
    }

    public void reset(View view) {
        cardZikrTxt.setText("");
        cardMinMaxCounterTxt.setText("Select Check Boxes and Click + to Start");
        text.setText("Start");
        adapter.refreshData();
        recyclerView.setAdapter(adapter);
    }
}