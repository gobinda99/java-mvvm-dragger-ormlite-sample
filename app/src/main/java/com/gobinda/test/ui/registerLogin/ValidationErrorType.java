package com.gobinda.test.ui.registerLogin;

import com.gobinda.test.R;

/**
 * Validation Error types
 */
public enum ValidationErrorType {
    EMAIL_EMPTY {
        @Override
        public int getMessageResId() {
            return R.string.email_empty;
        }
    },
    EMAIL_INCORRECT {
        @Override
        public int getMessageResId() {
            return R.string.email_incorrect;
        }
    },
    EMAIL_ALREADY_EXIST {
        @Override
        public int getMessageResId() {
            return R.string.email_already_registered;
        }
    },
    EMAIL_NOT_EXIST {
        @Override
        public int getMessageResId() {
            return R.string.email_not_exit;
        }
    },
    PASSWORD_EMPTY {
        @Override
        public int getMessageResId() {
            return R.string.password_empty;
        }
    },
    PASSWORD_INCORRECT {
        @Override
        public int getMessageResId() {
            return R.string.password_incorrect;
        }
    },
    PASSWORD_NOT_MATCHED {
        @Override
        public int getMessageResId() {
            return R.string.password_not_match;
        }
    };

    /**
     * Message resource Id of the error type
     * @return
     */
    public abstract int getMessageResId();

}
