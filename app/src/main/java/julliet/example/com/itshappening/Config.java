package julliet.example.com.itshappening;

public class Config {

    //Address of our scripts of the CRUD
    public static final String URL_ADD="http://10.0.0.64:80/android_login_api/addEmp.php";
    public static final String URL_GET_ALL = "http://10.0.0.64:80/android_login_api/getAllEmp.php";
    public static final String URL_GET_ART = "http://10.0.0.64:80/android_login_api/getArts.php";
    public static final String URL_GET_EMP = "http://10.0.0.64:80/android_login_api/getEmp.php";
    public static final String URL_UPDATE_EMP = "http://10.0.0.64:80/android_login_api/updateEmp.php";
    public static final String URL_DELETE_EMP = "http://10.0.0.64:80/android_login_api/deleteEmp.php";

    //Keys that will be used to send the request to php scripts
    public static final String KEY_EMP_ID = "id";
    public static final String KEY_EMP_NAME = "name";
    public static final String KEY_EMP_PRICE = "price";
    public static final String KEY_EMP_DESCRIPTION = "description";
    public static final String KEY_EMP_CATEGORY = "category";
    public static final String KEY_EMP_VENUE = "venue";
    public static final String KEY_EMP_IMAGE = "image";

    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID = "id";
    public static final String TAG_NAME = "name";
    public static final String TAG_PRICE = "price";
    public static final String TAG_DESCRIPTION = "description";
    public static final String TAG_CATEGORY = "category";
    public static final String TAG_VENUE = "venue";
    public static final String TAG_IMAGE = "image";
    public static final String USER_ID = "user_id";

    //employee id to pass with intent
    public static final String EMP_ID = "emp_id";
        public static final String EMAIL = "email";
    public static final String EMP_NAME = "emp_name";
    public static final String EMP_PRICE = "emp_price";
    public static final String EMP_DESCRIPTION = "emp_description";
    public static final String EMP_CATEGORY = "emp_category";
    public static final String EMP_VENUE = "emp_venue";
    public static final String EMP_IMAGE = "emp_image";

}