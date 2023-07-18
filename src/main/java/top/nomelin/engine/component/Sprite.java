package top.nomelin.engine.component;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import top.nomelin.engine.entity.Entity;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Sprite extends Component {
    private Table<String, Integer, BufferedImage> images;

    private Map<Integer, BufferedImage> nowImages;
    /**
     * 当前显示的图片组名字
     */
    private String imgState;
    //当前显示的图片索引
    private int index;
    //图片的宽度和高度
    private int width;
    private int height;
    private String imgSrc;//图片所在文件夹路径，如src\\main\\resources\\sprites\\gamer

    /**
     * @param entity 绑定实体
     * @param id     组件id
     */
    public Sprite(Entity entity, int id) {
        super(entity, id);
        images = HashBasedTable.create();
        index = 1;
        width = -1;
        height = -1;
    }

    @Override
    public boolean updateFunc() {
        // 如果图片集合不为空，就更新索引
        if (!nowImages.isEmpty()) {
            // 索引加一，如果超过了集合的大小，就从头开始
            index++;
            if (index > images.size()) {
                index = 1;
            }
        }
        return true;
    }

    @Override
    protected boolean fixedUpdateFunc() {
        return false;
    }

    @Override
    protected boolean lateUpdateFunc() {
        return false;
    }

    /**
     * 加载文件夹
     *
     * @param entityName 文件夹名字（只需要实体名字，也就是最上层的文件夹名字）
     * @return 当前类
     */
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
     */
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
                //TODO images.put(imgState,)
            }
            LOGGER.info("读取sprite img成功");
            return this;
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.warn("IO异常");
            return null;
        }
    }

    public boolean loadImages(String entityName) {
        try {
            // 获取目标文件夹的路径
            imgSrc = "src\\main\\resources\\sprites\\" + entityName;//TODO 要使用maven相对路径
            // 创建一个File对象来表示文件夹
            File folder = new File(imgSrc);
            // 获取文件夹中的所有文件，并用FileNameComparator来对它们进行排序
            File[] files = folder.listFiles();
            if (files == null) {
                LOGGER.warn("sprite读取失败，files为null", new IllegalArgumentException());
                return false;
            }
            Arrays.sort(files, new FileNameComparator());
            System.out.println("测试：排序后的文件名");
            Arrays.stream(files).forEach(file -> System.out.println(file.getName()));
            // 遍历所有文件，如果是png图片，就读取图片，并获取类别名和数字序号
            for (File file : files) {
                if (file.getName().endsWith(".png")) {
                    BufferedImage image = ImageIO.read(file);
                    // 获取文件名中的类别名和数字部分
                    String[] parts = file.getName().split("_");
                    String category = parts[0];
                    int number = Integer.parseInt(parts[1].replace(".png", ""));
                    // 把类别名、数字序号和图片放入Table中
                    images.put(category, number, image);
                }
            }
            LOGGER.info("读取sprite img成功");
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
     * @param imgState 设置类别
     */
    public boolean setState(String imgState) {
        if (imgState == null) {
            LOGGER.warn("组件id=" + getEntity().getId() + "切换到此imgState失败：" + imgState);
            return false;
        }
        this.imgState = imgState;
        LOGGER.info("组件id=" + getEntity().getId() + "切换imgState到" + imgState);
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
}
