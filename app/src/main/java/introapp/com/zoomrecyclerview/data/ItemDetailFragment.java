package introapp.com.zoomrecyclerview.data;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;

import introapp.com.zoomrecyclerview.R;


public abstract class ItemDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private MenuContent.MenuItem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = MenuContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.content);
                //appBarLayout.setBackgroundResource(mItem.background);
            }
        }
    }

    protected void openFullscreen(FullscreenExample helloWorld) {
        Intent intent = new Intent(getActivity(), FullscreenActivity.class);
        intent.putExtra(FullscreenExample.ARG_ID, helloWorld.name());
        startActivity(intent);
    }

    protected void openSource(FullscreenExample helloWorld) {
        Intent i = new Intent("android.intent.action.VIEW");
        i.setData(Uri.parse(helloWorld.url));
        startActivity(i);
    }
}
