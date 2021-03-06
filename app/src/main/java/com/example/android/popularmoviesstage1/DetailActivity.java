package com.example.android.popularmoviesstage1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.popularmoviesstage1.utilities.NetworkUtils;
import com.squareup.picasso.Picasso;

import java.net.URL;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        MovieData data = intent.getParcelableExtra(MovieData.EXTRA_NAME_MOVIEDATA);

        String title = data.getTitle();
        String release_date = data.getRelease_date();
        String poster_path = data.getPoster_path();
        double vote_average = data.getVote_average();
        String overview = data.getOverview();

        ImageView imageView = findViewById(R.id.iv_poster);
        URL posterUrl = NetworkUtils.buildPosterUrlString(poster_path);
        Picasso.with(this).load(posterUrl.toString()).into(imageView);

        TextView tv_title = findViewById(R.id.tv_title);
        tv_title.setText(title);

        TextView tv_average = findViewById(R.id.tv_average);
        tv_average.setText(Double.toString(vote_average));

        TextView tv_release = findViewById(R.id.tv_release);
        tv_release.setText(release_date);

        TextView tv_overview = findViewById(R.id.tv_overview);
        tv_overview.setText(overview);
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }
}
