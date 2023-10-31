package com.example.liveTv;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.liveTv.R;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.StyledPlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer2.util.Util;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.PrivateKey;

public class MainActivity extends AppCompatActivity {

    public String videoUri = "https://dje6yassknq8t.cloudfront.net/playlist720p.m3u8";
    private String currentUrl = "";
    private ExoPlayer player;
    private StyledPlayerView styledPlayerView;
    private ProgressBar progressBar;
    private ImageView fullScreenBtn;
    private boolean fullscreen = false;
    private ImageButton button1, button2, button3, button4, button5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        styledPlayerView = findViewById(R.id.video_player);
        fullScreenBtn = styledPlayerView.findViewById(R.id.exo_fullscreen_btn);
        button1 = findViewById(R.id.MC_1);
        button2 = findViewById(R.id.MC_3);
        button3 = findViewById(R.id.NW_3);
        button4 = findViewById(R.id.NW_1);
        button5 = findViewById(R.id.SC_3);

        // Initialize Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference buttonRef = database.getReference().child("url");
        // Initialize the player
        player = new ExoPlayer.Builder(this).build();
        styledPlayerView.setPlayer(player);
        MediaItem mediaItem = MediaItem.fromUri(videoUri);
        player.setMediaItem(mediaItem);
        player.prepare();
        player.play();

        // Set up a listener to handle playback events
//        player.addListener(new Player.Listener() {
//            @Override
//            public void onPlaybackStateChanged(int state) {
//                switch (state) {
//                    case Player.STATE_BUFFERING:
//                        //progressBar.setVisibility(View.VISIBLE);
//                        break;
//                    case Player.STATE_READY:
//                        //progressBar.setVisibility(View.GONE);
//                        break;
//                    case Player.STATE_ENDED:
//                        // Handle the end of playback
//                        break;
//                    default:
//                        break;
//                }
//            }
//        });
        fullScreenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleOrientation();
            }
        });

        // Create button click listeners
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "Button1 Clicked", Toast.LENGTH_SHORT).show();
                buttonRef.child("url1").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String hlsLink = snapshot.getValue(String.class);
                        playMedia(hlsLink);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.e( "help", "Error retrieving HLS link from Firebase", error.toException());
                    }
                });
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "Button2 Clicked", Toast.LENGTH_SHORT).show();
                buttonRef.child("url2").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String hlsLink = snapshot.getValue(String.class);
                        playMedia(hlsLink);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.e( "help", "Error retrieving HLS link from Firebase", error.toException());
                    }
                });
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "Button2 Clicked", Toast.LENGTH_SHORT).show();
                buttonRef.child("url3").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String hlsLink = snapshot.getValue(String.class);
                        playMedia(hlsLink);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.e( "help", "Error retrieving HLS link from Firebase", error.toException());
                    }
                });
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "Button2 Clicked", Toast.LENGTH_SHORT).show();
                buttonRef.child("url4").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String hlsLink = snapshot.getValue(String.class);
                        playMedia(hlsLink);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.e( "help", "Error retrieving HLS link from Firebase", error.toException());
                    }
                });
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "Button2 Clicked", Toast.LENGTH_SHORT).show();
                buttonRef.child("url5").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String hlsLink = snapshot.getValue(String.class);
                        playMedia(hlsLink);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.e( "help", "Error retrieving HLS link from Firebase", error.toException());
                    }
                });
            }
        });

    }

    private void toggleOrientation() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    // Method to play media with ExoPlayer
    private void playMedia(String hlsLink) {
        this.currentUrl = hlsLink;
        if (player!= null) {
            player.release();
        }
        player = new ExoPlayer.Builder(this).build();
        styledPlayerView.setPlayer(player);
        MediaItem mediaItem = MediaItem.fromUri(currentUrl);
        player.setMediaItem(mediaItem);
        player.prepare();
        player.play();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        player.release();
    }

    @Override
    protected void onPause() {
        super.onPause();
        player.setPlayWhenReady(false);
        player.getPlaybackState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            // Hide UI elements for landscape mode
        }
    }



}