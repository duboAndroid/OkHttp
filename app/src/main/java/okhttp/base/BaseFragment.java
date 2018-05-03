package okhttp.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import okhttp.activity.OKHttpListActivity;

public abstract class BaseFragment extends Fragment {
    /**
     * 上下文
     */
    protected Context mContext;
    protected OKHttpListActivity activity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = initView();
        return view;
    }

    //得到view实例,用于fragment
    public <T extends View>T findUiViewToInstantiation(View mainView, int viewId){
        return (T)mainView.findViewById(viewId);
    }

    /**
     * 强制子类重写，实现子类特有的ui
     * @return
     */
    protected abstract View initView();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initData();
    }

    /**
     * 当孩子需要初始化数据，或者联网请求绑定数据，展示数据的 等等可以重写该方法
     */
    protected void initData() {

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        /*if((OKHttpListActivity)activity instanceof  Activity){
            this.activity = (OKHttpListActivity)activity;
        }else if((MyReadingActivity)activity instanceof  Activity){
            this.activity = (OKHttpListActivity) activity;
        }*/
        this.activity = (OKHttpListActivity)activity; //报MyReadingActivity cannot be to OKHttpListActivity
    }

    public Activity getMyActivity() {
        if (activity != null) return activity;
        throw new NullPointerException("u should init first");
    }


    @Override
    public void onStop() {
        super.onStop();
        //ButterKnife.unbind(activity);
    }
}
