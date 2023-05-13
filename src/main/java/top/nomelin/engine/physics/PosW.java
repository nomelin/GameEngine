package top.nomelin.engine.physics;

/**
 * <p>世界像素坐标,id为只读,xy为了方便不使用setter-getter,默认左上角 0,0</p>
 * @author nomelin
 */
public class PosW {
    /**
     * 实体对象的唯一id
     */
    private final int id;
    public int x;
    public int y;

    public PosW(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public int getId() {
        return id;
    }
}
