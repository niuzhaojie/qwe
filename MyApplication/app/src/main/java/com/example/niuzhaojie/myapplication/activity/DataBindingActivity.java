package com.example.niuzhaojie.myapplication.activity;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.example.niuzhaojie.myapplication.R;
import com.example.niuzhaojie.myapplication.databinding.ActivityDatabindingBinding;
import com.example.niuzhaojie.myapplication.model.Employee;
import com.example.niuzhaojie.myapplication.utils.ToastUtils;

/**
 * Created by niuzhaojie on 16/11/4.
 */
public class DataBindingActivity extends Activity {

    Employee employee = new Employee("肇杰", "钮");

    private ActivityDatabindingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_databinding);


        binding.textview1.setText("you are sb!");

        binding.setEmployee(employee);
        binding.setPresenter(new Preenter());


    }

    public class Preenter {

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            employee.setFirstName(s.toString());
            binding.setEmployee(employee);
        }

        public void onClick(View view) {
            ToastUtils.showToast(DataBindingActivity.this, "kkk");
        }

        public void onClickListener(Employee employee) {
            ToastUtils.showToast(DataBindingActivity.this, employee.getLastName());
        }

    }

}
