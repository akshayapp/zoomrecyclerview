package introapp.com.zoomrecyclerview.data;

import android.support.v4.app.Fragment;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 */
public class MenuContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<MenuItem> ITEMS = new ArrayList<MenuItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, MenuItem> ITEM_MAP = new HashMap<String, MenuItem>();

    static {
        addItem(new MenuItem("1", "Hello world graph", ZoomingAndScrollingFragment.class));
        addItem(new MenuItem("2", "Zooming and scrolling", ZoomingAndScrollingFragment.class));
        addItem(new MenuItem("3", "Realtime plotting", ZoomingAndScrollingFragment.class));
        addItem(new MenuItem("4", "Time and dates", ZoomingAndScrollingFragment.class));
        addItem(new MenuItem("5", "Second scale and labels", ZoomingAndScrollingFragment.class));
        addItem(new MenuItem("6", "Line graph", ZoomingAndScrollingFragment.class));
        addItem(new MenuItem("7", "Bar graph", ZoomingAndScrollingFragment.class));
        addItem(new MenuItem("8", "Points graph", ZoomingAndScrollingFragment.class));
        addItem(new MenuItem("9", "On tap listener", ZoomingAndScrollingFragment.class));
        addItem(new MenuItem("10", "Styling examples", ZoomingAndScrollingFragment.class));
        addItem(new MenuItem("11", "Other examples", ZoomingAndScrollingFragment.class));
    }

    private static void addItem(MenuItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    /**
     * A item representing a piece of content.
     */
    public static class MenuItem {
        public final String id;
        public final String content;
        public final Class<? extends Fragment> fragmentClass;

        //@DrawableRes
        //public final int background;

        public MenuItem(String id,
                        String content,
                        Class<? extends Fragment> fragmentClass) {
            this.id = id;
            this.content = content;
            this.fragmentClass = fragmentClass;
            //this.background = background;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
