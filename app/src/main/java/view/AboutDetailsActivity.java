package view;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abc.chinesemedicine.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.ButterKnife;
import customview.MyTitleBar;

public class AboutDetailsActivity extends AppCompatActivity implements View.OnClickListener {


    private View contentView;

    private TextView tv_mail;
    private TextView tv_qq;
    private TextView tv_weiBo;

    private Button btn_weiXinPay;
    private Button btn_aliPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater mInflater = LayoutInflater.from(this);


        String sortType = getIntent().getStringExtra("sortType");

        switch (sortType) {
            case "contact":
                contentView = mInflater.inflate(R.layout.activity_contact, null);
                setContentView(contentView);
                MyTitleBar contactTitleBar = contentView.findViewById(R.id.contact_titleBar);
                contactTitleBar.getActivityForFinish(this);
                tv_mail = contentView.findViewById(R.id.tv_mail);
                tv_qq = contentView.findViewById(R.id.tv_qq);
                 tv_weiBo = contentView.findViewById(R.id.tv_weiBo);
                tv_mail.setOnClickListener(this);
                tv_qq.setOnClickListener(this);
                tv_weiBo.setOnClickListener(this);

                break;
            case "agreement":
                contentView = mInflater.inflate(R.layout.activity_agreement, null);
                setContentView(contentView);
                MyTitleBar agreementTitleBar = contentView.findViewById(R.id.agreement_titleBar);
                agreementTitleBar.getActivityForFinish(this);
                break;
            case "reward":
                contentView = mInflater.inflate(R.layout.activity_reward, null);
                setContentView(contentView);
                MyTitleBar rewardTitleBar = contentView.findViewById(R.id.reward_titleBar);
                btn_aliPay=contentView.findViewById(R.id.btn_aliPay);
                btn_weiXinPay=contentView.findViewById(R.id.btn_weiXinPay);
                btn_weiXinPay.setOnClickListener(this);
                btn_aliPay.setOnClickListener(this);
                rewardTitleBar.getActivityForFinish(this);
                break;
        }
    }


    public void copyToClipboard(Context context, String text) {
        ClipboardManager clip = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        //clip.getText(); // 粘贴
        clip.setText(text); // 复制
        Toast.makeText(context, "已复制到粘贴板", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_mail:
                copyToClipboard(this,tv_mail.getText().toString().trim());
                break;
            case R.id.tv_qq:
                copyToClipboard(this,tv_qq.getText().toString().trim());
                break;
            case R.id.tv_weiBo:
                copyToClipboard(this,tv_weiBo.getText().toString().trim());
                break;
            case R.id.btn_weiXinPay:
                Bitmap  weiXinPayBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.weixinpay);
                if(saveImageToGallery(this,weiXinPayBitmap))
                {
                    tipsQRCodeSaveSuccessed();
                }
                break;
            case R.id.btn_aliPay:
                Bitmap  aliPayBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.alipay);
                if(saveImageToGallery(this,aliPayBitmap))
                {
                    tipsQRCodeSaveSuccessed();
                }
                break;
        }
    }


    //保存文件到指定路径
    public static boolean saveImageToGallery(Context context, Bitmap bmp) {
        // 首先保存图片
        String storePath = Environment.getExternalStorageDirectory()
                + File.separator + Environment.DIRECTORY_DCIM
                +File.separator+"Camera"+File.separator;//系统相册根目录
        File appDir = new File(storePath);
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = "中草药坊支付码" + ".jpg";//保存后的二维码图片名
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            //通过io流的方式来压缩保存图片
            boolean isSuccess = bmp.compress(Bitmap.CompressFormat.JPEG, 90, fos);
            fos.flush();
            fos.close();

            //把文件插入到系统图库
            //MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), fileName, null);

            //保存图片后发送广播通知更新数据库
            Uri uri = Uri.fromFile(file);
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
            if (isSuccess) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    //提示保存支付二维码成功
    public void tipsQRCodeSaveSuccessed()
    {
        Snackbar.make(contentView,"已保存我的相册",Snackbar.LENGTH_LONG)
                .setAction("好的", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent =intent = new Intent(Intent.ACTION_VIEW, Uri.parse(MediaStore.Images.Media.INTERNAL_CONTENT_URI.toString()));;
                        startActivity(intent);//打开系统相册
                    }
                }).show();
    }

}
