package uz.adkhamjon.uzmobileussd.utils;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.view.LayoutInflater;
import android.view.View;

import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import uz.adkhamjon.uzmobileussd.R;
import uz.adkhamjon.uzmobileussd.adapters.SimSlotAdapter;

public class RunUssd {
    public static void call(final Context context, final String code) {
        final Intent i = new Intent(Intent.ACTION_CALL)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            TelecomManager telecomManager = (TelecomManager) context.getSystemService(Context.TELECOM_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_PHONE_STATE}, 1);
                    return;
                }
                final List<PhoneAccountHandle> phoneAccountHandleList = telecomManager.getCallCapablePhoneAccounts();
                List<SubscriptionInfo> subscriptionInfos = SubscriptionManager.from(context).getActiveSubscriptionInfoList();
                final int[] sim = {0};
                ArrayList<String> data = new ArrayList<>();
                if (subscriptionInfos.size() > 1) {
                    for (int j = 0; j < subscriptionInfos.size(); j++) {
                        data.add("SIM " + (j + 1) + " - " + subscriptionInfos.get(j).getCarrierName());
                    }
                    final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    View view = LayoutInflater.from(context).inflate(R.layout.item_sim_list, null, false);
                    RecyclerView list = view.findViewById(R.id.list);
                    list.setLayoutManager(new LinearLayoutManager(context));
                    SimSlotAdapter simSlotAdapter = new SimSlotAdapter(data);
                    list.setAdapter(simSlotAdapter);
                    builder.setView(view);
                    final AlertDialog dialog = builder.show();
                    simSlotAdapter.setOnItemClick(position -> {
                        sim[0] = position;
                        builder.setCancelable(true);
                        i.putExtra("android.telecom.extra.PHONE_ACCOUNT_HANDLE", phoneAccountHandleList.get(sim[0]));
                        dialog.dismiss();
                        actionCall(i, code, context);
                    });

                } else {
                    i.putExtra("android.telecom.extra.PHONE_ACCOUNT_HANDLE", phoneAccountHandleList.get(sim[0]));
                    actionCall(i, code, context);
                }
            } else {
                actionCall(i, code, context);
            }
        } else {
            actionCall(i, code, context);

        }
    }

    private static void actionCall(Intent i, String code, Context context) {
        String encodedHash = Uri.encode(code);
        i.setData(Uri.parse("tel:" + encodedHash));
        i.putExtra("com.android.phone.force.slot", true);
        i.putExtra("Cdma_Supp", true);


        if (ActivityCompat.checkSelfPermission(context,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CALL_PHONE}, 1);
            return;
        }
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        builder.setMessage(context.getResources().getString(R.string.ussd_kod_bajarilishini_xoxlaysizmi));
//        builder.setPositiveButton(context.getResources().getString(R.string.ha), (dialog, which) -> {
            context.startActivity(i);
    //    });
//        builder.setNegativeButton(context.getResources().getString(R.string.yuq), null);
//        builder.show();
    }
}
