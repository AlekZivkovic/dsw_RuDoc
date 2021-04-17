package app.ErrorHandler;

import app.core.PublisherString;

public interface ErrorHandlerInterface extends PublisherString {

     void throwException(String text);

}
