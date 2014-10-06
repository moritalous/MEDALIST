package forest.rice.feeld.k.medalist.manager;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleyManager {

	private static RequestQueue mQueue = null;

	public static RequestQueue getRequestQueue(Context context) {
		if (mQueue != null) {
			return mQueue;
		}

		mQueue = Volley.newRequestQueue(context);

		return mQueue;
	}
}
