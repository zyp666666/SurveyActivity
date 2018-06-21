package com.zyp.surveyactivity;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zyp on 2018/6/20.
 */
public class ColorProgressBar extends View {

    private float width;//布局宽度
    private float height;//布局高度


    //最大进度值0-100
    private float maxProgress = 100;
    //尺寸转换比例
    private float k;

    private float curProgress   = 0;
    private float startProgress = 0;

    private float currentProgress = 0;

    //画笔
    private Paint                backgroundPaint;//底部背景的画笔
    private Paint                progressPaint;//进度条的画笔
    private Paint                textPaint;
    private Paint                scalePaint;
    //抗锯齿
    private PaintFlagsDrawFilter mDrawFilter;
    //背景（没使用）
    private RectF                bgRect;
    //渐变颜色选择
    private int[] colors = new int[]{Color.GREEN, Color.YELLOW, Color.RED, Color.RED};

    private ValueAnimator progressAnimator;//执行动画
    private float         curValues;//执行动画的动态值


    public ColorProgressBar(Context context) {
        super(context, null);
        initView();
    }

    public ColorProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        initCofig(context, attrs);
        initView();
    }

    public ColorProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initCofig(context, attrs);
        initView();
    }

    /**
     * 初始化布局配置
     *
     * @param context
     * @param attrs
     */
    private void initCofig(Context context, AttributeSet attrs) {

    }

    /**
     * 初始化视图
     */
    private void initView() {
        //矩阵区域
       /* bgRect = new RectF();
        bgRect.top = 100;
        bgRect.left = 100;
        bgRect.right = 100;
        bgRect.bottom = 100;*/


        backgroundPaint = new Paint();
        backgroundPaint.setAntiAlias(true);
        backgroundPaint.setStyle(Paint.Style.FILL);
        backgroundPaint.setColor(Color.GRAY);


        progressPaint = new Paint();
        progressPaint.setAntiAlias(true);
        progressPaint.setStyle(Paint.Style.FILL);
        progressPaint.setStrokeCap(Paint.Cap.ROUND);
        progressPaint.setStrokeWidth(dipToPx(5));
        progressPaint.setColor(Color.GREEN);


        textPaint = new Paint();


        scalePaint = new Paint();

        mDrawFilter = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = dipToPx(350);
        this.width = width;
        int height = dipToPx(20);
        setMeasuredDimension(width, height);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.setDrawFilter(mDrawFilter);

        //left, top, right, bottom, Paint
        canvas.drawRect(0, 0, width, 50, backgroundPaint);// 长方形  


        // 一个材质,打造出一个线性梯度沿著一条线。
        Shader mShader = new LinearGradient(0, 0, width, 50,
                colors, null, Shader.TileMode.REPEAT);
        progressPaint.setShader(mShader);

        canvas.drawRect(0, 0, curValues, 50, progressPaint);

        invalidate();
    }


    /**
     * 设置最大值
     *
     * @param maxProgress
     */
    public void setMaxProgress(float maxProgress) {
        this.maxProgress = maxProgress;
        k = (width - dipToPx(8)) / maxProgress;//每一个进度对应的像素值
    }

    /**
     * 设置当前值
     *
     * @param currentValue
     */
    public void setCurrentProgress(float currentValue) {

        int b = (int) (currentValue / 20);
        if (currentValue >= maxProgress) {
            this.currentProgress = width;
        } else if (currentValue <= 0) {
            this.currentProgress = 0;
        } else {
            this.currentProgress = currentValue * k - b * dipToPx((float) 5.90);
        }
        setAnimation(startProgress, currentProgress);
    }


    /**
     * 为进度设置动画
     *
     * @param last
     * @param current
     */
    private void setAnimation(float last, float current) {
        progressAnimator = ValueAnimator.ofFloat(last, currentProgress);
        progressAnimator.setDuration(1500);
        progressAnimator.setTarget(currentProgress);
        progressAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                curValues = (float) animation.getAnimatedValue();
            }
        });
        progressAnimator.start();
    }

    /**
     * dip 转换成px
     *
     * @param dip
     * @return
     */
    private int dipToPx(float dip) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * density + 0.5f * (dip >= 0 ? 1 : -1));
    }
}
