package utouu.functiondemo;

import android.Manifest;
import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.CancellationSignal;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import utouu.functiondemo.moudle.main.LoginActivity;

/**
 * Created by Du_Li on 2016/11/10.
 * Desc:
 */
public class FingerVerify {
    private static volatile FingerVerify mFingerVerify;
    private FingerprintManager manager;
    private KeyguardManager mKeyManager;
    private final static int REQUEST_CODE_CONFIRM_DEVICE_CREDENTIALS = 0;
    private final static String TAG = "finger_log";
    private static Activity activity;

    private FingerVerify(Activity activity) {
        this.activity = activity;
        init();

    }

    public static FingerVerify getInstance(Activity activity) {
        if (mFingerVerify == null) {
            synchronized (FingerVerify.class) {
                if (mFingerVerify == null) {
                    mFingerVerify = new FingerVerify(activity);
                }
            }
        }
        return mFingerVerify;
    }


    private void init() {
        manager = (FingerprintManager) activity.getSystemService(Context.FINGERPRINT_SERVICE);
        mKeyManager = (KeyguardManager) activity.getSystemService(Context.KEYGUARD_SERVICE);
        String msg = isFinger();
        if (isFinger) {
            Toast.makeText(activity, msg + "请进行指纹识别", Toast.LENGTH_LONG).show();
            Log.e(TAG, msg);
            startListening(null);
        } else
            Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
    }

    private boolean isFinger;

    private String isFinger() {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(activity, "没有指纹识别权限", Toast.LENGTH_SHORT).show();
            isFinger = false;
            return "没有指纹识别权限";
        }
        Log.e(TAG, "有指纹权限");
        //判断硬件是否支持指纹识别
        if (!manager.isHardwareDetected()) {
            Toast.makeText(activity, "没有指纹识别模块", Toast.LENGTH_SHORT).show();
            isFinger = false;
            return "没有指纹识别模块";
        }
        Log.e(TAG, "有指纹模块");
        //判断 是否开启锁屏密码
        if (!mKeyManager.isKeyguardSecure()) {
            Toast.makeText(activity, "没有开启锁屏密码", Toast.LENGTH_SHORT).show();
            isFinger = false;
            return "没有开启锁屏密码";
        }
        Log.e(TAG, "已开启锁屏密码");
        //判断是否有指纹录入
        if (!manager.hasEnrolledFingerprints()) {
            Toast.makeText(activity, "没有录入指纹", Toast.LENGTH_SHORT).show();
            isFinger = false;
            return "没有录入指纹";
        }
        Log.e(TAG, "已录入指纹");

        return "已录入指纹";
    }

    private CancellationSignal mCancellationSignal = new CancellationSignal();
    //回调方法
    private FingerprintManager.AuthenticationCallback mSelfCancelled = new FingerprintManager.AuthenticationCallback() {
        @Override
        public void onAuthenticationError(int errorCode, CharSequence errString) {
            //但多次指纹密码验证错误后，进入此方法；并且，不能短时间内调用指纹验证
            Toast.makeText(activity, "错误信息：" + errString, Toast.LENGTH_SHORT).show();
            showAuthenticationScreen();
        }

        @Override
        public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
            Toast.makeText(activity, "帮助信息：" + helpString, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
            Toast.makeText(activity, "指纹识别成功！", Toast.LENGTH_SHORT).show();
            activity.startActivity(new Intent(activity, MainActivity.class));
        }

        @Override
        public void onAuthenticationFailed() {
            Toast.makeText(activity, "指纹识别失败！", Toast.LENGTH_SHORT).show();
            mCancellationSignal.cancel();
            activity.startActivity(new Intent(activity, LoginActivity.class));
        }
    };


    private void startListening(FingerprintManager.CryptoObject cryptoObject) {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(activity, "没有指纹识别权限！", Toast.LENGTH_SHORT).show();
            return;
        }
        manager.authenticate(cryptoObject, mCancellationSignal, 0, mSelfCancelled, null);

    }

    /**
     * 锁屏密码
     */
    private void showAuthenticationScreen() {

        Intent intent = mKeyManager.createConfirmDeviceCredentialIntent("finger", "指纹验证失败，请输入密码。");
        if (intent != null) {
            activity.startActivityForResult(intent, REQUEST_CODE_CONFIRM_DEVICE_CREDENTIALS);
        }
    }

    /**
     *
     * 如果有锁频密码  在Activity中的onActivityResult中设置
     */

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == REQUEST_CODE_CONFIRM_DEVICE_CREDENTIALS) {
//            // Challenge completed, proceed with using cipher
//            if (resultCode == RESULT_OK) {
//                Toast.makeText(this, "识别成功！", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(this, "识别失败！", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }

}
