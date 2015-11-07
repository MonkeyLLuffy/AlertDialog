package com.example.monkeyllyffy.dialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{
    String[] provinces = {"长海","重庆","北京","南京","天津","四川"};
    private Button button;
    private EditText editText;
    private Button singleButton;
    private Button multiButton;
    private Button button_login;
    private Button button_file_delete;
    private int DIALOG_DELETE_FILE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        editText = (EditText) findViewById(R.id.edit_text);
        singleButton = (Button) findViewById(R.id.button_singlechioce);
        multiButton = (Button) findViewById(R.id.button_multychioce);
        button_login = (Button) findViewById(R.id.button_login);
        button_file_delete = (Button) findViewById(R.id.button_file_delete);
        button.setOnClickListener(this);
        singleButton.setOnClickListener(this);
        multiButton.setOnClickListener(this);
        button_login.setOnClickListener(this);
        button_file_delete.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    int index=0;
    ArrayList<Integer> lv = new ArrayList<Integer>();
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
            new AlertDialog.Builder(this).setTitle("选择省份").setIcon(R.drawable.shoucang).setItems(provinces, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    final AlertDialog ad = new AlertDialog.Builder(MainActivity.this).setMessage("你已经选择了" + provinces[which]).show();
                    editText.setText(provinces[which]);//设置输入框的值
                    Handler handler = new Handler();
                    //设置定时器，5秒调用run方法
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ad.dismiss();//关闭对话框
                        }
                    }, 5000);//第二个参数为时间长度
                }
            }).show();break;

            case R.id.button_singlechioce:
                new AlertDialog.Builder(this).setIcon(R.drawable.shoucang).setTitle("请选择省份：").setSingleChoiceItems(provinces, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        editText.setText(provinces[which]);
                        index = which;
                    }
                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final AlertDialog ad = new AlertDialog.Builder(MainActivity.this).setMessage("你已经选择了" + provinces[index]).show();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final AlertDialog ad = new AlertDialog.Builder(MainActivity.this).setMessage("您什么都还未选择").show();
                    }
                }).show();



                break;

            case R.id.button_multychioce:

                new AlertDialog.Builder(this).setIcon(R.drawable.shoucang).setTitle("请选择省份：").setMultiChoiceItems(provinces, new boolean[]{true, false, false, false, false, false}, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (isChecked) {
                            lv.add(which);//会导致重复添加，建议做一个遍历，然后看是否已经添加，或者通过同样的index来索引，false则值为空，true则是对应的值
                        }
                    }
                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    int count = lv.size();

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                            if (count == 0){
                                final AlertDialog ad = new AlertDialog.Builder(MainActivity.this).setMessage("您什么都还未选择").show();
                            }
                        else {
                                String string = "";
                                for (int i=0;i<lv.size();i++){
                                    string += provinces[lv.get(i)];
                                }
                                final AlertDialog ad = new AlertDialog.Builder(MainActivity.this).setMessage("您选择了"+string).show();
                            }



                         }


                }).setNegativeButton("取消", null).show();
        break;

            case R.id.button_login:
                LinearLayout layout = (LinearLayout) getLayoutInflater().inflate(R.layout.login,null);
                new AlertDialog.Builder(MainActivity.this).setTitle("登录").setView(layout).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                     //这里写登陆的代码
                    }
                }).setNegativeButton("取消",null).show();//用户取消登录

                break;
            case R.id.button_file_delete:
                Log.e("----------------------","文件删除？");//
                showDialog(DIALOG_DELETE_FILE);break;
                //调用OnDialogCreat 方法创建的对话框，实现对话框托管
    }
    }

}
