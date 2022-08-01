package com.example.pedometer;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subactivity);
        TextView textViewNo = (TextView) findViewById(R.id.textViewNo);
        TextView textViewNo2 = (TextView) findViewById(R.id.textViewNo2);
        // 자동차 >> km 97g n보 == 0.00076km X n *
        textViewNo.setText(String.valueOf(Var.count));
        textViewNo2.setText("자동차 기준 :"+ String.valueOf((double)Var.count*0.00076*100)+" g만큼 이산화탄소를 아꼈어요.");



        Button ChangeButton = findViewById(R.id.changeButton2);
        ChangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SubActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}