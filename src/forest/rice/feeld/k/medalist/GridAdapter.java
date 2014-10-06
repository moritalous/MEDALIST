package forest.rice.feeld.k.medalist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageContainer;
import com.android.volley.toolbox.ImageLoader.ImageListener;

import forest.rice.feeld.k.medalist.entity.Medal;
import forest.rice.feeld.k.medalist.entity.MedalList;
import forest.rice.feeld.k.medalist.manager.LruCacheImpl;
import forest.rice.feeld.k.medalist.manager.VolleyManager;

public class GridAdapter extends ArrayAdapter<Medal> {

	private LayoutInflater layoutInflater_;

	private RequestQueue mQueue;
	private ImageLoader mImageLoader;

	public class GridViewHoler {
		public ImageView gridImageView;
		public TextView gridRowName;
		public TextView gridRowFamily;
	}

	public GridAdapter(Context context, int resource, int textViewResourceId,
			MedalList medalList) {
		super(context, resource, textViewResourceId, medalList.allMedal);

		layoutInflater_ = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		mQueue = VolleyManager.getRequestQueue(context);
		mImageLoader = new ImageLoader(mQueue, new LruCacheImpl());
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final GridViewHoler holder;

		if (convertView == null) {
			convertView = layoutInflater_
					.inflate(R.layout.grid_row_medal, null);
			holder = new GridViewHoler();
			holder.gridRowName = (TextView) convertView
					.findViewById(R.id.grid_row_name);
			holder.gridRowFamily = (TextView) convertView
					.findViewById(R.id.grid_row_family);
			holder.gridImageView = (ImageView) convertView
					.findViewById(R.id.grid_row_image);

			convertView.setTag(holder);
		} else {
			holder = (GridViewHoler) convertView.getTag();
		}

		Medal item = getItem(position);
		holder.gridRowName.setText(item.name);
		holder.gridRowFamily.setText(item.family);

		ImageContainer imageContainer = (ImageContainer) holder.gridImageView
				.getTag();
		if (imageContainer != null) {
			imageContainer.cancelRequest();
		}

		ImageListener listener = ImageLoader.getImageListener(
				holder.gridImageView, R.drawable.ic_action_refresh/* 表示待ち時の画像 */,
				android.R.drawable.ic_dialog_alert /* エラー時の画像 */);

		imageContainer = mImageLoader.get(item.getImageUrl(), listener); /* URLから画像を取得する */

		holder.gridImageView.setTag(imageContainer);

		return convertView;
	}

}