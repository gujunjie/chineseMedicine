package adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

public class MyFragmentPageAdapter extends FragmentPagerAdapter {

    private List<Fragment> list;

    private FragmentManager manager;




    public MyFragmentPageAdapter(FragmentManager fm,List<Fragment> list) {
        super(fm);
        this.manager=fm;
        this.list=list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // 将实例化的fragment进行显示即可。
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        manager.beginTransaction().show(fragment).commit();
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//      super.destroyItem(container, position, object);// 注释父类方法
        Fragment fragment = list.get(position);// 获取要销毁的fragment
        manager.beginTransaction().hide(fragment).commit();// 将其隐藏即可，并不需要真正销毁，这样fragment状态就得到了保存
    }
}
