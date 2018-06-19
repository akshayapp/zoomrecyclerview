package introapp.com.zoomrecyclerview;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.util.HashMap;

public class FetchMyDataTask extends AsyncTask<String, Void, String> {
	private ProgressDialog mProgressDialog;
	private AsyncTaskCompleteListener<String> listener;
	private boolean secondParameter = false;
	HashMap<String, String> paramss;
	private HTTPURLConnection service;

	private HTTPURLConnectionGet serviceGet;
	String json, type, message,baseUrl,requestType="";
	private boolean flag_error = false;
	AppSettings appSettings;
	private AsyncTaskDual<String, String> asyncTaskDualListener;
//
	private Dialog pDialog;
	public FetchMyDataTask(Context context, String json,
                           AsyncTaskCompleteListener<String> listener, boolean progbar) {
		if (progbar) {
			mProgressDialog = Utils.getProgressBar(context);
		}
		this.json = json;

		this.listener = listener;
		appSettings=new AppSettings(context);
	}


	public FetchMyDataTask(Context context, HashMap<String, String> paramss,
                           AsyncTaskDual<String, String> asyncTaskDualListener,
                           boolean progbar, String type, String message, String baseUrl) {

		this.paramss = paramss;
		this.type = type;
		this.message = message;
		this.baseUrl=baseUrl;
		service=new HTTPURLConnection();
		this.asyncTaskDualListener = asyncTaskDualListener;
		secondParameter = true;
		if (progbar) {
			pDialog=Utils.getProgressBar(context);
			pDialog.show();
		}
		appSettings=new AppSettings(context);
	}


	public FetchMyDataTask(Context context,
                           AsyncTaskDual<String, String> asyncTaskDualListener,
                           boolean progbar, String type, String message, String baseUrl, String requestType) {


		this.type = type;
		this.message = message;
		this.baseUrl=baseUrl;
		this.requestType = requestType;
		serviceGet=new HTTPURLConnectionGet();
		this.asyncTaskDualListener = asyncTaskDualListener;
		secondParameter = true;
		if (progbar) {
			pDialog=Utils.getProgressBar(context);
			pDialog.show();
		}
		appSettings=new AppSettings(context);
	}
	@Override
	protected String doInBackground(String... params) {

		String result;
		//Call ServerData() method to call webservice and store result in response
		if(requestType.equalsIgnoreCase("about"))
			result= serviceGet.sendGet(baseUrl,message,appSettings.getString(AppSettings.ACCESS_TOKEN));
		else
		result= service.ServerData(baseUrl,paramss,appSettings.getString(AppSettings.ACCESS_TOKEN));
		return result;

	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		if (mProgressDialog != null)
			mProgressDialog.show();

	}

	@Override
	protected void onCancelled() {
		// TODO Auto-generated method stub
		super.onCancelled();
		if (mProgressDialog != null)
			mProgressDialog.dismiss();
		cancel(true);
		if (!secondParameter)
			listener.onTaskCompleteWithError(null);
		else
			asyncTaskDualListener.onTaskCompleteWithError(null, null);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void onPostExecute(String result) {
		if (mProgressDialog != null)
			mProgressDialog.dismiss();
		if(null==pDialog){

		}else{
			pDialog.dismiss();
		}


		if (!flag_error) {
			if (!secondParameter) {
				listener.onTaskCompleteWithSuccess(result);
			} else {
				asyncTaskDualListener.onTaskCompleteWithSuccess(result, type);
			}

		} else {
			if (!secondParameter)
				listener.onTaskCompleteWithError(result);
			else
				asyncTaskDualListener.onTaskCompleteWithError(result, type);
		}

	}



	public  void close_progrsdialog() {
		if(pDialog.isShowing()){
		pDialog.dismiss();
		}
	}

}
