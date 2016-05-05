package madgeek.cmart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ListView;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterAuthException;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.CollectionTimeline;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;

import io.fabric.sdk.android.Fabric;

public class Home extends AppCompatActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "KyLjj7anAqlCwp9ExInVj9uJw";
    private static final String TWITTER_SECRET = "1z5XdfxEaUtCpv85NsawkFzzcBpbHeIY8styD3KGurfB6lQuOa";

    final Callback<Tweet> actionCallback = new Callback<Tweet>() {
        @Override
        public void success(Result<Tweet> result) {
            // Intentionally blank
        }

        @Override
        public void failure(TwitterException exception) {
            if (exception instanceof TwitterAuthException) {

            }
        }
    };

    ListView collectionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.activity_home);

        collectionList = (ListView)findViewById(R.id.listView);

        // Collection "CMaRT Gallery"
        final CollectionTimeline timeline = new CollectionTimeline.Builder()
                .id(728011784968359937L)
                .build();
        final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(this)
                .setTimeline(timeline)
                .setViewStyle(R.style.tw__TweetDarkWithActionsStyle)
                .setOnActionCallback(actionCallback)
                .build();
        collectionList.setAdapter(adapter);




    }
}
