package com.rajuuu.milkdiary.Adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.rajuuu.milkdiary.Model.PDFModel;
import com.rajuuu.milkdiary.R;

import java.util.List;

public class PdfCreateAdapter extends RecyclerView.Adapter<PdfCreateAdapter.MyViewHolder> {

    private List<PDFModel> pdfModels;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pdf_creation, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        PDFModel model = pdfModels.get(position);
        if (model != null) {
           /* if (model.isReceived()) {
                holder.mReceivedTV.setVisibility(View.VISIBLE);
            } else {
                holder.mReceivedTV.setVisibility(View.GONE);
            }*/


            holder.date.setText(model.getOrderdate());
            holder.line.setText(model.getOrderline());
            holder.obal.setText(model.getOpenbalance());
            holder.mBn.setText(model.getMproduct_name());
            holder.mBnTl.setText(model.getMtl());
            holder.mBnMl.setText(model.getMml());
            holder.mBnSl.setText(model.getMsl());
            holder.mBnRate.setText(model.getMrate());
            holder.mBnAmo.setText(model.getMamount());
            holder.mCn.setText(model.getMproduct_name1());
            holder.mCnTl.setText(model.getMtl1());
            holder.mCnMl.setText(model.getMml1());
            holder.mCnSl.setText(model.getMsl1());
            holder.mCnRate.setText(model.getMrate1());
            holder.mCnAmo.setText(model.getMamount1());
            holder.eBn.setText(model.getEproduct_name());
            holder.eBnTl.setText(model.getEtl());
            holder.eBnMl.setText(model.getEml());
            holder.eBnSl.setText(model.getEsl());
            holder.eBnRate.setText(model.getErate());
            holder.eBnAmo.setText(model.getEamount());
            holder.eCn.setText(model.getEproduct_name1());
            holder.eCnTl.setText(model.getEtl1());
            holder.eCnMl.setText(model.getEml1());
            holder.eCnSl.setText(model.getEsl1());
            holder.eCnRate.setText(model.getErate1());
            holder.eCnAmo.setText(model.getEamount1());
            holder.amtTot.setText(model.getAMTTOTAL());
            holder.mrngReci.setText(model.getMrngrept());
            holder.evngReci.setText(model.getEvngrept());
            holder.reciTot.setText(model.getTotal());
            holder.cbal.setText(model.getClosebalance());


        }
    }

    @Override
    public int getItemCount() {
        return pdfModels.size();
    }

    /**
     * This is set from PDFCreateByXML class
     * This is my own model. This model have to set data from api
     *
     * @param pdfModels
     */
    public void setListData(List<PDFModel> pdfModels) {
        this.pdfModels = pdfModels;
        notifyDataSetChanged();
    }

    /**
     * Set rating image
     *
     * @return
     */

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView date;
        private final TextView line;
        private final TextView obal;
        private final TextView mBn;
        private final TextView mBnTl;
        private final TextView mBnMl;
        private final TextView mBnSl;
        private final TextView mBnRate;
        private final TextView mBnAmo;
        private final TextView mCn;
        private final TextView mCnTl;
        private final TextView mCnMl;
        private final TextView mCnSl;
        private final TextView mCnRate;
        private final TextView mCnAmo;
        private final TextView eBn;
        private final TextView eBnTl;
        private final TextView eBnMl;
        private final TextView eBnSl;
        private final TextView eBnRate;
        private final TextView eBnAmo;
        private final TextView eCn;
        private final TextView eCnTl;
        private final TextView eCnMl;
        private final TextView eCnSl;
        private final TextView eCnRate;
        private final TextView eCnAmo;
        private final TextView amtTot;
        private final TextView mrngReci;
        private final TextView evngReci;
        private final TextView reciTot;
        private final TextView cbal;


        public MyViewHolder(View view) {
            super(view);
            date = (TextView) view.findViewById(R.id.date);
            line = (TextView) view.findViewById(R.id.line);
            obal = (TextView) view.findViewById(R.id.obal);
            mBn = (TextView) view.findViewById(R.id.mBn);
            mBnTl = (TextView) view.findViewById(R.id.mBnTl);
            mBnMl = (TextView) view.findViewById(R.id.mBnMl);
            mBnSl = (TextView) view.findViewById(R.id.mBnSl);
            mBnRate = (TextView) view.findViewById(R.id.mBnRate);
            mBnAmo = (TextView) view.findViewById(R.id.mBnAmo);
            mCnTl = (TextView) view.findViewById(R.id.mCnTl);
            mCnMl = (TextView) view.findViewById(R.id.mCnMl);
            mCn = (TextView) view.findViewById(R.id.mCn);
            mCnSl = (TextView) view.findViewById(R.id.mCnSl);
            mCnRate = (TextView) view.findViewById(R.id.mCnRate);
            mCnAmo = (TextView) view.findViewById(R.id.mCnAmo);
            eBnTl = (TextView) view.findViewById(R.id.eBnTl);
            eBnMl = (TextView) view.findViewById(R.id.eBnMl);
            eBn = (TextView) view.findViewById(R.id.eBn);
            eBnSl = (TextView) view.findViewById(R.id.eBnSl);
            eBnRate = (TextView) view.findViewById(R.id.eBnRate);
            eBnAmo = (TextView) view.findViewById(R.id.eBnAmo);
            eCnTl = (TextView) view.findViewById(R.id.eCnTl);
            eCn = (TextView) view.findViewById(R.id.eCn);
            eCnMl = (TextView) view.findViewById(R.id.eCnMl);
            eCnSl = (TextView) view.findViewById(R.id.eCnSl);
            eCnRate = (TextView) view.findViewById(R.id.eCnRate);
            eCnAmo = (TextView) view.findViewById(R.id.eCnAmo);
            amtTot = (TextView) view.findViewById(R.id.amtTot);
            mrngReci = (TextView) view.findViewById(R.id.mrngReci);
            evngReci = (TextView) view.findViewById(R.id.evngReci);
            reciTot = (TextView) view.findViewById(R.id.reciTot);
            cbal = (TextView) view.findViewById(R.id.cbal);

        }
    }

}