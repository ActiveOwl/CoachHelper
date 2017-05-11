package org.dmschools.central.coachhelper.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceBase {
    private SharedPreferences mPref;
    private SharedPreferences.Editor mEditor;
    private boolean mBulkUpdate = false;


    public SharedPreferenceBase(Context context, String settingsName) {
        mPref = context.getSharedPreferences(settingsName, Context.MODE_PRIVATE);
    }

    /**
     * Enum representing your setting names or key for your setting.
     */
    public enum Key {
    }


    public void put(SharedPreferenceUtil.Key key, String val) {
        doEdit();
        mEditor.putString(key.name(), val);
        doCommit();
    }

    public void put(SharedPreferenceUtil.Key key, int val) {
        doEdit();
        mEditor.putInt(key.name(), val);
        doCommit();
    }

    public void put(SharedPreferenceUtil.Key key, boolean val) {
        doEdit();
        mEditor.putBoolean(key.name(), val);
        doCommit();
    }

    public void put(SharedPreferenceUtil.Key key, float val) {
        doEdit();
        mEditor.putFloat(key.name(), val);
        doCommit();
    }

    /**
     * Convenience method for storing doubles.
     * <p/>
     * There may be instances where the accuracy of a double is desired.
     * SharedPreferences does not handle doubles so they have to
     * cast to and from String.
     *
     * @param key The enum of the preference to store.
     * @param val The new value for the preference.
     */
    public void put(SharedPreferenceUtil.Key key, double val) {
        doEdit();
        mEditor.putString(key.name(), String.valueOf(val));
        doCommit();
    }

    public void put(SharedPreferenceUtil.Key key, long val) {
        doEdit();
        mEditor.putLong(key.name(), val);
        doCommit();
    }

    public String getString(SharedPreferenceUtil.Key key, String defaultValue) {
        return mPref.getString(key.name(), defaultValue);
    }

    public String getString(SharedPreferenceUtil.Key key) {
        return mPref.getString(key.name(), null);
    }

    public int getInt(SharedPreferenceUtil.Key key) {
        return mPref.getInt(key.name(), 0);
    }

    public int getInt(SharedPreferenceUtil.Key key, int defaultValue) {
        return mPref.getInt(key.name(), defaultValue);
    }

    public long getLong(SharedPreferenceUtil.Key key) {
        return mPref.getLong(key.name(), 0);
    }

    public long getLong(SharedPreferenceUtil.Key key, long defaultValue) {
        return mPref.getLong(key.name(), defaultValue);
    }

    public float getFloat(SharedPreferenceUtil.Key key) {
        return mPref.getFloat(key.name(), 0);
    }

    public float getFloat(SharedPreferenceUtil.Key key, float defaultValue) {
        return mPref.getFloat(key.name(), defaultValue);
    }

    /**
     * Convenience method for retrieving doubles.
     * <p/>
     * There may be instances where the accuracy of a double is desired.
     * SharedPreferences does not handle doubles so they have to
     * cast to and from String.
     *
     * @param key The enum of the preference to fetch.
     */
    public double getDouble(SharedPreferenceUtil.Key key) {
        return getDouble(key, 0);
    }

    /**
     * Convenience method for retrieving doubles.
     * <p/>
     * There may be instances where the accuracy of a double is desired.
     * SharedPreferences does not handle doubles so they have to
     * cast to and from String.
     *
     * @param key The enum of the preference to fetch.
     */
    public double getDouble(SharedPreferenceUtil.Key key, double defaultValue) {
        try {
            return Double.valueOf(mPref.getString(key.name(), String.valueOf(defaultValue)));
        } catch (NumberFormatException nfe) {
            return defaultValue;
        }
    }

    public boolean getBoolean(SharedPreferenceUtil.Key key, boolean defaultValue) {
        return mPref.getBoolean(key.name(), defaultValue);
    }

    public boolean getBoolean(SharedPreferenceUtil.Key key) {
        return mPref.getBoolean(key.name(), false);
    }

    /**
     * Remove keys from SharedPreferences.
     *
     * @param keys The enum of the key(s) to be removed.
     */
    public void remove(SharedPreferenceUtil.Key... keys) {
        doEdit();
        for (SharedPreferenceUtil.Key key : keys) {
            mEditor.remove(key.name());
        }
        doCommit();
    }

    /**
     * Remove all keys from SharedPreferences.
     */
    public void clear() {
        doEdit();
        mEditor.clear();
        doCommit();
    }

    public void edit() {
        mBulkUpdate = true;
        mEditor = mPref.edit();
    }

    public void commit() {
        mBulkUpdate = false;
        mEditor.commit();
        mEditor = null;
    }

    private void doEdit() {
        if (!mBulkUpdate && mEditor == null) {
            mEditor = mPref.edit();
        }
    }

    private void doCommit() {
        if (!mBulkUpdate && mEditor != null) {
            mEditor.commit();
            mEditor = null;
        }
    }

}
