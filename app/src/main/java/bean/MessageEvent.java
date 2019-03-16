package bean;

public class MessageEvent {

    //eventbus事件实体类

    private int textSize;//需要改变字体的大小

    private boolean isCorrect;//用户是否答对

    public MessageEvent(int textSize) {
        this.textSize = textSize;
    }

    public int getTextSize() {
        return textSize;
    }


    public MessageEvent(boolean isCorrect) {
        this.isCorrect=isCorrect;
    }

    public boolean getIsCorrect() {
        return isCorrect;
    }




}


