package com.isoft.eventbus.slice;

import com.isoft.eventbus.EventMessage;
import com.isoft.eventbus.ResourceTable;
import com.isoft.eventbusmodule.EventBus;
import com.isoft.eventbusmodule.Subscribe;
import com.isoft.eventbusmodule.ThreadMode;
import ohos.aafwk.ability.AbilityForm;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.ability.OnClickListener;
import ohos.aafwk.ability.ViewsStatus;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.agp.components.Text;


public class MainAbilitySlice extends AbilitySlice implements Component.ClickedListener {
    private Text tvResult;
    private Button button;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);
        EventBus.getDefault().register(this);
        tvResult=(Text) findComponentById(ResourceTable.Id_text);
        button=(Button) findComponentById(ResourceTable.Id_button);
        button.setClickedListener(this);
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }


    @Override
    public void onClick(Component component) {
        Intent secondIntent = new Intent();
        // 指定待启动FA的bundleName和abilityName
        Operation operation = new Intent.OperationBuilder()
                .withDeviceId("")
                .withBundleName("com.isoft.eventbus")
                .withAbilityName("com.isoft.eventbus.SecondAbility")
                .build();
        secondIntent.setOperation(operation);
        // 通过AbilitySlice的startAbility接口实现启动另一个页面
        startAbility(secondIntent);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveMessage(EventMessage eventMessage){
        tvResult.setText(eventMessage.getMessage());
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
