package cn.gx.exception;

/**
 * Created by guanxine on 16-4-17.
 */
public class NotFoundException extends RuntimeException{

    public NotFoundException(String msg) {
        super(msg);
    }

    public NotFoundException(Integer id) {
        super(String.format("课程编号：%d 不存在.",id));
    }
}
