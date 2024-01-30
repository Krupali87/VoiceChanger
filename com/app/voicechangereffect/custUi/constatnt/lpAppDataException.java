package com.app.voicechangereffect.custUi.constatnt;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.DisplayMetrics;
import com.app.voicechangereffect.R;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;

public final class lpAppDataException {
    public static AsyncTask<String, String, String[]> executeAsync(CoroutineScope coroutineScope, Function1 function1, Function1 function12, Function1 function13) {
        return new AsyncTaskExample(function1, function12, function13).execute(new String[0]);
    }

    public static class AsyncTaskExample extends AsyncTask<String, String, String[]> {
        private final Function1 lpmDoInBackground;
        private final Function1 lpmOnPostExecute;
        private final Function1 lpmOnPreExecute;

        public <T> AsyncTaskExample(Function1 function1, Function1 function12, Function1 function13) {
            this.lpmOnPreExecute = function1;
            this.lpmDoInBackground = function12;
            this.lpmOnPostExecute = function13;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.lpmOnPreExecute.invoke("onPreExecute");
        }

        @Override
        public String[] doInBackground(String... strArr) {
            this.lpmDoInBackground.invoke(strArr);
            return strArr;
        }

        @Override
        public void onPostExecute(String[] strArr) {
            super.onPostExecute(strArr);
            this.lpmOnPostExecute.invoke(strArr);
        }
    }

    public static int getWithMetrics(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public static int getIconEffect(String str) {
        int i = StringsKt.contains(str, "normal", false) ? R.drawable.ic_normal_selected : -1;
        if (StringsKt.contains(str, (CharSequence) "robot", false)) {
            i = R.drawable.ic_roboto_selected;
        }
        if (StringsKt.contains(str, "helium", false)) {
            i = R.drawable.ic_helium_selected;
        }
        if (StringsKt.contains(str, "hexafluoride", false)) {
            i = R.drawable.ic_hexafluoride_selected;
        }
        if (StringsKt.contains(str, "cave", false)) {
            i = R.drawable.ic_cave_selected;
        }
        if (StringsKt.contains(str, "drunk", false)) {
            i = R.drawable.ic_drunk_selected;
        }
        if (StringsKt.contains(str, "bigobot", false)) {
            i = R.drawable.ic_big_roboto_selected;
        }
        if (StringsKt.contains(str, "extraterrestrial", false)) {
            i = R.drawable.ic_extraterrestrial_selected;
        }
        if (StringsKt.contains(str, "monster", false)) {
            i = R.drawable.ic_monster_selected;
        }
        if (StringsKt.contains(str, "nervous", false)) {
            i = R.drawable.ic_nervous_selected;
        }
        if (StringsKt.contains(str, "telephone", false)) {
            i = R.drawable.ic_telephone_selected;
        }
        if (StringsKt.contains(str, "backchipmunk", false)) {
            i = R.drawable.back_chimpmuk_seleted;
        }
        if (StringsKt.contains(str, "underwater", false)) {
            i = R.drawable.ic_underwater_selected;
        }
        if (StringsKt.contains(str, "zombie", false)) {
            i = R.drawable.ic_zombie_selected;
        }
        if (StringsKt.contains(str, "child", false)) {
            i = R.drawable.ic_child_selected;
        }
        if (StringsKt.contains(str, "megaphone", false)) {
            i = R.drawable.ic_megaphone_selected;
        }
        if (StringsKt.contains(str, "death", false)) {
            i = R.drawable.ic_death_selected;
        }
        if (StringsKt.contains(str, "villain", false)) {
            i = R.drawable.ic_villain_selected;
        }
        if (StringsKt.contains(str, "alien", false)) {
            i = R.drawable.ic_alien_selected;
        }
        if (StringsKt.contains(str, "grandcanyon", false)) {
            i = R.drawable.ic_grand_canyon_selected;
        }
        if (StringsKt.contains(str, "reverse", false)) {
            i = R.drawable.ic_reverse_selected;
        }
        if (StringsKt.contains(str, "smallalien", false)) {
            i = R.drawable.back_chimp_seleted;
        }
        if (StringsKt.contains(str, "motorcycler", false)) {
            i = R.drawable.motorcycle;
        }
        if (StringsKt.contains(str, "stormwind", false)) {
            i = R.drawable.stromwind;
        }
        if (StringsKt.contains(str, "autowash", false)) {
            i = R.drawable.autowash;
        }
        if (StringsKt.contains(str, "volumeenvelope", false)) {
            i = R.drawable.volumeenvelope;
        }
        if (StringsKt.contains(str, "bassosinger", false)) {
            i = R.drawable.bassosinger;
        }
        if (StringsKt.contains(str, "tenorsinger", false)) {
            i = R.drawable.tenor;
        }
        if (StringsKt.contains(str, "mrpanic", false)) {
            i = R.drawable.panic;
        }
        if (StringsKt.contains(str, "dancingghost", false)) {
            i = R.drawable.ghost;
        }







        return StringsKt.contains(str, "squirrel", false) ? R.drawable.ic_squirrel_selected : i;
    }

    public static boolean lpgetSpecialChar(String str) {
        Pattern compile = Pattern.compile("[^A-Za-z0-9_ ]");
        Intrinsics.checkNotNullExpressionValue(compile, "compile(\"[^A-Za-z0-9_ ]\")");
        Matcher matcher = compile.matcher(str);
        Intrinsics.checkNotNullExpressionValue(matcher, "p.matcher(s)");
        return matcher.find();
    }
}
