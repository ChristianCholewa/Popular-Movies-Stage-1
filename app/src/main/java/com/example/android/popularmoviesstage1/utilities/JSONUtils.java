package com.example.android.popularmoviesstage1.utilities;

import android.text.TextUtils;

import com.example.android.popularmoviesstage1.MovieData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONUtils {

    private static String JSON_KEY_RESULTS = "results";
    private static String JSON_KEY_TITLE = "title";
    private static String JSON_KEY_RELEASE_DATE = "release_date";
    private static String JSON_KEY_POSTER_PATH = "poster_path";
    private static String JSON_KEY_VOTE_AVERAGE = "vote_average";
    private static String JSON_KEY_OVERVIEW = "overview";

    public static List<MovieData> ParseServerResult(String jsonString) {
        List movieData = new ArrayList();

        if(!TextUtils.isEmpty(jsonString)){
            try {
                JSONObject allData = new JSONObject(jsonString);
                JSONArray results = allData.getJSONArray(JSON_KEY_RESULTS);

                for (int i = 0; i < results.length(); i++){
                    JSONObject movie = results.getJSONObject(i);

                    MovieData newMovie = new MovieData(
                        movie.getString(JSON_KEY_TITLE),
                        movie.getString(JSON_KEY_RELEASE_DATE),
                        movie.getString(JSON_KEY_POSTER_PATH),
                        movie.getDouble(JSON_KEY_VOTE_AVERAGE),
                        movie.getString(JSON_KEY_OVERVIEW)
                    );

                    movieData.add(newMovie);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return movieData;
    }
}
