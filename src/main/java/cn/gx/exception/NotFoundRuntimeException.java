package cn.gx.exception;

/**
 * Created by guanxine on 16-4-17.
 */
public class NotFoundRuntimeException extends RuntimeException{

    public NotFoundRuntimeException(String msg) {
        super(msg);
    }

    public NotFoundRuntimeException(Integer id) {
        super(String.format("课程编号：%d 不存在.",id));
    }
}
