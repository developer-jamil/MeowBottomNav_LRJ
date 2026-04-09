package com.jamillabltd.meowbottomnav_lrj;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import kotlin.Unit;

public class MainActivity extends AppCompatActivity {

    private static final int ID_HOME    = 1;
    private static final int ID_EARN    = 2;
    private static final int ID_PROMOTE = 3;
    private static final int ID_WALLET  = 4;
    private static final int ID_PROFILE = 5;

    private TextView tvHome, tvEarn, tvPromote, tvWallet, tvProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvHome    = findViewById(R.id.tvHome);
        tvEarn    = findViewById(R.id.tvEarn);
        tvPromote = findViewById(R.id.tvPromote);
        tvWallet  = findViewById(R.id.tvWallet);
        tvProfile = findViewById(R.id.tvProfile);

        MeowBottomNavigation bottomNavigation = findViewById(R.id.bottomNavigation);

        // Add nav items (id, icon)
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_HOME,    R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_EARN,    R.drawable.ic_earn));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_PROMOTE, R.drawable.ic_promote));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_WALLET,  R.drawable.ic_wallet));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_PROFILE, R.drawable.ic_profile));

        // Switch fragment when a tab is shown (first load + re-select)
        bottomNavigation.setOnShowMenuListener(model -> {
            loadFragment(model.getId());
            highlightTitle(model.getId());
            return Unit.INSTANCE;
        });

        // Switch fragment on user click
        bottomNavigation.setOnClickMenuListener(model -> {
            loadFragment(model.getId());
            highlightTitle(model.getId());
            return Unit.INSTANCE;
        });

        // Show the Home tab by default
        if (savedInstanceState == null) {
            bottomNavigation.show(ID_HOME, true);
        }
    }

    private void loadFragment(int id) {
        Fragment fragment;
        switch (id) {
            case ID_EARN:    fragment = new EarnFragment();    break;
            case ID_PROMOTE: fragment = new PromoteFragment(); break;
            case ID_WALLET:  fragment = new WalletFragment();  break;
            case ID_PROFILE: fragment = new ProfileFragment(); break;
            default:         fragment = new HomeFragment();    break;
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, fragment);
        transaction.commitAllowingStateLoss();
    }

    private void highlightTitle(int id) {
        int defaultColor  = ContextCompat.getColor(this, R.color.nav_default_icon);
        int selectedColor = ContextCompat.getColor(this, R.color.nav_circle);

        tvHome.setTextColor(defaultColor);
        tvEarn.setTextColor(defaultColor);
        tvPromote.setTextColor(defaultColor);
        tvWallet.setTextColor(defaultColor);
        tvProfile.setTextColor(defaultColor);

        switch (id) {
            case ID_HOME:    tvHome.setTextColor(selectedColor);    break;
            case ID_EARN:    tvEarn.setTextColor(selectedColor);    break;
            case ID_PROMOTE: tvPromote.setTextColor(selectedColor); break;
            case ID_WALLET:  tvWallet.setTextColor(selectedColor);  break;
            case ID_PROFILE: tvProfile.setTextColor(selectedColor); break;
        }
    }
}
