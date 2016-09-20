/**
 * Nguyen, Benjamin
 * CS273 - Mobile Development
 * C02253862
 */

package edu.orangecoastcollege.cs273.bnguyen336.occars;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Second screen/activity that displays loan report after all calculations
 */
public class LoanSummaryActivity extends Activity {

    /**
     * Empty default TextView that will be populated
     */
    private TextView monthlyPaymentTextView;
    private TextView loanReportTextView;

    /**
     * Strings used as names/identifications for activity intent
     */
    private final static String MONTHLY_PAYMENT_EXTRA = "MonthlyPayment";
    private final static String LOAN_SUMMARY_EXTRA = "LoanSummary";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_summary);

        //Reference instances with corresponding layouts
        monthlyPaymentTextView = (TextView) findViewById(R.id.monthlyPaymentTextView);
        loanReportTextView = (TextView) findViewById(R.id.loanReportTextView);

        //Extract and display String values from MainActivity's intent
        Intent intentFromPurchaseActivity = getIntent();
        String monthlyPayment = intentFromPurchaseActivity.getStringExtra(MONTHLY_PAYMENT_EXTRA);
        monthlyPaymentTextView.setText(monthlyPayment);
        String loanSumamry = intentFromPurchaseActivity.getStringExtra(LOAN_SUMMARY_EXTRA);
        loanReportTextView.setText(loanSumamry);
    }

    /**
     * Close view/activity
     * @param v
     */
    public void finishLoanSummary(View v) {
        this.finish();
    }
}
