package br.com.xplore.xploreretrofit1.utils.device;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;

import android.content.pm.PackageManager;
import android.net.ConnectivityManager;

import android.net.Network;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.RequiresPermission;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.telecom.TelecomManager;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;


/**
 * Created by r028367 on 26/01/2017.
 */

public class Device {

    public static final int REQUEST_PERMISSION = 0;
    private static Context context;
    private static String id;

    public static String genUUID() {
        UUID uuid = UUID.randomUUID();
        String res = uuid.toString();
        Log.i("UUID_", String.format("%d\n%s\n%d", uuid.version(), uuid.toString(), uuid.variant()));
        return res;
    }

    public static Calendar longToCalendar(long time) {
        Calendar calendar = Calendar.getInstance();
        // calendar.setTime(new Date(time));
        calendar.setTimeInMillis(time);
        return calendar;
    }

    private static void alertPermission(Context context, final String title, final String message, final boolean cancelable
            , final DialogInterface.OnClickListener posListener, final DialogInterface.OnClickListener negListener) {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        ((Activity) context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                dialog.setTitle(title)
                        .setMessage(message)
                        .setCancelable(cancelable)
                        .setPositiveButton("Sim", posListener)
                        .setNegativeButton("Não", negListener)
                        .create()
                        .show();
            }
        });
    }

    public static void getPermissions(final Context context, final String[] permissions, String title, String message, boolean cancelable) {
        DialogInterface.OnClickListener pos = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Activity activity = (Activity) context;
                ActivityCompat.requestPermissions(activity, permissions, REQUEST_PERMISSION);
                dialog.dismiss();
            }
        };
        DialogInterface.OnClickListener neg = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        };
        alertPermission(context, title, message, cancelable, pos, neg);
    }

    public static void getPermission(final Context context, final String permission, String title, String message, boolean cancelable) {
        DialogInterface.OnClickListener pos = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String[] perms = {permission};
                Activity activity = (Activity) context;
                ActivityCompat.requestPermissions(activity, perms, REQUEST_PERMISSION);
                dialog.dismiss();
            }
        };

        DialogInterface.OnClickListener neg = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        };
        alertPermission(context, title, message, cancelable, pos, neg);
    }


    @RequiresPermission(android.Manifest.permission.READ_PHONE_STATE)
    public static String getId(Context context) {
        String id = "";
        TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(context
                , Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                id = manager.getImei();
            }
            else  {
                id = manager.getDeviceId();
            }
        }
        return id;
    }

    public static boolean exploreHasConnection(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Network[] allNetworks = manager.getAllNetworks();
            if(allNetworks != null) {
                for(Network network : allNetworks) {

                }
            }
        }
        return false;
    }

    public static boolean simpleIsConnected(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager != null) {
            NetworkInfo [] infoNetWorks = manager.getAllNetworkInfo();
            for(NetworkInfo net : infoNetWorks) {
                if(net != null) {
                    /**
                     * Indicates whether network connectivity exists or is in the process
                     * of being established. This is good for applications that need to
                     * do anything related to the network other than read or write data.
                     * For the latter, call {@link #isConnected()} instead, which guarantees
                     * that the network is fully usable.
                     * @return {@code true} if network connectivity exists or is in the process
                     * of being established, {@code false} otherwise.
                     */
                    Log.i("CONNECTED_OR_CONNECTING", String.valueOf(net.isConnectedOrConnecting()));
                    /**
                     * Indicates whether network connectivity exists and it is possible to establish
                     * connections and pass data.
                     * <p>Always call this before attempting to perform data transactions.
                     * @return {@code true} if network connectivity exists, {@code false} otherwise.
                     */
                    boolean isConnected = net.isConnected();
                    if(isConnected)
                        return true;
                }
            }
        }
        return false;
    }

    public static boolean exploreConnection(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = manager.getActiveNetworkInfo();
        if(netInfo != null) {
            String netExtraInfo = netInfo.getExtraInfo();
            if(netExtraInfo != null) {
                Log.i("NETWORK_EXTRA_INFO", netExtraInfo);
            }

            String reason = netInfo.getReason();
            if(reason != null) {
                Log.i("NETWORK_REASON", reason);
            }
        }

        ArrayList<Integer> connType = new ArrayList<>();
        connType.add(ConnectivityManager.TYPE_BLUETOOTH);
        connType.add(ConnectivityManager.TYPE_ETHERNET);
        connType.add(ConnectivityManager.TYPE_MOBILE);
        connType.add(ConnectivityManager.TYPE_WIFI);
        connType.add(ConnectivityManager.TYPE_VPN);

        for(Integer type : connType) {
            NetworkInfo networkInfo = manager.getNetworkInfo(type);
            if(netInfo != null) {
                String extraInfo = netInfo.getExtraInfo();
                if(extraInfo != null) {
                    Log.i("NETWORK_EXTRA_INFO", extraInfo);
                }
            }
        }

        NetworkInfo [] networkInfos = manager.getAllNetworkInfo();
        for(NetworkInfo net : networkInfos) {
            if(net != null) {
                String info = net.getExtraInfo();
                if(info != null)
                    Log.i("NETWORK_EXTRA_INFO", info);

                String reason = net.getReason();
                if(reason != null) {
                    Log.i("NETWORK_REASON", reason);
                }

                String typeName = net.getTypeName();
                if(typeName != null) {
                    Log.i("TYPE_NAME", typeName);
                }

                NetworkInfo.DetailedState detailedState = net.getDetailedState();
                if(detailedState != null) {
                    Log.i("DETAILS_STATE", detailedState.name());
                }
                /**
                 * Indicates whether network connectivity exists or is in the process
                 * of being established. This is good for applications that need to
                 * do anything related to the network other than read or write data.
                 * For the latter, call {@link #isConnected()} instead, which guarantees
                 * that the network is fully usable.
                 * @return {@code true} if network connectivity exists or is in the process
                 * of being established, {@code false} otherwise.
                 */
                boolean isConnectedOrConnecting = net.isConnectedOrConnecting();
                Log.i("isconnectedorconnecting", String.valueOf(isConnectedOrConnecting));

                /**
                 * Indicates whether network connectivity exists and it is possible to establish
                 * connections and pass data.
                 * <p>Always call this before attempting to perform data transactions.
                 * @return {@code true} if network connectivity exists, {@code false} otherwise.
                 */
                boolean isConnected = net.isConnected();
                Log.i("isConnected", String.valueOf(isConnected));
                if(isConnected /*&& i sConnectedOrConnecting*/) {
                    if(reason != null) {
                        //StateConnection stateConnection = new StateConnection(reason, isConnected);
                    }
                    return true;
                }
            }
        }
        return false;
    }


    public static WifiManager getWifiManager(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        return wifiManager;
    }

    public static boolean isWifiEnabled(Context context) {
        WifiManager wm = getWifiManager(context);
        if(wm != null)
            return wm.isWifiEnabled();
        return false;
    }

    public static void toggleStateWifi(Context context) {
        WifiManager wm = getWifiManager(context);
        if(wm != null)
            wm.setWifiEnabled( ! wm.isWifiEnabled() );
    }

    public static void setStateWifi(Context context, boolean state) {
        WifiManager wm = getWifiManager(context);
        if(wm != null)
            wm.setWifiEnabled(state);
    }

    public static int getStateWifi(Context context) {
        WifiManager wm = getWifiManager(context);
        if(wm != null) {
            wm.getWifiState();
        }
        return -1;
    }


    /**
     * https://developer.android.com/reference/android/provider/Settings.Secure.html#ANDROID_ID
     *
     * */

    public static void getSecurityID(Context context) {
        String id = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        Log.i("SECURITY_ID", id);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    public static TelephonyManager get(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        SubscriptionManager sm= (SubscriptionManager) context.getSystemService(Context.TELEPHONY_SUBSCRIPTION_SERVICE);
        List<SubscriptionInfo> subsInfo = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            if (sm != null) {
                subsInfo = sm.getActiveSubscriptionInfoList();
                if(subsInfo != null && subsInfo.size() > 0) {
                    for(SubscriptionInfo si : subsInfo) {
                        String number = si.getNumber();
                    }
                }
            }
        }
        TelecomManager selecomManager = (TelecomManager) context.getSystemService(Context.TELECOM_SERVICE);
        return tm;
    }

    public static String encodeBase64(String data) {
        byte [] input = data.getBytes();
        byte [] output = Base64.encode(input, Base64.NO_WRAP);
        return new String(output);
    }

    public static String decodeBase64(String data) {
        byte [] input = data.getBytes();
        byte [] output = Base64.decode(input, Base64.NO_WRAP);
        return new String(output);
    }

    public static String getMD5Code(byte [] buffer) {
        String hash = "";
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte [] digest = md5.digest(buffer);
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<digest.length; i++) {
                /**
                 * em java os bytes variam de -127 a 128,
                 * mas precisamos de valores extritamente possitivos. Assim, fazemos um
                 * bitwise com and 255
                 * */
                short b = (short)(digest[i] & 0xff);
                sb.append(b);
            }
            hash = sb.toString(); //new BigInteger(1, digest).toString();
        }
        catch (NoSuchAlgorithmException e) {
            Log.e("MD5_Exception", e.getMessage());
        }
        return hash;
    }

    public static String getMD5Code2(byte [] buffer) {
        String hash = "";
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.reset();
            md5.update(buffer);
            byte [] result = md5.digest();
            StringBuilder sb = new StringBuilder();
        } catch (NoSuchAlgorithmException e) {
            Log.e("MD5_Exception", e.getMessage());
        }
        return hash;
    }
}
