package com.example.laundryhubv2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

public class Placeorder extends AppCompatActivity {

    Prices prices;
    TextView total;
    double total_price;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placeorder);

        prices = new Prices();
        total = findViewById(R.id. total1);


    }

    public void radioClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()){

            case R.id.Radiobutton4:
                if (checked)
                    prices.setLaundry_prices(35);
                break;

            case R.id.Radiobutton5:
                if (checked)
                    prices.setLaundry_prices(70);
                break;

            case R.id.Radiobutton6:
                if (checked)
                    prices.setLaundry_prices(105);
                break;

            case R.id.Radiobutton7:
                if (checked)
                    prices.setLaundry_prices(140);
                break;

            case R.id.Radiobutton8:
                if (checked)
                    prices.setLaundry_prices(175);
                break;
        }

        total.setText("TOTAL PRICE:" + calculate_total1());
    }

    private double calculate_total1(){
        total_price = prices.getLaundry_prices() + prices.getWash_price() + prices.getDry_price() + prices.getFold_price() + prices.getPress_price();
        return  total_price;
    }

    public void onCheckboxClicked(View view) {

        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId()){
            case R.id.Checkboxwash:
                if (checked)
                    prices.setWash_price(105);
                else
                    prices.setWash_price(0);
                break;

            case R.id.Checkboxdry:
                if (checked)
                    prices.setDry_price(20);
                else
                    prices.setDry_price(0);
                break;

            case R.id.Checkboxfold:
                if (checked)
                    prices.setFold_price(20);
                else
                    prices.setFold_price(0);
                break;

            case R.id.Checkboxpress:
                if (checked)
                    prices.setPress_price(20);
                else
                    prices.setPress_price(0);
                break;

        }

        total.setText("TOTAL PRICE:" + calculate_total());
    }

    private double calculate_total(){
        total_price = prices.getLaundry_prices() + prices.getWash_price() + prices.getDry_price() + prices.getFold_price() + prices.getPress_price();
        return  total_price;
    }
}