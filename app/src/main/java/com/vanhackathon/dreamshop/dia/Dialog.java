package com.vanhackathon.dreamshop.dia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.vanhackathon.dreamshop.R;

import me.drakeet.materialdialog.MaterialDialog;

/**
 * Created by jrvansuita place 19/10/16.
 */

public class Dialog extends MaterialDialog {

    private Context context;

    private ListView lvOptions;
    private EditText edDescription;
    private TextView tvTitle;
    private TextView tvMessage;

    public Dialog(Context context) {
        super(context);
        this.context = context;
        setCanceledOnTouchOutside(true);
    }

    public Dialog setOptions(String... options) {
        final ArrayAdapter<String> arrayAdapter
                = new ArrayAdapter(context, android.R.layout.simple_list_item_1);

        for (int i = 0; i < options.length; i++) {
            arrayAdapter.add(options[i]);
        }

        lvOptions = (ListView) LayoutInflater.from(context).inflate(R.layout.options, null, false);

        lvOptions.setAdapter(arrayAdapter);
        setContentView(lvOptions);

        return this;
    }

    public Dialog setOptions(BaseAdapter adapter) {
        lvOptions = (ListView) LayoutInflater.from(context).inflate(R.layout.options, null, false);

        lvOptions.setAdapter(adapter);
        setContentView(lvOptions);

        return this;
    }

    public Dialog setOnItemClickListener(final AdapterView.OnItemClickListener listener) {
        if (lvOptions != null)
            lvOptions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    dismiss();

                    if (listener != null)
                        listener.onItemClick(adapterView, view, i, l);
                }
            });

        return this;
    }


    public Dialog setEdit(String hint, String text) {
        View v = LayoutInflater.from(context).inflate(R.layout.material_edit_dialog, null, false);

        tvTitle = (TextView) v.findViewById(R.id.title);
        tvMessage = (TextView) v.findViewById(R.id.message);

        tvTitle.setText(titleStr);
        tvMessage.setText(messageStr);
        edDescription = (EditText) v.findViewById(R.id.edit);
        edDescription.setText(text);
        edDescription.setHint(hint);

        setView(v);

        return this;
    }


    private String titleStr;

    @Override
    public Dialog setTitle(int resId) {
        return setTitle(context.getString(resId));
    }


    @Override
    public Dialog setTitle(CharSequence s) {
        titleStr = s.toString();

        if (tvTitle != null) {
            tvTitle.setText(titleStr);
        }

        super.setTitle(s);

        return this;
    }

    private String messageStr;


    @Override
    public Dialog setMessage(int resId) {
        return setMessage(context.getString(resId));
    }

    @Override
    public Dialog setMessage(CharSequence s) {
        messageStr = s.toString();

        if (tvMessage != null) {
            tvMessage.setText(messageStr);
        }

        super.setMessage(s);

        return this;
    }

    @Override
    public Dialog setPositiveButton(int resId, final View.OnClickListener listener) {
        return setPositiveButton(context.getString(resId), listener);
    }


    public Dialog setPositiveButton(int res) {
        return setPositiveButton(res, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }


    @Override
    public Dialog setPositiveButton(String text, final View.OnClickListener listener) {
        super.setPositiveButton(text, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null)
                    listener.onClick(view);

                dismiss();
            }
        });

        return this;
    }



    @Override
    public Dialog setNegativeButton(int resId, final View.OnClickListener listener) {
        return setNegativeButton(context.getString(resId), listener);
    }

    public Dialog setNegativeButton(int res) {
        return setNegativeButton(res, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }


    @Override
    public Dialog setNegativeButton(String text, final View.OnClickListener listener) {
        super.setNegativeButton(text, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null)
                    listener.onClick(view);

                dismiss();
            }
        });

        return this;
    }

    public EditText getEditText() {
        return edDescription;
    }


    @Override
    public void show() {
        super.show();
    }
}
