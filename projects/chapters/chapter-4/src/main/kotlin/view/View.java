package view;

public interface View {
    interface OnClickListener {
        void onClick(View view);
    }

    void setOnClickListener(OnClickListener listener);
}
