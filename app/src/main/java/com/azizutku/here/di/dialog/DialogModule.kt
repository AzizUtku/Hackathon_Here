package com.azizutku.here.di.dialog

import android.content.Context
import com.azizutku.here.R
import com.azizutku.here.presentation.dialogs.AlertDialog
import com.azizutku.here.presentation.dialogs.LoadingDialog
import com.azizutku.here.presentation.dialogs.list.SimpleListDialog
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object DialogModule {

    @ActivityScoped
    @Provides
    fun provideAlertDialog(@ActivityContext activityContext: Context): AlertDialog {
        return AlertDialog(activityContext).setPositiveButton(activityContext.getString(R.string.text_button_okay))
    }

    @ActivityScoped
    @Provides
    fun provideLoadingDialog(@ActivityContext activityContext: Context): LoadingDialog {
        return LoadingDialog(activityContext)
    }

    @ActivityScoped
    @Provides
    fun provideSimpleListDialog(@ActivityContext activityContext: Context): SimpleListDialog<Int, String> {
        return SimpleListDialog(activityContext)
    }


}