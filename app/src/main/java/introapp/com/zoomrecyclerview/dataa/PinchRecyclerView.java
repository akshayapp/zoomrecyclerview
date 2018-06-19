package introapp.com.zoomrecyclerview.dataa;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONObject;

import introapp.com.zoomrecyclerview.AppSettings;
import introapp.com.zoomrecyclerview.HomeFragment;
import introapp.com.zoomrecyclerview.HomeModel;
import introapp.com.zoomrecyclerview.HomeViewAdapter;

/**
 @author yogesh (https://stackoverflow.com/a/37895783/6591585)
  * */
public class PinchRecyclerView extends RecyclerView {
    private static final int INVALID_POINTER_ID = -1;
    private int mActivePointerId = INVALID_POINTER_ID;
    private ScaleGestureDetector mScaleDetector;
    private float mScaleFactor = 1.f;
    private float maxWidth = 0.0f;
    private float maxHeight = 0.0f;
    private float mLastTouchX;
    private float mLastTouchY;
    private float mPosX;
    private float mPosY;
    private float width;
    private float height;
    float mScaleFactor1=1.0f;
    int scaletype=0;
    AppSettings appSettings;


    public PinchRecyclerView(Context context) {
        super(context);
        if (!isInEditMode())
            mScaleDetector = new ScaleGestureDetector(getContext(), new ScaleListener());
    }

    public PinchRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode())
            mScaleDetector = new ScaleGestureDetector(getContext(), new ScaleListener());
    }

    public PinchRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!isInEditMode())
            mScaleDetector = new ScaleGestureDetector(getContext(), new ScaleListener());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        width = View.MeasureSpec.getSize(widthMeasureSpec);
        height = View.MeasureSpec.getSize(heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return super.onInterceptTouchEvent(ev);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent ev) {
        super.onTouchEvent(ev);
        final int action = ev.getAction();
        mScaleDetector.onTouchEvent(ev);
        switch (action & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN: {
                final float x = ev.getX();
                final float y = ev.getY();
                mLastTouchX = x;
                mLastTouchY = y;
                mActivePointerId = ev.getPointerId(0);
                break;
            }

            case MotionEvent.ACTION_MOVE: {
                final int pointerIndex = (action & MotionEvent.ACTION_POINTER_INDEX_MASK)
                        >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
                final float x = ev.getX(pointerIndex);
                final float y = ev.getY(pointerIndex);
                final float dx = x - mLastTouchX;
                final float dy = y - mLastTouchY;

                mPosX += dx;
                mPosY = dy;

                if (mPosX > 0.0f)
                    mPosX = 0.0f;
                else if (mPosX < maxWidth)
                    mPosX = maxWidth;

                if (mPosY > 0.0f)
                    mPosY = 0.0f;
                else if (mPosY < maxHeight)
                    mPosY = maxHeight;

                mLastTouchX = x;
                mLastTouchY = y;

                invalidate();
                break;
            }

            case MotionEvent.ACTION_UP: {
                mActivePointerId = INVALID_POINTER_ID;
                break;
            }

            case MotionEvent.ACTION_CANCEL: {
                mActivePointerId = INVALID_POINTER_ID;
                break;
            }

            case MotionEvent.ACTION_POINTER_UP: {
                final int pointerIndex = (action & MotionEvent.ACTION_POINTER_INDEX_MASK) >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
                final int pointerId = ev.getPointerId(pointerIndex);
                if (pointerId == mActivePointerId) {
                    final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
                    mLastTouchX = ev.getX(newPointerIndex);
                    mLastTouchY = ev.getY(newPointerIndex);
                    mActivePointerId = ev.getPointerId(newPointerIndex);
                }
                break;
            }
        }

        return true;
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save(Canvas.MATRIX_SAVE_FLAG);
        canvas.translate(mPosX, mPosY);
        canvas.scale(mScaleFactor, mScaleFactor);
        canvas.restore();
    }

    @Override
    protected void dispatchDraw(@NonNull Canvas canvas) {
        canvas.save(Canvas.MATRIX_SAVE_FLAG);
        if (mScaleFactor == 3.0f) {
            mPosX = 0.0f;
            mPosY = 0.0f;
        }
        canvas.translate(mPosX, mPosY);
        canvas.scale(mScaleFactor, 1.0f);
        super.dispatchDraw(canvas);
        canvas.restore();
        invalidate();
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            mScaleFactor *= detector.getScaleFactor();
            mScaleFactor = Math.max(1.0f, Math.min(mScaleFactor, 2.5f));
            maxWidth = width - (width * mScaleFactor);
            maxHeight = 1.0f;
            appSettings=new AppSettings(getContext());

            if(mScaleFactor>1.5 && scaletype==0){
                HomeViewAdapter.horizontalList.clear();
                scaletype=1;
                mScaleFactor1=1.5f;
                mScaleFactor=1.5f;
                appSettings=new AppSettings(getContext());
                String months=appSettings.getString(AppSettings.ALL_MONTHS);

                try {
                    JSONArray jsonmomentArray = new JSONArray(months);
                    HomeViewAdapter.horizontalList.clear();
                    for (int i = 0; i < jsonmomentArray.length(); i++) {
                        JSONObject jsonBeanObj = jsonmomentArray.getJSONObject(i);
                        if (null != jsonBeanObj) {
                            HomeViewAdapter.horizontalList.add(new HomeModel(i, jsonBeanObj.optString("name"), jsonBeanObj.optString("startTime"), jsonBeanObj.optString("endTime"), jsonBeanObj.optString("moments"), jsonBeanObj.optString("timelines"), jsonBeanObj.optString("year"), jsonBeanObj.optString("timelines")));
                        }
                    }
                }catch (Exception e){

                }
                HomeFragment.homeViewAdapter.notifyDataSetChanged();
                invalidate();

//                HomeFragment.drawerRecyclerView1.setScaleX(mScaleFactor);
//                    drawerRecyclerView1.setScaleX(1.0f);
//                    drawerRecyclerView1.setScaleY(1.0f);
//                    homeViewAdapter.notifyDataSetChanged();


            }
            if(mScaleFactor>2.4 && scaletype==1){
                HomeViewAdapter.horizontalList.clear();

                String months=appSettings.getString(AppSettings.ALL_DAYS);

                try {
                    JSONArray jsonmomentArray = new JSONArray(months);

                    HomeViewAdapter.horizontalList.clear();

                    for (int i = 0; i < jsonmomentArray.length(); i++) {

                        JSONObject jsonBeanObj = jsonmomentArray.getJSONObject(i);

                        if (null != jsonBeanObj) {
                            HomeViewAdapter.horizontalList.add(new HomeModel(i, jsonBeanObj.optString("name"), jsonBeanObj.optString("startTime"), jsonBeanObj.optString("endTime"), jsonBeanObj.optString("moments"), jsonBeanObj.optString("timelines"), jsonBeanObj.optString("year"), jsonBeanObj.optString("month")));
                        }


                    }
                }catch (Exception e){

                }
                HomeFragment.homeViewAdapter.notifyDataSetChanged();
                scaletype=2;
                mScaleFactor1=2.0f;
                mScaleFactor=2.0f;
               // drawerRecyclerView1.setScaleX(mScaleFactor);
                HomeFragment.homeViewAdapter.notifyDataSetChanged();
                invalidate();
            }


            if(mScaleFactor<1.2 && scaletype==2){
                HomeViewAdapter.horizontalList.clear();

                String months=appSettings.getString(AppSettings.ALL_MONTHS);

                try {
                    JSONArray jsonmomentArray = new JSONArray(months);

                    HomeViewAdapter.horizontalList.clear();

                    for (int i = 0; i < jsonmomentArray.length(); i++) {

                        JSONObject jsonBeanObj = jsonmomentArray.getJSONObject(i);

                        if (null != jsonBeanObj) {
                            HomeViewAdapter.horizontalList.add(new HomeModel(i, jsonBeanObj.optString("name"), jsonBeanObj.optString("startTime"), jsonBeanObj.optString("endTime"), jsonBeanObj.optString("moments"), jsonBeanObj.optString("timelines"), jsonBeanObj.optString("year"), jsonBeanObj.optString("timelines")));
                        }


                    }
                }catch (Exception e){

                }


                scaletype=1;
                mScaleFactor1=2.0f;
                mScaleFactor=2.0f;

                //drawerRecyclerView1.setScaleX(mScaleFactor);
                HomeFragment.homeViewAdapter.notifyDataSetChanged();
                invalidate();
            }
            if(mScaleFactor<1.2 && scaletype==1){
                HomeViewAdapter.horizontalList.clear();

                String months=appSettings.getString(AppSettings.ALL_YEAR);

                try {
                    JSONArray jsonmomentArray = new JSONArray(months);

                    HomeViewAdapter.horizontalList.clear();

                    for (int i = 0; i < jsonmomentArray.length(); i++) {

                        JSONObject jsonBeanObj = jsonmomentArray.getJSONObject(i);

                        if (null != jsonBeanObj) {
                            HomeViewAdapter.horizontalList.add(new HomeModel(i, jsonBeanObj.optString("name"), jsonBeanObj.optString("startTime"), jsonBeanObj.optString("endTime"), jsonBeanObj.optString("moments"), jsonBeanObj.optString("timelines"), jsonBeanObj.optString("timelines"), jsonBeanObj.optString("timelines")));
                        }


                    }
                }catch (Exception e){

                }
               // homeViewAdapter.notifyDataSetChanged();

                scaletype=0;
                mScaleFactor1=1;
                mScaleFactor=1f;

               // drawerRecyclerView1.setScaleX(mScaleFactor);
                HomeFragment.homeViewAdapter.notifyDataSetChanged();
                invalidate();
            }



            invalidate();
            return true;
        }
    }
}