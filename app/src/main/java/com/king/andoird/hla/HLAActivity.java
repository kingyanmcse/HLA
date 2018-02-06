package com.king.andoird.hla;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class HLAActivity extends AppCompatActivity {

    private static String[] A1 = {"1", "3", "11", "29", "36"};
    private static String[] A2 = {"2", "23", "24", "68", "69"};
    private static String[] A10 = {"25", "26", "34", "66", "19", "31", "32", "33", "74", "43"};

    private static String[] B5 = {"5", "51", "35", "53", "78", "57"};
    private static String[] B7 = {"7", "22", "54", "55", "56", "27", "42", "46", "67"};
    private static String[] B8 = {"8", "14", "64", "65", "16", "39", "78"};
    private static String[] B12 = {"12", "44", "45", "13", "21", "49", "50", "40", "60", "61", "41", "47"};
    private static String[] B17 = {"17", "57", "58", "63", "59"};
    private static String[] BW4 = {"9", "23", "24", "25", "32", "5", "51", "52", "12", "13", "17", "57", "58", "21", "49", "27", "37", "38", "47", "53", "59", "63", "77"};
    private static String[] BW6 = {"11", "7", "8", "18", "14", "64", "65", "15", "62", "75", "76", "78", "16", "39", "22", "54", "55", "56", "35", "40", "60", "61", "48", "4005", "41", "42", "45", "46", "50", "67", "70", "71", "72"};

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
//    private TextView mAboutText;


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
//        mAboutText = findViewById(R.id.about);
//        mAboutText.setTextColor(Color.WHITE);

        mConfirmButton = findViewById(R.id.cofirm_button);
        mConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(),
                            0);
                }
                String a1 = mA1EditText.getText().toString();
                String a2 = mA2EditText.getText().toString();
                String a11 = mA11EditText.getText().toString();
                String a22 = mA22EditText.getText().toString();
                Integer resultA = matching("A", a1, a2, a11, a22);

                String b1 = mB1EditText.getText().toString();
                String b2 = mB2EditText.getText().toString();
                String b11 = mB11EditText.getText().toString();
                String b22 = mB22EditText.getText().toString();
                Integer resultB = matching("B", b1, b2, b11, b22);

                String dq1 = mDQ1EditText.getText().toString();
                String dq2 = mDQ2EditText.getText().toString();
                String dq11 = mDQ11EditText.getText().toString();
                String dq22 = mDQ22EditText.getText().toString();
                Integer resultDQ = matching("DQ", dq1, dq2, dq11, dq22);

                String dr1 = mDR1EditText.getText().toString();
                String dr2 = mDR2EditText.getText().toString();
                String dr11 = mDR11EditText.getText().toString();
                String dr22 = mDR22EditText.getText().toString();
                Integer resultDR = matching("DR", dr1, dr2, dr11, dr22);

                mResultText.setText(Html.fromHtml(checkAll(resultA, resultB, resultDQ, resultDR)));
            }
        });
        mClearButton = findViewById(R.id.clear_button);
        mClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearEditText(1);
                showSoftInputFromWindow(HLAActivity.this, mA11EditText);
            }
        });
        mClearButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                clearEditText(2);
                showSoftInputFromWindow(HLAActivity.this, mA1EditText);
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.hla, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setView(R.layout.dialog_hla)
                        .setCancelable(true)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //do things
                            }
                        });
//                builder.setMessage(R.string.about_text)
//                        .setCancelable(false)
//                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                //do things
//                            }
//                        });
                AlertDialog alert = builder.create();
                alert.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
     * 获得结果
     */
    public String checkAll(Integer aCnt, Integer bCnt, Integer dqCnt, Integer drCnt) {
        StringBuffer sb = new StringBuffer();
        int i = 0;
        if (aCnt != null) {
            if (aCnt == 2) {
                sb.append("<font color='#008000'>A组两点匹配;</font>");
            } else if (aCnt == 1) {
                sb.append("<font color='#FF0000'>A组一点匹配;</font>");
            } else {
                sb.append("<font color='#FF0000'>A组不匹配;</font>");
            }
            i++;
        }
        if (bCnt != null) {
            if (bCnt == 2) {
                sb.append("<font color='#008000'>B组两点匹配;</font>");
            } else if (bCnt == 1) {
                sb.append("<font color='#FF0000'>B组一点匹配;</font>");
            } else {
                sb.append("<font color='#FF0000'>B组不匹配;</font>");
            }
            i++;
            if (i == 2) {
                sb.append("<br>");
            }
        }
        if (drCnt != null) {
            if (drCnt == 2) {
                sb.append("<font color='#008000'>DR组两点匹配;</font>");
            } else if (drCnt == 1) {
                sb.append("<font color='#FF0000'>DR组一点匹配;</font>");
            } else {
                sb.append("<font color='#FF0000'>DR组不匹配;</font>");
            }
            i++;
            if (i == 2) {
                sb.append("<br>");
            }
        }
        if (dqCnt != null) {
            if (dqCnt == 2) {
                sb.append("<font color='#008000'>DQ组两点匹配;</font>");
            } else if (dqCnt == 1) {
                sb.append("<font color='#FF0000'>DQ组一点匹配;</font>");
            } else {
                sb.append("<font color='#FF0000'>DQ组不匹配;</font>");
            }
        }
        String result = sb.toString();
        if (result.endsWith("<br>")) {
            result = result.substring(0, result.length() - 2);
        }
        return result;
    }

    /**
     * 匹配数据并返回结果
     */
    public Integer matching(String type, String a1, String a2, String a11, String a22) {
        Integer result = 0;
        if ("A".equals(type)) {
            if (isNotEmpty(a1) && isNotEmpty(a11) && isNotEmpty(a2) && isNotEmpty(a22)) {
                int count1 = 0;
                int count2 = 0;
                for (String str : A1) {
                    count1 = count(str, a1, a11, count1);
                    count2 = count(str, a2, a22, count2);
                }
                //如果4点在同一组直接返回结果
                if (inSameGroup(count1, count2)) {
                    return 2;
                }
                count1 = resetZero(count1);
                count2 = resetZero(count2);
                for (String str : A2) {
                    count1 = count(str, a1, a11, count1);
                    count2 = count(str, a2, a22, count2);
                }
                if (inSameGroup(count1, count2)) {
                    return 2;
                }
                count1 = resetZero(count1);
                count2 = resetZero(count2);
                for (String str : A10) {
                    count1 = count(str, a1, a11, count1);
                    count2 = count(str, a2, a22, count2);
                }
                return getResult(count1, count2);
            } else {
                result = null;
            }
        } else if ("B".equals(type)) {
            if (isNotEmpty(a1) && isNotEmpty(a11) && isNotEmpty(a2) && isNotEmpty(a22)) {
                int count1 = 0;
                int count2 = 0;
                for (String str : B5) {
                    count1 = count(str, a1, a11, count1);
                    count2 = count(str, a2, a22, count2);
                }
                if (inSameGroup(count1, count2)) {
                    return 2;
                }
                count1 = resetZero(count1);
                count2 = resetZero(count2);
                for (String str : B7) {
                    count1 = count(str, a1, a11, count1);
                    count2 = count(str, a2, a22, count2);
                }
                if (inSameGroup(count1, count2)) {
                    return 2;
                }
                count1 = resetZero(count1);
                count2 = resetZero(count2);
                for (String str : B8) {
                    count1 = count(str, a1, a11, count1);
                    count2 = count(str, a2, a22, count2);
                }
                if (inSameGroup(count1, count2)) {
                    return 2;
                }
                count1 = resetZero(count1);
                count2 = resetZero(count2);
                for (String str : B12) {
                    count1 = count(str, a1, a11, count1);
                    count2 = count(str, a2, a22, count2);
                }
                if (inSameGroup(count1, count2)) {
                    return 2;
                }
                count1 = resetZero(count1);
                count2 = resetZero(count2);
                for (String str : B17) {
                    count1 = count(str, a1, a11, count1);
                    count2 = count(str, a2, a22, count2);
                }
                if (inSameGroup(count1, count2)) {
                    return 2;
                }
                count1 = resetZero(count1);
                count2 = resetZero(count2);
                for (String str : BW4) {
                    count1 = count(str, a1, a11, count1);
                    count2 = count(str, a2, a22, count2);
                }
                if (inSameGroup(count1, count2)) {
                    return 2;
                }
                count1 = resetZero(count1);
                count2 = resetZero(count2);
                for (String str : BW6) {
                    count1 = count(str, a1, a11, count1);
                    count2 = count(str, a2, a22, count2);
                }
                return getResult(count1, count2);
            } else {
                result = null;
            }
        } else if ("DQ".equals(type)) {
            if (isNotEmpty(a1) && isNotEmpty(a11) && isNotEmpty(a2) && isNotEmpty(a22)) {
                int count1 = 0;
                int count2 = 0;
                for (String str : DQ1) {
                    count1 = count(str, a1, a11, count1);
                    count2 = count(str, a2, a22, count2);
                }
                if (inSameGroup(count1, count2)) {
                    return 2;
                }
                count1 = resetZero(count1);
                count2 = resetZero(count2);
                for (String str : DQ2) {
                    count1 = count(str, a1, a11, count1);
                    count2 = count(str, a2, a22, count2);
                }
                if (inSameGroup(count1, count2)) {
                    return 2;
                }
                count1 = resetZero(count1);
                count2 = resetZero(count2);
                for (String str : DQ3) {
                    count1 = count(str, a1, a11, count1);
                    count2 = count(str, a2, a22, count2);
                }
                if (inSameGroup(count1, count2)) {
                    return 2;
                }
                count1 = resetZero(count1);
                count2 = resetZero(count2);
                for (String str : DQ4) {
                    count1 = count(str, a1, a11, count1);
                    count2 = count(str, a2, a22, count2);
                }
                return getResult(count1, count2);
            } else {
                result = null;
            }
        } else if ("DR".equals(type)) {
            if (isNotEmpty(a1) && isNotEmpty(a11) && isNotEmpty(a2) && isNotEmpty(a22)) {
                int count1 = 0;
                int count2 = 0;
                for (String str : DRB3) {
                    count1 = count(str, a1, a11, count1);
                    count2 = count(str, a2, a22, count2);
                }
                if (inSameGroup(count1, count2)) {
                    return 2;
                }
                count1 = resetZero(count1);
                count2 = resetZero(count2);
                for (String str : DRB4) {
                    count1 = count(str, a1, a11, count1);
                    count2 = count(str, a2, a22, count2);
                }
                if (inSameGroup(count1, count2)) {
                    return 2;
                }
                count1 = resetZero(count1);
                count2 = resetZero(count2);
                for (String str : DRB5) {
                    count1 = count(str, a1, a11, count1);
                    count2 = count(str, a2, a22, count2);
                }
                return getResult(count1, count2);
            } else {
                result = null;
            }
        }
        return result;
    }


    /**
     * 清除输入框(单点只清除受体，长按则全清除)
     */
    public void clearEditText(int type) {
        if (type == 2) {
            mA1EditText.setText("");
            mA2EditText.setText("");
            mB1EditText.setText("");
            mB2EditText.setText("");
            mDR1EditText.setText("");
            mDR2EditText.setText("");
            mDQ1EditText.setText("");
            mDQ2EditText.setText("");
        }
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

    private boolean isEmpty(String str) {
        if (str == null || str.trim().length() == 0) {
            return true;
        }
        return false;
    }

    private boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    //该组结果
    private int getResult(int count1, int count2) {
        if (count1 == 2 && count2 == 2) {
            return 2;
        } else {
            if (count1 > 1 || count2 > 1) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    private int resetZero(int count) {
        if (count != 2) {
            return 0;
        }
        return count;
    }

    private boolean inSameGroup(int count1, int count2) {
        if (count1 == 2 && count2 == 2) {
            return true;
        }
        return false;
    }

    private int count(String str, String a1, String a11, int count) {
        if (str.equals(a1) || str.equals(a11)) {
            if (a1.equals(a11)) {
                return count + 2;
            }
            return count + 1;
        }
        return count;
    }
}
