package top.nomelin.engine.interfaces;

import java.awt.image.BufferedImage;

public interface SpriteInterface {
    /**
     * 获得精灵当前显示的图片
     * @return 当前图片的BufferedImage
     */
    BufferedImage getImage();

    /**
     * 加载图片到内存，控制器会在初始化时调用一次。
     * @param entityName 实体名，用作最底层文件夹名
     * @return 成功与否
     */
    boolean loadImages(String entityName);
}
