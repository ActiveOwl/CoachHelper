package org.dmschools.central.coachhelper.util;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.dmschools.central.coachhelper.domain.Team;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/*
 * A Singleton for managing your SharedPreferences.
 *
 * You should make sure to change the SETTINGS_NAME to what you want
 * and choose the operating made that suits your needs, the default is
 * MODE_PRIVATE.
 *
 * IMPORTANT: The class is not thread safe. It should work fine in most 
 * circumstances since the write and read operations are fast. However
 * if you call edit for bulk updates and do not commit your changes
 * there is a possibility of data loss if a background thread has modified
 * preferences at the same time.
 * 
 * Usage:
 * 
 * int sampleInt = SharedPreferenceUtil.getInstance(context).getInt(Key.SAMPLE_INT);
 * SharedPreferenceUtil.getInstance(context).set(Key.SAMPLE_INT, sampleInt);
 * 
 * If SharedPreferenceUtil.getInstance(Context) has been called once, you can 
 * simple use SharedPreferenceUtil.getInstance() to save some precious line space.
 */
public class SharedPreferenceUtil extends SharedPreferenceBase {
    // TODO: CHANGE THIS TO SOMETHING MEANINGFUL
    private static final String SETTINGS_NAME = "ATM_LOCATOR";
    private static SharedPreferenceUtil sSharedPrefs;

    private SharedPreferenceUtil(Context context) {
        super(context, SETTINGS_NAME);
    }


    public static SharedPreferenceUtil getInstance(Context context) {
        if (sSharedPrefs == null) {
            sSharedPrefs = new SharedPreferenceUtil(context.getApplicationContext());
        }
        return sSharedPrefs;
    }

    public static SharedPreferenceUtil getInstance() {
        if (sSharedPrefs != null) {
            return sSharedPrefs;
        }
        throw new IllegalArgumentException("Should use getInstance(Context) at least once before using this method.");
    }

    /**
     * Enum representing your setting names or key for your setting.
     */
    public enum Key {
        /* Recommended naming convention:
         * ints, floats, doubles, longs:
         * SAMPLE_NUM or SAMPLE_COUNT or SAMPLE_INT, SAMPLE_LONG etc.
         *
         * boolean: IS_SAMPLE, HAS_SAMPLE, CONTAINS_SAMPLE
         * 
         * String: SAMPLE_KEY, SAMPLE_STR or just SAMPLE
         */
        TEAMS
    }

    public void saveTeam(Team team) {
        // get all the teams
        HashMap<String, Team> teams = readTeams();

        // save the team
//        teams.put(team.getName(), team);
    }
    public Team getTeam(String name) {
        // get all the teams
        HashMap<String, Team> teams = readTeams();
        // check for the name in current
        if (teams != null && !TextUtils.isEmpty(name)) {
            return teams.get(name);
        }
        return null;
    }


    /*
        Using SharedPreferences for simple CRUD operations
            Create - not used
            Read - readTeams - get all of the teams
            Update - updateTeams - add or update a team
            Delete - deleteTeam - delete the given team
     */

    private HashMap<String, Team> readTeams() {
        String teamsJson = SharedPreferenceUtil.getInstance().getString(Key.TEAMS, "");
        Gson gson = new Gson();
        HashMap<String, Team> teams = gson.fromJson(teamsJson, new TypeToken<HashMap<String, Team>>() {
        }.getType());
        return teams;
    }

    private void updateTeams(Team team){
        HashMap<String,Team> teams = readTeams();

    }

    private void updateTeams(HashMap<String, Team> teams) {
        String teamsJson = new Gson().toJson(teams);
        SharedPreferenceUtil.getInstance().put(Key.TEAMS, teamsJson);
    }

    public void deleteTeam(String name) {
        if (!TextUtils.isEmpty(name)) {
            return;
        }
        HashMap<String, Team> teams = readTeams();
        teams.remove(name);
        updateTeams(teams);
    }

//    public void saveMap(Map<String, Integer> inputMap) {
//        JSONObject jsonObject = new JSONObject(inputMap);
//        String jsonString = jsonObject.toString();
//        SharedPreferenceUtil.getInstance().put(Key.FILTER_MAP, jsonString);
//    }

//    public Map<String, Integer> loadMap() {
//        Map<String, Integer> outputMap = new HashMap<String, Integer>();
//        try {
//            String jsonString = SharedPreferenceUtil.getInstance().getString(Key.FILTER_MAP);
//            JSONObject jsonObject = new JSONObject(jsonString);
//            Iterator<String> keysItr = jsonObject.keys();
//            while (keysItr.hasNext()) {
//                String key = keysItr.next();
//                Integer value = (Integer) jsonObject.get(key);
//                outputMap.put(key, value);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return outputMap;
//    }




}