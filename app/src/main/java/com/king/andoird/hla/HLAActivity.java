package com.king.andoird.hla;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class HLAActivity extends AppCompatActivity {

    private static String[] A1 = {"1", "3", "11", "29", "36"};
    private static String[] A2 = {"2", "23", "24", "68", "69"};
    private static String[] A10 = {"25", "26", "34", "66", "19", "31", "32", "33", "74", "43"};
//    private static String[] B5 = {B5(51), "35", "53", "78", "5", "57"};
//    private static String[] B7 = {"7", B22(B54, B55, B56), "27", "42", "46", "67"};
//    private static String[] B8 = {B8, B14(B64, B65), B16(B39), "78"};
//    private static String[] B12 = {B12(B44, B45), B13, B21, (B49, B50), B40(B60, B61),"41","47"};
//    private static String[]B17={B17(B57,B58),"63","59"};
//    private static String[]DQ1={DR1（10）,2（15,16）,6（13,14）};
//    private static String[]DQ2={DR3（17,18）,"7"};
//    private static String[]DQ3={DR4,5（11,12）,"9","14"};
//    private static String[]DQ4={"8","18"};
//    private static String[]DRB3={DR3(17,18),5（11,,1）,6（13,14）};
//    private static String[]DRB4={"4","7","9"};
//    private static String[]DRB5={DR1(10),2（15,16）};

    private Button mConfirmButton;
    private Button mClearButton;

    private EditText mA1EditText;
    private EditText mA2EditText;
    private EditText mB1EditText;
    private EditText mB2EditText;
    private EditText mDR1EditText;
    private EditText mDR2EditText;
    private EditText mDQ1EditText;
    private EditText mDQ2EditText;

    private EditText mA11EditText;
    private EditText mA22EditText;
    private EditText mB11EditText;
    private EditText mB22EditText;
    private EditText mDR11EditText;
    private EditText mDR22EditText;
    private EditText mDQ11EditText;
    private EditText mDQ22EditText;
    private TextView mResultText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_hla);

        mA1EditText = findViewById(R.id.a1_edittext);
        mA2EditText = findViewById(R.id.a2_edittext);
        mB1EditText = findViewById(R.id.b1_edittext);
        mB2EditText = findViewById(R.id.b2_edittext);
        mDR1EditText = findViewById(R.id.dr1_edittext);
        mDR2EditText = findViewById(R.id.dr2_edittext);
        mDQ1EditText = findViewById(R.id.dq1_edittext);
        mDQ2EditText = findViewById(R.id.dq2_edittext);

        mA11EditText = findViewById(R.id.a11_edittext);
        mA22EditText = findViewById(R.id.a22_edittext);
        mB11EditText = findViewById(R.id.b11_edittext);
        mB22EditText = findViewById(R.id.b22_edittext);
        mDR11EditText = findViewById(R.id.dr11_edittext);
        mDR22EditText = findViewById(R.id.dr22_edittext);
        mDQ11EditText = findViewById(R.id.dq11_edittext);
        mDQ22EditText = findViewById(R.id.dq22_edittext);

        mResultText = findViewById(R.id.result_text);


        mConfirmButton = findViewById(R.id.cofirm_button);
        mConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a1 = mA1EditText.getText().toString();
                String a2 = mA2EditText.getText().toString();
                String a11 = mA11EditText.getText().toString();
                String a22 = mA22EditText.getText().toString();
                String result = matching(a1, a2, a11, a22);
                mResultText.setText(result);
            }
        });
        mClearButton = findViewById(R.id.clear_button);
        mClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearEditText();
                showSoftInputFromWindow(HLAActivity.this, mA1EditText);
            }
        });

    }

    /**
     * EditText获取焦点并显示软键盘
     */
    public void showSoftInputFromWindow(Activity activity, EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
//        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 匹配数据
     */
    public String matching(String a1, String a2, String a11, String a22) {
        String result = "供受不匹配";
        if (a1 == null || a1.length() == 0) {
            return "请输入供体A1点位";
        }
        if (a2 == null || a2.length() == 0) {
            return "请输入供体A2点位";
        }
        if (a11 == null || a11.length() == 0) {
            return "请输入受体A1点位";
        }
        if (a22 == null || a22.length() == 0) {
            return "请输入受体A2点位";
        }

        int a1A1Count = 0;
        int a2A1Count = 0;
        int a1A2Count = 0;
        int a2A2Count = 0;
        int a1A10Count = 0;
        int a2A10Count = 0;
        for (String str : A1) {
            if (str.equals(a1) || "0".equals(a1)) {
                a1A1Count++;
            }
            if (str.equals(a11) || "0".equals(a11)) {
                a1A1Count++;
            }
            if (str.equals(a2) || "0".equals(a2)) {
                a2A1Count++;
            }
            if (str.equals(a22) || "0".equals(a22)) {
                a2A1Count++;
            }
        }
        for (String str : A2) {
            if (str.equals(a1) || "0".equals(a1)) {
                a1A2Count++;
            }
            if (str.equals(a11) || "0".equals(a11)) {
                a1A2Count++;
            }
            if (str.equals(a2) || "0".equals(a2)) {
                a2A2Count++;
            }
            if (str.equals(a22) || "0".equals(a22)) {
                a2A2Count++;
            }
        }
        for (String str : A10) {
            if (str.equals(a1) || "0".equals(a1)) {
                a1A10Count++;
            }
            if (str.equals(a11) || "0".equals(a11)) {
                a1A10Count++;
            }
            if (str.equals(a2) || "0".equals(a2)) {
                a2A10Count++;
            }
            if (str.equals(a22) || "0".equals(a22)) {
                a2A10Count++;
            }
        }
        StringBuffer sb = new StringBuffer();
        if (a1A1Count > 1) {
            sb.append("A1匹配成功");
            sb.append("\r");
        }
        if (a1A2Count > 1) {
            sb.append("A2匹配成功");
            sb.append("\r");
        }
        if (a1A10Count > 1) {
            sb.append("A10匹配成功");
            sb.append("\r");
        }
        if (a2A1Count > 1) {
            if (!sb.toString().contains("A1匹配成功")) {
                sb.append("A1匹配成功");
            }
        }
        if (a2A2Count > 1) {
            if (!sb.toString().contains("A2匹配成功")) {
                sb.append("A2匹配成功");
            }
        }
        if (a2A10Count > 1) {
            if (!sb.toString().contains("A10匹配成功")) {
                sb.append("A10匹配成功");
            }
        }
        if (sb.toString().length() > 0) {
            return sb.toString();
        }
        return result;
    }

    /**
     * 清除输入框
     */
    public void clearEditText() {
        mA1EditText.setText("");
        mA2EditText.setText("");
        mB1EditText.setText("");
        mB2EditText.setText("");
        mDR1EditText.setText("");
        mDR2EditText.setText("");
        mDQ1EditText.setText("");
        mDQ2EditText.setText("");

        mA11EditText.setText("");
        mA22EditText.setText("");
        mB11EditText.setText("");
        mB22EditText.setText("");
        mDR11EditText.setText("");
        mDR22EditText.setText("");
        mDQ11EditText.setText("");
        mDQ22EditText.setText("");

        mResultText.setText("");
    }
}
