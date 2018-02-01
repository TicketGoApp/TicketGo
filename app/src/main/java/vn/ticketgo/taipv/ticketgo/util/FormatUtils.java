package vn.ticketgo.taipv.ticketgo.util;

import java.text.DecimalFormat;

/**
 * Author: Phùng Tài NeverGiveUp
 * Date: 1/18/2018
 * Email: tai97nd@gmail.com
 */

public class FormatUtils {
    private static final FormatUtils instance = new FormatUtils();

    public static FormatUtils getInstance() {
        return instance;
    }
        public String getFormatMoney(int money){
            DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
            return decimalFormat.format(money);
        }
}
