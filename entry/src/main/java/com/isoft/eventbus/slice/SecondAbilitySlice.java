package com.isoft.eventbus.slice;

import com.isoft.eventbus.EventMessage;
import com.isoft.eventbus.ResourceTable;
import com.isoft.eventbusmodule.EventBus;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.agp.components.Component;

public class SecondAbilitySlice extends AbilitySlice implements Component.ClickedListener {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_second);
        Button btSend=(Button)findComponentById(ResourceTable.Id_button_send);
        btSend.setClickedListener(this);
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
        EventMessage eventMessage=new EventMessage();
        eventMessage.setMessage("您有新消息啦");
        EventBus.getDefault().post(eventMessage);
        terminateAbility();
    }

}
