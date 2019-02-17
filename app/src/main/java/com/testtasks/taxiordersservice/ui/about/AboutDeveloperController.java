package com.testtasks.taxiordersservice.ui.about;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.testtasks.taxiordersservice.R;
import com.testtasks.taxiordersservice.ui.MainActivityCallback;
import com.testtasks.taxiordersservice.ui.base.BaseController;
import com.testtasks.taxiordersservice.ui.base.PresenterI;

import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutDeveloperController extends BaseController implements AboutDeveloperControllerI {

    @BindView(R.id.webView)
    WebView webView;

    private boolean wasWebViewLoaded;

    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        View view = inflater.inflate(R.layout.controller_about_developer, container, false);
        ButterKnife.bind(this, view);

        initView();

        if (!wasWebViewLoaded) {
            loadWebView();
        }

        return view;
    }

    @Override
    public void initView() {
        mainActivityCallback = (MainActivityCallback) getActivity();
        mainActivityCallback.setToolbar(R.string.about_developer, true);
        mainActivityCallback.setMenu(false);
        refreshButtonListener = v -> loadWebView();
        initWebView();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView() {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);

        webView.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView webView, String url) {
                mainActivityCallback.showLoading(false);
                wasWebViewLoaded = true;
            }
        });
    }

    @Override
    public PresenterI getPresenter() {
        return null;
    }

    @Override
    public WebView getWebView() {
        return webView;
    }

    private void loadWebView(){
        if(isNetworkConnected()){
            mainActivityCallback.showLoading(true);
            mainActivityCallback.setRefreshButton(null, false);
            webView.loadUrl(getActivity().getString(R.string.resume_url));
        } else {
            mainActivityCallback.setRefreshButton(refreshButtonListener, true);
            showMessage(R.string.network_absent_error);
        }
    }

    @Override
    protected void onSaveViewState(@NonNull View view, @NonNull Bundle outState) {
        super.onSaveViewState(view, outState);
        webView.saveState(outState);
    }

    @Override
    protected void onRestoreViewState(@NonNull View view, @NonNull Bundle savedViewState) {
        super.onRestoreViewState(view, savedViewState);
        if (isNetworkConnected()){
            webView.restoreState(savedViewState);
        } else {
            mainActivityCallback.setRefreshButton(refreshButtonListener, true);
            showMessage(R.string.network_absent_error);
            wasWebViewLoaded = false;
        }
    }
}
