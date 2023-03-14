package com.ti.apitraining.interfaces;

import com.ti.apitraining.enums.ActionType;
import com.ti.apitraining.models.Task;

public interface TaskCallback {

    void onClickDeleteTasks( int id,int pos);
    void onClickUpdateTasks( int id,int pos);

}
