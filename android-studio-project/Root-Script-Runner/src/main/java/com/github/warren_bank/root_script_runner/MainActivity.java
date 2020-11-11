package com.github.warren_bank.root_script_runner;

import com.github.warren_bank.root_script_runner.helpers.Shell;
import com.github.warren_bank.root_script_runner.helpers.Shell.Result;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.io.File;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private static final String ACTION_WIDGET             = "com.github.warren_bank.root_script_runner.ACTION_WIDGET";
    private static final Pattern SHELL_SCRIPT_PATTERN     = Pattern.compile(".*\\.sh$", Pattern.CASE_INSENSITIVE);
    private static final int     FILE_PICKER_REQUEST_CODE = 1;

    private String currentPath;

    private TextView script;
    private TextView stdout;
    private TextView stderr;
    private TextView status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        if ((intent != null) && intent.getAction().equals(ACTION_WIDGET)) {
            Uri data = intent.getData();
            if (data != null) {
                try {
                    String path = data.getPath();
                    Shell.execScript(path);
                }
                catch(Exception e) {
                }
                finally {
                    finish();
                    return;
                }
            }
        }

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        toolbar.setNavigationIcon(null);

        currentPath = "/storage";

        script = (TextView) findViewById(R.id.script);
        stdout = (TextView) findViewById(R.id.stdout);
        stderr = (TextView) findViewById(R.id.stderr);
        status = (TextView) findViewById(R.id.status);

        openFilePicker();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch(menuItem.getItemId()) {
            case R.id.action_open_filepicker:
                openFilePicker();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    private void openFilePicker() {
        script.setText("");
        stdout.setText("");
        stderr.setText("");
        status.setText("");

        new MaterialFilePicker()
                .withActivity(MainActivity.this)
                .withRequestCode(FILE_PICKER_REQUEST_CODE)
                .withRootPath("/")
                .withPath(currentPath)
                .withHiddenFiles(true)
                .withFilter(SHELL_SCRIPT_PATTERN)
                .withFilterDirectories(false)
                .start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == FILE_PICKER_REQUEST_CODE && resultCode == RESULT_OK) {
            String path = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
            if (path == null) return;

            File file = new File(path);
            if (!file.exists()) return;

            currentPath = file.getParent();
            if (currentPath == null) currentPath = "/";

            script.setText(path);
            try {
                Result result = Shell.execScriptForResult(file);

                stdout.setText(result.stdout);
                stderr.setText(result.stderr);
                status.setText(Integer.toString(result.status));
            }
            catch(Exception e) {
                stderr.setText(e.getMessage());
            }
        }
    }
}
