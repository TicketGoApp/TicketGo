package vn.ticketgo.taipv.sdk.callbacks;

/**
 * Author: Lê Công Long Vũ
 * Date: 10/12/2017
 * Email: leconglongvu@gmail.com
 */
public abstract class AbsICmd implements Icmd {

    @Override
    public void run() {
        try {
            getFB();
            invoke();
        } catch (Exception e) {
            exception(e.getMessage());
        }
    }
    protected abstract void getFB();
    protected abstract void invoke();
    protected abstract void exception(String message);
}