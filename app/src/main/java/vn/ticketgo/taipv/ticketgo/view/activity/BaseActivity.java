package vn.ticketgo.taipv.ticketgo.view.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import vn.ticketgo.taipv.sdk.callbacks.ConfirmListener;
import vn.ticketgo.taipv.sdk.callbacks.DialogListener;
import vn.ticketgo.taipv.ticketgo.util.PrefUtil;
import vn.ticketgo.taipv.ticketgo.util.SharedUtils;
import vn.ticketgo.taipv.ticketgo.util.TextUtils;
import vn.ticketgo.taipv.ticketgo.view.activity.inf.IBasicView;
import vn.ticketgo.taipv.ticketgo.view.activity.login.LoginActivity;

import java.io.Serializable;

/**
 * Author: Phùng Tài NeverGiveUp
 * Date: 1/3/2018
 * Email: tai97nd@gmail.com
 */

public class BaseActivity extends AppCompatActivity implements IBasicView {
    private Dialog progressDialog;
    boolean isWaitingForExit = false;
    private Toast toast;
    private View basicView;
    PrefUtil prefUtil=new PrefUtil(this);
    public void initToolbar(int id, String title) {
        Toolbar toolbar = (Toolbar) findViewById(id);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        assert actionBar != null;
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        if (title != null)
            actionBar.setTitle(title);
    }

    public void initToolbar(int id, String title, int resource) {
        Toolbar toolbar = (Toolbar) findViewById(id);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar == null) return;
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        if (resource != -1)
            toolbar.setNavigationIcon(resource);

        if (title != null)
            actionBar.setTitle(title);
    }
    @Override
    public void showLongToast(String message) {
        if (toast != null)
            toast.cancel();

        toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    public void onError(int code) {
        closeProgressBar();

        String error = TextUtils.getErrorCode(code);

        if (error != null) {
            showLongToast(error);
        }
    }


    @Override
    public void onEndSession() {

        SharedUtils.getInstance().clearData();
        prefUtil.clearToken();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        finish();
        startActivity(intent);
    }
    protected void showProgressBar(boolean isTouchOutside, boolean isCancel, String message) {
        try {
            progressDialog = new Dialog(BaseActivity.this, vn.ticketgo.taipv.ticketgo.R.style.Theme_Transparent);
            progressDialog.setContentView(vn.ticketgo.taipv.ticketgo.R.layout.dialog_progress_bar);
            //noinspection ConstantConditions
            progressDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            progressDialog.setCancelable(isTouchOutside);
            progressDialog.setCanceledOnTouchOutside(isCancel);

            if (message != null) {
                TextView textView = progressDialog.findViewById(vn.ticketgo.taipv.ticketgo.R.id.txt_message);
                textView.setText(message);
            }

            progressDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*
    * Kết thúc hiển thị tiến trình (đang thực hiện)
    * */
    protected void closeProgressBar() {
        if (progressDialog != null)
            progressDialog.dismiss();
    }
    protected void showErrorGetData(String message) {
        String mes = getString(vn.ticketgo.taipv.ticketgo.R.string.error_try_again);

        if (message != null)
            mes = message;

        showMaterialDialog(false, false, null, mes, null, getString(vn.ticketgo.taipv.ticketgo.R.string.layout_ok), new DialogListener() {
            @Override
            public void negativeClicked() {

            }

            @Override
            public void positiveClicked() {
                finish();
            }
        });
    }

    /*
 * Hiển thị thông báo (chuẩn material)
 * */
    @SuppressWarnings("ConstantConditions")
    protected void showMaterialDialog(boolean isTouchOutside, boolean isCancelable, String title, String message, String negative, String positive, final DialogListener dialogListener) {
        final Dialog dialog = new Dialog(BaseActivity.this, vn.ticketgo.taipv.ticketgo.R.style.Theme_Transparent);
        dialog.setContentView(vn.ticketgo.taipv.ticketgo.R.layout.dialog_material);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(isTouchOutside);
        dialog.setCanceledOnTouchOutside(isCancelable);

        TextView txt_title = dialog.findViewById(vn.ticketgo.taipv.ticketgo.R.id.dialog_txt_title);
        TextView txt_message = dialog.findViewById(vn.ticketgo.taipv.ticketgo.R.id.dialog_txt_message);
        Button btn_negative = dialog.findViewById(vn.ticketgo.taipv.ticketgo.R.id.dialog_btn_negative);
        Button btn_positive = dialog.findViewById(vn.ticketgo.taipv.ticketgo.R.id.dialog_btn_positive);

        if (title == null)
            txt_title.setVisibility(View.GONE);
        else
            txt_title.setText(title);

        if (message == null)
            txt_message.setVisibility(View.GONE);
        else
            txt_message.setText(message);

        if (negative == null)
            btn_negative.setVisibility(View.GONE);
        else
            btn_negative.setText(negative);

        if (positive == null)
            btn_positive.setVisibility(View.GONE);
        else
            btn_positive.setText(positive);

        btn_negative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                dialogListener.negativeClicked();
            }
        });

        btn_positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                dialogListener.positiveClicked();
            }
        });

        dialog.show();
    }
    protected void showConfirmDialog(String title, String message, final ConfirmListener confirmListener) {
        final Dialog dialog = new Dialog(BaseActivity.this, vn.ticketgo.taipv.ticketgo.R.style.Theme_Transparent);
        dialog.setContentView(vn.ticketgo.taipv.ticketgo.R.layout.dialog_material);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);

        TextView txt_title = (TextView) dialog.findViewById(vn.ticketgo.taipv.ticketgo.R.id.dialog_txt_title);
        TextView txt_message = (TextView) dialog.findViewById(vn.ticketgo.taipv.ticketgo.R.id.dialog_txt_message);
        Button btn_negative = (Button) dialog.findViewById(vn.ticketgo.taipv.ticketgo.R.id.dialog_btn_negative);
        Button btn_positive = (Button) dialog.findViewById(vn.ticketgo.taipv.ticketgo.R.id.dialog_btn_positive);

        if (title == null)
            txt_title.setVisibility(View.GONE);
        else
            txt_title.setText(title);

        if (message == null)
            txt_message.setVisibility(View.GONE);
        else
            txt_message.setText(message);

        btn_negative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btn_positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                confirmListener.onConfirmSuccess();
            }
        });

        dialog.show();
    }

    /*
    * Khởi tạo fragment vào 1 view layout (FrameLayout)
    * */
    protected void replaceFragment(int id, Fragment fragment, String tag) {
        if (getSupportFragmentManager().findFragmentByTag(tag) == null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(id, fragment, tag);
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            fragmentTransaction.commit();
        }
    }

    protected void startActivity(Class clazz) {
        startActivity(new Intent(this, clazz));
    }

    protected void startActivity(Class clazz, String key, Object object) {
        Intent intent = new Intent(this, clazz);
        intent.putExtra(key, (Serializable) object);
        startActivity(intent);
    }

    protected void startActivityForResult(Class clazz, int requestCode) {
        startActivityForResult(new Intent(this, clazz), requestCode);
    }

    protected void startActivityForResultWithInteger(Class clazz, String key, int data, int requestCode) {
        Intent intent = new Intent(this, clazz);
        intent.putExtra(key, data);
        startActivityForResult(intent, requestCode);
    }

    protected void startActivityForResult(Class clazz, String key, Object object, int requestCode) {
        Intent intent = new Intent(this, clazz);
        intent.putExtra(key, (Serializable) object);
        startActivityForResult(intent, requestCode);
    }

    protected void startActivityAndFinish(Class clazz) {
        startActivity(new Intent(this, clazz));
        finish();
    }

    protected void startActivityAndFinish(Class clazz, String key, Object object) {
        Intent intent = new Intent(this, clazz);
        intent.putExtra(key, (Serializable) object);
        startActivity(intent);
        finish();
    }
    protected void showConfirmExitApp() {
        if (isWaitingForExit) {
            finish();
        } else {
            new AsyncTask<Object, Object, Object>() {

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    isWaitingForExit = true;
                    showLongToast(getString(vn.ticketgo.taipv.ticketgo.R.string.message_back_press_to_exit));

                }

                @Override
                protected Object doInBackground(Object... params) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Object o) {
                    super.onPostExecute(o);
                    isWaitingForExit = false;
                }
            }.execute();
        }
    }

}
