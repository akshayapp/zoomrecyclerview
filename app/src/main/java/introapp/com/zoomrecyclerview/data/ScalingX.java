package introapp.com.zoomrecyclerview.data;

import android.graphics.Color;

import introapp.com.zoomrecyclerview.BarGraphSeries;
import introapp.com.zoomrecyclerview.DataPoint;
import introapp.com.zoomrecyclerview.GraphView;
import introapp.com.zoomrecyclerview.GridLabelRenderer;
import introapp.com.zoomrecyclerview.LegendRenderer;
import introapp.com.zoomrecyclerview.LineGraphSeries;
import introapp.com.zoomrecyclerview.R;


/**
 * Created by jonas on 10.09.16.
 */
public class ScalingX extends BaseExample {
    @Override
    public void onCreate(FullscreenActivity activity) {
        GraphView graph = (GraphView) activity.findViewById(R.id.graph);
        initGraph(graph);
    }

    @Override
    public void initGraph(GraphView graph) {
        // first series is a line
        DataPoint[] points = new DataPoint[100];
        for (int i = 0; i < points.length; i++) {
            points[i] = new DataPoint(i, Math.sin(i*0.5) * 20*(Math.random()*10+1));
        }
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(points);

        // set manual X bounds
//        graph.getViewport().setYAxisBoundsManual(true);
//        graph.getViewport().setMinY(-200);
//        graph.getViewport().setMaxY(200);

        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(4);
        graph.getViewport().setMaxX(80);

        // enable scaling
        graph.getViewport().setScalable(true);

        series.setTitle("Random Curve");

        graph.addSeries(series);

        BarGraphSeries<DataPoint> series1= new BarGraphSeries<>(new DataPoint[] {
                new DataPoint(0, -2),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        series1.setSpacing(30);
        series1.setAnimated(true);
        graph.addSeries(series1);

        BarGraphSeries<DataPoint> series2 = new BarGraphSeries<>(new DataPoint[] {
                new DataPoint(0, -5),
                new DataPoint(1, 3),
                new DataPoint(2, 4),
                new DataPoint(3, 4),
                new DataPoint(4, 1)
        });
        series2.setColor(Color.RED);
        series2.setSpacing(30);
        series2.setAnimated(true);
        graph.addSeries(series2);

        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(-2);
        graph.getViewport().setMaxX(6);

        graph.getLegendRenderer().setVisible(false);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        graph.getGridLabelRenderer().setGridStyle(GridLabelRenderer.GridStyle.HORIZONTAL);
        graph.getGridLabelRenderer().setHorizontalLabelsVisible(true);// remove horizontal x labels and line
        graph.getGridLabelRenderer().setVerticalLabelsVisible(false);

        graph.getViewport().setDrawBorder(false);

    }
}
