package com.ti.apitraining.Adapter.users;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ti.apitraining.databinding.ItemUserBinding;
import com.ti.apitraining.models.User;

public class UserHolder extends RecyclerView.ViewHolder  {
    ItemUserBinding binding;
    public UserHolder(@NonNull ItemUserBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void setData(User user){
        binding.tvName.setText(user.firstName);
        binding.tvEmail.setText(user.email);
    }


}
