package form.patterns.immut;

public final class Message implements Cloneable {
    private final String content;
    private final int length;

    public Message(String content, int length) {
        this.content = content;
        this.length = length;
    }

    public String getContent() {
        return content;
    }

    public int getLength() {
        return length;
    }

    @Override
    protected Object clone() {
        return new Message(this.content, this.length);
    }
}
