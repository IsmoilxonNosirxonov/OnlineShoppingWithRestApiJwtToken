package uz.in.onlineshoppingwithrestapijwttoken.exception;

public class BalanceNotEnoughException extends RuntimeException{
    public BalanceNotEnoughException(String message) {
        super(message);
    }
}
