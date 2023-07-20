package top.nomelin.engine.component;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import top.nomelin.engine.entity.Entity;
import top.nomelin.engine.interfaces.Input;
import top.nomelin.engine.interfaces.SpriteInterface;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * 最简单的sprite，只有一张图片。src\\main\\resources\\sprites\\【实体名】\\default.png
 */
public class SimpleSprite extends Component implements SpriteInterface {
    private BufferedImage image;

    public static final Logger LOGGER= LogManager.getLogger(SimpleSprite.class);

    public SimpleSprite(Entity entity) {
        super(entity);
    }

    @Override
    protected boolean fixedUpdate() {
        return true;
    }

    @Override
    protected boolean update() {
        return true;
    }

    @Override
    protected boolean lateUpdate() {
        return true;
    }

    @Override
    public BufferedImage getImage() {
        if(image==null){
            LOGGER.warn("SimpleSprite没有加载图片,返回默认图片，id="+getId());
            InputStream is=this.getClass().getResourceAsStream("/sprites/default/default.png");
            if(is==null){
                LOGGER.warn("SimpleSprite返回默认图片失败，id="+getId());
                return null;
            }
            try {
                return ImageIO.read(is);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return image;
    }

    @Override
    public boolean loadImages(String entityName) {
        InputStream is=this.getClass().getResourceAsStream("/sprites/"+entityName+"/default.png");
        if(is==null){
            LOGGER.info("SimpleSprite加载图片失败，id="+getId()+"名字="+entityName);
            return false;
        }
        try {
            image=ImageIO.read(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
