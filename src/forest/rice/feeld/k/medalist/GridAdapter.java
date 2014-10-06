package forest.rice.feeld.k.medalist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import forest.rice.feeld.k.medalist.entity.Medal;
import forest.rice.feeld.k.medalist.entity.MedalList;

public class GridAdapter extends ArrayAdapter<Medal> {

	private LayoutInflater layoutInflater_;

	public class GridViewHoler {
		public TextView gridRowText;
	}

	public GridAdapter(Context context, int resource, int textViewResourceId,
			MedalList medalList) {

		super(context, resource, textViewResourceId, medalList.allMedal);

		layoutInflater_ = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final GridViewHoler holder;

		if (convertView == null) {
			convertView = layoutInflater_
					.inflate(R.layout.grid_row_medal, null);
			holder = new GridViewHoler();
			holder.gridRowText = (TextView) convertView
					.findViewById(R.id.grid_row_text);

			

			convertView.setTag(holder);
		} else {
			holder = (GridViewHoler) convertView.getTag();
		}
		
		Medal item = getItem(position);
		holder.gridRowText.setText(item.name);

		return convertView;
	}

}