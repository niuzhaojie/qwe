package com.example.niuzhaojie.myapplication.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.example.niuzhaojie.myapplication.R;

/**
 * Created by niuzhaojie on 16/9/19.
 */
public class ScreenActivity extends Activity {
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_screen);

//        float xdpi = getResources().getDisplayMetrics().xdpi;
//        float ydpi = getResources().getDisplayMetrics().ydpi;
//        float density = getResources().getDisplayMetrics().density;
//        float densityDpi = getResources().getDisplayMetrics().densityDpi;
//
//
//        L.e(xdpi + "; " + ydpi + "; " + density + "; " + densityDpi);
//
//
//        Log.e("tag", PhoneUtils.getPhonePixels(this)[0] + "x" + PhoneUtils.getPhonePixels(this)[1]);


        mImageView = (ImageView) findViewById(R.id.img_test);

        int logo_id = R.drawable.testone;
        int dogs_id = R.drawable.testtwo;
        int bdog_id = R.drawable.testthree;

//        testCenter(logo_id);
//        testCenter(dogs_id);
//        testCenter(bdog_id);
//
//
//        testCenterCrop(logo_id);
//        testCenterCrop(dogs_id);
//        testCenterCrop(bdog_id);
//
//
//        testCenterInside(logo_id);
//        testCenterInside(dogs_id);
//        testCenterInside(bdog_id);
//
//
//        testFitCenter(logo_id);
//        testFitCenter(dogs_id);
//        testFitCenter(bdog_id);
//
//
        testFitEnd(logo_id);
//        testFitEnd(dogs_id);
//        testFitEnd(bdog_id);
//
//
//        testFitStart(logo_id);
//        testFitStart(dogs_id);
//        testFitStart(bdog_id);
//
//
//        testFitXY(logo_id);
//        testFitXY(dogs_id);
//        testFitXY(bdog_id);


    }

    /**
     * 测试CENTER属性
     * <p>
     * 将图片显示在ImageView的中心点，不执行任何缩放
     * <p>
     * 1.1 若图片宽和高均小于ImageView控件的宽高，则图片显示在ImageView正中间
     * 1.2 若图片宽或高大于ImageView控件的宽或高,则图片不会完整地显示
     * <p>
     * 即在此属性作用下:
     * 如果图片宽高均小于图片宽高，那么图片可以在ImageView中间完整地以原大小显示
     * 如果图片的宽或者高大于ImageView大小，图片不可完整显示
     */
    private void testCenter(int resourceID) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resourceID);
        System.out.println("---> width=" + bitmap.getWidth() + " ,height=" + bitmap.getHeight());
        mImageView.setScaleType(ScaleType.CENTER);
        mImageView.setImageResource(resourceID);
    }

    /**
     * 测试CENTER_CROP属性
     * <p>
     * 将图片放在ImageView的中心点，然后对图片进行等比例缩放
     * <p>
     * 官方文档描述：
     * Scale the image uniformly (maintain the image's aspect ratio) so that
     * both dimensions (width and height) of the image will be equal to or larger
     * than the corresponding dimension of the view (minus padding).
     * 这段文字值得好好体会一下。
     * 简而言之：
     * 等比例缩放图片使得图片的宽和高均不小于控件对应的宽高
     * <p>
     * 1.1 若图片宽和高均小于ImageView控件的宽高，则等比例放大图片，直至铺满ImageView.
     * 当然这种情况下的等比例放大，就可能导致图片X或Y方向的图片显示不全
     * 1.2 若图片宽或高小于ImageView控件的宽或高,则将图片等比例放大直到铺满ImageView
     * 比如ImageView为750*750,图片为600*800,所以会等比例放大图片，直至图片宽度变为
     * 750.但是这样会造成图片的高度大于了ImageView的高，所以图片的垂直方向显示不全，会被剪裁
     * 1.3 如图片宽和高均大于ImageView控件的宽和高
     * 此时等比例缩小图片，当图片的宽高中任意一值等于控件对应的宽高时停止缩小图片。
     * 在这种情况下就很可能造成：图片显示不完整
     * <p>
     * 所以CENTER_CROP属性的显著特点:
     * 图片会铺满整个ImageView，但图片可能显示不完整
     */
    private void testCenterCrop(int resourceID) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resourceID);
        System.out.println("---> width=" + bitmap.getWidth() + " ,height=" + bitmap.getHeight());
        mImageView.setScaleType(ScaleType.CENTER_CROP);
        mImageView.setImageResource(resourceID);
    }


    /**
     * 测试CENTER_INSIDE属性
     * <p>
     * 将图片放在ImageView的中心点，然后对图片进行等比例缩放
     * <p>
     * 官方文档:
     * Scale the image uniformly (maintain the image's aspect ratio) so that
     * both dimensions (width and height) of the image will be equal to or less
     * than the corresponding dimension of the view (minus padding).
     * <p>
     * 简而言之：
     * 等比例缩放图片使得图片的宽和高均不大于控件对应的宽高
     * <p>
     * 1.1 若图片宽和高均小于ImageView控件的宽高，则将图片放在ImageView中间。
     * 此情况下，图片的四周可见ImageView的背景色
     * 1.2 若图片宽或高大于ImageView控件的宽或高,则将图片等比例缩小直到ImageView可以完整显示该图片
     * 此情况下，图片的上下或者左右可见ImageView的背景色
     * 1.3 如图片宽和高均大于ImageView控件的宽和高
     * 此情况下，等比例缩小图片使得图片的宽和高均不大于控件的宽高。
     * 所以，这也很容易导致图片的上下或者左右可见ImageView的背景色
     * <p>
     * 至此，可见CENTER_INSIDE属性的特点
     * 1 始终会完全显示整张图片
     * 2 很可能导致ImageView控件留白，即露出背景色
     * <p>
     * <p>
     * 到这就可以明显看出CENTER_CROP和CENTER_INSIDE的区别：
     * 1 CENTER_CROP   要求图片的宽高均不小于控件宽高
     * 2 CENTER_INSIDE 要求图片的宽高均不大于控件宽高
     */
    private void testCenterInside(int resourceID) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resourceID);
        System.out.println("---> width=" + bitmap.getWidth() + " ,height=" + bitmap.getHeight());
        mImageView.setScaleType(ScaleType.CENTER_INSIDE);
        mImageView.setImageResource(resourceID);
    }

    /**
     * 测试FIT_CENTER属性
     * <p>
     * 将图片放在ImageView的中心点，然后对图片进行等比例缩放
     * <p>
     * 官方文档：
     * Scale the image using CENTER.
     * CENTER:
     * Compute a scale that will maintain the original src aspect ratio,
     * but will also ensure that src fits entirely inside dst.
     * At least one axis (X or Y) will fit exactly. The result is centered inside dst.
     * <p>
     * 在理解了CENTER_CROP和CENTER_INSIDE之后再看FIT_CENTER就会好理解一些
     * <p>
     * 将图片放在ImageView的中心点，对图片进行等比例缩放从而完整地显示图片
     * <p>
     * <p>
     * <p>
     * 1.1 若图片宽和高均小于ImageView控件的宽高，则等比例放大图片
     * 直到图片的宽高中任意一值等于控件的宽或者高
     * 此情况下，图片的上下或者左右可见ImageView的背景色
     * <p>
     * <p>
     * 1.2  若图片宽和高均大于ImageView控件的宽和高，则等比例缩小图片
     * 直到图片的宽高中任意一值等于控件的宽或者高
     * 所以，这也很容易导致图片的上下或者左右可见ImageView的背景色
     * <p>
     * 1.3  若图片宽或高大于ImageView控件的宽或高
     * 这时候是该执行放大呢？还是缩小呢？
     * 其实，此时是有一个原则的：完整地显示图片。
     * 就像文档中所说：ensure that src fits entirely inside dst
     * 所以，此时会对图片进行缩放，直到图片的宽高均不大于控件的宽高
     * <p>
     * <p>
     * 所以，在使用FIT_CENTER时可以完整地显示图片，而且它有一个显著的特点：
     * 它使得图片的宽高中至少有一个值恰好等于控件的宽或者高。
     * 这一点也是它和CENTER_INSIDE属性的主要区别
     */
    private void testFitCenter(int resourceID) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resourceID);
        System.out.println("---> width=" + bitmap.getWidth() + " ,height=" + bitmap.getHeight());
        mImageView.setScaleType(ScaleType.FIT_CENTER);
        mImageView.setImageResource(resourceID);
    }


    /**
     * 测试FIT_END属性
     * <p>
     * FIT_END和FIT_CENTER属性非常类似
     * 差别在于，FIT_END最终会将缩放后的图片放在ImageView控件的右下角
     */
    private void testFitEnd(int resourceID) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resourceID);
        System.out.println("---> width=" + bitmap.getWidth() + " ,height=" + bitmap.getHeight());
        mImageView.setScaleType(ScaleType.FIT_END);
        mImageView.setImageResource(resourceID);
    }

    /**
     * 测试FIT_START属性
     * <p>
     * FIT_START和FIT_CENTER属性非常类似
     * 差别在于，FIT_END最终会将缩放后的图片放在ImageView控件的左上角
     */
    private void testFitStart(int resourceID) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resourceID);
        System.out.println("---> width=" + bitmap.getWidth() + " ,height=" + bitmap.getHeight());
        mImageView.setScaleType(ScaleType.FIT_START);
        mImageView.setImageResource(resourceID);
    }

    /**
     * 测试FIT_XY属性
     * <p>
     * 不按照比例缩放图片，从而使图片铺满整个ImageView。
     * 在此情况下虽然可以完整地显示图片，但是图片会被拉伸导致失真
     */
    private void testFitXY(int resourceID) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resourceID);
        System.out.println("---> width=" + bitmap.getWidth() + " ,height=" + bitmap.getHeight());
        mImageView.setScaleType(ScaleType.FIT_XY);
        mImageView.setImageResource(resourceID);
    }


}
