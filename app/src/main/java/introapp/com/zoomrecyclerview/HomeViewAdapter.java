package introapp.com.zoomrecyclerview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;



import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;



/**
 * Created by choudhary on 3/8/2018.
 */

public class HomeViewAdapter  extends RecyclerView.Adapter<HomeViewAdapter.MyViewHolder> implements AsyncTaskDual<String, String> {

    int row_index=-1;
    public static List<HomeModel> horizontalList = Collections.emptyList();
    Context context;
    private List<HomeModel> data;
    private int selectedPos = 0;
    String list="all";
    long LAST_CLICK_TIME = 0;
    public static int viewType=1;
    int daysInMonth=0;
    HomeModel dataclick;
    AppSettings appSettings;
    HomeModel dataclick1;
    private ScaleGestureDetector mScaleGestureDetector;
    private float mScaleFactor = 1.0f,mScaleFactor1=1.0f;
    int scaletype=0;
    // 1 for year, 2 for month, 3 for week, 4 for days, 5 for hours


    public HomeViewAdapter(List<HomeModel> horizontalList, Context context) {
        this.horizontalList = horizontalList;
        this.context = context;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView txtview,momentcount,subtimelinecount;
        View moment_view,subtimeline_view,moment_view1,subtimeline_view1;

        public MyViewHolder(View view) {
            super(view);

            txtview=(TextView) view.findViewById(R.id.txtview);
            momentcount=(TextView)view.findViewById(R.id.momentcount);
            moment_view=(View)view.findViewById(R.id.moment_view);
            subtimeline_view=(View)view.findViewById(R.id.subtimeline_view);
            moment_view1=(View)view.findViewById(R.id.moment_view1);
            subtimeline_view1=(View)view.findViewById(R.id.subtimeline_view1);
            subtimelinecount=(TextView)view.findViewById(R.id.subtimelinecount);

            subtimelinecount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    dataclick= horizontalList.get(getAdapterPosition());



                }
            });
            momentcount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dataclick = horizontalList.get(getAdapterPosition());

                }
            });


        }
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder,  int position) {
        final HomeModel model = horizontalList.get(holder.getAdapterPosition());
        //  holder.imageView.setImageResource(horizontalList.get(position).category_id);

        holder.txtview.setText(model.getname());
        holder.txtview.setTextSize(12);
        holder.subtimelinecount.setTextSize(10);
        holder.subtimelinecount.setBackgroundResource(R.mipmap.subtimeline_icon);

        holder.itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Toast.makeText(context, model.getname(), Toast.LENGTH_SHORT).show();
                HomeFragment.txt_back.setVisibility(View.VISIBLE);
                HomeFragment.txt_back.setText(model.getname());
                Log.d("fpp", view.getClass().getName() + " is clicked");
                return true;
            }
        });


        holder.momentcount.setText(model.getmoments());

        holder.subtimelinecount.setText(model.gettimelines());


        holder.itemView.setSelected(selectedPos == position);


//        holder.itemView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//               //mScaleGestureDetector.onTouchEvent(event);
//                return false;
//            }
//        });
//
//        holder.txtview.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                //mScaleGestureDetector.onTouchEvent(event);
//                return false;
//            }
//        });




        mScaleGestureDetector = new ScaleGestureDetector(context, new ScaleGestureDetector.SimpleOnScaleGestureListener() {


            @Override
            public boolean onScale(ScaleGestureDetector detector) {

                mScaleFactor *= detector.getScaleFactor();

                // Don't let the object get too small or too large.
                mScaleFactor = Math.max(1.0f, Math.min(mScaleFactor, 4.0f));


//                drawerRecyclerView1.setLayoutManager(horizontalLayoutManager);
              notifyDataSetChanged();
                holder.itemView.setScaleX(mScaleFactor);
                if(mScaleFactor>2.0 && scaletype==0){
                    HomeViewAdapter.horizontalList.clear();
                    scaletype=1;
                    mScaleFactor1=2.0f;
                    mScaleFactor=2.0f;
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
                    notifyDataSetChanged();

                    holder.itemView.setScaleX(mScaleFactor);
//                    drawerRecyclerView1.setScaleX(1.0f);
//                    drawerRecyclerView1.setScaleY(1.0f);
//                    homeViewAdapter.notifyDataSetChanged();


                }
                if(mScaleFactor>3.0 && scaletype==1){
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
                    notifyDataSetChanged();
                    scaletype=2;
                    mScaleFactor1=2.0f;
                    mScaleFactor=2.0f;
                    holder.itemView.setScaleX(mScaleFactor);
                    notifyDataSetChanged();
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
                    notifyDataSetChanged();

                    scaletype=1;
                    mScaleFactor1=2.0f;
                    mScaleFactor=2.0f;

                    holder.itemView.setScaleX(mScaleFactor);
                    notifyDataSetChanged();
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
                    notifyDataSetChanged();

                    scaletype=0;
                    mScaleFactor1=1;
                    mScaleFactor=1f;

                    holder.itemView.setScaleX(mScaleFactor);
                    notifyDataSetChanged();
                }
                //mnllout.setScaleX(mScaleFactor);
                return true;

//                if (detector.getCurrentSpan() > 200 && detector.getTimeDelta() > 200) {
//                    if (detector.getCurrentSpan() - detector.getPreviousSpan() < -1) {
////                        if (mCurrentLayoutManager == mGridLayoutManager1) {
////                            mCurrentLayoutManager = mGridLayoutManager2;
////                            mRvPhotos.setLayoutManager(mGridLayoutManager2);
//                        return true;
////                        } else if (mCurrentLayoutManager == mGridLayoutManager2) {
////                            mCurrentLayoutManager = mGridLayoutManager3;
////                            mRvPhotos.setLayoutManager(mGridLayoutManager3);
////                            return true;
//                        //}
//                    } else if (detector.getCurrentSpan() - detector.getPreviousSpan() > 1) {
////                        if (mCurrentLayoutManager == mGridLayoutManager3) {
////                            mCurrentLayoutManager = mGridLayoutManager2;
////                            mRvPhotos.setLayoutManager(mGridLayoutManager2);
//                        return true;
////                        } else if (mCurrentLayoutManager == mGridLayoutManager2) {
////                            mCurrentLayoutManager = mGridLayoutManager1;
////                            mRvPhotos.setLayoutManager(mGridLayoutManager1);
////                            return true;
////                }
//                    }
//                }


            }
        });



    }







    @Override
    public void onTaskCompleteWithSuccess(String result, String type) {

        if (result == null || result.equalsIgnoreCase("")) {
            if (type.equalsIgnoreCase("two")){

            }
           // Toast.makeText(context, context.getString(R.string.server_norespond), Toast.LENGTH_SHORT).show();
            return;
        }



    }

    @Override
    public void onTaskCompleteWithError(String result, String type) {

    }


    @Override
    public int getItemCount()
    {
        return horizontalList.size();
    }
}
