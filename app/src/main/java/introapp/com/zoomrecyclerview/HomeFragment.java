package introapp.com.zoomrecyclerview;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GestureDetectorCompat;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;



import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import introapp.com.zoomrecyclerview.dataa.PinchRecyclerView;


/**
 * Created by choudhary on 2/15/2018.
 */

public class HomeFragment extends Fragment implements AsyncTaskDual<String, String> {


    View view;
    TextView txt_amount,add_moment;
    public static CardView card_view;
    public static PinchRecyclerView drawerRecyclerView1;
    public static HomeViewAdapter homeViewAdapter;
    public static ArrayList<HomeModel> allDates;
    public  static LinearLayout moment_llout,timeline_llout;
    public static LinearLayout mnllout;
    long LAST_CLICK_TIME = 0;
    int viewType=1;
    int daysInMonth=0;
    public static TextView txt_back;
    LinearLayout llout;
    AppSettings appSettings;
    String latitudes,longitudes;
    LinearLayoutManager horizontalLayoutManager;
    public static ScaleGestureDetector mScaleGestureDetector;
    private float mScaleFactor = 1.0f,mScaleFactor1=1.0f;
    int scaletype=0;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        allDates = new ArrayList<HomeModel>();

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.home_fragment, container, false);
        add_moment=(TextView)view.findViewById(R.id.add_moment);
        drawerRecyclerView1=(PinchRecyclerView) view.findViewById(R.id.drawerRecyclerView1);
        card_view=(CardView)view.findViewById(R.id.card_view);
        mnllout=(LinearLayout)view.findViewById(R.id.mnllout);
        llout=(LinearLayout)view.findViewById(R.id.llout1);
        moment_llout=(LinearLayout)view.findViewById(R.id.moment_llout);
        txt_back=(TextView)view.findViewById(R.id.txt_back);
        timeline_llout=(LinearLayout)view.findViewById(R.id.timeline_llout);





        txt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                        int aaa=HomeViewAdapter.viewType;

                if(HomeViewAdapter.viewType==2){
                    HomeViewAdapter.viewType--;
                    appSettings=new AppSettings(getActivity());
                    txt_back.setVisibility(View.INVISIBLE);
                    String aa=appSettings.getString(AppSettings.ALL_YEAR);
                    HomeViewAdapter.horizontalList.clear();
                    try{
                        JSONArray jsonmomentArray=new JSONArray(aa);

                        for (int i = 0; i < jsonmomentArray.length(); i++) {

                            JSONObject jsonBeanObj = jsonmomentArray.getJSONObject(i);

                            if (null != jsonBeanObj) {
                                HomeViewAdapter.horizontalList.add(new HomeModel(i, jsonBeanObj.optString("name"), jsonBeanObj.optString("startTime"), jsonBeanObj.optString("endTime"), jsonBeanObj.optString("moments"), jsonBeanObj.optString("timelines"), jsonBeanObj.optString("weekTimeValues"), jsonBeanObj.optString("dayValues")));
                            }
                        }
                    }catch(Exception e){
                            e.printStackTrace();
                    }




                    homeViewAdapter.notifyDataSetChanged();
                }
                else if(HomeViewAdapter.viewType>=3 ){
                    HomeViewAdapter.viewType--;
                    HomeViewAdapter.viewType=2;
                   // txt_back.setText(R.string.back_month_view);
                    appSettings=new AppSettings(getActivity());
                    String aa=appSettings.getString(AppSettings.ALL_MONTH);
                    HomeViewAdapter.horizontalList.clear();
                    try{
                        JSONArray jsonmomentArray=new JSONArray(aa);

                        for (int i = 0; i < jsonmomentArray.length(); i++) {

                            JSONObject jsonBeanObj = jsonmomentArray.getJSONObject(i);

                            if (null != jsonBeanObj) {
                                HomeViewAdapter.horizontalList.add(new HomeModel(i, jsonBeanObj.optString("name"),jsonBeanObj.optString("startTime"),jsonBeanObj.optString("endTime"),jsonBeanObj.optString("moments"),jsonBeanObj.optString("timelines"), jsonBeanObj.optString("weekTimeValues"),""));
                            }
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                    }




                    homeViewAdapter.notifyDataSetChanged();
                }

            }
        });



        llout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // Toast.makeText(getActivity(), "Double click", Toast.LENGTH_SHORT).show();
                final int mDoubleClickInterval = 400;
                long currentClickTime = System.currentTimeMillis();
                if (currentClickTime - LAST_CLICK_TIME <= mDoubleClickInterval) {
                    if (viewType <= 0) {
                        viewType=1;

                    } else {
                        if (viewType >= 4) {
                            viewType = 4;

                        }
                        viewType--;
                        if (viewType == 3) {


                            HomeViewAdapter.horizontalList.clear();
                            for (int i = 0; i <= daysInMonth; i++) {

                                HomeViewAdapter.horizontalList.add(new HomeModel(i, String.valueOf(i),String.valueOf(i),String.valueOf(i),String.valueOf(i),String.valueOf(i),String.valueOf(i),String.valueOf(i)));


                            }
                            homeViewAdapter.notifyDataSetChanged();
                        }
                    }

                    //Toast.makeText(getActivity(), "Double click detected", Toast.LENGTH_SHORT).show();
                } else {
                    LAST_CLICK_TIME = System.currentTimeMillis();
                    //Toast.makeText(getActivity(), "Double click detected", Toast.LENGTH_SHORT).show();
                }

            }
        });


         horizontalLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        drawerRecyclerView1.setLayoutManager(horizontalLayoutManager);
        drawerRecyclerView1.getLayoutManager().scrollToPosition(12);

        drawerRecyclerView1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //mScaleGestureDetector.onTouchEvent(event);
                return false;
            }
        });


//        final ZoomLayout zoomlayout=(ZoomLayout)view.findViewById(R.id.zoomLayout);
//        zoomlayout.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//               // zoomlayout.onTouchEvent(event);
//               // zoomlayout.init(getActivity());
//                mScaleGestureDetector.onTouchEvent(event);
//                return false;
//            }
//        });


        mScaleGestureDetector = new ScaleGestureDetector(getActivity(), new ScaleGestureDetector.SimpleOnScaleGestureListener() {


            @Override
            public boolean onScale(ScaleGestureDetector detector) {

                mScaleFactor *= detector.getScaleFactor();

                // Don't let the object get too small or too large.
                mScaleFactor = Math.max(1.0f, Math.min(mScaleFactor, 4.0f));


                drawerRecyclerView1.setLayoutManager(horizontalLayoutManager);
                homeViewAdapter.notifyDataSetChanged();
                drawerRecyclerView1.setScaleX(mScaleFactor);
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
                    homeViewAdapter.notifyDataSetChanged();

                    drawerRecyclerView1.setScaleX(mScaleFactor);
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
                    homeViewAdapter.notifyDataSetChanged();
                                        scaletype=2;
                     mScaleFactor1=2.0f;
                    mScaleFactor=2.0f;
                    drawerRecyclerView1.setScaleX(mScaleFactor);
                    homeViewAdapter.notifyDataSetChanged();
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
                    homeViewAdapter.notifyDataSetChanged();

                                       scaletype=1;
                    mScaleFactor1=2.0f;
                    mScaleFactor=2.0f;

                    drawerRecyclerView1.setScaleX(mScaleFactor);
                    homeViewAdapter.notifyDataSetChanged();
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
                    homeViewAdapter.notifyDataSetChanged();

                    scaletype=0;
                    mScaleFactor1=1;
                    mScaleFactor=1f;

                    drawerRecyclerView1.setScaleX(mScaleFactor);
                    homeViewAdapter.notifyDataSetChanged();
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


        if (getUserVisibleHint()) {
            if (Utils.checkConnectivity(getActivity())) {
                try {
                    DateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
                    String format = formatter.format(new Date());
                    Date startDate = (Date)formatter.parse(format);
                    Calendar cal = Calendar.getInstance();

                    cal.add(Calendar.MONTH, -12);
                    cal.set(Calendar.DAY_OF_MONTH, 1);
                    Date startDates = cal.getTime();
                    cal.add(Calendar.MONTH, 24);
                    cal.add(Calendar.MONTH, 1);
                    cal.set(Calendar.DAY_OF_MONTH, 1);
                    cal.add(Calendar.DATE, -1);
                    Date enddate = cal.getTime();

                    long startdateinms=startDates.getTime();
                    long enddateinms=enddate.getTime();
                    getTimelineCount(String.valueOf(startdateinms),String.valueOf(enddateinms));
                } catch (Exception e) {
                }
            }else {
               // Toast.makeText(getActivity(), getString(R.string.internet_connection), Toast.LENGTH_LONG).show();
            }

        }

        return view;
    }


    public void setMenuVisibility(final boolean visible) {
        super.setMenuVisibility(visible);



        if (visible) {
            if (isResumed()){


                    if (Utils.checkConnectivity(getActivity())) {
                        try {
                            DateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
                            String format = formatter.format(new Date());
                            Date startDate = (Date) formatter.parse(format);
                            Calendar cal = Calendar.getInstance();

                            cal.add(Calendar.MONTH, -12);
                            cal.set(Calendar.DAY_OF_MONTH, 1);
                            Date startDates = cal.getTime();
                            cal.add(Calendar.MONTH, 24);
                            cal.add(Calendar.MONTH, 1);
                            cal.set(Calendar.DAY_OF_MONTH, 1);
                            cal.add(Calendar.DATE, -1);
                            Date enddate = cal.getTime();

                            long startdateinms = startDates.getTime();
                            long enddateinms = enddate.getTime();


                                getTimelineCount(String.valueOf(startdateinms), String.valueOf(enddateinms));


                        } catch (Exception e) {
                        }
                    } else {
                       // Toast.makeText(getActivity(), getString(R.string.internet_connection), Toast.LENGTH_LONG).show();
                    }



            }}
    }

    public void update_on_server(HashMap<String, String> postDataParams, String num, String baseUrl) {
        final FetchMyDataTask fetchdata_task = new FetchMyDataTask(getActivity(), postDataParams, this, true, num, "Please wait...",
                baseUrl);


        if (!(fetchdata_task.getStatus() == AsyncTask.Status.RUNNING))
            fetchdata_task.execute(baseUrl);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (fetchdata_task.getStatus() == AsyncTask.Status.RUNNING) {
                    Log.e("NETWORK NOT FOUND", "Handler for async Task manage");
                    fetchdata_task.cancel(true);
                }
            }
        }, 30000);
    }

    public void getTimelineCount(String startDate,String endDate) {

        HashMap<String, String> postDataParams=new HashMap<String, String>();
        try {




            postDataParams.put("alias_name","aboutus");

            update_on_server(postDataParams, "one", "http://52.24.101.77:3002/api/timeline/dummy");
        } catch (Exception e) {
        }
    }

    @Override
    public void onTaskCompleteWithSuccess(String result, String type) {

        if (result == null || result.equalsIgnoreCase("")) {
            if (type.equalsIgnoreCase("two")){
                getActivity().finish();
            }
           // Toast.makeText(getActivity(), getString(R.string.server_norespond), Toast.LENGTH_SHORT).show();
            return;
        }
        if (type.equalsIgnoreCase("one")) {
            try {
                JSONObject json = new JSONObject(result);
                String status = json.optString("code");
                String message = json.optString("message");
                if(status.equalsIgnoreCase("200")){
                    Calendar cal = Calendar.getInstance();
                    allDates = new ArrayList<HomeModel>();
                    String aa = new SimpleDateFormat("MMM yy").format(cal.getTime());
                    String maxDate = aa;
                    SimpleDateFormat monthDate = new SimpleDateFormat("MMM yy");
                    JSONObject json1=json.optJSONObject("data");
                    JSONArray jsonmomentArray = json1.optJSONArray("yearData");

                    if(jsonmomentArray.length()>0) {
                        card_view.setVisibility(View.GONE);
                        drawerRecyclerView1.setVisibility(View.VISIBLE);

                        cal.setTime(monthDate.parse(maxDate));
                        cal.add(Calendar.MONTH, -12);




                        for (int i = 0; i < jsonmomentArray.length(); i++) {

                            JSONObject jsonBeanObj = jsonmomentArray.getJSONObject(i);

                            if (null != jsonBeanObj) {
                                allDates.add(new HomeModel(i, jsonBeanObj.optString("name"),jsonBeanObj.optString("startTime"),jsonBeanObj.optString("endTime"),jsonBeanObj.optString("moments"),jsonBeanObj.optString("timelines"), jsonBeanObj.optString("timelines"),jsonBeanObj.optString("timelines")));
                            }



                        }
                        appSettings=new AppSettings(getActivity());
                        appSettings.saveString(AppSettings.ALL_YEAR,jsonmomentArray.toString());
                        appSettings.saveString(AppSettings.ALL_MONTHS,json1.optJSONArray("monthData").toString());
                        appSettings.saveString(AppSettings.ALL_DAYS,json1.optJSONArray("dayData").toString());
                        if(allDates.size()>0) {
                            card_view.setVisibility(View.GONE);
                            drawerRecyclerView1.setVisibility(View.VISIBLE);
                            moment_llout.setVisibility(View.VISIBLE);
                            timeline_llout.setVisibility(View.VISIBLE);
                            homeViewAdapter=new HomeViewAdapter(allDates, getActivity());
                            drawerRecyclerView1.setAdapter(homeViewAdapter);
                        }else{
                            card_view.setVisibility(View.VISIBLE);
                            drawerRecyclerView1.setVisibility(View.GONE);
                            moment_llout.setVisibility(View.GONE);
                            timeline_llout.setVisibility(View.GONE);
                        }


                    }else{
                        card_view.setVisibility(View.VISIBLE);
                        drawerRecyclerView1.setVisibility(View.GONE);
                    }



                }else  if(status.equalsIgnoreCase("400")){
                    Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
                }


            } catch (Exception e) {
                e.printStackTrace();
            }


        }


    }

    @Override
    public void onTaskCompleteWithError(String result, String type) {

    }






}
