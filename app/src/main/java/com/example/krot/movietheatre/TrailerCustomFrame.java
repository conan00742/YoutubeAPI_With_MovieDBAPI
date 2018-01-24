package com.example.krot.movietheatre;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by Krot on 1/18/18.
 */

public class TrailerCustomFrame extends FrameLayout {

    private int width = 16;
    private int height = 9;

    public TrailerCustomFrame(@NonNull Context context) {
        super(context);
    }

    public TrailerCustomFrame(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TrailerCustomFrame(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TrailerCustomFrame(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int customWidth = MeasureSpec.getSize(widthMeasureSpec);
        int customHeight = Math.round((height * customWidth) / width);
        setMeasuredDimension(MeasureSpec.makeMeasureSpec(customWidth, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(customHeight, MeasureSpec.EXACTLY));
    }
}
