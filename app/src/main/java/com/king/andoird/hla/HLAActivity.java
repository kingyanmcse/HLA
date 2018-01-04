package com.king.andoird.hla;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class HLAActivity extends AppCompatActivity {

    private static String[] A1 = {"1", "3", "11", "29", "36"};
    private static String[] A2 = {"2", "23", "24", "68", "69"};
    private static String[] A10 = {"25", "26", "34", "66", "19", "31", "32", "33", "74", "43"};
    private static String[] B5 = {"5", "51", "35", "53", "78", "5", "57"};
    private static String[] B7 = {"7", "22", "54", "55", "56", "27", "42", "46", "67"};
    private static String[] B8 = {"8", "14", "64", "B65", "16", "39", "78"};
    private static String[] B12 = {"12", "44", "45", "13", "21", "49", "50", "40", "60", "61", "41", "47"};
    private static String[] B17 = {"17", "57", "58", "63", "59"};
    private static String[] DQ1 = {"1", "10", "2", "15", "16", "6", "13", "14"};
    private static String[] DQ2 = {"3", "17", "18", "7"};
    private static String[] DQ3 = {"4", "5", "11", "12", "9", "14"};
    private static String[] DQ4 = {"8", "18"};
    private static String[] DRB3 = {"3", "17", "18", "5", "11", "1", "6", "13", "14"};
    private static String[] DRB4 = {"4", "7", "9"};
    private static String[] DRB5 = {"1", "10", "2", "15", "16"};

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
                int resultA = matching("A", a1, a2, a11, a22);

                String b1 = mB1EditText.getText().toString();
                String b2 = mB2EditText.getText().toString();
                String b11 = mB11EditText.getText().toString();
                String b22 = mB22EditText.getText().toString();
                int resultB = matching("B", b1, b2, b11, b22);

                String dq1 = mDQ1EditText.getText().toString();
                String dq2 = mDQ2EditText.getText().toString();
                String dq11 = mDQ11EditText.getText().toString();
                String dq22 = mDQ22EditText.getText().toString();
                int resultDQ = matching("DQ", dq1, dq2, dq11, dq22);

                String dr1 = mDR1EditText.getText().toString();
                String dr2 = mDR2EditText.getText().toString();
                String dr11 = mDR11EditText.getText().toString();
                String dr22 = mDR22EditText.getText().toString();
                int resultDR = matching("DR", dr1, dr2, dr11, dr22);

                mResultText.setText(checkAll(resultA, resultB, resultDQ, resultDR));
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
     * EditText获取焦点并显示软键盘
     */
    public String checkAll(int aCnt, int bCnt, int dqCnt, int drCnt) {
        String result = "不匹配";
        int allCount = 0;
        if (aCnt > 0) {
            allCount++;
        }
        if (bCnt > 0) {
            allCount++;
        }
        if (dqCnt > 0) {
            allCount++;
        }
        if (drCnt > 0) {
            allCount++;
        }
        if (allCount == 1) {
            result = "1点匹配";
        } else if (allCount == 2) {
            result = "2点匹配";
        } else if (allCount == 3) {
            result = "3点匹配";
        } else if (allCount == 4) {
            result = "完全匹配";
        }
        return result;
    }

    /**
     * 匹配A点数据,若供体抗源特异性为0，则可以匹配任意值，若受体抗源特异性维0，则只能匹配为0的供体。
     */
    public int matching(String type, String a1, String a2, String a11, String a22) {
        int result = 0;
        int a1Count = 0;
        int a2Count = 0;
        int b1Count = 0;
        int b2Count = 0;
        int dr1Count = 0;
        int dr2Count = 0;
        int dq1Count = 0;
        int dq2Count = 0;
        if ("A".equals(type)) {
//            if (a1 == null || a1.length() == 0) {
//                Toast.makeText(this, "请输入供体A1点位", Toast.LENGTH_SHORT)
//                        .show();
//            }
//            if (a2 == null || a2.length() == 0) {
//                Toast.makeText(this, "请输入供体A2点位", Toast.LENGTH_SHORT)
//                        .show();
//            }
//            if (a11 == null || a11.length() == 0) {
//                Toast.makeText(this, "请输入受体A1点位", Toast.LENGTH_SHORT)
//                        .show();
//            }
//            if (a22 == null || a22.length() == 0) {
//                Toast.makeText(this, "请输入受体A2点位", Toast.LENGTH_SHORT)
//                        .show();
//            }
            for (String str : A1) {
                if (str.equals(a1) || "0".equals(a1)) {
                    a1Count++;
                }
                if (str.equals(a11) || ("0".equals(a11) && "0".equals(a1))) {
                    a1Count++;
                }
                if (str.equals(a2) || "0".equals(a2)) {
                    a2Count++;
                }
                if (str.equals(a22) || ("0".equals(a22) && "0".equals(a2))) {
                    a2Count++;
                }
            }
            for (String str : A2) {
                if (str.equals(a1) || "0".equals(a1)) {
                    a1Count++;
                }
                if (str.equals(a11) || ("0".equals(a11) && "0".equals(a1))) {
                    a1Count++;
                }
                if (str.equals(a2) || "0".equals(a2)) {
                    a2Count++;
                }
                if (str.equals(a22) || ("0".equals(a22) && "0".equals(a2))) {
                    a2Count++;
                }
            }
            for (String str : A10) {
                if (str.equals(a1) || "0".equals(a1)) {
                    a1Count++;
                }
                if (str.equals(a11) || ("0".equals(a11) && "0".equals(a1))) {
                    a1Count++;
                }
                if (str.equals(a2) || "0".equals(a2)) {
                    a2Count++;
                }
                if (str.equals(a22) || ("0".equals(a22) && "0".equals(a2))) {
                    a2Count++;
                }
            }
            if (a1Count > 1 && a2Count > 1) {
                result = 1;
            }
        } else if ("B".equals(type)) {
//            if (a1 == null || a1.length() == 0) {
//                Toast.makeText(this, "请输入供体B1点位", Toast.LENGTH_SHORT)
//                        .show();
//            }
//            if (a2 == null || a2.length() == 0) {
//                Toast.makeText(this, "请输入供体B2点位", Toast.LENGTH_SHORT)
//                        .show();
//            }
//            if (a11 == null || a11.length() == 0) {
//                Toast.makeText(this, "请输入受体B1点位", Toast.LENGTH_SHORT)
//                        .show();
//            }
//            if (a22 == null || a22.length() == 0) {
//                Toast.makeText(this, "请输入受体B2点位", Toast.LENGTH_SHORT)
//                        .show();
//            }
            for (String str : B5) {
                if (str.equals(a1) || "0".equals(a1)) {
                    b1Count++;
                }
                if (str.equals(a11) || ("0".equals(a11) && "0".equals(a1))) {
                    b1Count++;
                }
                if (str.equals(a2) || "0".equals(a2)) {
                    b2Count++;
                }
                if (str.equals(a22) || ("0".equals(a22) && "0".equals(a2))) {
                    b2Count++;
                }
            }
            for (String str : B7) {
                if (str.equals(a1) || "0".equals(a1)) {
                    b1Count++;
                }
                if (str.equals(a11) || ("0".equals(a11) && "0".equals(a1))) {
                    b1Count++;
                }
                if (str.equals(a2) || "0".equals(a2)) {
                    b2Count++;
                }
                if (str.equals(a22) || ("0".equals(a22) && "0".equals(a2))) {
                    b2Count++;
                }
            }
            for (String str : B8) {
                if (str.equals(a1) || "0".equals(a1)) {
                    b1Count++;
                }
                if (str.equals(a11) || ("0".equals(a11) && "0".equals(a1))) {
                    b1Count++;
                }
                if (str.equals(a2) || "0".equals(a2)) {
                    b2Count++;
                }
                if (str.equals(a22) || ("0".equals(a22) && "0".equals(a2))) {
                    b2Count++;
                }
            }
            for (String str : B12) {
                if (str.equals(a1) || "0".equals(a1)) {
                    b1Count++;
                }
                if (str.equals(a11) || ("0".equals(a11) && "0".equals(a1))) {
                    b1Count++;
                }
                if (str.equals(a2) || "0".equals(a2)) {
                    b2Count++;
                }
                if (str.equals(a22) || ("0".equals(a22) && "0".equals(a2))) {
                    b2Count++;
                }
            }
            for (String str : B17) {
                if (str.equals(a1) || "0".equals(a1)) {
                    b1Count++;
                }
                if (str.equals(a11) || ("0".equals(a11) && "0".equals(a1))) {
                    b1Count++;
                }
                if (str.equals(a2) || "0".equals(a2)) {
                    b2Count++;
                }
                if (str.equals(a22) || ("0".equals(a22) && "0".equals(a2))) {
                    b2Count++;
                }
            }
            if (b1Count > 1 && b2Count > 1) {
                result = 1;
            }
        } else if ("DQ".equals(type)) {
//            if (a1 == null || a1.length() == 0) {
//                Toast.makeText(this, "请输入供体DQ1点位", Toast.LENGTH_SHORT)
//                        .show();
//            }
//            if (a2 == null || a2.length() == 0) {
//                Toast.makeText(this, "请输入供体DQ2点位", Toast.LENGTH_SHORT)
//                        .show();
//            }
//            if (a11 == null || a11.length() == 0) {
//                Toast.makeText(this, "请输入受体DQ1点位", Toast.LENGTH_SHORT)
//                        .show();
//            }
//            if (a22 == null || a22.length() == 0) {
//                Toast.makeText(this, "请输入受体DQ2点位", Toast.LENGTH_SHORT)
//                        .show();
//            }
            for (String str : DQ1) {
                if (str.equals(a1) || "0".equals(a1)) {
                    dq1Count++;
                }
                if (str.equals(a11) || ("0".equals(a11) && "0".equals(a1))) {
                    dq1Count++;
                }
                if (str.equals(a2) || "0".equals(a2)) {
                    dq2Count++;
                }
                if (str.equals(a22) || ("0".equals(a22) && "0".equals(a2))) {
                    dq2Count++;
                }
            }
            for (String str : DQ2) {
                if (str.equals(a1) || "0".equals(a1)) {
                    dq1Count++;
                }
                if (str.equals(a11) || ("0".equals(a11) && "0".equals(a1))) {
                    dq1Count++;
                }
                if (str.equals(a2) || "0".equals(a2)) {
                    dq2Count++;
                }
                if (str.equals(a22) || ("0".equals(a22) && "0".equals(a2))) {
                    dq2Count++;
                }
            }
            for (String str : DQ3) {
                if (str.equals(a1) || "0".equals(a1)) {
                    dq1Count++;
                }
                if (str.equals(a11) || ("0".equals(a11) && "0".equals(a1))) {
                    dq1Count++;
                }
                if (str.equals(a2) || "0".equals(a2)) {
                    dq2Count++;
                }
                if (str.equals(a22) || ("0".equals(a22) && "0".equals(a2))) {
                    dq2Count++;
                }
            }
            for (String str : DQ4) {
                if (str.equals(a1) || "0".equals(a1)) {
                    dq1Count++;
                }
                if (str.equals(a11) || ("0".equals(a11) && "0".equals(a1))) {
                    dq1Count++;
                }
                if (str.equals(a2) || "0".equals(a2)) {
                    dq2Count++;
                }
                if (str.equals(a22) || ("0".equals(a22) && "0".equals(a2))) {
                    dq2Count++;
                }
            }
            if (dq1Count > 1 && dq2Count > 1) {
                result = 1;
            }
        } else if ("DR".equals(type)) {
//            if (a1 == null || a1.length() == 0) {
//                Toast.makeText(this, "请输入供体DR1点位", Toast.LENGTH_SHORT)
//                        .show();
//            }
//            if (a2 == null || a2.length() == 0) {
//                Toast.makeText(this, "请输入供体DR2点位", Toast.LENGTH_SHORT)
//                        .show();
//            }
//            if (a11 == null || a11.length() == 0) {
//                Toast.makeText(this, "请输入受体DR1点位", Toast.LENGTH_SHORT)
//                        .show();
//            }
//            if (a22 == null || a22.length() == 0) {
//                Toast.makeText(this, "请输入受体DR2点位", Toast.LENGTH_SHORT)
//                        .show();
//            }
            for (String str : DRB3) {
                if (str.equals(a1) || "0".equals(a1)) {
                    dr1Count++;
                }
                if (str.equals(a11) || ("0".equals(a11) && "0".equals(a1))) {
                    dr1Count++;
                }
                if (str.equals(a2) || "0".equals(a2)) {
                    dr2Count++;
                }
                if (str.equals(a22) || ("0".equals(a22) && "0".equals(a2))) {
                    dr2Count++;
                }
            }
            for (String str : DRB4) {
                if (str.equals(a1) || "0".equals(a1)) {
                    dr1Count++;
                }
                if (str.equals(a11) || ("0".equals(a11) && "0".equals(a1))) {
                    dr1Count++;
                }
                if (str.equals(a2) || "0".equals(a2)) {
                    dr2Count++;
                }
                if (str.equals(a22) || ("0".equals(a22) && "0".equals(a2))) {
                    dr2Count++;
                }
            }
            for (String str : DRB5) {
                if (str.equals(a1) || "0".equals(a1)) {
                    dr1Count++;
                }
                if (str.equals(a11) || ("0".equals(a11) && "0".equals(a1))) {
                    dr1Count++;
                }
                if (str.equals(a2) || "0".equals(a2)) {
                    dr2Count++;
                }
                if (str.equals(a22) || ("0".equals(a22) && "0".equals(a2))) {
                    dr2Count++;
                }
            }
            if (dr1Count > 1 && dr2Count > 1) {
                result = 1;
            }
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
