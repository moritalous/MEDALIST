package forest.rice.feeld.k.medalist.manager;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.ImageLoader.ImageCache;

public class LruCacheImpl implements ImageCache {

	private LruCache<String, Bitmap> mMemoryCache;

	public LruCacheImpl() {
		int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
		int cacheSize = maxMemory / 8; // 最大メモリに依存
		// int cacheSize = 5 * 1024 * 1024; // 5MB

		mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
			@Override
			protected int sizeOf(String key, Bitmap bitmap) {
				// 使用キャッシュサイズ(KB単位)
				return bitmap.getRowBytes() * bitmap.getHeight();
			}
		};
	}

	// ImageCacheのインターフェイス実装
	@Override
	public Bitmap getBitmap(String url) {
		return mMemoryCache.get(url);
	}

	@Override
	public void putBitmap(String url, Bitmap bitmap) {
		mMemoryCache.put(url, bitmap);
	}
}