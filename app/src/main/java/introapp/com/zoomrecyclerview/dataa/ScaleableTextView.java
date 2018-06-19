package introapp.com.zoomrecyclerview.dataa;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.TextView;

/**
 * Created by choudhary on 6/16/2018.
 */

public class ScaleableTextView extends TextView
        implements View.OnTouchListener, ScaleGestureDetector.OnScaleGestureListener {

    ScaleGestureDetector mScaleDetector =
            new ScaleGestureDetector(getContext(), this);

    public ScaleableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        // Code for scale here
        return true;
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {
        // Code for scale begin here
        return true;
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {
        // Code for scale end here
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (mScaleDetector.onTouchEvent(event))
            return true;
        return super.onTouchEvent(event);
    }
}
