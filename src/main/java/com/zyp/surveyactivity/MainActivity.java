package com.zyp.surveyactivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zyp on 2018/6/20.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout mItem1;
    private LinearLayout mItem2;
    private LinearLayout mItem3;
    private LinearLayout mItem4;
    private ImageView    mImage1;
    private ImageView    mImage2;
    private ImageView    mImage3;
    private ImageView    mImage4;
    private TextView     mText1;
    private TextView     mText2;
    private TextView     mText3;
    private TextView     mText4;

    private List<SurveyBean> beanAar = new ArrayList<>();
    private TextView title;
    private TextView pre;
    private Button   next;

    private ImageView[] mImageView;

    private Handler handler = new Handler();

    private static int possion = 0;
    private static int score   = 0;

    private int[] possionAar = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
    private Button    submit;
    private TextView  tv_page;
    private ImageView mIv_back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = (TextView) findViewById(R.id.title);

        pre = (TextView) findViewById(R.id.pre);
        submit = (Button) findViewById(R.id.submit);
        pre.setOnClickListener(this);
        submit.setOnClickListener(this);

        mItem1 = (LinearLayout) findViewById(R.id.check_item1);
        mItem2 = (LinearLayout) findViewById(R.id.check_item2);
        mItem3 = (LinearLayout) findViewById(R.id.check_item3);
        mItem4 = (LinearLayout) findViewById(R.id.check_item4);

        mImage1 = (ImageView) findViewById(R.id.check_image1);
        mImage2 = (ImageView) findViewById(R.id.check_image2);
        mImage3 = (ImageView) findViewById(R.id.check_image3);
        mImage4 = (ImageView) findViewById(R.id.check_image4);
        mIv_back = (ImageView) findViewById(R.id.iv_back);

        mImageView = new ImageView[]{mImage1, mImage2, mImage3, mImage4};

        mText1 = (TextView) findViewById(R.id.check_text1);
        mText2 = (TextView) findViewById(R.id.check_text2);
        mText3 = (TextView) findViewById(R.id.check_text3);
        mText4 = (TextView) findViewById(R.id.check_text4);
        tv_page = (TextView) findViewById(R.id.tv_page);

        mItem1.setOnClickListener(this);
        mItem2.setOnClickListener(this);
        mItem3.setOnClickListener(this);
        mItem4.setOnClickListener(this);
        mIv_back.setOnClickListener(this);

        possion = 0;
        init();
        setData();
    }


    public void init() {
        beanAar.add(new SurveyBean("1、您的年龄：", new String[]{"18--30岁", "31-45岁", "46-55岁", "55岁以上"}));
        beanAar.add(new SurveyBean("2、您的身体健康状况：", new String[]{"非常好", "好", "一般", "差"}));
        beanAar.add(new SurveyBean("3、您的投资年限", new String[]{"10年以上", "5-10年", "1-5年", "1年内"}));
        beanAar.add(new SurveyBean("4、您的投资经验可以被概括为：", new String[]{"丰富：是一位积极和有经验的证券投资者，并倾向于自己作出投资决定", "一般：具有一定的证券投资经验，需要进一步的指导", "有限：有过购买国债，货币型基金等保本型金融产品投资经验", "无：除银行活期和投定期储蓄存款外，基本没有其他资经验"}));
        beanAar.add(new SurveyBean("5、您曾经或正在做的投资产品（若有多项请选风险最大的一项）：", new String[]{"期货、权证", "股票", "债券、基金", "无"}));
        beanAar.add(new SurveyBean("6、今后5年内您的预期收入：", new String[]{"预期收入将逐渐增加", "预期收入将保持稳定", "预期收入将不断减少"}));
        beanAar.add(new SurveyBean("7、以下哪项描述最符合您的投资态度？", new String[]{"希望赚取高回报，愿意为此承担较大本金损失", "寻求资金的较高收益和成长性，愿意为此承担有限本金损失", "保守投资，不希望本金损失，愿意承担一定幅度的收益波动", "厌恶风险，不希望本金损失，希望获得稳定回报"}));
        beanAar.add(new SurveyBean("8、您用于投资的资金在您的总资产中占比大致是多少（除自用和经营性财产外）？", new String[]{"大于50％", "30％～50％", "10％～30％", "小于10％"}));
        beanAar.add(new SurveyBean("9、您在投资中能够接受的最大本金损失大致是多少？", new String[]{"最大本金亏损50%以上", "最大本金亏损20%～50%", "最大本金亏损5%～20%", "最大本金亏损5%以内"}));
        beanAar.add(new SurveyBean("10.您的投资目的是？", new String[]{"关心长期的高回报，能够接受短期的资产价值波动", "倾向长期的成长，较少关心短期的回报以及波动", "希望投资能获得一定的增值，同时获得波动适度的年回报", "只想确保资产的安全性，同时希望能够得到固定的收益"}));
    }


    private void setData() {
        title.setText(beanAar.get(possion).getTitle());
        mText1.setText(beanAar.get(possion).getSel()[0]);
        mText2.setText(beanAar.get(possion).getSel()[1]);
        mText3.setText(beanAar.get(possion).getSel()[2]);
        tv_page.setText((possion + 1) + "/10");
        if (beanAar.get(possion).getSel().length < 4) {
            mItem4.setVisibility(View.GONE);
        } else {
            mItem4.setVisibility(View.VISIBLE);
            mText4.setText(beanAar.get(possion).getSel()[3]);
        }
    }

    /**
     * 点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.check_item1:
                possionAar[possion] = 0;//记录当前的选择的下标
                hide(0);//只让当前状态为选择
                next();//执行下一步
                break;
            case R.id.check_item2:
                possionAar[possion] = 1;
                hide(1);
                next();
                break;
            case R.id.check_item3:
                possionAar[possion] = 2;
                hide(2);
                next();
                break;
            case R.id.check_item4:
                possionAar[possion] = 3;
                hide(3);
                next();
                break;
            case R.id.pre:
                pre();
                break;
            case R.id.submit:
                Toast.makeText(MainActivity.this, "点击了提交", Toast.LENGTH_SHORT).show();
                //
                Intent intent = new Intent(MainActivity.this, SurveyActivityafter.class);
                intent.putExtra("score", score);
                startActivity(intent);

                finish();
                break;
            case R.id.iv_back:
                exitSurvey();
                break;
        }
    }

    private void exitSurvey() {
        AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this);
        b.setTitle("网汇贷温馨提示：")
                .setMessage("本次投资人风险评估未完成，确定退出？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //                        startActivity(new Intent(MainActivity.this, SurveyActivityafter.class));
                        //                        finish();
                        Toast.makeText(MainActivity.this, "点击确定了", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setCancelable(false)
                .show();
    }

    /**
     * 下一步
     */
    private void next() {
        possion += 1;
        if (possion > 9) {
            possion = 9;
            score = 0;
            addScore();
            submit.setVisibility(View.VISIBLE);
            return;
        }
        handler.postDelayed(new Runnable() {

            public void run() {
                hide();
                setData();
                // handler.removeCallbacksAndMessages(null);
            }
        }, 300);


    }

    /**
     * 上一步
     */
    private void pre() {
        possion -= 1;
        if (possion < 0) {
            possion = 0;
        }
        if (possion < 9) {
            submit.setVisibility(View.INVISIBLE);
        }
        hide(possionAar[possion]);
        setData();
    }


    public void hide() {
        for (int i = 0; i < mImageView.length; i++) {
            mImageView[i].setImageResource(R.drawable.button_white);
        }
    }

    public void hide(int k) {
        for (int i = 0; i < mImageView.length; i++) {
            if (i == k) {
                mImageView[i].setImageResource(R.drawable.button_orange_1);
            } else {
                mImageView[i].setImageResource(R.drawable.button_white);
            }
        }
    }

    /**
     * 计算分数
     */
    public void addScore() {
        for (int i = 0; i < possionAar.length; i++) {
            if (possionAar[i] == -1) {
                score = 0;
                return;
            }
        }
        for (int i = 0; i < possionAar.length; i++) {
            switch (possionAar[i]) {
                case 0:
                    score += 10;
                    break;
                case 1:
                    score += 8;
                    break;
                case 2:
                    score += 5;
                    break;
                case 3:
                    score += 3;
                    break;
            }
        }

    }

    @Override
    public void onBackPressed() {
        exitSurvey();
    }
}
