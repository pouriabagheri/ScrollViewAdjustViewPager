package com.bagherifaez.app.scrollviewadjustviewpager;

import android.app.Activity;
import android.graphics.Rect;
import android.support.v4.widget.NestedScrollView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.FrameLayout;

/**
 * Created by poorya on 25/07/2017.
 */

public class AndroidBug5497Workaround {

    // For more information, see https://code.google.com/p/android/issues/detail?id=5497
    // To use this class, simply invoke assistActivity() on an Activity that already has its content view set.

    public static void assistActivity (Activity activity, View view, int svChildLayoutId) {
        new AndroidBug5497Workaround(activity, view,svChildLayoutId);
    }

    private View mChildOfContent;
    private int usableHeightPrevious;
    private FrameLayout.LayoutParams frameLayoutParams;
    private int usableHeight;

    View svChildLayout;
    int originalGravity;
    Activity activity;
    View view;

    private AndroidBug5497Workaround(Activity activity, View view, int svChildLayoutId) {


        this.activity = activity;
        this.view = view;
        svChildLayout = view.findViewById(svChildLayoutId);
        originalGravity = ((NestedScrollView.LayoutParams)svChildLayout.getLayoutParams()).gravity;

        FrameLayout content = (FrameLayout) activity.findViewById(android.R.id.content);
        mChildOfContent = content.getChildAt(0);
        mChildOfContent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                possiblyResizeChildOfContent();
            }
        });
        frameLayoutParams = (FrameLayout.LayoutParams) mChildOfContent.getLayoutParams();
        usableHeight = activity.getWindow().findViewById(Window.ID_ANDROID_CONTENT).getHeight();

    }

    private void possiblyResizeChildOfContent() {
        int usableHeightNow = computeUsableHeight();
        if (usableHeightNow != usableHeightPrevious) {
            int usableHeightSansKeyboard = mChildOfContent.getRootView().getHeight();
            int heightDifference = usableHeightSansKeyboard - usableHeightNow;
            if (heightDifference > (usableHeightSansKeyboard/4)) {
                // keyboard probably just became visible
                onKeyboardVisible();
                frameLayoutParams.height = usableHeightSansKeyboard- heightDifference;
            } else {
                // keyboard probably just became hidden
                onKeyboardHidden();
                frameLayoutParams.height = usableHeightSansKeyboard;
            }
            mChildOfContent.requestLayout();
            usableHeightPrevious = usableHeightNow;
        }
    }

    private int computeUsableHeight() {


        Rect r = new Rect();
        mChildOfContent.getWindowVisibleDisplayFrame(r);
        return (r.bottom - r.top);
    }

    private void onKeyboardVisible() {

        NestedScrollView.LayoutParams params = (NestedScrollView.LayoutParams) svChildLayout.getLayoutParams();
        params.gravity = Gravity.TOP;
        svChildLayout.requestLayout();

        final NestedScrollView parentSv = (NestedScrollView) svChildLayout.getParent();
        parentSv.post(new Runnable() {
            @Override
            public void run() {
                View focusedEditText = activity.getWindow().getCurrentFocus();
                parentSv.smoothScrollTo(0, focusedEditText.getTop() );
            }
        });
    }

    private void onKeyboardHidden() {
        NestedScrollView.LayoutParams params = (NestedScrollView.LayoutParams) svChildLayout.getLayoutParams();
        params.gravity = originalGravity;
        svChildLayout.requestLayout();
    }
}