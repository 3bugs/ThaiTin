package th.ac.dusit.dbizcom.thaitin.fragment;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import th.ac.dusit.dbizcom.thaitin.R;

public class AboutFragment extends BaseFragment {

    private static final String TAG = AboutFragment.class.getName();

    private AboutFragmentListener mListener;

    public AboutFragment() {
        // Required empty public constructor
        mTitle = "เกี่ยวกับภาคอีสาน";
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_about, container, false);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        WebView webView = view.findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            }

            @TargetApi(android.os.Build.VERSION_CODES.M)
            @Override
            public void onReceivedError(WebView view, WebResourceRequest req, WebResourceError rerr) {
                // Redirect to deprecated method, so you can use it in all SDK versions
                onReceivedError(
                        view,
                        rerr.getErrorCode(),
                        rerr.getDescription().toString(),
                        req.getUrl().toString()
                );
            }
        });

        webView.loadUrl("http://5911011802022.msci.dusit.ac.th/thaitin/esan.html");
    }

    public interface AboutFragmentListener {
    }
}
