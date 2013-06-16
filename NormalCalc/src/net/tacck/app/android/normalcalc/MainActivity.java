/*
 * Copyright (c) 2013, Takuya KIHARA / Tacck.NET All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice,
 *     this list of conditions and the following disclaimer.
 *  2. Redistributions in binary form must reproduce the above copyright notice,
 *     this list of conditions and the following disclaimer in the documentation and/or
 *     other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING
 * IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.tacck.app.android.normalcalc;

import net.tacck.app.android.normalcalc.Controller.OnDisplayListener;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

/**
 * Normal Calc Main Activity class.
 */
public class MainActivity extends Activity implements OnDisplayListener, OnClickListener {

    /** Button IDs. */
    private static final int[] BUTTON_IDS = { R.id.buttonNum0, R.id.buttonNum1, R.id.buttonNum2,
            R.id.buttonNum3, R.id.buttonNum4, R.id.buttonNum5, R.id.buttonNum6, R.id.buttonNum7,
            R.id.buttonNum8, R.id.buttonNum9, R.id.buttonClear, R.id.buttonAllClear,
            R.id.buttonPlus, R.id.buttonMinus, R.id.buttonAsterisk, R.id.buttonSlash,
            R.id.buttonEqual, R.id.buttonMemClear, R.id.buttonMemRead, R.id.buttonMemPlus,
            R.id.buttonMemMinus, };

    /** Controller. */
    private Controller mController = null;

    /** Display. */
    private TextView mTextView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_type2);

        mController = new Controller();
        mController.setOnDisplayListener(this);

        mTextView = (TextView) findViewById(R.id.textViewDisplay);

        for (int buttonId : BUTTON_IDS) {
            findViewById(buttonId).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonNum0:
                mController.pushButtonNumber(0);
                break;
            case R.id.buttonNum1:
                mController.pushButtonNumber(1);
                break;
            case R.id.buttonNum2:
                mController.pushButtonNumber(2);
                break;
            case R.id.buttonNum3:
                mController.pushButtonNumber(3);
                break;
            case R.id.buttonNum4:
                mController.pushButtonNumber(4);
                break;
            case R.id.buttonNum5:
                mController.pushButtonNumber(5);
                break;
            case R.id.buttonNum6:
                mController.pushButtonNumber(6);
                break;
            case R.id.buttonNum7:
                mController.pushButtonNumber(7);
                break;
            case R.id.buttonNum8:
                mController.pushButtonNumber(8);
                break;
            case R.id.buttonNum9:
                mController.pushButtonNumber(9);
                break;
            case R.id.buttonClear:
                mController.pushButtonClear();
                break;
            case R.id.buttonAllClear:
                mController.pushButtonAllClear();
                break;
            case R.id.buttonPlus:
                mController.pushButtonPlus();
                break;
            case R.id.buttonMinus:
                // TODO: Phase 2.
                break;
            case R.id.buttonAsterisk:
                // TODO: Phase 2.
                break;
            case R.id.buttonSlash:
                // TODO: Phase 3.
                break;
            case R.id.buttonEqual:
                mController.pushButtonEqual();
                break;
            case R.id.buttonMemRead:
                // TODO: Phase 3.
                break;
            case R.id.buttonMemClear:
                // TODO: Phase 3.
                break;
            case R.id.buttonMemPlus:
                // TODO: Phase 3.
                break;
            case R.id.buttonMemMinus:
                // TODO: Phase 3.
                break;
            default:
                break;
        }
    }

    @Override
    public void onDrawDisplayListener(long value) {
        mTextView.setText(String.valueOf(value));
    }
}
