package vn.ticketgo.taipv.ticketgo.presenter.HomePre;

import android.text.TextUtils;

import com.blankj.utilcode.util.RegexUtils;

import vn.ticketgo.taipv.ticketgo.util.RegexUtil;
import vn.ticketgo.taipv.ticketgo.view.activity.inf.Home.IDialogAddressView;

/**
 * Author: Phùng Tài NeverGiveUp
 * Date: 1/31/2018
 * Email: tai97nd@gmail.com
 */

public class DialogAddressPre implements DialogAddressLogic {
    IDialogAddressView iDialogAddressView;

    public DialogAddressPre(IDialogAddressView iDialogAddressView) {
        this.iDialogAddressView = iDialogAddressView;
    }

    @Override
    public void checkSave(String name, String phone, String email, String city, String address) {
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(phone) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(city) && !TextUtils.isEmpty(address)) {
            if (RegexUtil.isEmailValid(email) && RegexUtil.isFullNameValid(name) && RegexUtil.isPhoneNumberValid(phone)) {
                    iDialogAddressView.onSuccess();
            }else {
                iDialogAddressView.inputMatch();
            }
        }else {
                iDialogAddressView.inputEmpty();
        }

    }
}
