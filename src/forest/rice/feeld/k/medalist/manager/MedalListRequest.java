package forest.rice.feeld.k.medalist.manager;

import java.io.UnsupportedEncodingException;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;

import forest.rice.feeld.k.medalist.entity.Medal;
import forest.rice.feeld.k.medalist.entity.MedalList;

public class MedalListRequest extends Request<MedalList> {

	private final Listener<MedalList> mListener;

	/**
	 * Creates a new request with the given method.
	 * 
	 * @param method
	 *            the request {@link Method} to use
	 * @param url
	 *            URL to fetch the string at
	 * @param listener
	 *            Listener to receive the String response
	 * @param errorListener
	 *            Error listener, or null to ignore errors
	 */
	public MedalListRequest(int method, String url,
			Listener<MedalList> listener, ErrorListener errorListener) {
		super(method, url, errorListener);
		mListener = listener;
	}

	/**
	 * Creates a new GET request.
	 * 
	 * @param url
	 *            URL to fetch the string at
	 * @param listener
	 *            Listener to receive the String response
	 * @param errorListener
	 *            Error listener, or null to ignore errors
	 */
	public MedalListRequest(String url, Listener<MedalList> listener,
			ErrorListener errorListener) {
		this(Method.GET, url, listener, errorListener);
	}

	@Override
	protected void deliverResponse(MedalList response) {
		mListener.onResponse(response);
	}

	@Override
	protected Response<MedalList> parseNetworkResponse(NetworkResponse response) {
		MedalList medalList = new MedalList();

		try {
			String parsed = new String(response.data, "UTF-8");
			String[] lines = parsed.split("\r\n");
			for (String line : lines) {
				try{
					Medal medal = Medal.createMedal(line);
					medalList.add(medal);		
				} catch(Exception e){
					e.printStackTrace();
				}
			
			}
		} catch (UnsupportedEncodingException e) {
		}
		return Response.success(medalList,
				HttpHeaderParser.parseCacheHeaders(response));
	}

}
