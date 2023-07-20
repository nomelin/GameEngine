package top.nomelin.engine.component;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import top.nomelin.engine.entity.Entity;
import top.nomelin.engine.interfaces.SpriteInterface;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Sprite extends Component implements SpriteInterface {
    private final Table<String, Integer, BufferedImage> images;

    private Map<Integer, BufferedImage> nowImages;
    /**
     * 当前显示的图片组名字
     */
    private String imgState;

    /**
     * 包含的所有图片组名字。
     */
    private Set<String> imgStates;
    /**
     * 当前显示的图片索引
     */
    private int index;
    //图片的宽度和高度
    private int width;
    private int height;
    private String folderPath;//图片所在文件夹路径，如src\\main\\resources\\sprites\\gamer


    /**
     * 刷新速度，每经过speed数目的物理帧刷新一次。
     */
    private int speed;
    private int counter;

    /**
     * @param entity 绑定实体
     * @param id     组件id
     */
    public Sprite(Entity entity, int id, int speed) {
        super(entity, id);
        images = HashBasedTable.create();
        imgStates = new HashSet<>();
        index = 1;
        width = -1;
        height = -1;
        this.speed = speed;
        counter = 1;
    }

    public Sprite(Entity entity, int speed) {
        super(entity);
        images = HashBasedTable.create();
        imgStates = new HashSet<>();
        index = 1;
        width = -1;
        height = -1;
        this.speed = speed;
        counter = 1;
    }

    public Sprite(Entity entity) {
        super(entity);
        images = HashBasedTable.create();
        imgStates = new HashSet<>();
        index = 1;
        width = -1;
        height = -1;
        speed = 1;
        counter = 1;
    }

    @Override
    public boolean update() {
        return false;
    }

    @Override
    protected boolean fixedUpdate() {
        if (counter < speed) {
            counter++;
            return true;
        }
        counter = 1;
        if (nowImages.isEmpty()) {
            LOGGER.warn("图片集合空。精灵id=" + getId());
            return false;
        }
        // 索引加一，如果超过了当前图片集合的大小，就从头开始。index从1开始。
        index++;
        if (index > nowImages.size()) {
            index = 1;
        }

        return true;
    }

    @Override
    protected boolean lateUpdate() {
        return false;
    }
/*
    ///**
     * 加载文件夹
     *
     * @param entityName 文件夹名字（只需要实体名字，也就是最上层的文件夹名字）
     * @return 当前类
     *
    @Deprecated(forRemoval = true)
    private Sprite loadDirectory(String entityName) {
        if (entityName == null) {
            LOGGER.warn("文件夹名字为null");
        }
        imgSrc = "src\\main\\resources\\" + entityName;
        LOGGER.info("读取图片资源，src=" + imgSrc);
        return this;
    }

    /**
     * 加载图片组
     *
     * @param imgState 图片组的名字，比如run，walk等等
     * @param maxNum   本组图片数量(1到maxNum)
     * @return 当前类
     *
    @Deprecated(forRemoval = true)
    public Sprite loadImages(String imgState, int maxNum) {
        try {
            // 获取文件夹中的所有文件
            File folder = new File(imgSrc);
            File[] files = folder.listFiles();
            if (files == null) {
                LOGGER.warn("sprite读取失败，files为null");
                return null;
            }
            List<File> imgs = new ArrayList<>();
            // 遍历文件
            for (File file : files) {
                if (file.getName().startsWith(imgState)) {
                    imgs.add(file);
                }
            }
            imgs.sort(new FileNameComparator());
            for (File file : imgs) {
                BufferedImage image = ImageIO.read(file);
                width = image.getWidth();
                height = image.getHeight();
                //TOO images.put(imgState,)
            }
            LOGGER.info("读取sprite img成功");
            return this;
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.warn("IO异常");
            return null;
        }
    }
*/

    /**
     * 加载所有精灵图片到内存。
     *
     * @param entityName 实体名字用作文件夹名字。src\\main\\resources\\sprites\\“entityName”
     * @return 成功与否
     */
    public boolean loadImages(String entityName) {
        try {
            // 获取文件夹的路径
            URL folderUrl = this.getClass().getResource("/sprites/" + entityName);
            if (folderUrl == null) {
                LOGGER.warn("精灵文件夹路径加载失败。");
                return false;
            }
            // 创建一个File对象来表示文件夹
            File folder = new File(folderUrl.getFile());
            // 获取文件夹中的所有文件，并用FileNameComparator来对它们进行排序
            File[] files = folder.listFiles();
            if (files == null) {
                LOGGER.warn("sprite读取失败，files为null.folderUrl=" + folderUrl, new IllegalArgumentException());
                return false;
            }
            Arrays.sort(files, new FileNameComparator());
            /*LOGGER.info("测试：排序后的文件名");
            Arrays.stream(files).forEach(file -> LOGGER.info(file.getName()));*/
            // 遍历所有文件，如果是png图片，就读取图片，并获取类别名和数字序号
            for (File file : files) {
                if (file.getName().endsWith(".png")) {
                    BufferedImage image = ImageIO.read(file);
                    // 获取文件名中的类别名和数字部分
                    String[] parts = file.getName().split("_");
                    String category = parts[0];
                    imgStates.add(category);
                    int number = Integer.parseInt(parts[1].replace(".png", ""));
                    // 把类别名、数字序号和图片放入Table中
                    images.put(category, number, image);
                }
            }
            LOGGER.info("读取sprite img成功");
            //自动加载一个状态。
            Iterator<String> iterator = imgStates.iterator();
            if (iterator.hasNext()) {
                setState(iterator.next());
            }
            /*测试每种状态的图片数量
            for (String state :imgStates){
                Map<Integer,BufferedImage> map=images.row(state);
                LOGGER.info(state+":"+map.size());
            }*/
            return true;
        } catch (IOException e) {
            LOGGER.warn("IO异常", e);
            e.printStackTrace();
            return false;
        }
    }

    // 定义一个按照不同类别分别对数字排序的比较器
    private static class FileNameComparator implements Comparator<File> {
        public int compare(File f1, File f2) {
            // 获取文件名中的类别名和数字部分
            String[] parts1 = f1.getName().split("_");
            String[] parts2 = f2.getName().split("_");
            String category1 = parts1[0];
            String category2 = parts2[0];
            int number1 = Integer.parseInt(parts1[1].replace(".png", ""));
            int number2 = Integer.parseInt(parts2[1].replace(".png", ""));
            // 先比较类别名，如果相同，再比较数字大小
            int categoryCompare = category1.compareTo(category2);
            if (categoryCompare == 0) {
                return Integer.compare(number1, number2);
            } else {
                return categoryCompare;
            }
        }
    }

    /**
     * @param imgState 设置类别名
     */
    public boolean setState(String imgState) {
        if (imgState == null) {
            LOGGER.warn("精灵id=" + getEntity().getId() + "切换到imgState失败,null");
            return false;
        }
        if (!imgStates.contains(imgState)) {
            LOGGER.warn("精灵id=" + getEntity().getId() + "切换到此imgState失败,不存在的状态：" + imgState);
            return false;
        }
        this.imgState = imgState;
        LOGGER.info("精灵id=" + getEntity().getId() + "切换imgState到：" + imgState);
        nowImages = images.row(imgState);
        index = 1;
        return true;
    }

    public String getImgState() {
        return imgState;
    }

    // 获取当前显示的图片的方法
    public BufferedImage getImage() {
        return nowImages.get(index);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
