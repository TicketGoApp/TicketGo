package vn.ticketgo.taipv.ticketgo.view.fragment;


import android.app.Dialog;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import vn.ticketgo.taipv.sdk.callbacks.ConfirmListener;
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

public class BasicFragment extends Fragment implements IBasicView {
    private Dialog progressDialog;
    private Toast toast;
    private SharedUtils sharedUtils;
    PrefUtil prefUtil=new PrefUtil(getActivity());


    @Override
    public void showLongToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onEndSession() {
        prefUtil.clearToken();
        SharedUtils.getInstance().clearData();
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        getActivity().finish();
        startActivity(intent);
    }
    protected void showProgressBar(boolean isTouchOutside, boolean isCancel, String message) {
        try {
            progressDialog = new Dialog(getContext(), vn.ticketgo.taipv.ticketgo.R.style.Theme_Transparent);
            progressDialog.setContentView(vn.ticketgo.taipv.ticketgo.R.layout.dialog_progress_bar);
            //noinspection ConstantConditions
            progressDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            progressDialog.setCancelable(isTouchOutside);
            progressDialog.setCanceledOnTouchOutside(isCancel);

            if (message != null) {
                TextView textView = progressDialog.findViewById(vn.ticketgo.taipv.ticketgo.R.id.txt_message);
                textView.setText(message);
            }

            if (progressDialog != null)
                progressDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void closeProgressBar() {
        if (progressDialog != null)
            progressDialog.dismiss();
    }

    protected void showConfirmDialog(String title, String message, final ConfirmListener confirmListener) {
        final Dialog dialog = new Dialog(getContext(), vn.ticketgo.taipv.ticketgo.R.style.Theme_Transparent);
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
    protected void startActivity(Class clazz) {
        startActivity(new Intent(getActivity(), clazz));
    }

    protected void startActivity(Class clazz, String key, Object object) {
        Intent intent = new Intent(getActivity(), clazz);
        intent.putExtra(key, (Serializable) object);
        startActivity(intent);
    }

    protected void startActivityForResult(Class clazz, int requestCode) {
        Intent intent = new Intent(getActivity(), clazz);
        startActivityForResult(intent, requestCode);
    }

    protected void startActivityForResult(Class clazz, String key, Object object, int requestCode) {
        Intent intent = new Intent(getActivity(), clazz);
        intent.putExtra(key, (Serializable) object);
        startActivityForResult(intent, requestCode);
    }

    protected void startActivityAndFinish(Class clazz) {
        startActivity(new Intent(getActivity(), clazz));
        getActivity().finish();
    }

    protected void finishActivity() {
        getActivity().finish();
    }

    @Override
    public void onError(int code) {

        closeProgressBar();

        String error = TextUtils.getErrorCode(code);

        if (error != null) {
            showLongToast(error);
        }
    }
}
