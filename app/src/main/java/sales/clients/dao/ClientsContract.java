package sales.clients.dao;

import android.provider.BaseColumns;

public class ClientsContract {

    public static abstract class ClientsEntry implements BaseColumns{
        public static final String TABLE_NAME ="clients";

        public static final String ID = "cli_id";
        public static final String CODE = "cli_code";
        public static final String NAME = "cli_name";
        public static final String FANTASY_NAME = "cli_fantasy_name";
        public static final String LOCATION = "cli_location";
        public static final String POSTAL_CODE = "cli_postal_code";
        public static final String ADDRESS = "cli_address";
        public static final String PHONE = "cli_phone";
        public static final String MAIL = "cli_mail";
        public static final String REGULAR_DISCOUNT = "cli_regular_discount";
        public static final String STATE = "cli_state";
        public static final String TIMESTAMP = "cli_timestamp";
    }
}
