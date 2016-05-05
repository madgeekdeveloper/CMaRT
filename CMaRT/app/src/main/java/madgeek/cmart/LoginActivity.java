package madgeek.cmart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import io.fabric.sdk.android.Fabric;

/**
 * Created by devonaward on 5/5/16.
 * Log user in to Twitter
 * User that is logged in will be able to like Tweets from collection
 */
public class LoginActivity extends AppCompatActivity {

    private static final String TWITTER_KEY = "KyLjj7anAqlCwp9ExInVj9uJw";
    private static final String TWITTER_SECRET = "1z5XdfxEaUtCpv85NsawkFzzcBpbHeIY8styD3KGurfB6lQuOa";

    private TwitterLoginButton Tlogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.activity_login);

        Tlogin = (TwitterLoginButton) findViewById(R.id.twitter_login_button);
        Tlogin.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                //Get the Twitter session
                TwitterSession session = result.data;
                String success = "Welcome to CMaRT @" + session.getUserName() + "!";
                Toast.makeText(getApplicationContext(), success, Toast.LENGTH_LONG).show();
            }

            @Override
            public void failure(TwitterException exception) {
                Log.d("TwitterKit", "Login with Twitter failure", exception);
            }
        });
    }

    @Override
    protected void onActivityResult ( int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        //Gets the result back from the Twitter login
        Tlogin.onActivityResult(requestCode, resultCode, data);
        finish();
    }
}
