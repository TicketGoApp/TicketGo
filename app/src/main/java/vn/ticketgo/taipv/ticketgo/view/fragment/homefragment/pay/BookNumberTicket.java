package vn.ticketgo.taipv.ticketgo.view.fragment.homefragment.pay;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.ticketgo.taipv.MyApplication;
import vn.ticketgo.taipv.sdk.callbacks.IPassPos;
import vn.ticketgo.taipv.ticketgo.adapter.BookNumberAdapter;
import vn.ticketgo.taipv.ticketgo.adapter.HomeAdapter;
import vn.ticketgo.taipv.ticketgo.model.GetEventHot;
import vn.ticketgo.taipv.ticketgo.presenter.HomePre.HomePresenter;
import vn.ticketgo.taipv.ticketgo.view.activity.home.PayEvent;
import vn.ticketgo.taipv.ticketgo.view.activity.inf.IHomeView;
import vn.ticketgo.taipv.ticketgo.view.fragment.BasicFragment;

import java.util.List;

/**
 * Author: Phùng Tài NeverGiveUp
 * Date: 1/15/2018
 * Email: tai97nd@gmail.com
 */

public class BookNumberTicket extends BasicFragment implements IHomeView,IPassPos {
    RecyclerView recyclerView;
    HomePresenter homePresenter;
    BookNumberAdapter adapter;
    HomeAdapter homeAdapter;
    int posi;

    public static BookNumberTicket newInstance(int position) {
        Bundle args = new Bundle();
        args.putInt("position",position);
        BookNumberTicket fragment = new BookNumberTicket();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            posi=getArguments().getInt("position");
            MyApplication.log("Posi",posi+"");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        ((PayEvent)getActivity()).getTotalMoney();
        return inflater.inflate(vn.ticketgo.taipv.ticketgo.R.layout.book_number_ticket,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        homePresenter=new HomePresenter(this);
//        ((PayEvent)getActivity()).setTitleActionBar("Đặt vé");
        initRecyclerView(view);
        homePresenter.getHome(1);

        super.onViewCreated(view, savedInstanceState);
    }


    private void initRecyclerView(View view) {
        recyclerView=view.findViewById(vn.ticketgo.taipv.ticketgo.R.id.rcv_book_ticket);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getFragment().getContext()));
    }

    @Override
    public void onGetSuscess(List<GetEventHot> list) {
        adapter=new BookNumberAdapter(getContext(),list.get(posi).getTickets_name(),posi);
//        MyApplication.log("so luong",list.get(0).getTickets_name().get(0).getPrice()+"price");
        recyclerView.setAdapter(adapter);
        adapter.setiPassPos(this);
//        closeProgressBar();
    }

    @Override
    public void onGetSuccessAll(List<GetEventHot> list) {
        adapter=new BookNumberAdapter(getContext(),list.get(posi).getTickets_name(),posi);
//        MyApplication.log("so luong",list.get(0).getTickets_name().get(0).getPrice()+"price");
        recyclerView.setAdapter(adapter);
        adapter.setiPassPos(this);
//        closeProgressBar();
    }

    @Override
    public void onLogoutSuccess(boolean isSuccess) {

    }

    @Override
    public void showProgressBar(int type) {

    }

    @Override
    public Fragment getFragment() {
        return this;
    }

    @Override
    public void passPos(int x) {
        ((PayEvent)getActivity()).getTotalMoney(x);
    }
}
