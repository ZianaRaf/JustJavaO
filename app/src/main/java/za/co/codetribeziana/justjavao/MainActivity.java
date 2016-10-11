package za.co.codetribeziana.justjavao;

import android.annotation.TargetApi;
import android.content.Intent;
import android.icu.math.BigDecimal;
import android.icu.text.NumberFormat;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int quantity = 0;
    int pricePerCup=5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }



    public void increment(View view){
            if(quantity==100){
                Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
                return;
            }
           quantity += 1;
           displayQuantity(quantity);


    }

    public void decrement(View view)
    {
        if(quantity==1){
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();

            return;
        }
        quantity-=1;
        displayQuantity(quantity);


    }
    public void submitOrder(View view) {

        CheckBox whippedCream = (CheckBox) findViewById(R.id.whipped_cream_check);
        boolean hasWhippedCream =whippedCream.isChecked();
        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate_check);
        boolean hasChocolate = chocolate.isChecked();

        EditText customerName = (EditText)findViewById(R.id.customer_name_field);
        String enteredName=customerName.getText().toString();

        int price =  calculatePrice(hasWhippedCream,hasChocolate);
        //display(orderedCoffes);
        //displayPrice(quantity*5);
        String priceMessage = createOrderSummary(price,hasWhippedCream,enteredName, hasChocolate);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT,"Just Java order for "+enteredName);
        intent.putExtra(Intent.EXTRA_TEXT,priceMessage);
        if(intent.resolveActivity(getPackageManager()) !=null){
            startActivity(intent);
        }

    }
    /**
     * Calculates rhe price of the order.
     *
     * Rertuning total price
     *
     */
    @TargetApi(Build.VERSION_CODES.N)
    public int calculatePrice(boolean whippedCream, boolean chocolate ){
        int basePrice = 5;
        int tPrice=0;
        if(whippedCream){
            basePrice+=1;
        }
        if(chocolate)
        {
            basePrice += 2;
        }
        return tPrice;
    }


    private String createOrderSummary(int price, boolean hasCream, String customerName,boolean hasChocolate ){
        String name = "Name:"+ customerName;
        String oQuantity = "Quantity: " + quantity;
        String whippedCream="Add whipped cream? "+hasCream;
        String addChocolate="Add whipped cream? "+hasChocolate;
        String thanks= "Thank you";
        String priceMessage = name + "\n"+whippedCream+"\n"+addChocolate+"\n" +oQuantity+"\n Total: $" + price+ "\n" + thanks;

        return priceMessage;
    }

    /**     *
     * @param number number cups orderd
     * displays the given quantity on the screen
     */
    private void displayQuantity(int number){

        TextView orderSummaryTextView = (TextView) findViewById(R.id.quantity_text_view);
        orderSummaryTextView.setText(number);
    }

    @TargetApi(Build.VERSION_CODES.N)
    private void displayPrice(int number){

       // TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
       // priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }



}

