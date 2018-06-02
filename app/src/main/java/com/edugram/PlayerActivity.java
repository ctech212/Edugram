package com.edugram;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubePlayer.PlayerStateChangeListener;

/**
 * Merupakan class untuk melakukan setting terhadap video yang akan ditampilkan yaitu untuk materi sistem tata surya. Digunakan API youtube untuk menampilkan video.
 *
 * @version 02/06/2018
 */
public class PlayerActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    YouTubePlayer player;
    private MyPlayerStateChangeListener playerStateChangeListener;


    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.yt_player);

        YouTubePlayerView playerView = (YouTubePlayerView) findViewById(R.id.youTubePlayerView);
        playerView.initialize(DeveloperKey.DEVELOPER_KEY, this);

        playerStateChangeListener= new MyPlayerStateChangeListener();


    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean b) {

        this.player = player;
        player.setPlayerStateChangeListener(playerStateChangeListener);

        if (!b) {

            player.setFullscreen(true);
            player.setShowFullscreenButton(false);
            player.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
            // You can change the ID.. of the video to be played
            player.loadVideo("qS_VQDL4Im0");

        }


    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }

    private final class MyPlayerStateChangeListener implements PlayerStateChangeListener {
        @Override
        public void onLoading() {
        }

        @Override
        public void onLoaded(String s) {

        }

        @Override
        public void onAdStarted() {

        }

        @Override
        public void onVideoStarted() {

        }

        @Override
        public void onVideoEnded() {
            AlertDialog.Builder builder = new AlertDialog.Builder(PlayerActivity.this);
            builder.setMessage("Are you sure you want to Exercise?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(PlayerActivity.this,PilganSolarActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(PlayerActivity.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {

        }


    }



}
