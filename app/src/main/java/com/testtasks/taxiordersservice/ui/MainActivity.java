package com.testtasks.taxiordersservice.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Conductor;
import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler;
import com.google.android.material.snackbar.Snackbar;
import com.testtasks.taxiordersservice.Application;
import com.testtasks.taxiordersservice.R;
import com.testtasks.taxiordersservice.data.order.Order;
import com.testtasks.taxiordersservice.service.PhotoCacheService;
import com.testtasks.taxiordersservice.ui.about.AboutDeveloperController;
import com.testtasks.taxiordersservice.ui.details.OrderDetailsController;
import com.testtasks.taxiordersservice.ui.orders.OrdersController;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainActivityCallback {

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.controller_container)
    ViewGroup container;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.loader)
    ConstraintLayout loaderLayout;
    @BindView(R.id.refresh_button)
    AppCompatButton refreshButton;

    private Router mRouter;
    private AboutDeveloperController aboutDeveloperController;
    private boolean infoMenuVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Application.getComponent().inject(this);
        ButterKnife.bind(this);

        mRouter = Conductor.attachRouter(this, container, savedInstanceState);
        if (!mRouter.hasRootController()) {
            mRouter.setRoot(RouterTransaction.with(new OrdersController()));
        }

        setSupportActionBar(toolbar);

        startService(new Intent(getBaseContext(), PhotoCacheService.class));
    }

    @Override
    public void onBackPressed() {
        if (!mRouter.handleBack()) {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        menu.findItem(R.id.action_info).setVisible(infoMenuVisible);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_info) {
            openAboutDeveloperController();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setToolbar(int titleResId, boolean backButtonEnabled) {
        toolbar.setTitle(getString(titleResId));
        if(backButtonEnabled){
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
            toolbar.setNavigationOnClickListener(v -> onBackPressed());
        } else {
            toolbar.setNavigationIcon(null);
        }
    }

    @Override
    public void setMenu(boolean infoItemVisible) {
        infoMenuVisible = infoItemVisible;
        invalidateOptionsMenu();
    }

    @Override
    public void showSnackbar(String message) {
        Snackbar.make(drawerLayout, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showLoading(boolean isVisible) {
        loaderLayout.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setRefreshButton(View.OnClickListener listener, boolean isVisible) {
        refreshButton.setVisibility(isVisible ? View.VISIBLE : View.GONE);
        refreshButton.setOnClickListener(listener);
    }

    @Override
    public void openOrderDetailsController(Order order) {
        OrderDetailsController orderDetailsController = new OrderDetailsController();
        orderDetailsController.setOrder(order);
        pushController(orderDetailsController);
    }

    private void openAboutDeveloperController() {
        aboutDeveloperController = new AboutDeveloperController();
        pushController(aboutDeveloperController);
    }

    private void pushController(Controller controller) {
        mRouter.pushController(RouterTransaction.with(controller)
                .pushChangeHandler(new FadeChangeHandler())
                .popChangeHandler(new FadeChangeHandler()));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && aboutDeveloperController != null && aboutDeveloperController.getWebView().canGoBack()) {
            aboutDeveloperController.getWebView().goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
