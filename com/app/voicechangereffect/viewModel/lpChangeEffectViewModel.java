package com.app.voicechangereffect.viewModel;

import android.content.Context;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.MutableLiveData;
import com.app.voicechangereffect.R;
import com.app.voicechangereffect.allBaseAct.lpBaseViewModel;
import com.app.voicechangereffect.custUi.constatnt.lpAppDataException;
import com.app.voicechangereffect.getApiData.allModel.lpEffectModel;
import com.app.voicechangereffect.getApiData.allModel.lpTypeEffectModel;
import java.util.ArrayList;
import java.util.List;
import kotlin.Unit;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;


public final class lpChangeEffectViewModel extends lpBaseViewModel {
    private int label;
    private List<lpEffectModel> effectList = new ArrayList();
    private List<lpTypeEffectModel> effectTypeList = new ArrayList();
    private MutableLiveData<List<lpTypeEffectModel>> liveType = new MutableLiveData<>();

    public List<lpTypeEffectModel> getEffectTypeList() {
        return this.effectTypeList;
    }

    public MutableLiveData<List<lpTypeEffectModel>> getLiveType() {
        return this.liveType;
    }

    public List<lpEffectModel> getEffectList() {
        return this.effectList;
    }

    public void getTypeEffects(final Context context) {

        lpAppDataException.executeAsync(LifecycleOwnerKt.getLifecycleScope((LifecycleOwner) context), obj -> {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (lpChangeEffectViewModel.this.label == 0) {
                lpChangeEffectViewModel.this.getEffectTypeList().clear();
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }, obj -> {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (lpChangeEffectViewModel.this.label == 0) {
                lpChangeEffectViewModel.this.getEffectTypeList().add(new lpTypeEffectModel(context.getString(R.string.all_effects), true));
                lpChangeEffectViewModel.this.getEffectTypeList().add(new lpTypeEffectModel(context.getString(R.string.scary_effects), false));
                lpChangeEffectViewModel.this.getEffectTypeList().add(new lpTypeEffectModel(context.getString(R.string.other_effects), false));
                lpChangeEffectViewModel.this.getEffectTypeList().add(new lpTypeEffectModel(context.getString(R.string.people_effects), false));
                return lpChangeEffectViewModel.this.getEffectTypeList().add(new lpTypeEffectModel(context.getString(R.string.robot_effects), false));
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }, obj -> {
            lpChangeEffectViewModel.this.getLiveType().setValue(lpChangeEffectViewModel.this.getEffectTypeList());
            return Unit.INSTANCE;
        });
    }

    public List<lpEffectModel> getAllVoiceEffects(final Context context) {

        lpAppDataException.executeAsync(LifecycleOwnerKt.getLifecycleScope((LifecycleOwner) context), obj -> {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (lpChangeEffectViewModel.this.label == 0) {
                lpChangeEffectViewModel.this.getEffectList().clear();
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }, obj -> {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (lpChangeEffectViewModel.this.label == 0) {
                lpChangeEffectViewModel.this.getEffectList().add(new lpEffectModel(0, context.getString(R.string.normal), "normal", R.drawable.ic_normal_unselected, R.drawable.ic_normal_selected, 0, true));
                lpChangeEffectViewModel.this.getEffectList().add(new lpEffectModel(6, context.getString(R.string.drunk), "drunk", R.drawable.ic_drunk_unselected, R.drawable.ic_drunk_selected, 0, false));
                lpChangeEffectViewModel.this.getEffectList().add(new lpEffectModel(10, context.getString(R.string.reverse), "reverse", R.drawable.ic_reverse_unselected, R.drawable.ic_reverse_selected, 0, false));
                lpChangeEffectViewModel.this.getEffectList().add(new lpEffectModel(18, context.getString(R.string.zombie), "zombie", R.drawable.ic_zombie_unselected, R.drawable.ic_zombie_selected, 0, false));
                lpChangeEffectViewModel.this.getEffectList().add(new lpEffectModel(2, context.getString(R.string.robot), "robot", R.drawable.ic_roboto_unselected, R.drawable.ic_roboto_selected, 0, false));
                lpChangeEffectViewModel.this.getEffectList().add(new lpEffectModel(5, context.getString(R.string.nervous), "nervous", R.drawable.ic_nervous_unselected, R.drawable.ic_nervous_selected, 0, false));
                lpChangeEffectViewModel.this.getEffectList().add(new lpEffectModel(9, context.getString(R.string.death), "death", R.drawable.ic_death_unselected, R.drawable.ic_death_selected, 0, false));
                lpChangeEffectViewModel.this.getEffectList().add(new lpEffectModel(1, context.getString(R.string.helium), "helium", R.drawable.ic_helium_unselected, R.drawable.ic_helium_selected, 0, false));
                lpChangeEffectViewModel.this.getEffectList().add(new lpEffectModel(7, context.getString(R.string.squirrel), "squirrel", R.drawable.ic_squirrel_unselected, R.drawable.ic_squirrel_selected, 0, false));
                lpChangeEffectViewModel.this.getEffectList().add(new lpEffectModel(4, context.getString(R.string.monster), "monster", R.drawable.ic_monster_unselected, R.drawable.ic_monster_selected, 0, false));
                lpChangeEffectViewModel.this.getEffectList().add(new lpEffectModel(13, context.getString(R.string.big_robot), "bigrobot", R.drawable.ic_big_roboto_unselected, R.drawable.ic_big_roboto_selected, 0, false));
                lpChangeEffectViewModel.this.getEffectList().add(new lpEffectModel(20, context.getString(R.string.alien), "alien", R.drawable.ic_alien_unselected, R.drawable.ic_alien_selected, 0, false));
                lpChangeEffectViewModel.this.getEffectList().add(new lpEffectModel(8, context.getString(R.string.child), "child", R.drawable.ic_child_unselected, R.drawable.ic_child_selected, 0, false));
                lpChangeEffectViewModel.this.getEffectList().add(new lpEffectModel(15, context.getString(R.string.underwater), "underwater", R.drawable.ic_underwater_unselected, R.drawable.ic_underwater_selected, 0, false));
                lpChangeEffectViewModel.this.getEffectList().add(new lpEffectModel(12, context.getString(R.string.hexafluoride), "hexafluoride", R.drawable.ic_hexafluoride_unselected, R.drawable.ic_hexafluoride_selected, 0, false));
                lpChangeEffectViewModel.this.getEffectList().add(new lpEffectModel(21, context.getString(R.string.small_alien), "smallalien", R.drawable.back_chimp_unseleted, R.drawable.back_chimp_seleted, 0, false));
                lpChangeEffectViewModel.this.getEffectList().add(new lpEffectModel(14, context.getString(R.string.telephone), "telephone", R.drawable.ic_telephone_unselected, R.drawable.ic_telephone_selected, 0, false));
                lpChangeEffectViewModel.this.getEffectList().add(new lpEffectModel(16, context.getString(R.string.extraterrestrial), "extraterrestrial", R.drawable.ic_extraterrestrial_unselected, R.drawable.ic_extraterrestrial_selected, 0, false));
                lpChangeEffectViewModel.this.getEffectList().add(new lpEffectModel(3, context.getString(R.string.cave), "cave", R.drawable.ic_cave_unselected, R.drawable.ic_cave_selected, 0, false));
                lpChangeEffectViewModel.this.getEffectList().add(new lpEffectModel(19, context.getString(R.string.megaphone), "megaphone", R.drawable.ic_megaphone_unselected, R.drawable.ic_megaphone_selected, 0, false));
                lpChangeEffectViewModel.this.getEffectList().add(new lpEffectModel(17, context.getString(R.string.villain), "villain", R.drawable.ic_villain_unselected, R.drawable.ic_villain_selected, 0, false));
                lpChangeEffectViewModel.this.getEffectList().add(new lpEffectModel(25, context.getString(R.string.motorcycle), "motorcycler", R.drawable.motorcycle, R.drawable.motor_cycle_unselected, 0, false));
                lpChangeEffectViewModel.this.getEffectList().add(new lpEffectModel(24, context.getString(R.string.stormwind), "stormwind", R.drawable.stromwind, R.drawable.motor_cycle_unselected, 0, false));
                lpChangeEffectViewModel.this.getEffectList().add(new lpEffectModel(26, context.getString(R.string.autowash), "autowash", R.drawable.autowash, R.drawable.motor_cycle_unselected, 0, false));
                lpChangeEffectViewModel.this.getEffectList().add(new lpEffectModel(27, context.getString(R.string.volumeenvelope), "volumeenvelope", R.drawable.volumeenvelope, R.drawable.motor_cycle_unselected, 0, false));
                lpChangeEffectViewModel.this.getEffectList().add(new lpEffectModel(29, context.getString(R.string.bassosinger), "bassosinger", R.drawable.bassosinger, R.drawable.motor_cycle_unselected, 0, false));
                lpChangeEffectViewModel.this.getEffectList().add(new lpEffectModel(30, context.getString(R.string.tenorsinger), "tenorsinger", R.drawable.tenor, R.drawable.motor_cycle_unselected, 0, false));
                lpChangeEffectViewModel.this.getEffectList().add(new lpEffectModel(31, context.getString(R.string.mrpanic), "mrpanic", R.drawable.panic, R.drawable.motor_cycle_unselected, 0, false));
                lpChangeEffectViewModel.this.getEffectList().add(new lpEffectModel(32, context.getString(R.string.dancingghost), "dancingghost", R.drawable.ghost, R.drawable.motor_cycle_unselected, 0, false));

                return lpChangeEffectViewModel.this.getEffectList().add(new lpEffectModel(11, context.getString(R.string.grand_canyon), "grandcanyon", R.drawable.ic_grand_canyon_unselected, R.drawable.ic_grand_canyon_selected, 0, false));
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }, obj -> Unit.INSTANCE);
        return this.effectList;
    }

    public List<lpEffectModel> getAllRobotEffects(final Context context) {
        Intrinsics.checkNotNullParameter(context, "context");

        lpAppDataException.executeAsync(LifecycleOwnerKt.getLifecycleScope((LifecycleOwner) context), obj -> {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (lpChangeEffectViewModel.this.label == 0) {
                lpChangeEffectViewModel.this.getEffectList().clear();
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }, obj -> {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (lpChangeEffectViewModel.this.label == 0) {
                lpChangeEffectViewModel.this.getEffectList().add(new lpEffectModel(13, context.getString(R.string.big_robot), "bigrobot", R.drawable.ic_big_roboto_unselected, R.drawable.ic_big_roboto_selected, 0, false));
                return lpChangeEffectViewModel.this.getEffectList().add(new lpEffectModel(2, context.getString(R.string.robot), "robot", R.drawable.ic_roboto_unselected, R.drawable.ic_roboto_selected, 0, false));
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }, obj -> Unit.INSTANCE);
        return this.effectList;
    }

    public List<lpEffectModel> getAllPeopleEffects(final Context context) {


        lpAppDataException.executeAsync(LifecycleOwnerKt.getLifecycleScope((LifecycleOwner) context), obj -> {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (lpChangeEffectViewModel.this.label==0){
                lpChangeEffectViewModel.this.getEffectList().clear();
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");

        }, obj -> {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (lpChangeEffectViewModel.this.label == 0) {
                lpChangeEffectViewModel.this.getEffectList().add(new lpEffectModel(8, context.getString(R.string.child), "child", R.drawable.ic_child_unselected, R.drawable.ic_child_selected, 0, false));
                lpChangeEffectViewModel.this.getEffectList().add(new lpEffectModel(5, context.getString(R.string.nervous), "nervous", R.drawable.ic_nervous_unselected, R.drawable.ic_nervous_selected, 0, false));
                lpChangeEffectViewModel.this.getEffectList().add(new lpEffectModel(17, context.getString(R.string.villain), "villain", R.drawable.ic_villain_unselected, R.drawable.ic_villain_selected, 0, false));
                lpChangeEffectViewModel.this.getEffectList().add(new lpEffectModel(15, context.getString(R.string.underwater), "underwater", R.drawable.ic_underwater_unselected, R.drawable.ic_underwater_selected, 0, false));
                lpChangeEffectViewModel.this.getEffectList().add(new lpEffectModel(29, context.getString(R.string.bassosinger), "bassosinger", R.drawable.bassosinger, R.drawable.motor_cycle_unselected, 0, false));
                lpChangeEffectViewModel.this.getEffectList().add(new lpEffectModel(30, context.getString(R.string.tenorsinger), "tenorsinger", R.drawable.tenor, R.drawable.motor_cycle_unselected, 0, false));
                lpChangeEffectViewModel.this.getEffectList().add(new lpEffectModel(31, context.getString(R.string.mrpanic), "mrpanic", R.drawable.panic, R.drawable.motor_cycle_unselected, 0, false));

                return lpChangeEffectViewModel.this.getEffectList().add(new lpEffectModel(6, context.getString(R.string.drunk), "drunk", R.drawable.ic_drunk_unselected, R.drawable.ic_drunk_selected, 0, false));
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }, obj -> Unit.INSTANCE);
        return this.effectList;
    }

    public List<lpEffectModel> lpgetAllScaryEffects(final Context context) {

        lpAppDataException.executeAsync(LifecycleOwnerKt.getLifecycleScope((LifecycleOwner) context), obj -> {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (lpChangeEffectViewModel.this.label == 0) {
                lpChangeEffectViewModel.this.getEffectList().clear();
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }, obj -> {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (lpChangeEffectViewModel.this.label == 0) {
                lpChangeEffectViewModel.this.getEffectList().add(new lpEffectModel(18, context.getString(R.string.zombie), "zombie", R.drawable.ic_zombie_unselected, R.drawable.ic_zombie_selected, 0, false));
                lpChangeEffectViewModel.this.getEffectList().add(new lpEffectModel(20, context.getString(R.string.alien), "alien", R.drawable.ic_alien_unselected, R.drawable.ic_alien_selected, 0, false));
                lpChangeEffectViewModel.this.getEffectList().add(new lpEffectModel(21, context.getString(R.string.small_alien), "smallalien", R.drawable.back_chimp_unseleted, R.drawable.back_chimp_seleted, 0, false));
                lpChangeEffectViewModel.this.getEffectList().add(new lpEffectModel(4, context.getString(R.string.monster), "monster", R.drawable.ic_monster_unselected, R.drawable.ic_monster_selected, 0, false));
                lpChangeEffectViewModel.this.getEffectList().add(new lpEffectModel(16, context.getString(R.string.extraterrestrial), "extraterrestrial", R.drawable.ic_extraterrestrial_unselected, R.drawable.ic_extraterrestrial_selected, 0, false));
                lpChangeEffectViewModel.this.getEffectList().add(new lpEffectModel(32, context.getString(R.string.dancingghost), "dancingghost", R.drawable.ghost, R.drawable.motor_cycle_unselected, 0, false));
                return lpChangeEffectViewModel.this.getEffectList().add(new lpEffectModel(9, context.getString(R.string.death), "death", R.drawable.ic_death_unselected, R.drawable.ic_death_selected, 0, false));

            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }, obj -> Unit.INSTANCE);
        return this.effectList;
    }

    public List<lpEffectModel> getAllOtherEffects(final Context context) {

        lpAppDataException.executeAsync(LifecycleOwnerKt.getLifecycleScope((LifecycleOwner) context), obj -> {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (lpChangeEffectViewModel.this.label == 0) {
                lpChangeEffectViewModel.this.getEffectList().clear();
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }, obj -> {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (lpChangeEffectViewModel.this.label == 0) {
                lpChangeEffectViewModel.this.getEffectList().add(new lpEffectModel(3, context.getString(R.string.cave), "cave", R.drawable.ic_cave_unselected, R.drawable.ic_cave_selected, 0, false));
                lpChangeEffectViewModel.this.getEffectList().add(new lpEffectModel(19, context.getString(R.string.megaphone), "megaphone", R.drawable.ic_megaphone_unselected, R.drawable.ic_megaphone_selected, 0, false));
                lpChangeEffectViewModel.this.getEffectList().add(new lpEffectModel(7, context.getString(R.string.squirrel), "squirrel", R.drawable.ic_squirrel_unselected, R.drawable.ic_squirrel_selected, 0, false));
                lpChangeEffectViewModel.this.getEffectList().add(new lpEffectModel(14, context.getString(R.string.telephone), "telephone", R.drawable.ic_telephone_unselected, R.drawable.ic_telephone_selected, 0, false));
                lpChangeEffectViewModel.this.getEffectList().add(new lpEffectModel(12, context.getString(R.string.hexafluoride), "hexafluoride", R.drawable.ic_hexafluoride_unselected, R.drawable.ic_hexafluoride_selected, 0, false));
                lpChangeEffectViewModel.this.getEffectList().add(new lpEffectModel(11, context.getString(R.string.grand_canyon), "grandcanyon", R.drawable.ic_grand_canyon_unselected, R.drawable.ic_grand_canyon_selected, 0, false));
                lpChangeEffectViewModel.this.getEffectList().add(new lpEffectModel(10, context.getString(R.string.reverse), "reverse", R.drawable.ic_reverse_unselected, R.drawable.ic_reverse_selected, 0, false));
                lpChangeEffectViewModel.this.getEffectList().add(new lpEffectModel(1, context.getString(R.string.helium), "helium", R.drawable.ic_helium_unselected, R.drawable.ic_helium_selected, 0, false));
                lpChangeEffectViewModel.this.getEffectList().add(new lpEffectModel(24, context.getString(R.string.stormwind), "stormwind", R.drawable.stromwind, R.drawable.motor_cycle_unselected, 0, false));
                lpChangeEffectViewModel.this.getEffectList().add(new lpEffectModel(26, context.getString(R.string.autowash), "autowash", R.drawable.autowash, R.drawable.motor_cycle_unselected, 0, false));
                lpChangeEffectViewModel.this.getEffectList().add(new lpEffectModel(27, context.getString(R.string.volumeenvelope), "volumeenvelope", R.drawable.volumeenvelope, R.drawable.motor_cycle_unselected, 0, false));
                lpChangeEffectViewModel.this.getEffectList().add(new lpEffectModel(25, context.getString(R.string.motorcycle), "motorcycler", R.drawable.motorcycle, R.drawable.motor_cycle_unselected, 0, false));

                return lpChangeEffectViewModel.this.getEffectList().add(new lpEffectModel(22, context.getString(R.string.back_chipmunks), "backchipmunk", R.drawable.back_chimp_unseleted, R.drawable.back_chimpmuk_seleted, 0, false));
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }, obj -> Unit.INSTANCE);
        return this.effectList;
    }
}
