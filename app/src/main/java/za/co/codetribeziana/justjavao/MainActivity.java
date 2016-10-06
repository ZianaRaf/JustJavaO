package za.co.codetribeziana.justjavao;

import android.annotation.TargetApi;
import android.icu.text.NumberFormat;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    public void increment(View view){

           quantity += 1;
           displayQuantity(quantity);


    }

    public void decrement(View view)
    {
        quantity-=1;
        displayQuantity(quantity);


    }
    public void submitOrder(View view) {
        int price = quantity*5;
        String thanks= "Thank you";
        String priceMessage = "Total: $" + price+ "\n" + thanks;
        //display(orderedCoffes);
        //dislayPrice(quantity*5);
        displayMessage(priceMessage);

    }
    private void displayQuantity(int number){

        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(""+number);
    }

    @TargetApi(Build.VERSION_CODES.N)
    private void dislayPrice(int number){

        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    private void displayMessage(String message){
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }

}

