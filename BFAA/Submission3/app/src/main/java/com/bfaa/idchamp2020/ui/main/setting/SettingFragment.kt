package com.bfaa.idchamp2020.ui.main.setting

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.bfaa.idchamp2020.R
import com.bfaa.idchamp2020.databinding.FragmentSettingBinding
import com.bfaa.idchamp2020.reminder.AlarmHelper.alarmOff
import com.bfaa.idchamp2020.reminder.AlarmHelper.alarmOn
import com.bfaa.idchamp2020.reminder.AlarmReceiver.Companion.ID_REPEATING
import com.bfaa.idchamp2020.ui.base.BaseFragment
import java.util.*
import javax.inject.Inject

class SettingFragment : BaseFragment<FragmentSettingBinding, SettingViewModel>() {
    companion object {
    const val SHARE_PREFERENCE_NAME = "share_preference_name"
    const val SHARE_PREFERENCE_KEY = "share_preference_key"
}

    @Inject
    lateinit var sharedPreferences: SharedPreferences
    override var getLayoutId: Int = R.layout.fragment_setting
    override var getViewModel: Class<SettingViewModel> = SettingViewModel::class.java
    override var title: MutableLiveData<String> = MutableLiveData("Setting")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBinding()
    }

    private fun initBinding() {
        mViewBinding.apply {
            ivBack.setOnClickListener {
                activity?.onBackPressed()
            }
            tvLanguage.setOnClickListener {
                val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
                startActivity(intent)
            }
            scReminder.apply {
                isChecked = sharedPreferences.getBoolean(SHARE_PREFERENCE_KEY, true)
                setOnCheckedChangeListener { _, isChecked ->
                    statusReminder(isChecked)
                }
            }
        }
    }

    private fun statusReminder(reminder:Boolean){
        sharedPreferences.edit().apply { putBoolean(SHARE_PREFERENCE_KEY, reminder)}.apply()
        context?.let {
            if (reminder) {
                alarmOn(it,
                    getString(R.string.app_name),
                    getString(R.string.message),
                    ID_REPEATING,
                    Calendar.getInstance().apply {
                        set(Calendar.HOUR_OF_DAY, 9)
                        set(Calendar.MINUTE, 0)
                        set(Calendar.SECOND, 0)
                    }
                )
            } else {
                alarmOff(it, ID_REPEATING)
            }
        }
    }
}