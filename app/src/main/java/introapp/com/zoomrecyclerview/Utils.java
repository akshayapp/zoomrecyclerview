package introapp.com.zoomrecyclerview;


import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.MediaScannerConnection;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Parcelable;
import android.provider.MediaStore;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;


public class Utils {
    static Context context_dialog;
    public  static PopupWindow popupWindow;

    public static int getScreenWidth(Context context) {
        WindowManager window = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Point point = new Point();
        window.getDefaultDisplay().getSize(point);
        return point.x;
    }
    public static String getColoredSpanned(String text, String color) {
        String input = "<font color=" + color + ">" + text + "</font>";
        return input;
    }
    public static void showToast(Context context, String msg) {
        Toast toast= Toast.makeText(context, msg, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }



//    public  static void TagsAlert(View views, final Activity act, final String msg , final String emailid, final AsyncTaskDual<String, String> act1)
//    {
//        LayoutInflater layoutInflater
//                = (LayoutInflater) act
//                .getSystemService(LAYOUT_INFLATER_SERVICE);
//        View popupView = layoutInflater.inflate(R.layout.alert_addtag, null);
//        popupWindow = new PopupWindow(popupView, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
//        popupWindow.setFocusable(true);
//        popupWindow.setTouchable(true);
//        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
//        popupWindow.setOutsideTouchable(false);
//        //popupWindow.getWindow().setBackgroundDrawable(new ColorDrawableResource(R.color.transparent));
//
//        final ImageView cancel=(ImageView) popupView.findViewById(R.id.cancel);
//        final AutoCompleteTextView tag_edt=(AutoCompleteTextView)popupView.findViewById(R.id.tag_edt) ;
//        final Button add_btn=(Button)popupView.findViewById(R.id.add_btn);
//
//        Rect location = new Rect();
//
//        cancel.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//
//
//                popupWindow.dismiss();
//
//
//            }
//        });
//        popupWindow.showAtLocation(views, Gravity.CENTER, location.centerY(), location.centerX());
//
//    }

    public static double haversine(double lat1, double lon1, double lat2, double lon2) {
        double Rad = 6372.8; //Earth's Radius In kilometers
        // TODO Auto-generated method stub
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.sin(dLon / 2) * Math.sin(dLon / 2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double haverdistanceKM = Rad * c;
        return  haverdistanceKM;


    }
//    public static LatLng getLocationFromAddress(Context context, String strAddress) {
//
//        Geocoder coder = new Geocoder(context);
//        List<Address> address;
//        LatLng p1 = null;
//
//        try {
//            // May throw an IOException
//            address = coder.getFromLocationName(strAddress, 5);
//            if (address == null) {
//                return null;
//            }
//            Address location = address.get(0);
//            location.getLatitude();
//            location.getLongitude();
//
//            p1 = new LatLng(location.getLatitude(), location.getLongitude() );
//
//        } catch (IOException ex) {
//
//            ex.printStackTrace();
//        }
//
//        return p1;
//    }
    public static boolean checkConnectivity(Context context) {
        boolean isConnected = false;
        try {
            ConnectivityManager cm =
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            isConnected = activeNetwork != null &&
                    activeNetwork.isConnectedOrConnecting();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return isConnected;
    }



//    public  static void AlertMsg(View views,Activity act, String msg )
//    {
//        LayoutInflater layoutInflater
//                = (LayoutInflater) act
//                .getSystemService(LAYOUT_INFLATER_SERVICE);
//        View popupView = layoutInflater.inflate(R.layout.showmessage, null);
//        final PopupWindow popupWindow = new PopupWindow(popupView, ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
//        popupWindow.setFocusable(true);
//        popupWindow.setTouchable(true);
//        popupWindow.setOutsideTouchable(false);
//        final Button Ok = (Button) popupView.findViewById(R.id.exit);
//        final TextView alertmsg = (TextView) popupView.findViewById(R.id.alertmesage);
//        alertmsg.setText(msg);
//        Rect location = new Rect();
//        Ok.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//
//                popupWindow.dismiss();
//
//            }
//        });
//        popupWindow.showAtLocation(views, Gravity.CENTER, location.centerY(), location.centerX());
//
//    }
//
//    public  static void AlertMsgact(View views, final Activity act, final String msg , final String emailid, final AsyncTaskDual<String, String> act1)
//    {
//        LayoutInflater layoutInflater
//                = (LayoutInflater) act
//                .getSystemService(LAYOUT_INFLATER_SERVICE);
//        View popupView = layoutInflater.inflate(R.layout.otp_alert, null);
//        popupWindow = new PopupWindow(popupView, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
//        popupWindow.setFocusable(true);
//        popupWindow.setTouchable(true);
//        popupWindow.setOutsideTouchable(false);
//        //popupWindow.getWindow().setBackgroundDrawable(new ColorDrawableResource(R.color.transparent));
//        final Button btn_confirm = (Button) popupView.findViewById(R.id.btn_confirm);
//        final TextView btn_resend_otp = (TextView) popupView.findViewById(R.id.txt_resendotp);
//        final EditText edt_otp = (EditText) popupView.findViewById(R.id.edt_otp);
//        final ImageView cancel=(ImageView)popupView.findViewById(R.id.cancel);
////        alertmsg.setText(msg);
//        Rect location = new Rect();
//        btn_confirm.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//
//                String otp=edt_otp.getText().toString();
//                if (otp.isEmpty()) {
//                    Toast.makeText(act,"Please enter otp.", Toast.LENGTH_LONG).show();
//
//                }else {
//
////                    try {
////                        InputMethodManager imm = (InputMethodManager)act.getSystemService(Context.INPUT_METHOD_SERVICE);
////                        imm.hideSoftInputFromWindow(act.getCurrentFocus().getWindowToken(), 0);
////                        imm.hideSoftInputFromWindow(act.getWindow().getDecorView()
////                                .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
////                    } catch (Exception e) {
////                        // TODO: handle exception
////                    }
//                    LoginActivity loginActivity = new LoginActivity();
//
//                    loginActivity.sendOTP(emailid,otp,act,msg,act1);
//
//
//
////                    popupWindow.dismiss();
////
////                    if(msg.equalsIgnoreCase("merchant")) {
////                       // Toast.makeText(act,"we contac", Toast.LENGTH_LONG).show();
////                        Intent intent12 = new Intent(act, LoginActivity.class);
////                        intent12.setFlags(
////                                Intent.FLAG_ACTIVITY_CLEAR_TOP);
////
////                        act.startActivity(intent12);
////                        act.finish();
////                    }else  if(msg.equalsIgnoreCase("user")) {
////                        Intent intent12 = new Intent(act, MainActivity.class);
////                        intent12.setFlags(
////                                Intent.FLAG_ACTIVITY_CLEAR_TOP);
////
////                        act.startActivity(intent12);
////                        act.finish();
////                    }else  if(msg.equalsIgnoreCase("login")) {
////                        Intent intent12 = new Intent(act, MainActivity.class);
////                        intent12.setFlags(
////                                Intent.FLAG_ACTIVITY_CLEAR_TOP);
////
////                        act.startActivity(intent12);
////                        act.finish();
////                    }
//
//                }
////                if(acttype.equalsIgnoreCase("mainact")){
////                    Intent intent12= new Intent(act, MyAllOrderActivity.class);
////                    intent12.setFlags(
////                            Intent.FLAG_ACTIVITY_CLEAR_TOP );
////                    act.startActivity(intent12);
////                }
//
//            }
//        });
//        btn_resend_otp.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                LoginActivity loginActivity = new LoginActivity();
//
//                loginActivity.resendOTP(emailid,act,act1);
//
//
//            }
//        });
//        cancel.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//
//            popupWindow.dismiss();
//
//
//            }
//        });
//        popupWindow.showAtLocation(views, Gravity.CENTER, location.centerY(), location.centerX());
//
//    }



//    public  static void ThankuAlert(View views, final Activity act, final String msg , final String emailid, final AsyncTaskDual<String, String> act1)
//    {
//        LayoutInflater layoutInflater
//                = (LayoutInflater) act
//                .getSystemService(LAYOUT_INFLATER_SERVICE);
//        View popupView = layoutInflater.inflate(R.layout.thanku_alert, null);
//        popupWindow = new PopupWindow(popupView, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
//        popupWindow.setFocusable(true);
//        popupWindow.setTouchable(true);
//        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
//        popupWindow.setOutsideTouchable(false);
//        //popupWindow.getWindow().setBackgroundDrawable(new ColorDrawableResource(R.color.transparent));
//
//        final TextView cancel=(TextView) popupView.findViewById(R.id.cancel);
//        Rect location = new Rect();
//
//        cancel.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                Intent intent12 = new Intent(act, LoginActivity.class);
//                        intent12.setFlags(
//                                Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
//                        act.startActivity(intent12);
//                        act.finish();
//
//                popupWindow.dismiss();
//
//
//            }
//        });
//        popupWindow.showAtLocation(views, Gravity.CENTER, location.centerY(), location.centerX());
//
//    }



//    public  static void EstimatefareAlert(View views, final Activity act, final String amount)
//    {
//        LayoutInflater layoutInflater
//                = (LayoutInflater) act
//                .getSystemService(LAYOUT_INFLATER_SERVICE);
//        View popupView = layoutInflater.inflate(R.layout.estimated_fair_alert, null);
//        popupWindow = new PopupWindow(popupView, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
//        popupWindow.setFocusable(true);
//        popupWindow.setTouchable(true);
//        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
//        popupWindow.setOutsideTouchable(false);
//        //popupWindow.getWindow().setBackgroundDrawable(new ColorDrawableResource(R.color.transparent));
//
//        final ImageView cancel=(ImageView) popupView.findViewById(R.id.cancel);
//        final TextView txt_amount=(TextView) popupView.findViewById(R.id.txt_amount);
//        final TextView btn_back=(Button) popupView.findViewById(R.id.btn_back);
//        final TextView btn_proceed=(Button) popupView.findViewById(R.id.btn_proceed);
//        txt_amount.setText(amount);
//
//        takeSentdataModel.setamount(amount);
//
//        Rect location = new Rect();
//
//        cancel.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//
//
//                popupWindow.dismiss();
//
//
//            }
//        });btn_back.setOnClickListener(new View.OnClickListener()
//    {
//        @Override
//        public void onClick(View v)
//        {
//
//            popupWindow.dismiss();
//
//
//        }
//    });btn_proceed.setOnClickListener(new View.OnClickListener()
//    {
//        @Override
//        public void onClick(View v)
//        {
//
//            Intent intent12 = new Intent(act, EstimatedFareActivity.class);
//            act.startActivity(intent12);
//            popupWindow.dismiss();
//
//
//        }
//    });
//        popupWindow.showAtLocation(views, Gravity.CENTER, location.centerY(), location.centerX());
//
//    }


    public static ProgressDialog getProgressBar(Context _context) {
        ProgressDialog mProgressDialog = new ProgressDialog(_context);
        mProgressDialog.setMessage("Loading...");

        mProgressDialog.setIndeterminate(false);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setCancelable(false);
        return mProgressDialog;
    }

    public static ProgressDialog getImageProgressBar(Context _context) {
        ProgressDialog mProgressDialog = new ProgressDialog(_context);
        mProgressDialog.setMessage("Image Uploading...");

        mProgressDialog.setIndeterminate(false);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setCancelable(false);
        return mProgressDialog;
    }




    public static boolean isEmpty(String target) {
        if (target.length()>0) {
            return true;
        } else {
            return false;
        }
    }





    public static boolean isSame(String target, String newtar) {
        if (target.equals(newtar)) {
            return true;
        } else {
            return false;
        }
    }

    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    public final static boolean isMobileValid(String mobile)
    {
        return mobile.matches("^([0]|\\+91)?\\d{9,10}");
    }


//    public static Dialog ProgressDialog(Context context) {
//        Dialog dialog = null;
//        context_dialog = context;
//        Animation rotation = AnimationUtils.loadAnimation(context, R.anim.rotator);
//        rotation.setRepeatCount(Animation.INFINITE);
//        try {
//            dialog = new Dialog(context,android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);
//            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//            dialog.setContentView(R.layout.custom_progress_dialog);
//            dialog.setCancelable(false);
//            dialog.setCanceledOnTouchOutside(false);
//            View view = dialog.findViewById(R.id.progress);
//            view.startAnimation(rotation);
//            dialog.show();
//        } catch (Exception e) {
//        }
//        return dialog;
//    }


    public static String createImageFile() {
        return "img_" + System.currentTimeMillis() + ".jpg";
    }

    public static void openImageIntent(String fname, Uri outputFileUri, Activity activity, int requestCode) {
        // Camera.
        final List<Intent> cameraIntents = new ArrayList<Intent>();
        final Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        final PackageManager packageManager = activity.getPackageManager();
        final List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);
        for (ResolveInfo res : listCam) {
            final String packageName = res.activityInfo.packageName;
            final Intent intent = new Intent(captureIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(packageName);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
            cameraIntents.add(intent);
        }
        final Intent galleryIntent = new Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // Chooser of filesystem options.
        final Intent chooserIntent = Intent.createChooser(galleryIntent, "Select Source");
        // Add the camera options.
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, cameraIntents.toArray(new Parcelable[]{}));
        activity.startActivityForResult(chooserIntent, requestCode);
    }

    public static String SaveImage(Context context, Bitmap finalBitmap, String path) {

        File myDir = new File(path);
        myDir.mkdirs();
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        String fname = "Image-" + n + ".jpg";
        File file = new File(myDir, fname);
        if (file.exists()) file.delete();
        String pathString = file.getPath();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            pathString = "";
            e.printStackTrace();
        }

        // Tell the media scanner about the new file so that it is
        // immediately available to the user.
        MediaScannerConnection.scanFile(context, new String[] { file.toString() }, null,
                new MediaScannerConnection.OnScanCompletedListener() {
                    public void onScanCompleted(String path, Uri uri) {
                    }
                });


        return pathString;
    }


    public static byte[] getBytesFromFile(File file) throws IOException {
        long length = file.length();
        if (length > Integer.MAX_VALUE) {
            throw new IOException("File is too large!");
        }
        byte[] bytes = new byte[(int)length];
        int offset = 0;
        int numRead = 0;
        InputStream is = new FileInputStream(file);
        try {
            while (offset < bytes.length && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
                offset += numRead;
            }
        } finally {
            is.close();
        }
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file "+file.getName());
        }
        return bytes;
    }



}
