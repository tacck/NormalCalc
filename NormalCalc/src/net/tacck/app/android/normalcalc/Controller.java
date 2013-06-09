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

public class Controller {

    private static final int STATE_CALC = 0;
    private static final int STATE_EXECUTED = 1;

    private static final int OPERATOR_NONE = 0;
    private static final int OPERATOR_ADDITION = 1;
    private static final int OPERATOR_SUBTRACTION = 2;
    private static final int OPERATOR_MULTIPLICATION = 3;
    private static final int OPERATOR_DIVISION = 4;

    private long mValue = 0;
    private long mInputValue = 0;
    private int mState = STATE_EXECUTED;
    private int mOperator = OPERATOR_NONE;

    private OnDisplayListener mOnDisplayListener = null;

    public Controller() {
        init();
    }

    private void init() {
        mValue = 0;
        mInputValue = 0;
        mState = STATE_EXECUTED;
        mOperator = OPERATOR_NONE;
    }

    public void pushButtonClear() {
        mInputValue = 0;

        // Draw value.
        drawValue(mInputValue);
    }

    public void pushButtonAllClear() {
        init();

        // Draw value.
        drawValue(mValue);
    }

    public void pushButtonNumber(int number) {
        // Check state.
        if (mState == STATE_EXECUTED) {
            // Reset values.
            init();

            // Set state.
            mState = STATE_CALC;
        }

        // Set input value.
        mInputValue = mInputValue * 10 + number;

        // Draw value.
        drawValue(mInputValue);
    }

    public void pushButtonPlus() {
        // Check state.
        if (mState == STATE_EXECUTED) {
            // Set state.
            mState = STATE_CALC;
            // Set operator before calc().
            mOperator = OPERATOR_ADDITION;
        }

        calc();

        // Set operator.
        mOperator = OPERATOR_ADDITION;
    }

    public void pushButtonEqual() {
        // Check state.
        if (mState == STATE_CALC) {
            // Set state.
            mState = STATE_EXECUTED;

            calc();
        }
    }

    private void calc() {
        switch (mOperator) {
            case OPERATOR_ADDITION:
                mValue = mValue + mInputValue;
                break;
            case OPERATOR_SUBTRACTION:
                mValue = mValue - mInputValue;
                break;
            case OPERATOR_MULTIPLICATION:
                mValue = mValue * mInputValue;
                break;
            case OPERATOR_DIVISION:
                mValue = mValue / mInputValue;
                break;
            default:
                mValue = mInputValue;
                break;
        }

        // Reset input value.
        mInputValue = 0;
        // Reset operator.
        mOperator = OPERATOR_NONE;

        // Draw value.
        drawValue(mValue);
    }

    public void setOnDisplayListener(OnDisplayListener l) {
        mOnDisplayListener = l;
    }

    private void drawValue(long value) {
        // Draw value.
        if (mOnDisplayListener != null) {
            mOnDisplayListener.onDrawDisplayListener(value);
        }
    }

    public interface OnDisplayListener {
        public void onDrawDisplayListener(long value);
    }
}
