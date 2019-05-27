package com.teapink.damselindistress.openlive.ui;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.SurfaceView;
import android.view.WindowManager;

import com.teapink.damselindistress.openlive.model.VideoStatusData;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.ref.SoftReference;
import java.util.HashMap;


public class SmallVideoViewAdapter extends VideoViewAdapter {
    private final static Logger log = LoggerFactory.getLogger(SmallVideoViewAdapter.class);

    protected final Context context;
    protected final int exceptedUid;
    protected final HashMap<Integer, SurfaceView> uids;
    protected final VideoViewAdapter listener;




    public SmallVideoViewAdapter(Context context, int exceptedUid, HashMap<Integer, SurfaceView> uids, VideoViewEventListener listener) {
        super(context, exceptedUid, uids, listener);

        this.context=context;
        this.exceptedUid=exceptedUid;
        this.uids=uids;
        this.listener= (VideoViewAdapter) listener;
    }

    @Override
    protected void customizedInit(HashMap<Integer, SurfaceView> uids, boolean force) {
        for (HashMap.Entry<Integer, SurfaceView> entry : uids.entrySet()) {
            if (entry.getKey() != exceptedUid) {
                entry.getValue().setZOrderOnTop(true);
                entry.getValue().setZOrderMediaOverlay(true);
                mUsers.add(new VideoStatusData(entry.getKey(), entry.getValue(), VideoStatusData.DEFAULT_STATUS, VideoStatusData.DEFAULT_VOLUME));
            }
        }

        if (force || mItemWidth == 0 || mItemHeight == 0) {
            WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics outMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(outMetrics);
            mItemWidth = outMetrics.widthPixels / 4;
            mItemHeight = outMetrics.heightPixels / 4;
        }
    }

    @Override
    public void notifyUiChanged(HashMap<Integer, SurfaceView> uids, int uidExcluded, HashMap<Integer, Integer> status, HashMap<Integer, Integer> volume) {
        mUsers.clear();

        for (HashMap.Entry<Integer, SurfaceView> entry : uids.entrySet()) {
            log.debug("notifyUiChanged " + entry.getKey() + " " + uidExcluded);

            if (entry.getKey() != uidExcluded) {
                entry.getValue().setZOrderOnTop(true);
                entry.getValue().setZOrderMediaOverlay(true);
                mUsers.add(new VideoStatusData(entry.getKey(), entry.getValue(), VideoStatusData.DEFAULT_STATUS, VideoStatusData.DEFAULT_VOLUME));
            }
        }

        notifyDataSetChanged();
    }

    public int getExceptedUid() {
        return exceptedUid;
    }
}
