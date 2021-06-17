package com.utopiaxc.chest.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import androidx.navigation.Navigation;

import com.danielstone.materialaboutlibrary.ConvenienceBuilder;
import com.danielstone.materialaboutlibrary.MaterialAboutFragment;
import com.danielstone.materialaboutlibrary.items.MaterialAboutActionItem;
import com.danielstone.materialaboutlibrary.items.MaterialAboutTitleItem;
import com.danielstone.materialaboutlibrary.model.MaterialAboutCard;
import com.danielstone.materialaboutlibrary.model.MaterialAboutList;
import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.utopiaxc.chest.R;
import com.utopiaxc.chest.activities.ActivityLicences;
import com.utopiaxc.chest.activities.SettingsActivity;
import com.utopiaxc.chest.databinding.FragmentAboutBinding;


public class FragmentAbout extends MaterialAboutFragment {
    FragmentAboutBinding binding;

    @Override
    protected MaterialAboutList getMaterialAboutList(final Context activityContext) {
        MaterialAboutCard.Builder appCardBuilder = new MaterialAboutCard.Builder();

        appCardBuilder.addItem(new MaterialAboutTitleItem.Builder()
                .text(R.string.app_name)
                .desc(R.string.rights)
                .icon(R.mipmap.ic_launcher)
                .build());

        appCardBuilder.addItem(ConvenienceBuilder.createVersionActionItem(activityContext,
                new IconicsDrawable(activityContext)
                        .icon(CommunityMaterial.Icon.cmd_code_array)
                        .sizeDp(18),
                requireActivity().getString(R.string.version),
                true));

        appCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text(R.string.changelog)
                .icon(new IconicsDrawable(activityContext)
                        .icon(CommunityMaterial.Icon.cmd_content_paste)
                        .sizeDp(18))
                .setOnClickAction(ConvenienceBuilder.createWebViewDialogOnClickAction(activityContext, requireActivity().getString(R.string.changelog_title), "https://github.com/UtopiaXC/ChestOfDrawers/releases", true, false))
                .build());

        appCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text(R.string.licenses)
                .icon(new IconicsDrawable(activityContext)
                        .icon(CommunityMaterial.Icon.cmd_book)
                        .sizeDp(18))
                .setOnClickAction(() ->{
                    Intent intent=new Intent(activityContext, ActivityLicences.class);
                    startActivity(intent);
                })
                .build());

        MaterialAboutCard.Builder authorCardBuilder = new MaterialAboutCard.Builder();
        authorCardBuilder.title(R.string.author);

        authorCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text(R.string.author_name)
                .icon(new IconicsDrawable(activityContext)
                        .icon(CommunityMaterial.Icon.cmd_account)
                        .sizeDp(18))
                .build());

        authorCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text(R.string.fork_on_github)
                .icon(new IconicsDrawable(activityContext)
                        .icon(CommunityMaterial.Icon.cmd_github_circle)
                        .sizeDp(18))
                .setOnClickAction(ConvenienceBuilder.createWebsiteOnClickAction(activityContext, Uri.parse("https://github.com/UtopiaXC/ChestOfDrawers")))
                .build());

        authorCardBuilder.addItem(ConvenienceBuilder.createEmailItem(activityContext,
                new IconicsDrawable(activityContext)
                        .icon(CommunityMaterial.Icon.cmd_email)
                        .sizeDp(18),
                requireActivity().getString(R.string.send_an_email),
                true,
                "utopiaxc@utopiaxc.com",
                "Feedback Of "+requireActivity().getString(R.string.app_name)));


        MaterialAboutCard.Builder convenienceCardBuilder = new MaterialAboutCard.Builder();
        convenienceCardBuilder.title(R.string.settings);
        convenienceCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text(R.string.settings)
                .icon(new IconicsDrawable(activityContext)
                        .icon(CommunityMaterial.Icon.cmd_cellphone_settings_variant)
                        .sizeDp(18))
                .setOnClickAction(() -> {
                    Intent intent = new Intent(requireActivity(), SettingsActivity.class);
                    startActivity(intent);
                })
                .build());


        return new MaterialAboutList(appCardBuilder.build(), authorCardBuilder.build(), convenienceCardBuilder.build());
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}