package com.app.voicechangereffect.allBaseAct;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import java.util.Objects;
import kotlin.jvm.internal.Intrinsics;

public final class lpBasePopupMenu {
    private final PopupMenuCustomOnClickListener lponClickListener;
    private final View lppopupView;
    private final PopupWindow lppopupWindow;

    public interface PopupMenuCustomOnClickListener {
        void initView(View view);

        void onClick(int i, View view);
    }

    public lpBasePopupMenu(final Context context, int i, PopupMenuCustomOnClickListener onClickListener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(onClickListener, "onClickListener");
        this.lponClickListener = onClickListener;
        Object systemService = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.view.LayoutInflater");
        View inflate = ((LayoutInflater) systemService).inflate(i, (ViewGroup) null);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflater.inflate(rLayoutId, null)");
        this.lppopupView = inflate;
        onClickListener.initView(inflate);
        PopupWindow popupWindow = new PopupWindow(inflate, -2, -2, true);
        this.lppopupWindow = popupWindow;
        popupWindow.setElevation(10.0f);
        LinearLayout linearLayout = (LinearLayout) inflate;
        int childCount = linearLayout.getChildCount();
        int i2 = 0;
        while (i2 < childCount) {
            int i3 = i2 + 1;
            View childAt = linearLayout.getChildAt(i2);
            Intrinsics.checkNotNullExpressionValue(childAt, "linearLayout.getChildAt(i)");

            childAt.setOnClickListener(view -> {
                Context this$0 = context;
                Intrinsics.checkNotNullParameter(this$0, "this$0");
                lpBasePopupMenu.this.lponClickListener.onClick(view.getId(), lpBasePopupMenu.this.lppopupView);
                lpBasePopupMenu.this.lppopupWindow.dismiss();
            });
            i2 = i3;
        }
    }

    public void show() {
        this.lppopupWindow.showAtLocation(this.lppopupView, 17, 0, 0);
    }

    public void show(View view) {
        this.lppopupWindow.showAsDropDown(view, view.getWidth() - this.lppopupWindow.getWidth(), 0);
    }

    public void showRight(View view) {
        this.lppopupWindow.showAsDropDown(view, view.getWidth() - this.lppopupWindow.getWidth(), 0, 53);
    }
}
