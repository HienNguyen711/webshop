package project.webshop.utils;

public class Constants {
    public static final String PASSWORD_RESET_GREETING = "Hello, ";
    // Password
    public static final String PASSWORD_RESET_SUBJECT = "Reset password";
    public static final String PASSWORD_RESET_TEXT = "!\n\n" + "To reset your password click the link below: "
            + "\n\n";
    public static final String PASSWORD_RESET_NOTE = "\n\n" + "If you have received this message by mistake, please just ignore it."
            +"\n\n";
    public static final String PASSWORD_RESET_AUTHOR = "Yours truly," + "\r\n" + "Hien";
    public static final String MESSAGE_EXIST = " is already exist.";
    // Invalid input data
    public static final String MESSAGE_INVALID_DATA = "Invalid input data ( == null).";
    // user is not found
    public static final String MESSAGE_NOT_FOUND_USER = "User is not found.";
    public static final String MESSAGE_INVALID_USER = "User is invalid";
    // Token
    public static final String MESSAGE_NOT_CONSTRUCTED_TOKEN = "Token is not constructed.";
    public static final String NOT_CONSTRUCTED_MESSAGE = "Message is not constructed.";
    public static final String MESSAGE_NOT_VALID_TOKEN = "Token is not valid. Please request code generation again.";
    public static final int AMOUNT_MINUTES_VALID_TOKEN = 10;
    // role
    public static final String MESSAGE_NOT_FOUND_ROLE = "Role is not found.";
    public static final String MESSAGE_NOT_VALID_ROLE = "Role is not valid.";
    // Product
    public static final String MESSAGE_NOT_FOUND_PRODUCT = "Product is not found.";
    // Category
    public static final String MESSAGE_NOT_FOUND_CATEGORY = "Category is not found.";
    // Child
    public static final String MESSAGE_NOT_FOUND_CHILD = "Child is not found.";
    // Parent
    public static final String MESSAGE_NOT_FOUND_PARENT = "Parent is not found.";
    // Count
    public static final String MESSAGE_NOT_VALID_COUNT = "Count is not valid.";
    // Review
    public static final String MESSAGE_NOT_FOUND_REVIEW = "Review is not found.";
    // Shopping cart
    public static final String MESSAGE_EMPTY_CART = "Shopping cart is empty.";
    // Order
    public static final String MESSAGE_NOT_FOUND_ORDER = "Order is not found.";
    // Mail SMTP
    public static final String MESSAGE_MAIL_ERROR = "Sending mail has error";
}
