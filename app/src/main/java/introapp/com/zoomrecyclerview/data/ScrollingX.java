package introapp.com.zoomrecyclerview.data;


import introapp.com.zoomrecyclerview.DataPoint;
import introapp.com.zoomrecyclerview.GraphView;
import introapp.com.zoomrecyclerview.LegendRenderer;
import introapp.com.zoomrecyclerview.LineGraphSeries;
import introapp.com.zoomrecyclerview.R;

/**
 * Created by jonas on 10.09.16.
 */
public class ScrollingX extends BaseExample {
    @Override
    public void onCreate(FullscreenActivity activity) {
        GraphView graph = (GraphView) activity.findViewById(R.id.graph);
        initGraph(graph);
    }

    @Override
    public void initGraph(GraphView graph) {
        // first series is a line
        DataPoint[] points = new DataPoint[50];
        for (int i = 0; i < 50; i++) {
            points[i] = new DataPoint(i, Math.sin(i*0.5) * 20*(Math.random()*10+1));
        }
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(points);

        // set manual X bounds
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(10);

        // enable scrolling
        graph.getViewport().setScrollable(true);

        series.setTitle("Random Curve");

        graph.addSeries(series);

        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
    }
}
