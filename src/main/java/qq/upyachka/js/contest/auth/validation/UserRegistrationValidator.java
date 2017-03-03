package qq.upyachka.js.contest.auth.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import qq.upyachka.js.contest.auth.service.RegistrationService;
import qq.upyachka.js.contest.core.model.User;

/**
 * Validator for {@link User}.
 * Created on 24.02.17.
 * @author upyachka.
 */
@Component
public class UserRegistrationValidator implements Validator {

    /** Minimum length for username. */
    private static final int USERNAME_MIN_LENGTH = 3;

    /** Maximum length for username. */
    private static final int USERNAME_MAX_LENGTH = 12;

    /** Minimum length for password. */
    private static final int PASSWORD_MIN_LENGTH = 4;

    /** Maximum length for password. */
    private static final int PASSWORD_MAX_LENGTH = 32;

    /** ID of username field. */
    private static final String USERNAME_FIELD = "username";

    /** ID of password field. */
    private static final String PASSWORD_FIELD = "password";

    /** Prefix for keys in validation properties. */
    private static final String VALIDATION_PROPERTIES_PREFIX = "validation.";

    /** Key in validation properties for username length validation error. */
    private static final String USER_NAME_LENGTH_ERROR = VALIDATION_PROPERTIES_PREFIX + "length";

    /** Key in validation properties for already registered username validation error. */
    private static final String USER_NAME_REGISTERED_ERROR = VALIDATION_PROPERTIES_PREFIX + "already.registered";

    /** Key in validation properties for password length validation error. */
    private static final String USER_PASS_LENGTH_ERROR = VALIDATION_PROPERTIES_PREFIX + "length";

    /** Key in validation properties for empty mandatory field validation error. */
    private static final String NOT_EMPTY_ERROR = VALIDATION_PROPERTIES_PREFIX + "mandatory";

    /** Service to access user registration. */
    @Autowired
    private RegistrationService service;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User)target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, USERNAME_FIELD, NOT_EMPTY_ERROR);
        final String username = user.getUsername();
        final int usernameLength = user.getUsername().length();
        if (usernameLength < USERNAME_MIN_LENGTH || usernameLength > USERNAME_MAX_LENGTH) {
            errors.rejectValue(USERNAME_FIELD, USER_NAME_LENGTH_ERROR);
        }
        if (service.isRegistered(username)) {
            errors.rejectValue(USERNAME_FIELD, USER_NAME_REGISTERED_ERROR);
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, PASSWORD_FIELD, NOT_EMPTY_ERROR);
        final int passwordLength = user.getPassword().length();
        if (passwordLength < PASSWORD_MIN_LENGTH || passwordLength > PASSWORD_MAX_LENGTH) {
            errors.rejectValue(PASSWORD_FIELD, USER_PASS_LENGTH_ERROR);
        }
    }
}
