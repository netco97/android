package com.example.pedometer;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SubActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subactivity);
        TextView pedometer_count = (TextView) findViewById(R.id.pedometer_count);
        TextView carView = (TextView) findViewById(R.id.carView);
        TextView motorcycleView = (TextView) findViewById(R.id.motorcycleView);
        TextView subwayView = (TextView) findViewById(R.id.subwayView);
        TextView busView = (TextView) findViewById(R.id.busView);
        TextView e_scooter = (TextView) findViewById(R.id.e_scooter);

        // 자동차 >> km 208g
        // n보 == 0.00076km X n *
        pedometer_count.setText(String.valueOf(Var.count)+" 걸음");
        carView.setText(" \uD83D\uDE97 : "+" 약 " + String.valueOf((double)Var.count*0.00076*208)+"g 탄소를 아꼈어요.");
        motorcycleView.setText(" \uD83C\uDFCD️ : "+" 약 " + String.valueOf((double)Var.count*0.00076*145)+"g 탄소를 아꼈어요.");
        e_scooter.setText(" \uD83D\uDEF4 : "+" 약 " + String.valueOf((double)Var.count*0.00076*126)+"g 탄소를 아꼈어요.");
        subwayView.setText("\uD83D\uDE87 : "+" 약 " + String.valueOf((double)Var.count*0.00076*60)+"g 탄소를 아꼈어요.");
        busView.setText("\uD83D\uDE8C : "+" 약 " + String.valueOf((double)Var.count*0.00076*58)+"g 탄소를 아꼈어요.");


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