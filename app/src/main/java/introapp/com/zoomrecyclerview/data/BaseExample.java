package introapp.com.zoomrecyclerview.data;


import introapp.com.zoomrecyclerview.GraphView;

/**
 * Created by jonas on 10.09.16.
 */
public abstract class BaseExample {
    public abstract void onCreate(FullscreenActivity fullscreenActivity);
    public abstract void initGraph(GraphView graph);

    public void onPause() {}
    public void onResume() {}
}
