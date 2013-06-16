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

/**
 * Normal Calc Controller class.
 */
public class Controller {

    /** Max digits. */
    private static final int DISPLAY_DIGIT_MAX = 10;

    /** State: Calc mode. */
    private static final int STATE_CALC = 0;
    /** State: Executed mode. */
    private static final int STATE_EXECUTED = 1;

    /** Operator: None. */
    private static final int OPERATOR_NONE = 0;
    /** Operator: Addition. */
    private static final int OPERATOR_ADDITION = 1;
    /** Operator: Subtraction. */
    private static final int OPERATOR_SUBTRACTION = 2;
    /** Operator: Multiplication. */
    private static final int OPERATOR_MULTIPLICATION = 3;
    /** Operator: Division. */
    private static final int OPERATOR_DIVISION = 4;

    /** Value. */
    private long mValue = 0;
    /** Input Value. */
    private long mInputValue = 0;
    /** State. */
    private int mState = STATE_EXECUTED;
    /** Operator. */
    private int mOperator = OPERATOR_NONE;

    /** OnDisplayListener. */
    private OnDisplayListener mOnDisplayListener = null;

    /**
     * Constracter.
     */
    public Controller() {
        init();
    }

    /**
     * Initialization.
     */
    private void init() {
        mValue = 0;
        mInputValue = 0;
        mState = STATE_EXECUTED;
        mOperator = OPERATOR_NONE;
    }

    /**
     * Push Button: C.
     */
    public void pushButtonClear() {
        mInputValue = 0;

        // Draw value.
        drawValue(mInputValue);
    }

    /**
     * Push Button: AC.
     */
    public void pushButtonAllClear() {
        init();

        // Draw value.
        drawValue(mValue);
    }

    /**
     * Push Button: Number.
     */
    public void pushButtonNumber(int number) {
        // Check state.
        if (mState == STATE_EXECUTED) {
            // Reset values.
            init();

            // Set state.
            mState = STATE_CALC;
        }

        // Set input value.
        long inputValue = mInputValue * 10 + number;
        if (!isDigitOver(inputValue)) {
            mInputValue = inputValue;
        }

        // Draw value.
        drawValue(mInputValue);
    }

    /**
     * Push Button: +.
     */
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

    /**
     * Push Button: -.
     */
    public void pushButtonMinus() {
        // Check state.
        if (mState == STATE_EXECUTED) {
            // Set state.
            mState = STATE_CALC;
            // Set operator before calc().
            mOperator = OPERATOR_SUBTRACTION;
        }

        calc();

        // Set operator.
        mOperator = OPERATOR_SUBTRACTION;
    }

    /**
     * Push Button: *.
     */
    public void pushButtonAsterisk() {
        // Check state.
        if (mState == STATE_EXECUTED) {
            // Set state.
            mState = STATE_CALC;
            // Set operator before calc().
            mOperator = OPERATOR_MULTIPLICATION;
        }

        calc();

        // Set operator.
        mOperator = OPERATOR_MULTIPLICATION;
    }

    /**
     * Push Button: =.
     */
    public void pushButtonEqual() {
        // Check state.
        if (mState == STATE_CALC) {
            // Set state.
            mState = STATE_EXECUTED;

            calc();
        }
    }

    /**
     * Calculation.
     */
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

        // Check Digits.
        if (isDigitOver(mValue)) {
            drawErrorMessage();
        } else {
            // Draw value.
            drawValue(mValue);
        }
    }

    /**
     * Set OnDisplayListener.
     * 
     * @param l OnDisplayListener
     */
    public void setOnDisplayListener(OnDisplayListener l) {
        mOnDisplayListener = l;
    }

    /**
     * Draw Value.
     * 
     * @param value Value that want to draw
     */
    private void drawValue(long value) {
        // Draw value.
        if (mOnDisplayListener != null) {
            mOnDisplayListener.onDrawDisplayListener(value);
        }
    }

    /**
     * Draw Error Message.
     */
    private void drawErrorMessage() {
        // Draw Error Message.
        if (mOnDisplayListener != null) {
            mOnDisplayListener.onDrawErrorDisplayListener("Error");
        }
    }

    /**
     * Check the value's digit is over the limit.
     * 
     * @param value Value
     * @return true: over false: not over
     */
    private boolean isDigitOver(long value) {
        boolean ret = false;

        int digit = 0;
        if (value >= 0) {
            digit = String.valueOf(value).length();
        } else {
            digit = String.valueOf(value).length() - 1;
        }
        if (digit > DISPLAY_DIGIT_MAX) {
            ret = true;
        }

        return ret;
    }

    /**
     * OnDisplayListener
     */
    public interface OnDisplayListener {
        /**
         * Draw Value.
         * 
         * @param value Value that want to draw
         */
        public void onDrawDisplayListener(long value);

        /**
         * Draw Error Message.
         * 
         * @param message Error Message
         */
        public void onDrawErrorDisplayListener(String message);
    }
}
