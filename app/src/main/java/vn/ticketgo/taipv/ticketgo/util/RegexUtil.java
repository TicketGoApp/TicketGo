package vn.ticketgo.taipv.ticketgo.util;

import java.util.regex.Pattern;

/**
 * Created by Anubis on 1/17/2017.
 */

public class RegexUtil {

    //  private static final String USER_NAME_REGEX = "[A-Za-z]+(\\w|[\\s\\.])*[\\w]+";
    private static final String USER_NAME_REGEX = "^[a-zA-Z0-9._-]{4,25}$";
    private static final String FULL_NAME_REGEX = "[a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]+([\\wÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]|[\\s\\.])*[\\wÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]+";
    // private static final String EMAIL_REGEX = "^[\\w\\.\\-]+@([a-zA-Z0-9\\-]+\\.)+[a-zA-Z]{2,6}$";
    private static final String EMAIL_REGEX = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[^a-zA-Z0-9\\s]).{6,20}";
    private static final String MOBILE_PHONE_VN_REGEX = "(?:\\+84|\\(\\+84\\)|0)(?:1\\d{9}|9\\d{8})";
    private static final String BIEN_SO_REGEX = "[1-9][0-9][A-Za-z]{1,2}[- ]?(\\d{4,5}|\\d{3}\\.?\\d{2})";

    public static final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(EMAIL_REGEX);
    public static final Pattern USER_NAME_PATTERN = Pattern.compile(USER_NAME_REGEX);
    public static final Pattern FULL_NAME_PATTERN = Pattern.compile(FULL_NAME_REGEX);
    public static final Pattern MOBILE_PHONE_VN_REGEX_PATTERN = Pattern.compile(MOBILE_PHONE_VN_REGEX);
    public static final Pattern BIEN_SO_REGEX_PATTERN = Pattern.compile(BIEN_SO_REGEX);
    public static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);

    public static boolean isUserNameValid(String username) {
        return USER_NAME_PATTERN.matcher(username).matches();
    }

    public static boolean isEmailValid(String email) {
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
    }

    public static boolean isFullNameValid(String fullname) {
        return FULL_NAME_PATTERN.matcher(fullname).matches();
    }

    public static boolean isPasswordValid(String password) {

        return password.length() <= 20 && password.length() >= 6;
    }

    public static boolean isPhoneNumberValid(String phone) {

        return MOBILE_PHONE_VN_REGEX_PATTERN.matcher(phone).matches();
    }

    public static boolean isBienSoRegex(String phone) {
        return BIEN_SO_REGEX_PATTERN.matcher(phone).matches();
    }

}
