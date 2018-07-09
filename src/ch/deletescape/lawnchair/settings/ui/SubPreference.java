package ch.deletescape.lawnchair.settings.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.preference.Preference;
import android.util.AttributeSet;
import android.view.View;
import com.android.launcher3.R;

public class SubPreference extends Preference implements View.OnLongClickListener {

    private int mContent;
    private int mLongClickContent;
    private boolean mLongClick;
    private int mPreviewLayout;

    public SubPreference(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SubPreference);
        for (int i = a.getIndexCount() - 1; i >= 0; i--) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.SubPreference_content:
                    mContent = a.getResourceId(attr, 0);
                    break;
                case R.styleable.SubPreference_longClickContent:
                    mLongClickContent = a.getResourceId(attr, 0);
                    break;
                case R.styleable.SubPreference_previewLayout:
                    mPreviewLayout = a.getResourceId(attr, 0);
                    break;
            }
        }
        a.recycle();
        setFragment(SettingsActivity.SubSettingsFragment.class.getName());
    }

    @Override
    public Bundle getExtras() {
        Bundle b = new Bundle(2);
        b.putString(SettingsActivity.SubSettingsFragment.TITLE, (String) getTitle());
        b.putInt(SettingsActivity.SubSettingsFragment.CONTENT_RES_ID, getContent());
        b.putInt(SettingsActivity.SubSettingsFragment.PREVIEW_LAYOUT, getPreviewLayout());
        return b;
    }

    public int getContent() {
        return mLongClick ? mLongClickContent : mContent;
    }

    public int getPreviewLayout() {
        return mPreviewLayout;
    }

    @Override
    protected void onClick() {
        mLongClick = false;
        super.onClick();
    }

    @Override
    public boolean onLongClick(View view) {
        if (mLongClickContent != 0) {
            mLongClick = true;
            super.onClick();
            return true;
        } else {
            return false;
        }
    }
}