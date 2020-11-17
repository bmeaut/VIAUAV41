
public abstract class Task<T> {
    public interface OnCompleteListener<T> {
        void onComplete(T result);

        void onError(Throwable exception);
    }

    protected OnCompleteListener<T> listener;

    public void setListener(OnCompleteListener<T> listener) {
        this.listener = listener;
    }
}
