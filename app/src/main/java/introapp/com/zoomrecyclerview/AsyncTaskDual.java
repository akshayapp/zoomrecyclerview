package introapp.com.zoomrecyclerview;

public interface AsyncTaskDual<T,K> {
	

    public void onTaskCompleteWithSuccess(T result, K type);
    public void onTaskCompleteWithError(T result, K type);
    


}
