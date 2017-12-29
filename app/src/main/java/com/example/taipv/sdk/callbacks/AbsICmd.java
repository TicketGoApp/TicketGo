package com.example.taipv.sdk.callbacks;

/**
 * Created by taipv on 12/26/2017.
 */

public abstract class AbsICmd implements Icmd{
    @Override
    public void run() {
    try {
        invoke();
    }catch (Exception e){
        exception(e.getMessage());
    }
    }
    protected abstract void invoke();
    protected abstract void exception(String message);

}
