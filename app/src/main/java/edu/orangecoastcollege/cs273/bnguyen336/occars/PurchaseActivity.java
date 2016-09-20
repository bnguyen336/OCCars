/**
 * Nguyen, Benjamin
 * CS273 - Mobile Development
 * C02253862
 */

package edu.orangecoastcollege.cs273.bnguyen336.occars;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.NumberFormat;

/**
 * Main screen/launcher
 */
public class PurchaseActivity extends Activity {

    private EditText carPriceEditText;
    private EditText downPaymentEditText;
    private TextView carPriceTextView;
    private TextView downPaymentTextView;
    private RadioGroup loanTermRadioGroup;
    private RadioButton threeYearsRadioButton;
    private RadioButton fourYearsRadioButton;
    private RadioButton fiveYearsRadioButton;
    private Button loanReportButton;

    //Format variable to currency with 2 decimal places based on local
    private NumberFormat currency = NumberFormat.getCurrencyInstance();

    //New instance of Car()
    private Car car = new Car();

    //Strings to populate loan report
    String monthlyPaymentText;
    String loanSummaryText;

    //Static Strings used for intent's names/identifications
    private final static String MONTHLY_PAYMENT_EXTRA = "MonthlyPayment";
    private final static String LOAN_SUMMARY_EXTRA = "LoanSummary";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        //Reference instance variables to corresponding layouts
        carPriceEditText = (EditText) findViewById(R.id.carPriceEditText);
        downPaymentEditText = (EditText) findViewById(R.id.downPaymentEditText);
        carPriceTextView = (TextView) findViewById(R.id.carPriceTextView);
        downPaymentTextView = (TextView) findViewById(R.id.downPaymentTextView);
        loanTermRadioGroup = (RadioGroup) findViewById(R.id.loanTermRadioGroup);
        threeYearsRadioButton = (RadioButton) findViewById(R.id.threeYearsRadioButton);
        fourYearsRadioButton = (RadioButton) findViewById(R.id.fourYearsRadioButton);
        fiveYearsRadioButton = (RadioButton) findViewById(R.id.fiveYearsRadioButton);

        //Display initial 0.00 value of current local's currency
        carPriceTextView.setText(currency.format(car.getPrice()));
        downPaymentTextView.setText(currency.format(car.getDownPayment()));

        //Simultaneously read and display user's inputs
        carPriceEditText.addTextChangedListener(carPriceTextChangedListener);
        downPaymentEditText.addTextChangedListener(downPaymentTextChangedListener);
    }

    //Text change listener for car price
    private TextWatcher carPriceTextChangedListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            //Do nothing
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            double price;
            try {
                if (charSequence.length() < 1) {
                    price  = 0.0;
                } else {
                    price  = Double.parseDouble(charSequence.toString());
                }
            } catch (NumberFormatException e) {
                price = 0.0;
            }
            car.setPrice(price);
            carPriceTextView.setText(currency.format(car.getPrice()));
        }

        @Override
        public void afterTextChanged(Editable editable) {
            //Do nothing
        }
    };


    //Text change listener for down payment
    private TextWatcher downPaymentTextChangedListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            //Do nothing
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            double downPayment;
            try {
                if (charSequence.length() < 1) {
                    downPayment = 0.0;
                } else {
                    downPayment = Double.parseDouble(charSequence.toString());
                }
            } catch (NumberFormatException e) {
                downPayment = 0.0;
            }
            car.setDownPayment(downPayment);
            downPaymentTextView.setText(currency.format(car.getDownPayment()));
        }

        @Override
        public void afterTextChanged(Editable editable) {
            //Do nothing
        }
    };

    //Read user's choice of loan term
    private void checkLoanTerm() {
        if (threeYearsRadioButton.isChecked())
            car.setLoanTerm(3);
        else if (fourYearsRadioButton.isChecked())
            car.setLoanTerm(4);
        else
            car.setLoanTerm(5);
    }

    /**
     * Read all user's inputs and populate the information and results inside an intent
     * to send to LoanSummaryActivity
     * @param v
     */
    public void activateLoanReport(View v) {
        double price, downPayment;
        try {
            price = Double.parseDouble(carPriceEditText.getText().toString());
            downPayment = Double.parseDouble(downPaymentEditText.getText().toString());
        } catch (NumberFormatException e) {
            price = 0.0;
            downPayment = 0.0;
        }

        checkLoanTerm();
        car.setPrice(price);
        car.setDownPayment(downPayment);

        constructLoanSummaryText();

        Intent loanSummaryIntent = new Intent(this, LoanSummaryActivity.class);
        loanSummaryIntent.putExtra(MONTHLY_PAYMENT_EXTRA, monthlyPaymentText);
        loanSummaryIntent.putExtra(LOAN_SUMMARY_EXTRA, loanSummaryText);

        startActivity(loanSummaryIntent);
    }

    /**
     * Populate Strings with results from calculations in Car.java
     */
    private void constructLoanSummaryText() {
        monthlyPaymentText = getString(R.string.report_line1) + currency.format(car.calculateMonthlyPayment());
        loanSummaryText = getString(R.string.report_line2) + currency.format(car.getPrice())
                        + getString(R.string.report_line3) + currency.format(car.getDownPayment())
                        + getString(R.string.report_line5) + currency.format(car.calculateTaxAmount())
                        + getString(R.string.report_line6) + currency.format(car.calculateTotalCost())
                        + getString(R.string.report_line7) + currency.format(car.calculateBorrowedAmount())
                        + getString(R.string.report_line8) + currency.format(car.calculateInterestAmount())
                        + getString(R.string.report_line4) + car.getLoanTerm() + " years"
                        + getString(R.string.report_line9)
                        + getString(R.string.report_line10)
                        + getString(R.string.report_line11);
    }
}
