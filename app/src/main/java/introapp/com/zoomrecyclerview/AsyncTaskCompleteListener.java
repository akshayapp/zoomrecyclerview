package introapp.com.zoomrecyclerview;



public interface AsyncTaskCompleteListener<T>
{
    /**
     * Invoked when the AsyncTask has completed its execution.
     * @param result The resulting object from the AsyncTask.
     */
    public void onTaskCompleteWithSuccess(T result);
    public void onTaskCompleteWithError(T result);
    
  
    
}
