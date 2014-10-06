package forest.rice.feeld.k.medalist;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;

import forest.rice.feeld.k.medalist.entity.MedalList;
import forest.rice.feeld.k.medalist.manager.MedalListRequest;
import forest.rice.feeld.k.medalist.manager.VolleyManager;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_official_site) {
			Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://yw.b-boys.jp/member/products/product_list/"));
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		private RequestQueue mQueue;
		private MedalList medalList = new MedalList();

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);

			mQueue = VolleyManager.getRequestQueue(getActivity());
			mQueue.add(new MedalListRequest(
					"http://yw.b-boys.jp/member/maruwakalist_file/medallist.txt",
					listenerMedal1, errorListener));

			return rootView;
		}

		private Listener<MedalList> listenerMedal1 = new Listener<MedalList>() {
			@Override
			public void onResponse(MedalList response) {
				medalList.add(response);
				mQueue.add(new MedalListRequest(
						"http://yw.b-boys.jp/member/maruwakalist_file/medallist2.txt",
						listenerMedal2, errorListener));
			}
		};

		private Listener<MedalList> listenerMedal2 = new Listener<MedalList>() {
			@Override
			public void onResponse(MedalList response) {
				medalList.add(response);

				GridAdapter adapter = new GridAdapter(getActivity(), 0, R.id.grid_view_text,
						medalList);

				GridView gridView = (GridView) getActivity().findViewById(
						R.id.grid_view);
				gridView.setAdapter(adapter);
			}
		};

		private ErrorListener errorListener = new ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				System.out.println(error.toString());
			}
		};

	}

}
